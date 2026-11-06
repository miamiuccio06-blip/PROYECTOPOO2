package com.ushia.ventas.dto;

//DTO son objetos creados solo con algunos atributos de las demas clases
//se utilizan cuando no necesitamos enviar toda la informacion de una entidad

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ComprobanteDTO {

    private Long id;
    private String numero;
    private String tipoComprobante;
    private LocalDateTime fechaEmision;
    private String documentoCliente;
    private String nombreCliente;
    private BigDecimal total;

    private Long ventaId;

    public ComprobanteDTO() {
    }

    public ComprobanteDTO(Long id, String numero, String tipoComprobante,
                          LocalDateTime fechaEmision, String documentoCliente,
                          String nombreCliente, BigDecimal total, Long ventaId) {

        this.id = id;
        this.numero = numero;
        this.tipoComprobante = tipoComprobante;
        this.fechaEmision = fechaEmision;
        this.documentoCliente = documentoCliente;
        this.nombreCliente = nombreCliente;
        this.total = total;
        this.ventaId = ventaId;
    }

    // Getters y Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }

    public void setNumero(String numero) { this.numero = numero; }

    public String getTipoComprobante() { return tipoComprobante; }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public LocalDateTime getFechaEmision() { return fechaEmision; }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getDocumentoCliente() { return documentoCliente; }

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public String getNombreCliente() { return nombreCliente; }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public BigDecimal getTotal() { return total; }

    public void setTotal(BigDecimal total) { this.total = total; }

    public Long getVentaId() { return ventaId; }

    public void setVentaId(Long ventaId) { this.ventaId = ventaId; }
}
