package com.ushia.productos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false, precision = 12, scale = 2)
    private double precioVenta;

    @Column(nullable = false, precision = 12, scale = 2)
    private double precioCosto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    // Varios productos pueden pertenecer a una misma categoría
    private CategoriaProducto categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temporada_id")
    // Una temporada puede contener varios productos
    private Temporada temporada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coleccion_id")
    // Una colección puede contener varios productos
    private Coleccion coleccion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    // Guarda el nombre del estado en PostgreSQL, no su posición numérica
    private EstadoProducto estado;

    public Producto() {
    }

    public Producto(Long id, String codigo, String nombre,
                    String descripcion, double precioVenta,
                    double precioCosto, CategoriaProducto categoria,
                    Temporada temporada, Coleccion coleccion,
                    EstadoProducto estado) {

        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.precioCosto = precioCosto;
        this.categoria = categoria;
        this.temporada = temporada;
        this.coleccion = coleccion;
        this.estado = estado;
    }

    //setters y getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Coleccion getColeccion() {
        return coleccion;
    }

    public void setColeccion(Coleccion coleccion) {
        this.coleccion = coleccion;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }
}