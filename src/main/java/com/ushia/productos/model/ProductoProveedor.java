package com.ushia.productos.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "productos_proveedores",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"producto_id", "proveedor_id"}
                )
        }
)
public class ProductoProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    // Muchos registros de proveedores pueden relacionarse con un producto
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id", nullable = false)
    // Un proveedor puede suministrar distintos productos
    private Proveedor proveedor;

    @Column(length = 80)
    private String codigoProductoProveedor;

    @Column(nullable = false, precision = 12, scale = 2)
    private double costoCompra;

    @Column(nullable = false)
    private int tiempoEntregaDias;

    public ProductoProveedor() {
    }

    public ProductoProveedor(Long id, Producto producto,
                             Proveedor proveedor,
                             String codigoProductoProveedor,
                             double costoCompra,
                             int tiempoEntregaDias) {
        this.id = id;
        this.producto = producto;
        this.proveedor = proveedor;
        this.codigoProductoProveedor = codigoProductoProveedor;
        this.costoCompra = costoCompra;
        this.tiempoEntregaDias = tiempoEntregaDias;
    }

    //getters ysetters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getCodigoProductoProveedor() {
        return codigoProductoProveedor;
    }

    public void setCodigoProductoProveedor(String codigoProductoProveedor) {
        this.codigoProductoProveedor = codigoProductoProveedor;
    }

    public double getCostoCompra() {
        return costoCompra;
    }

    public void setCostoCompra(double costoCompra) {
        this.costoCompra = costoCompra;
    }

    public int getTiempoEntregaDias() {
        return tiempoEntregaDias;
    }

    public void setTiempoEntregaDias(int tiempoEntregaDias) {
        this.tiempoEntregaDias = tiempoEntregaDias;
    }
}