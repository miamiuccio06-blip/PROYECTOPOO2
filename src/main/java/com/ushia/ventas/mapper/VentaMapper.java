package com.ushia.ventas.mapper;

import com.ushia.ventas.model.Venta;
import com.ushia.ventas.dto.VentaDTO;

public class VentaMapper { //convierte ventas en ventas dto

    public VentaDTO toDTO(Venta venta) { //recibe un objeto venta como parametro

        VentaDTO dto = new VentaDTO(); //crea un objeto para copiar la info de la venta

        dto.setId(venta.getId());
        dto.setCodigo(venta.getCodigo());
        dto.setFechaHora(venta.getFechaHora());
        dto.setSubtotal(venta.getSubtotal());
        dto.setDescuentoTotal(venta.getDescuentoTotal());
        dto.setTotal(venta.getTotal());
        dto.setEstado(venta.getEstado());

        //se obtiene el id del cliente para no pasar toda la entidad al dto
        if (venta.getCliente() != null) {
            dto.setClienteId(venta.getCliente().getId());
        }

        return dto; //devuelve el objeto dto
    }

    public Venta toEntity(VentaDTO dto) { //cambia los ventas dto a ventas

        Venta venta = new Venta();

        venta.setId(dto.getId());
        venta.setCodigo(dto.getCodigo());
        venta.setFechaHora(dto.getFechaHora());
        venta.setSubtotal(dto.getSubtotal());
        venta.setDescuentoTotal(dto.getDescuentoTotal());
        venta.setTotal(dto.getTotal());
        venta.setEstado(dto.getEstado());

        return venta;
    }
}