package com.ushia.ventas.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cupones")
public class Cupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    private LocalDate fechaExpiracion;

    private int usosMaximos;

    private int usosActuales;

    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "descuento_id")
    private Descuento descuento;

    public Cupon() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public LocalDate getFechaExpiracion() { return fechaExpiracion; }
    public void setFechaExpiracion(LocalDate fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }

    public int getUsosMaximos() { return usosMaximos; }
    public void setUsosMaximos(int usosMaximos) { this.usosMaximos = usosMaximos; }

    public int getUsosActuales() { return usosActuales; }
    public void setUsosActuales(int usosActuales) { this.usosActuales = usosActuales; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public Descuento getDescuento() { return descuento; }
    public void setDescuento(Descuento descuento) { this.descuento = descuento; }
}