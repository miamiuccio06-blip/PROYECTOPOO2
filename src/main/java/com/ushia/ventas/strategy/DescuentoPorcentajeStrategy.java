package com.ushia.ventas.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DescuentoPorcentajeStrategy implements EstrategiaDescuento { //aplica descuentos mediante un porcentaje

    private final BigDecimal porcentaje; //almacena el porcentaje de descuento

    public DescuentoPorcentajeStrategy(BigDecimal porcentaje) {
        if (porcentaje == null || porcentaje.compareTo(BigDecimal.ZERO) < 0
                || porcentaje.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100.");
        }

        this.porcentaje = porcentaje;
    }

    @Override
    public BigDecimal calcularDescuento(BigDecimal subtotal) {

        if (subtotal == null || subtotal.signum() < 0) { //comprueba que el subtotal sea válido
            throw new IllegalArgumentException("El subtotal no puede ser nulo ni negativo.");
        }

        return subtotal.multiply(porcentaje) //multiplica el subtotal por el porcentaje
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP); //divide entre 100 y redondea a dos decimales
    }
}
