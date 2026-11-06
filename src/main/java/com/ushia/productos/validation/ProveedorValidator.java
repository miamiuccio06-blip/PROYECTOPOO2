package com.ushia.productos.validation;

import com.ushia.productos.model.Proveedor;

public class ProveedorValidator {

    public void validar(Proveedor proveedor) {

        if (proveedor == null) {
            throw new IllegalArgumentException(
                    "El proveedor no puede ser nulo."
            );
        }

        if (proveedor.getNombre() == null ||
                proveedor.getNombre().isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del proveedor es obligatorio."
            );
        }

        if (proveedor.getNombre().length() > 100) {
            throw new IllegalArgumentException(
                    "El nombre no puede superar los 100 caracteres."
            );
        }

        if (proveedor.getTelefono() != null &&
                proveedor.getTelefono().length() > 20) {
            throw new IllegalArgumentException(
                    "El teléfono no puede superar los 20 caracteres."
            );
        }

        if (proveedor.getCorreo() != null &&
                !proveedor.getCorreo().isBlank() &&
                !proveedor.getCorreo().matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException(
                    "El correo electrónico no tiene un formato válido."
            );
        }

        if (proveedor.getDireccion() != null &&
                proveedor.getDireccion().length() > 200) {
            throw new IllegalArgumentException(
                    "La dirección no puede superar los 200 caracteres."
            );
        }
    }
}
