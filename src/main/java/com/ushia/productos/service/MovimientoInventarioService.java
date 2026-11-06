package com.ushia.productos.service;

import com.ushia.productos.model.MovimientoInventario;
import com.ushia.productos.repository.MovimientoInventarioRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoRepository;
    private final EntityManager entityManager;

    public MovimientoInventarioService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.movimientoRepository =
                new MovimientoInventarioRepository(entityManager);
    }

    public MovimientoInventario registrar(
            MovimientoInventario movimiento) {

        return ejecutarEnTransaccion(
                () -> movimientoRepository.guardar(movimiento)
        );
    }

    public MovimientoInventario buscarPorId(Long id) {
        return movimientoRepository.buscarPorId(id);
    }

    public List<MovimientoInventario> listarTodos() {
        return movimientoRepository.listarTodos();
    }

    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            movimientoRepository.eliminar(id);
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
