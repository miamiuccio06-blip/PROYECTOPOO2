package com.ushia.productos.service;

import com.ushia.productos.model.Inventario; //trabajamos con inventario
import com.ushia.productos.repository.InventarioRepository;

import jakarta.persistence.EntityManager; //conecta el servicio con el repositorio

import java.util.List;
import java.util.stream.Collectors; //convierte resuktados de stream en una lista

public class AlertaStockService {

    private final InventarioRepository inventarioRepository; //no se puede cambiar de repositorio

    public AlertaStockService(EntityManager entityManager) {
        this.inventarioRepository =
                new InventarioRepository(entityManager);
    }

    public List<Inventario> obtenerInventariosConStockBajo() { //devuelve una lista de objetos inventario

        return inventarioRepository.listarTodos()
                .stream() //devuelv la lista de inventario como stream
                .filter(inventario -> //filtra los elementos de la lista
                        inventario.getStockActual() //unidades desponibles
                                <= inventario.getStockMinimo() //obtiene el minikmo
                )
                .collect(Collectors.toList()); //elementos que pasaron el filtro en lista
    }

    public boolean tieneStockBajo(Inventario inventario) {

        return inventario.getStockActual()
                <= inventario.getStockMinimo();
    }
}
