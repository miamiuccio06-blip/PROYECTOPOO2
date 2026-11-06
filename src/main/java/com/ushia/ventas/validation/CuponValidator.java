
package com.ushia.ventas.validation;

import com.ushia.ventas.model.Cupon;
import java.time.LocalDate;

public class CuponValidator {

    public void validar(Cupon cupon) {

        if (cupon == null) {
            throw new IllegalArgumentException( //error
                    "El cupon no puede ser nulo."
            );
        }

        if (cupon.getCodigo() == null ||
                cupon.getCodigo().isBlank()) {
            throw new IllegalArgumentException(
                    "El codigo del cupon es obligatorio."
            );
        }

        if (cupon.getFechaExpiracion() == null) {
            throw new IllegalArgumentException(
                    "La fecha de expiracion del cupon es obligatoria."
            );
        }

        if (cupon.getUsosMaximos() <= 0) {
            throw new IllegalArgumentException(
                    "Los usos maximos del cupon deben ser mayores a cero."
            );
        }

        if (cupon.getUsosActuales() < 0) {
            throw new IllegalArgumentException(
                    "Los usos actuales del cupon no pueden ser negativos."
            );
        }

        if (cupon.getUsosActuales() > cupon.getUsosMaximos()) {
            throw new IllegalArgumentException(
                    "Los usos actuales no pueden superar los usos maximos."
            );
        }

        if (cupon.getDescuento() == null) {
            throw new IllegalArgumentException(
                    "El cupon debe tener un descuento asociado."
            );
        }
    }
}
