import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaTasa {

    //Mi clave generada por la API de conversion
    private static final String claveApi = "0227dc08227b0a97a9346152";

    public ResultadoConversion consultarPar(String monedaOrigen, String monedaDestino, double monto) {

        String url = String.format(
                "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s",
                claveApi, monedaOrigen, monedaDestino
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() != 200)
                throw new RuntimeException("Error API: código " + res.statusCode());

            JsonObject json = JsonParser.parseString(res.body()).getAsJsonObject();

            if (json.has("result") && json.get("result").getAsString().equals("error")) {
                throw new RuntimeException("API: " + json.get("error-type").getAsString());
            }

            double tasa = json.get("conversion_rate").getAsDouble();
            double resultado = tasa * monto;

            //Objeto como resultado de la conversion
            return new ResultadoConversion(
                    monedaOrigen,
                    monedaDestino,
                    tasa,
                    resultado
            );

        } catch (Exception e) {
            throw new RuntimeException("No se pudo consultar la API: " + e.getMessage());
        }
    }
}
