package com.ushia.clientes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "preferencias_clientes")
public class PreferenciaCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaActualizacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "preferencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreferenciaColor> colores = new ArrayList<>();

    @OneToMany(mappedBy = "preferencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreferenciaTalla> tallas = new ArrayList<>();

    @OneToMany(mappedBy = "preferencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreferenciaEstilo> estilos = new ArrayList<>();

    public PreferenciaCliente() {
    }

    // Agrega un color preferido y establece la relación
    public void agregarColor(PreferenciaColor color) {
        colores.add(color);
        color.setPreferencia(this);
    }

    // Agrega una talla preferida y establece la relación
    public void agregarTalla(PreferenciaTalla talla) {
        tallas.add(talla);
        talla.setPreferencia(this);
    }

    // Agrega un estilo preferido y establece la relación
    public void agregarEstilo(PreferenciaEstilo estilo) {
        estilos.add(estilo);
        estilo.setPreferencia(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<PreferenciaColor> getColores() { return colores; }
    public void setColores(List<PreferenciaColor> colores) { this.colores = colores; }

    public List<PreferenciaTalla> getTallas() { return tallas; }
    public void setTallas(List<PreferenciaTalla> tallas) { this.tallas = tallas; }

    public List<PreferenciaEstilo> getEstilos() { return estilos; }
    public void setEstilos(List<PreferenciaEstilo> estilos) { this.estilos = estilos; }
}