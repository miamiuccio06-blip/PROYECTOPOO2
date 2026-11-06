package com.ushia.productos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias_producto")
public class CategoriaProducto {

    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 80)
    private String nombre;

    @Column(length = 200)
    private String descripcion;

    @Column(nullable = false)
    private boolean activa;

    public CategoriaProducto() {
    } //constructor vacio

    public CategoriaProducto(Long id, String nombre, String descripcion, boolean activa) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activa = activa;
    } //constructor

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActiva() {
        return activa;
    } //si l;a categoria esta activa

    public void setActiva(boolean activa) {
        this.activa = activa;
    } //actualizacion del estado
}