package com.ushia.productos.model;

import jakarta.persistence.*;

@Entity // Indica que Hibernate debe gestionar esta clase como entidad
@Table(name = "variantes_producto")
public class VarianteProducto { //comvinaciones especificas de iun producgto

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PostgreSQL genera el ID automáticamente
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //no necesitamos cargar automáticamente todo el producto, su talla y su color cada vez que consultamos una variante
    @JoinColumn(name = "producto_id", nullable = false)
    // Varias variantes pueden pertenecer a un mismo producto
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "talla_id", nullable = false)
    // Varias variantes pueden utilizar una misma talla
    private Talla talla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", nullable = false)
    // Varias variantes pueden compartir un mismo color
    private Color color;

    @Column(nullable = false, unique = true, length = 50)
    // El SKU identifica de manera única cada variante
    private String codigoSKU;

    public VarianteProducto() {
    }

    public VarianteProducto(Long id, Producto producto, Talla talla,
                            Color color, String codigoSKU) {
        this.id = id;
        this.producto = producto;
        this.talla = talla;
        this.color = color;
        this.codigoSKU = codigoSKU;
    }

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

    public Talla getTalla() {
        return talla;
    }

    public void setTalla(Talla talla) {
        this.talla = talla;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getCodigoSKU() {
        return codigoSKU;
    }

    public void setCodigoSKU(String codigoSKU) {
        this.codigoSKU = codigoSKU;
    }
}