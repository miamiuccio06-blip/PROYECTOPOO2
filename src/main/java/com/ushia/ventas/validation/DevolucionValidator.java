
package com.ushia.ventas.validation;

import com.ushia.ventas.model.Devolucion;

public class DevolucionValidator {

    public void validar(Devolucion devolucion) {

        if (devolucion == null) {
            throw new IllegalArgumentException( //error
                    "La devolucion no puede ser nula."
            );
        }

        if (devolucion.getFecha() == null) {
            throw new IllegalArgumentException(
                    "La fecha de la devolucion es obligatoria."
            );
        }

        if (devolucion.getMotivo() == null ||
                devolucion.getMotivo().isBlank()) {
            throw new IllegalArgumentException(
                    "El motivo de la devolucion es obligatorio."
            );
        }

        if (devolucion.getEstado() == null ||
                devolucion.getEstado().isBlank()) {
            throw new IllegalArgumentException(
                    "El estado de la devolucion es obligatorio."
            );
        }

        if (devolucion.getMontoTotal() == null ||
                devolucion.getMontoTotal().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "El monto total de la devolucion debe ser mayor a cero."
            );
        }

        if (devolucion.getDetalles() == null ||
                devolucion.getDetalles().isEmpty()) {
            throw new IllegalArgumentException(
                    "La devolucion debe tener al menos un detalle."
            );
        }
    }
}
