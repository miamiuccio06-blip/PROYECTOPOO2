package com.ushia.productos.mapper;

import com.ushia.productos.model.Inventario;
import com.ushia.productos.dto.InventarioDTO;

public class InventarioMapper {

    public InventarioDTO toDTO(Inventario inventario) {

        InventarioDTO dto = new InventarioDTO();

        dto.setId(inventario.getId());
        dto.setStockActual(inventario.getStockActual());
        dto.setStockMinimo(inventario.getStockMinimo());

        return dto;
    }

    public Inventario toEntity(InventarioDTO dto) {

        Inventario inventario = new Inventario();

        inventario.setId(dto.getId());
        inventario.setStockActual(dto.getStockActual());
        inventario.setStockMinimo(dto.getStockMinimo());

        return inventario;
    }
}
