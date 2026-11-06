package com.ushia.clientes.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "perfiles_clientes")
public class PerfilCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resumen;

    private String estiloPredominante;

    @Column(precision = 12, scale = 2)
    private BigDecimal presupuestoMinimo;

    @Column(precision = 12, scale = 2)
    private BigDecimal presupuestoMaximo;

    private LocalDateTime fechaActualizacion;

    @OneToOne(optional = false)
    @JoinColumn(name = "cliente_id", unique = true)
    private Cliente cliente;

    public PerfilCliente() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getResumen() { return resumen; }
    public void setResumen(String resumen) { this.resumen = resumen; }

    public String getEstiloPredominante() { return estiloPredominante; }
    public void setEstiloPredominante(String estiloPredominante) { this.estiloPredominante = estiloPredominante; }

    public BigDecimal getPresupuestoMinimo() { return presupuestoMinimo; }
    public void setPresupuestoMinimo(BigDecimal presupuestoMinimo) { this.presupuestoMinimo = presupuestoMinimo; }

    public BigDecimal getPresupuestoMaximo() { return presupuestoMaximo; }
    public void setPresupuestoMaximo(BigDecimal presupuestoMaximo) { this.presupuestoMaximo = presupuestoMaximo; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}