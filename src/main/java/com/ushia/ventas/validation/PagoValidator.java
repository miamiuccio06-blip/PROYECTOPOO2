
package com.ushia.ventas.validation;

import com.ushia.ventas.model.Pago;

public class PagoValidator {

    public void validar(Pago pago) {

        if (pago == null) {
            throw new IllegalArgumentException( //error
                    "El pago no puede ser nulo."
            );
        }

        if (pago.getMonto() == null ||
                pago.getMonto().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "El monto del pago debe ser mayor a cero."
            );
        }

        if (pago.getMetodoPago() == null) {
            throw new IllegalArgumentException(
                    "El metodo de pago es obligatorio."
            );
        }

        if (pago.getEstadoPago() == null) {
            throw new IllegalArgumentException(
                    "El estado del pago es obligatorio."
            );
        }

        if (pago.getFechaPago() == null) {
            throw new IllegalArgumentException(
                    "La fecha del pago es obligatoria."
            );
        }
    }
}
