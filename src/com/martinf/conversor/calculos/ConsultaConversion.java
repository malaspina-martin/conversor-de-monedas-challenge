package com.martinf.conversor.calculos;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.martinf.conversor.modelos.MonedaEscogida;

public class ConsultaConversion {
    public MonedaEscogida buscaMoneda(String base, String target) {
        // 1. Construir la dirección (URL) usando el "Pair Conversion" que recomendó Trello
        String direccion = "https://v6.exchangerate-api.com/v6/TU-API-KEY/pair/" + base + "/" + target;

        // 2. Pasos 4 y 5: HttpClient y HttpRequest
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        try {
            // 3. Paso 6: HttpResponse
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // 4. Paso 7: Analizar con GSON (Aquí es donde ocurre la magia)
            return new Gson().fromJson(response.body(), MonedaEscogida.class);

        } catch (Exception e) {
            throw new RuntimeException("No pude realizar la conversión.");
        }
    }
}
