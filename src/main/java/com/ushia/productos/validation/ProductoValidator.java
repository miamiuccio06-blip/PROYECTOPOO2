package com.ushia.productos.validation;

import com.ushia.productos.model.Producto;

public class ProductoValidator {

    public void validar(Producto producto) {

        if (producto == null) {
            throw new IllegalArgumentException( //error
                    "El producto no puede ser nulo."
            );
        }

        if (producto.getNombre() == null ||
                producto.getNombre().isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del producto es obligatorio."
            );
        }

        if (producto.getPrecioVenta() <= 0) {
            throw new IllegalArgumentException(
                    "El precio de venta debe ser mayor a cero."
            );
        }

        if (producto.getPrecioCosto() <= 0) {
            throw new IllegalArgumentException(
                    "El precio de costo debe ser mayor a cero."
            );
        }
    }
}
