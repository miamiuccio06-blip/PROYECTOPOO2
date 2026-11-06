package com.ushia.clientes.validation;

import com.ushia.clientes.model.Cliente;

public class ClienteValidator {

    public void validar(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "El cliente no puede ser nulo."
            );
        }

        if (cliente.getNombres() == null ||
                cliente.getNombres().isBlank()) {
            throw new IllegalArgumentException(
                    "Los nombres del cliente son obligatorios."
            );
        }

        if (cliente.getNombres().length() > 100) {
            throw new IllegalArgumentException(
                    "Los nombres no pueden superar los 100 caracteres."
            );
        }

        if (cliente.getApellidos() == null ||
                cliente.getApellidos().isBlank()) {
            throw new IllegalArgumentException(
                    "Los apellidos del cliente son obligatorios."
            );
        }

        if (cliente.getApellidos().length() > 100) {
            throw new IllegalArgumentException(
                    "Los apellidos no pueden superar los 100 caracteres."
            );
        }

        if (cliente.getDni() == null ||
                !cliente.getDni().matches("^\\d{8}$")) {
            throw new IllegalArgumentException(
                    "El DNI debe contener exactamente 8 dígitos."
            );
        }

        if (cliente.getCorreo() == null ||
                cliente.getCorreo().isBlank()) {
            throw new IllegalArgumentException(
                    "El correo electrónico es obligatorio."
            );
        }

        if (!cliente.getCorreo().matches(
                "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException(
                    "El correo electrónico no tiene un formato válido."
            );
        }

        if (cliente.getTelefono() != null &&
                cliente.getTelefono().length() > 20) {
            throw new IllegalArgumentException(
                    "El teléfono no puede superar los 20 caracteres."
            );
        }
    }
}
