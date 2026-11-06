package com.ushia.ventas.dto;

//DTO son objetos creados solo con algunos atributos de las demas clases
//se utilizan cuando no necesitamos enviar toda la informacion de una entidad

import com.ushia.ventas.model.MetodoPago;
import com.ushia.ventas.model.EstadoPago;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoDTO {

    private Long id;
    private MetodoPago metodoPago;
    private EstadoPago estadoPago;
    private BigDecimal monto;
    private LocalDateTime fechaPago;
    private String referencia;

    private Long ventaId;

    public PagoDTO() {
    }

    public PagoDTO(Long id, MetodoPago metodoPago, EstadoPago estadoPago,
                   BigDecimal monto, LocalDateTime fechaPago,
                   String referencia, Long ventaId) {

        this.id = id;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.referencia = referencia;
        this.ventaId = ventaId;
    }

    // Getters y Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public MetodoPago getMetodoPago() { return metodoPago; }

    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }

    public EstadoPago getEstadoPago() { return estadoPago; }

    public void setEstadoPago(EstadoPago estadoPago) { this.estadoPago = estadoPago; }

    public BigDecimal getMonto() { return monto; }

    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public LocalDateTime getFechaPago() { return fechaPago; }

    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }

    public String getReferencia() { return referencia; }

    public void setReferencia(String referencia) { this.referencia = referencia; }

    public Long getVentaId() { return ventaId; }

    public void setVentaId(Long ventaId) { this.ventaId = ventaId; }
}