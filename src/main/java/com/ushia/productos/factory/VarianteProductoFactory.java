package com.ushia.productos.factory;

import com.ushia.productos.model.Producto;
import com.ushia.productos.model.VarianteProducto;
import com.ushia.productos.model.Talla;
import com.ushia.productos.model.Color;

public class VarianteProductoFactory {

    public VarianteProducto crearVariante(
            Producto producto,
            Talla talla,
            Color color,
            String codigoSKU) {

        return new VarianteProducto(
                null,
                producto,
                talla,
                color,
                codigoSKU
        );
    }
}
