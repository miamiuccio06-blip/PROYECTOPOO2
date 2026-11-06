package com.ushia.clientes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recomendaciones")
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaGeneracion;

    private String estrategiaUtilizada;

    private String estado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "recomendacion",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ItemRecomendacion> items = new ArrayList<>();

    public Recomendacion() {
    }

    // Agrega un producto recomendado y establece su relación
    public void agregarItem(ItemRecomendacion item) {
        items.add(item);
        item.setRecomendacion(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }

    public String getEstrategiaUtilizada() { return estrategiaUtilizada; }
    public void setEstrategiaUtilizada(String estrategiaUtilizada) { this.estrategiaUtilizada = estrategiaUtilizada; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<ItemRecomendacion> getItems() { return items; }
    public void setItems(List<ItemRecomendacion> items) { this.items = items; }
}