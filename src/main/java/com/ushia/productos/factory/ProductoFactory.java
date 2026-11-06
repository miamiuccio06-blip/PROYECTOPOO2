package com.ushia.productos.factory;

import com.ushia.productos.model.Producto;
import com.ushia.productos.model.CategoriaProducto;
import com.ushia.productos.model.Temporada;
import com.ushia.productos.model.Coleccion;
import com.ushia.productos.model.EstadoProducto;

public class ProductoFactory {

    public Producto crearProducto(String codigo, String nombre,
                                  String descripcion, double precioVenta,
                                  double precioCosto, CategoriaProducto categoria,
                                  Temporada temporada, Coleccion coleccion,
                                  EstadoProducto estado) {

        return new Producto(
                null,
                codigo,
                nombre,
                descripcion,
                precioVenta,
                precioCosto,
                categoria,
                temporada,
                coleccion,
                estado
        );
    }
}