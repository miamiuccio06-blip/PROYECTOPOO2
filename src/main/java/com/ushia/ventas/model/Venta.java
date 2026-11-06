package com.ushia.ventas.model;

import com.ushia.clientes.model.Cliente;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    private LocalDateTime fechaHora;

    @Column(precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(precision = 12, scale = 2)
    private BigDecimal descuentoTotal;

    @Column(precision = 12, scale = 2)
    private BigDecimal total;

    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<DetalleVenta> detalles = new ArrayList<>();

    @OneToMany(mappedBy = "venta",
            cascade = CascadeType.ALL)
    private List<Pago> pagos = new ArrayList<>();

    @OneToOne(mappedBy = "venta",
            cascade = CascadeType.ALL)
    private Comprobante comprobante;

    @OneToMany(mappedBy = "venta",
            cascade = CascadeType.ALL)
    private List<Devolucion> devoluciones = new ArrayList<>();

    public Venta() {
    }

    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
        detalle.setVenta(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getDescuentoTotal() { return descuentoTotal; }
    public void setDescuentoTotal(BigDecimal descuentoTotal) { this.descuentoTotal = descuentoTotal; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<DetalleVenta> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVenta> detalles) { this.detalles = detalles; }

    public List<Pago> getPagos() { return pagos; }
    public void setPagos(List<Pago> pagos) { this.pagos = pagos; }

    public Comprobante getComprobante() { return comprobante; }
    public void setComprobante(Comprobante comprobante) { this.comprobante = comprobante; }

    public List<Devolucion> getDevoluciones() { return devoluciones; }
    public void setDevoluciones(List<Devolucion> devoluciones) { this.devoluciones = devoluciones; }
}