package com.ushia.productos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movimientos_inventario")
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    // Guarda el tipo de movimiento como texto en PostgreSQL
    private TipoMovimiento tipo;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(length = 300)
    private String observacion;

    @OneToMany(
            mappedBy = "movimiento",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    // Un movimiento puede tener varios detalles
    private List<DetalleMovimientoInventario> detalles;

    public MovimientoInventario() {
        this.fecha = LocalDateTime.now();
        this.detalles = new ArrayList<>();
    }

    public MovimientoInventario(Long id, TipoMovimiento tipo,
                                LocalDateTime fecha, String observacion) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.observacion = observacion;
        this.detalles = new ArrayList<>();
    }

    // Agrega un detalle y establece la relación entre ambas entidades
    public void agregarDetalle(DetalleMovimientoInventario detalle) {
        detalles.add(detalle);
        detalle.setMovimiento(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<DetalleMovimientoInventario> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleMovimientoInventario> detalles) {
        this.detalles = detalles;
    }
}