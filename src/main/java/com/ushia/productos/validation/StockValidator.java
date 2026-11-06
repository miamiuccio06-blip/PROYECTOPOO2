package com.ushia.productos.validation;

public class StockValidator {

    public void validarCantidad(int cantidad) {

        if (cantidad < 0) {
            throw new IllegalArgumentException(
                    "La cantidad de stock no puede ser negativa."
            );
        }
    }

    public void validarDisponibilidad(int stockActual, int cantidadSolicitada) {

        if (stockActual < 0 || cantidadSolicitada <= 0) {
            throw new IllegalArgumentException(
                    "Las cantidades ingresadas no son válidas."
            );
        }

        if (cantidadSolicitada > stockActual) {
            throw new IllegalArgumentException(
                    "No hay stock suficiente."
            );
        }
    }
}
