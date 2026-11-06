package com.ushia.clientes.model;

import com.ushia.productos.model.Producto;
import com.ushia.productos.model.VarianteProducto;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "items_lista_deseos")
public class ItemListaDeseos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaAgregado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lista_deseos_id")
    private ListaDeseos listaDeseos;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "variante_id")
    private VarianteProducto variante;

    public ItemListaDeseos() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaAgregado() { return fechaAgregado; }
    public void setFechaAgregado(LocalDateTime fechaAgregado) { this.fechaAgregado = fechaAgregado; }

    public ListaDeseos getListaDeseos() { return listaDeseos; }
    public void setListaDeseos(ListaDeseos listaDeseos) { this.listaDeseos = listaDeseos; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public VarianteProducto getVariante() { return variante; }
    public void setVariante(VarianteProducto variante) { this.variante = variante; }
}