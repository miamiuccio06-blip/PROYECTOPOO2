package com.ushia.clientes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "preferencias_estilos")
public class PreferenciaEstilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String estilo; // almacena el estilo preferido por el cliente

    @ManyToOne(optional = false)
    @JoinColumn(name = "preferencia_id")
    private PreferenciaCliente preferencia;

    public PreferenciaEstilo() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEstilo() { return estilo; }
    public void setEstilo(String estilo) { this.estilo = estilo; }

    public PreferenciaCliente getPreferencia() { return preferencia; }
    public void setPreferencia(PreferenciaCliente preferencia) { this.preferencia = preferencia; }
}