package com.ushia.clientes.model;

import com.ushia.productos.model.Producto;
import com.ushia.productos.model.VarianteProducto;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items_recomendacion")
public class ItemRecomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer posicion;

    @Column(precision = 5, scale = 2)
    private BigDecimal puntuacion;

    private String motivo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recomendacion_id")
    private Recomendacion recomendacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "variante_id")
    private VarianteProducto variante;

    public ItemRecomendacion() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getPosicion() { return posicion; }
    public void setPosicion(Integer posicion) { this.posicion = posicion; }

    public BigDecimal getPuntuacion() { return puntuacion; }
    public void setPuntuacion(BigDecimal puntuacion) { this.puntuacion = puntuacion; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public Recomendacion getRecomendacion() { return recomendacion; }
    public void setRecomendacion(Recomendacion recomendacion) { this.recomendacion = recomendacion; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public VarianteProducto getVariante() { return variante; }
    public void setVariante(VarianteProducto variante) { this.variante = variante; }
}