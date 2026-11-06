package com.ushia.productos.mapper;

import com.ushia.productos.model.Producto; //improta producto
import com.ushia.productos.dto.ProductoDTO; //importa prodcutodto

public class ProductoMapper {  //convierte productos en productos dto

    public ProductoDTO toDTO(Producto producto) { //recibe un obheto producto como parametro

        ProductoDTO dto = new ProductoDTO(); //crea un objeto para copiar la info del producto

        dto.setId(producto.getId());
        dto.setCodigo(producto.getCodigo());
        dto.setNombre(producto.getNombre()); //obtiene ekl nombre del producto y lo ppne en el del producto dto
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecioVenta(producto.getPrecioVenta());
        dto.setPrecioCosto(producto.getPrecioCosto());

        return dto; //devuelve el objeto dto
    }

    public Producto toEntity(ProductoDTO dto) { //cambia los productos dto a productos

        Producto producto = new Producto();

        producto.setId(dto.getId());
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setPrecioCosto(dto.getPrecioCosto());

        return producto;
    }
}
