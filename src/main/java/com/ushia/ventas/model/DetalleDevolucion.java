package com.ushia.ventas.model;

import com.ushia.productos.model.Producto;
import com.ushia.productos.model.VarianteProducto;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_devolucion")
public class DetalleDevolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "devolucion_id")
    private Devolucion devolucion;

    @ManyToOne
    @JoinColumn(name = "detalle_venta_id")
    private DetalleVenta detalleVenta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "variante_id")
    private VarianteProducto variante;

    private int cantidad;

    @Column(precision = 12, scale = 2)
    private BigDecimal montoReembolsado;

    public DetalleDevolucion() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Devolucion getDevolucion() { return devolucion; }
    public void setDevolucion(Devolucion devolucion) { this.devolucion = devolucion; }

    public DetalleVenta getDetalleVenta() { return detalleVenta; }
    public void setDetalleVenta(DetalleVenta detalleVenta) { this.detalleVenta = detalleVenta; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public VarianteProducto getVariante() { return variante; }
    public void setVariante(VarianteProducto variante) { this.variante = variante; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getMontoReembolsado() { return montoReembolsado; }
    public void setMontoReembolsado(BigDecimal montoReembolsado) { this.montoReembolsado = montoReembolsado; }
}