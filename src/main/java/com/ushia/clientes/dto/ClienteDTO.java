package com.ushia.clientes.dto;

import java.time.LocalDateTime;

// DTO que transporta la información principal de un cliente.
// Evita enviar directamente la entidad Cliente y todas sus relaciones.
public class ClienteDTO {

    private Long id;

    private String nombres;
    private String apellidos;
    private String dni;
    private String correo;
    private String telefono;

    private LocalDateTime fechaRegistro;
    private boolean activo;

    private Long segmentoId;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nombres, String apellidos,
                      String dni, String correo, String telefono,
                      LocalDateTime fechaRegistro, boolean activo,
                      Long segmentoId) {

        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
        this.segmentoId = segmentoId;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Long getSegmentoId() {
        return segmentoId;
    }

    public void setSegmentoId(Long segmentoId) {
        this.segmentoId = segmentoId;
    }
}
