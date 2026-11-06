package com.ushia.ventas.strategy;

import java.math.BigDecimal;

public class DescuentoMontoFijoStrategy implements EstrategiaDescuento { //aplica un descuento de cantidad fija

    private final BigDecimal montoDescuento; //almacena el monto fijo que se descontará

    public DescuentoMontoFijoStrategy(BigDecimal montoDescuento) {

        if (montoDescuento == null || montoDescuento.signum() < 0) { //comprueba que el descuento no sea negativo
            throw new IllegalArgumentException("El descuento no puede ser nulo ni negativo.");
        }

        this.montoDescuento = montoDescuento;
    }

    @Override
    public BigDecimal calcularDescuento(BigDecimal subtotal) {

        if (subtotal == null || subtotal.signum() < 0) { //comprueba que el subtotal sea válido
            throw new IllegalArgumentException("El subtotal no puede ser nulo ni negativo.");
        }

        return montoDescuento.min(subtotal); //evita que el descuento supere el subtotal de la venta
    }
}
