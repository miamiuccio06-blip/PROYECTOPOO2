package com.ushia.clientes.model;

import com.ushia.productos.model.Color;
import jakarta.persistence.*;

@Entity
@Table(name = "preferencias_colores")
public class PreferenciaColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "preferencia_id")
    private PreferenciaCliente preferencia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "color_id")
    private Color color;

    public PreferenciaColor() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PreferenciaCliente getPreferencia() { return preferencia; }
    public void setPreferencia(PreferenciaCliente preferencia) { this.preferencia = preferencia; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
}