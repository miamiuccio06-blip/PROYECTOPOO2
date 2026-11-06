package com.ushia.productos.model;

import jakarta.persistence.*; //relaciona las clases java con una base de datos

@Entity //entidad en la base de datos
@Table(name = "ubicaciones_almacen") //nombre de la tabla
public class UbicacionAlmacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50) //obligatorio
    private String nombre;

    @Column(length = 100)
    private String descripcion;

    public UbicacionAlmacen() {
    }

    public UbicacionAlmacen(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
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
}
