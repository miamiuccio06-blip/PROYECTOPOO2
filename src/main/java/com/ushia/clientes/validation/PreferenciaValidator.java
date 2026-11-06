package com.ushia.clientes.validation;

import com.ushia.clientes.model.PreferenciaCliente;
import com.ushia.clientes.model.PreferenciaColor;
import com.ushia.clientes.model.PreferenciaTalla;
import com.ushia.clientes.model.PreferenciaEstilo;

public class PreferenciaValidator {

    public void validar(PreferenciaCliente preferencia) {

        if (preferencia == null) {
            throw new IllegalArgumentException(
                    "La preferencia del cliente no puede ser nula."
            );
        }

        if (preferencia.getCliente() == null) {
            throw new IllegalArgumentException(
                    "La preferencia debe estar asociada a un cliente."
            );
        }

        if (preferencia.getColores() != null) {
            for (PreferenciaColor color : preferencia.getColores()) {

                if (color == null || color.getColor() == null) {
                    throw new IllegalArgumentException(
                            "Cada preferencia de color debe tener un color válido."
                    );
                }
            }
        }

        if (preferencia.getTallas() != null) {
            for (PreferenciaTalla talla : preferencia.getTallas()) {

                if (talla == null || talla.getTalla() == null) {
                    throw new IllegalArgumentException(
                            "Cada preferencia de talla debe tener una talla válida."
                    );
                }
            }
        }

        if (preferencia.getEstilos() != null) {
            for (PreferenciaEstilo estilo : preferencia.getEstilos()) {

                if (estilo == null ||
                        estilo.getEstilo() == null ||
                        estilo.getEstilo().isBlank()) {
                    throw new IllegalArgumentException(
                            "Cada preferencia de estilo debe tener un nombre válido."
                    );
                }

                if (estilo.getEstilo().length() > 100) {
                    throw new IllegalArgumentException(
                            "El estilo no puede superar los 100 caracteres."
                    );
                }
            }
        }
    }
}
