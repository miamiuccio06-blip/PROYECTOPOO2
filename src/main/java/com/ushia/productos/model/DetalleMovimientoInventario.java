package com.ushia.productos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_movimiento_inventario")
public class DetalleMovimientoInventario { //registra cada elemento de la operacion de movimiento

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimiento_id", nullable = false)
    // Muchos detalles pueden pertenecer a un mismo movimiento
    private MovimientoInventario movimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variante_id", nullable = false)
    // Cada detalle identifica una variante específica
    private VarianteProducto variante;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false, precision = 12, scale = 2)
    private double costoUnitario;

    public DetalleMovimientoInventario() {
    }

    public DetalleMovimientoInventario(Long id,
                                       MovimientoInventario movimiento,
                                       VarianteProducto variante,
                                       int cantidad,
                                       double costoUnitario) {
        this.id = id;
        this.movimiento = movimiento;
        this.variante = variante;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
    }

    //getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovimientoInventario getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoInventario movimiento) {
        this.movimiento = movimiento;
    }

    public VarianteProducto getVariante() {
        return variante;
    }

    public void setVariante(VarianteProducto variante) {
        this.variante = variante;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
}