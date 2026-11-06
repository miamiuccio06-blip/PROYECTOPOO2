package com.ushia.clientes.model;

import com.ushia.productos.model.Talla;
import jakarta.persistence.*;

@Entity
@Table(name = "preferencias_tallas")
public class PreferenciaTalla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "preferencia_id")
    private PreferenciaCliente preferencia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "talla_id")
    private Talla talla;

    public PreferenciaTalla() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PreferenciaCliente getPreferencia() { return preferencia; }
    public void setPreferencia(PreferenciaCliente preferencia) { this.preferencia = preferencia; }

    public Talla getTalla() { return talla; }
    public void setTalla(Talla talla) { this.talla = talla; }
}