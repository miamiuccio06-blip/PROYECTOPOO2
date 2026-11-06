package com.ushia.productos.service;

import com.ushia.productos.model.Producto;
import com.ushia.productos.repository.ProductoRepository;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class BusquedaProductoService {

    private final ProductoRepository productoRepository;

    public BusquedaProductoService(EntityManager entityManager) {
        this.productoRepository = new ProductoRepository(entityManager);
    }

    public List<Producto> buscarPorCodigo(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) { //trim elimina espacios
            return List.of(); //si el codgo es malo devuelve una lista vacia
        }

        return productoRepository.listarTodos()
                .stream()
                .filter(producto ->
                        producto.getCodigo() != null
                                && producto.getCodigo().equalsIgnoreCase(
                                codigo.trim()
                        )
                )
                .collect(Collectors.toList());
    }

    public List<Producto> buscarPorNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return List.of();
        }

        return productoRepository.listarTodos()
                .stream()
                .filter(producto ->
                        producto.getNombre() != null
                                && producto.getNombre().toLowerCase()
                                .contains(nombre.trim().toLowerCase())
                )
                .collect(Collectors.toList());
    }
}
