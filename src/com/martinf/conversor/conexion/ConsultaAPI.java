package com.martinf.conversor.conexion;

import com.google.gson.Gson;
import com.martinf.conversor.modelos.Moneda;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaAPI {
    public Moneda buscarTasa(String monedaBase, String monedaDestino) {
        // Reemplaza 'TU-API-KEY' con tu clave real
        String apiKey = "TU-API-KEY";
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + monedaBase + "/" + monedaDestino);

        HttpClient client = HttpClient.newHttpClient(); // Paso 4: Cliente
        HttpRequest request = HttpRequest.newBuilder()   // Paso 5: Solicitud
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client       // Paso 6: Respuesta
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Paso 7: Analizar JSON con Gson y convertirlo a nuestro Record 'Moneda'
            return new Gson().fromJson(response.body(), Moneda.class);

        } catch (Exception e) {
            throw new RuntimeException("Error: No se pudo obtener la cotización.");
        }
    }
}
