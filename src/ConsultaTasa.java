import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaTasa {

    private static final String API_KEY = "0227dc08227b0a97a9346152";

    public ResultadoConversion consultarPar(String base, String target, double amount) {

        String url = String.format(
                "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s",
                API_KEY, base, target
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

            double rate = json.get("conversion_rate").getAsDouble();
            double resultado = rate * amount;

            return new ResultadoConversion(
                    base,
                    target,
                    rate,
                    resultado
            );

        } catch (Exception e) {
            throw new RuntimeException("No se pudo consultar la API: " + e.getMessage());
        }
    }
}
