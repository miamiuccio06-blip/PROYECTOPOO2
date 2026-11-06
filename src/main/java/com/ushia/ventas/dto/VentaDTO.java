package com.ushia.ventas.dto;

//DTO son objetos creados solo con algunos atributos de las demas clases
//se utilizan cuando no necesitamos enviar toda la informacion de una entidad

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VentaDTO {

    private Long id;
    private String codigo;
    private LocalDateTime fechaHora;
    private BigDecimal subtotal;
    private BigDecimal descuentoTotal;
    private BigDecimal total;
    private String estado;

    private Long clienteId;

    public VentaDTO() {
    }

    public VentaDTO(Long id, String codigo, LocalDateTime fechaHora,
                    BigDecimal subtotal, BigDecimal descuentoTotal,
                    BigDecimal total, String estado, Long clienteId) {

        this.id = id;
        this.codigo = codigo;
        this.fechaHora = fechaHora;
        this.subtotal = subtotal;
        this.descuentoTotal = descuentoTotal;
        this.total = total;
        this.estado = estado;
        this.clienteId = clienteId;
    }

    // Getters y Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public LocalDateTime getFechaHora() { return fechaHora; }

    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public BigDecimal getSubtotal() { return subtotal; }

    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getDescuentoTotal() { return descuentoTotal; }

    public void setDescuentoTotal(BigDecimal descuentoTotal) { this.descuentoTotal = descuentoTotal; }

    public BigDecimal getTotal() { return total; }

    public void setTotal(BigDecimal total) { this.total = total; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public Long getClienteId() { return clienteId; }

    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
}