package com.ushia.clientes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "listas_deseos")
public class ListaDeseos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private LocalDateTime fechaCreacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "listaDeseos",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ItemListaDeseos> items = new ArrayList<>();

    public ListaDeseos() {
    }

    // Agrega un producto a la lista y establece su relación
    public void agregarItem(ItemListaDeseos item) {
        items.add(item);
        item.setListaDeseos(this);
    }

    // Retira un producto de la lista y elimina su relación
    public void eliminarItem(ItemListaDeseos item) {
        items.remove(item);
        item.setListaDeseos(null);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<ItemListaDeseos> getItems() { return items; }
    public void setItems(List<ItemListaDeseos> items) { this.items = items; }
}