
package com.ushia.clientes.dto;

import java.time.LocalDateTime;

// DTO que transporta la información de una recomendación.
// Utiliza el identificador del cliente en lugar de enviar su entidad completa.
public class RecomendacionDTO {

    private Long id;

    private LocalDateTime fechaGeneracion;

    private String estrategiaUtilizada;
    private String estado;

    private Long clienteId;

    public RecomendacionDTO() {
    }

    public RecomendacionDTO(Long id, LocalDateTime fechaGeneracion,
                            String estrategiaUtilizada, String estado,
                            Long clienteId) {

        this.id = id;
        this.fechaGeneracion = fechaGeneracion;
        this.estrategiaUtilizada = estrategiaUtilizada;
        this.estado = estado;
        this.clienteId = clienteId;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getEstrategiaUtilizada() {
        return estrategiaUtilizada;
    }

    public void setEstrategiaUtilizada(String estrategiaUtilizada) {
        this.estrategiaUtilizada = estrategiaUtilizada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
