package com.ushia.ventas.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "devoluciones")
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "venta_id")
    private Venta venta;

    private LocalDateTime fecha;

    private String motivo;

    private String estado;

    @Column(precision = 12, scale = 2)
    private BigDecimal montoTotal;

    @OneToMany(mappedBy = "devolucion",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<DetalleDevolucion> detalles = new ArrayList<>();

    public Devolucion() {
    }

    public void agregarDetalle(DetalleDevolucion detalle) {
        detalles.add(detalle);
        detalle.setDevolucion(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }

    public List<DetalleDevolucion> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleDevolucion> detalles) { this.detalles = detalles; }
}