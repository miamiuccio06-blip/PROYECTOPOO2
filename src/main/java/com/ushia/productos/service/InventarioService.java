package com.ushia.productos.service;

import com.ushia.productos.model.Inventario;
import com.ushia.productos.repository.InventarioRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final EntityManager entityManager;

    public InventarioService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.inventarioRepository = new InventarioRepository(entityManager);
    }

    public Inventario guardar(Inventario inventario) {
        return ejecutarEnTransaccion(
                () -> inventarioRepository.guardar(inventario)
        );
    }

    public Inventario buscarPorId(Long id) {
        return inventarioRepository.buscarPorId(id);
    }

    public List<Inventario> listarTodos() {
        return inventarioRepository.listarTodos();
    }

    public Inventario actualizarStock(Long id, int nuevaCantidad) {

        if (nuevaCantidad < 0) {
            throw new IllegalArgumentException(
                    "El stock no puede ser negativo."
            );
        }

        return ejecutarEnTransaccion(() -> {

            Inventario inventario = inventarioRepository.buscarPorId(id);

            if (inventario == null) {
                throw new IllegalArgumentException(
                        "No se encontró el inventario."
                );
            }

            inventario.setStockActual(nuevaCantidad);

            return inventarioRepository.guardar(inventario);
        });
    }

    public Inventario ajustarStock(Long id, int variacion) {

        return ejecutarEnTransaccion(() -> {

            Inventario inventario = inventarioRepository.buscarPorId(id);

            if (inventario == null) {
                throw new IllegalArgumentException(
                        "No se encontró el inventario."
                );
            }

            int nuevoStock = inventario.getStockActual() + variacion;

            if (nuevoStock < 0) {
                throw new IllegalArgumentException(
                        "El ajuste produciría un stock negativo."
                );
            }

            inventario.setStockActual(nuevoStock);

            return inventarioRepository.guardar(inventario);
        });
    }

    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            inventarioRepository.eliminar(id);
            return null;
        });
    }

    private <T> T ejecutarEnTransaccion(Supplier<T> operacion) {

        EntityTransaction transaccion = entityManager.getTransaction();

        try {
            transaccion.begin();

            T resultado = operacion.get();

            transaccion.commit();

            return resultado;

        } catch (RuntimeException e) {

            if (transaccion.isActive()) {
                transaccion.rollback();
            }

            throw e;
        }
    }
}
