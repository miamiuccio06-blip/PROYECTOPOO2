package com.ushia.ventas.validation;

import com.ushia.ventas.model.Venta;

public class VentaValidator {

    public void validar(Venta venta) {

        if (venta == null) {
            throw new IllegalArgumentException( //error
                    "La venta no puede ser nula."
            );
        }

        if (venta.getCodigo() == null ||
                venta.getCodigo().isBlank()) {
            throw new IllegalArgumentException(
                    "El codigo de la venta es obligatorio."
            );
        }

        if (venta.getSubtotal() == null ||
                venta.getSubtotal().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "El subtotal no puede ser nulo ni negativo."
            );
        }

        if (venta.getDescuentoTotal() == null ||
                venta.getDescuentoTotal().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "El descuento total no puede ser nulo ni negativo."
            );
        }

        if (venta.getTotal() == null ||
                venta.getTotal().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "El total no puede ser nulo ni negativo."
            );
        }

        if (venta.getFechaHora() == null) {
            throw new IllegalArgumentException(
                    "La fecha y hora de la venta son obligatorias."
            );
        }
    }
}
