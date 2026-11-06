package com.ushia.productos.service;

import com.ushia.productos.model.Producto;
import com.ushia.productos.repository.ProductoRepository;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class CatalogoProductoService {

    private final ProductoRepository productoRepository;

    public CatalogoProductoService(EntityManager entityManager) {
        this.productoRepository = new ProductoRepository(entityManager);
    }

    public List<Producto> obtenerCatalogo() {
        return productoRepository.listarTodos();
    }

    public List<Producto> listarPorCategoria(Long categoriaId) {

        return productoRepository.listarTodos()
                .stream()
                .filter(producto ->
                        producto.getCategoria() != null
                                && producto.getCategoria().getId().equals(categoriaId)
                )
                .collect(Collectors.toList());
    }
}
