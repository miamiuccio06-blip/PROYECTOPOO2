package com.ushia.clientes.model;

import com.ushia.ventas.model.Venta;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historiales_compras")
public class HistorialCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaCompra;

    @Column(length = 300)
    private String observacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne(optional = false)
    @JoinColumn(name = "venta_id", unique = true)
    private Venta venta;

    public HistorialCompra() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }
}