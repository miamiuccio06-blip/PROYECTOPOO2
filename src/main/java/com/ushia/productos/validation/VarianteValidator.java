package com.ushia.productos.validation;

import com.ushia.productos.model.VarianteProducto;

public class VarianteValidator {

    public void validar(VarianteProducto variante) {

        if (variante == null) {
            throw new IllegalArgumentException(
                    "La variante no puede ser nula."
            );
        }

        if (variante.getProducto() == null) {
            throw new IllegalArgumentException(
                    "La variante debe estar asociada a un producto."
            );
        }

        if (variante.getTalla() == null) {
            throw new IllegalArgumentException(
                    "La talla es obligatoria."
            );
        }

        if (variante.getColor() == null) {
            throw new IllegalArgumentException(
                    "El color es obligatorio."
            );
        }
    }
}
