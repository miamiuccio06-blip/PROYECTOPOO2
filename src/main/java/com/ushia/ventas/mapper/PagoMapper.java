package com.ushia.ventas.mapper;

import com.ushia.ventas.model.Pago; //importa pago
import com.ushia.ventas.dto.PagoDTO; //importa pagodto

public class PagoMapper { //convierte pagos en pagos dto

    public PagoDTO toDTO(Pago pago) { //recibe un objeto pago como parametro

        PagoDTO dto = new PagoDTO(); //crea un objeto para copiar la info del pago

        dto.setId(pago.getId());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setEstadoPago(pago.getEstadoPago());
        dto.setMonto(pago.getMonto());
        dto.setFechaPago(pago.getFechaPago());
        dto.setReferencia(pago.getReferencia());

        //obtiene el id de la venta para no pasar toda la entidad al dto
        if (pago.getVenta() != null) {
            dto.setVentaId(pago.getVenta().getId());
        }

        return dto; //devuelve el objeto dto
    }

    public Pago toEntity(PagoDTO dto) { //cambia los pagos dto a pagos

        Pago pago = new Pago();

        pago.setId(dto.getId());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setEstadoPago(dto.getEstadoPago());
        pago.setMonto(dto.getMonto());
        pago.setFechaPago(dto.getFechaPago());
        pago.setReferencia(dto.getReferencia());

        return pago;
    }
}