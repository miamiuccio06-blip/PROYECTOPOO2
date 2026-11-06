package com.ushia.productos.validation;

public class CodigoProductoValidator {

    public void validar(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                    "El código del producto es obligatorio."
            );
        }

        if (!codigo.matches("^[A-Za-z0-9-]+$")) {
            throw new IllegalArgumentException(
                    "El código solo puede contener letras, números y guiones."
            );
        }

        if (codigo.length() < 3 || codigo.length() > 20) {
            throw new IllegalArgumentException(
                    "El código debe tener entre 3 y 20 caracteres."
            );
        }
    }
}
