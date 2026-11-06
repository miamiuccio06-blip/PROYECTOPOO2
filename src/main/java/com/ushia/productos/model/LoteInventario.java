package com.ushia.productos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lotes_inventario")
public class LoteInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variante_id", nullable = false)
    // Una variante puede estar distribuida en varios lotes
    private VarianteProducto variante;

    @Column(nullable = false, unique = true, length = 50)
    private String codigoLote;

    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @Column(nullable = false)
    private int cantidadInicial;

    @Column(nullable = false)
    private int cantidadDisponible;

    @Column(nullable = false, precision = 12, scale = 2)
    private double costoUnitario;

    public LoteInventario() {
    }

    public LoteInventario(Long id, VarianteProducto variante,
                          String codigoLote, LocalDate fechaIngreso,
                          int cantidadInicial, int cantidadDisponible,
                          double costoUnitario) {
        this.id = id;
        this.variante = variante;
        this.codigoLote = codigoLote;
        this.fechaIngreso = fechaIngreso;
        this.cantidadInicial = cantidadInicial;
        this.cantidadDisponible = cantidadDisponible;
        this.costoUnitario = costoUnitario;
    }

    //getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VarianteProducto getVariante() {
        return variante;
    }

    public void setVariante(VarianteProducto variante) {
        this.variante = variante;
    }

    public String getCodigoLote() {
        return codigoLote;
    }

    public void setCodigoLote(String codigoLote) {
        this.codigoLote = codigoLote;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(int cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
}