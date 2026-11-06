package com.ushia.ventas.service;

import com.ushia.ventas.model.DetalleVenta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CalculoTotalService {

    // Calcula el subtotal sumando los importes de cada detalle.
    public BigDecimal calcularSubtotal(List<DetalleVenta> detalles) {

        BigDecimal subtotal = BigDecimal.ZERO;

        for (DetalleVenta detalle : detalles) {

            BigDecimal importe = detalle.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(detalle.getCantidad()));

            subtotal = subtotal.add(importe);
        }

        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    // Calcula el total descontando el importe correspondiente.
    public BigDecimal calcularTotal(
            BigDecimal subtotal,
            BigDecimal descuentoTotal) {

        if (subtotal == null) {
            throw new IllegalArgumentException("El subtotal no puede ser nulo.");
        }

        if (descuentoTotal == null) {
            descuentoTotal = BigDecimal.ZERO;
        }

        if (descuentoTotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El descuento no puede ser negativo.");
        }

        if (descuentoTotal.compareTo(subtotal) > 0) {
            throw new IllegalArgumentException("El descuento supera el subtotal.");
        }

        return subtotal.subtract(descuentoTotal)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
