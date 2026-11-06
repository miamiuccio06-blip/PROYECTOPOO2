package com.ushia.productos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "colores")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(length = 7)
    // Permite almacenar códigos hexadecimales como #FF0000
    private String codigoHexadecimal;

    public Color() {
    }

    public Color(Long id, String nombre, String codigoHexadecimal) {
        this.id = id;
        this.nombre = nombre;
        this.codigoHexadecimal = codigoHexadecimal;
    }

    //getters y setters

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

    public String getCodigoHexadecimal() {
        return codigoHexadecimal;
    }

    public void setCodigoHexadecimal(String codigoHexadecimal) {
        this.codigoHexadecimal = codigoHexadecimal;
    }
}