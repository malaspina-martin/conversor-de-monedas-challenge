package com.martinf.conversor.principal;

import com.martinf.conversor.conexion.ConsultaAPI;
import com.martinf.conversor.modelos.Moneda;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        List<String> historial = new ArrayList<>();
        ConsultaAPI consulta = new ConsultaAPI();
        int opcion = 0;

        String menu = """
                ********************************************
                Sea bienvenido/a al Conversor de Moneda =]
                
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileño
                4) Real brasileño =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Salir
                8) Ver historial de consultas
                Elija una opción válida:
                ********************************************
                """;

        while (opcion != 7) {
            System.out.println(menu);
            try {
                opcion = Integer.parseInt(lectura.nextLine());

                if (opcion == 7) break;

                // Caso especial: Ver Historial (No pide monto)
                if (opcion == 8) {
                    System.out.println("\n======= HISTORIAL DE CONVERSIONES =======");
                    if (historial.isEmpty()) {
                        System.out.println("Aún no has realizado ninguna conversión.");
                    } else {
                        historial.forEach(System.out::println);
                    }
                    System.out.println("==========================================\n");
                    continue; // Volvemos al menú sin pedir cantidad
                }

                // Validación de opciones de conversión (1 a 6)
                if (opcion < 1 || opcion > 6) {
                    System.out.println("Opción inválida. Intente nuevamente.");
                    continue;
                }

                // Si llegó aquí, es porque eligió 1-6. AHORA pedimos el monto:
                System.out.println("Ingrese el valor que desea convertir:");
                double cantidad = Double.parseDouble(lectura.nextLine());

                String resultado = null;
                switch (opcion) {
                    case 1 -> resultado = realizarConversion(consulta, "USD", "ARS", cantidad);
                    case 2 -> resultado = realizarConversion(consulta, "ARS", "USD", cantidad);
                    case 3 -> resultado = realizarConversion(consulta, "USD", "BRL", cantidad);
                    case 4 -> resultado = realizarConversion(consulta, "BRL", "USD", cantidad);
                    case 5 -> resultado = realizarConversion(consulta, "USD", "COP", cantidad);
                    case 6 -> resultado = realizarConversion(consulta, "COP", "USD", cantidad);
                }

                if (resultado != null) {
                    historial.add(resultado);
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
        System.out.println("Finalizando programa. ¡Gracias por usar el conversor!");
    }

    // Metodo auxiliar para no repetir código
    private static String realizarConversion(ConsultaAPI consulta, String base, String destino, double cantidad) {
        try {
            Moneda moneda = consulta.buscarTasa(base, destino);
            double resultado = cantidad * moneda.conversion_rate();
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            String registro = String.format("[%s] %.2f %s >>> %.2f %s",
                    fecha, cantidad, base, resultado, destino);

            System.out.println("--------------------------------------------------");
            System.out.println("Resultado: " + registro);
            System.out.println("--------------------------------------------------");

            return registro; // Devolvemos el texto para el historial
        } catch (Exception e) {
            System.out.println("Error al obtener los datos: " + e.getMessage());
            return null;
        }
    }
}
