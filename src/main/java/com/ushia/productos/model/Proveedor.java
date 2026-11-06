package com.ushia.productos.model;

import jakarta.persistence.*;
import java.util.Objects; //metodos para trabajar con objetos, no usado por el momento

@Entity //entidad para lamacenar en la base de datos
@Table(name = "proveedores") //el nom,bre de la tabla

public class Proveedor {

    @Id //identificador unico del proved
    @GeneratedValue(strategy = GenerationType.IDENTITY) //post genera el identificador automaticamente
    private Long id;

    @Column(nullable = false, length = 100) //el nombre es obligatorio
    private String nombre;

    @Column(length = 20)
    private String telefono;

    @Column(length = 120)
    private String correo;

    @Column(length = 200)
    private String direccion;

    public Proveedor() {
    }

    public Proveedor(String nombre, String telefono, String correo, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
