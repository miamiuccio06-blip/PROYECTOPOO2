package com.ushia.productos.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "inventarios",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"variante_id", "ubicacion_id"}
                )
        }
)
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variante_id", nullable = false)
    private VarianteProducto variante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacion_id", nullable = false)
    private UbicacionAlmacen ubicacion;

    @Column(nullable = false)
    private int stockActual;

    @Column(nullable = false)
    private int stockMinimo;

    public Inventario() {
    }

    public Inventario(Long id, VarianteProducto variante,
                      UbicacionAlmacen ubicacion,
                      int stockActual, int stockMinimo) {
        this.id = id;
        this.variante = variante;
        this.ubicacion = ubicacion;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
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

    public UbicacionAlmacen getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionAlmacen ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}