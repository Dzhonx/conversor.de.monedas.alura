import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaTasa {

    //Mi clave generada por la API de conversion
    private static final String claveApi = "0227dc08227b0a97a9346152";

    //Creamos el metodo consultarPar para convertir las monedas
    public ResultadoConversion consultarPar(String monedaOrigen, String monedaDestino, double monto) {

        //Construimos la url para consultar a la API, usamos el metodo format que evita errores y mantiene mas ordenado la url
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s", claveApi, monedaOrigen, monedaDestino);

        //Preparamos y enviamos la peticion web que nos permitira obtener la tasa de conversion
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            //client.send() -> envia la peticion
            //BodyHandlers.ofString() -> solicita la respuesta en string
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

            //200 = OK -> pero si lanza otro codigo significa que algo salio mal
            if (res.statusCode() != 200)
                throw new RuntimeException("Error API: código " + res.statusCode());

            //res.body() -> trae el contenido en string
            //JsonParser.parseString() -> convierte la respuesta en json , el cual ya podemos leer
            JsonObject json = JsonParser.parseString(res.body()).getAsJsonObject();

            //Algunas APIs  responden con error aunque el codigo sea 200, este if detecta este tipo de errores
            if (json.has("result") && json.get("result").getAsString().equals("error")) {
                throw new RuntimeException("API: " + json.get("error-type").getAsString());
            }

            //Obteniendo tasa de conversion, se extrae del json
            double tasa = json.get("conversion_rate").getAsDouble();
            //Calculo del monto convertido
            double resultado = tasa * monto;

            //Objeto como resultado de la conversion, el cual se envia a la clase Principal para su impresion
            return new ResultadoConversion(
                    monedaOrigen,
                    monedaDestino,
                    tasa,
                    resultado
            );

            //Si algo falla en el try , se envia al catch
        } catch (Exception e) {
            throw new RuntimeException("No se pudo consultar la API: " + e.getMessage());
        }
    }
}
