package com.martinf.conversor.modelos;

public record MonedaEscogida(String base_code,
                            String target_code,
                            double conversion_rate) {

}
