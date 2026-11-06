package com.ushia.ventas.service;

import com.ushia.ventas.model.Devolucion;
import com.ushia.ventas.repository.DevolucionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class DevolucionService {

    private final DevolucionRepository devolucionRepository;
    private final EntityManager entityManager;

    public DevolucionService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.devolucionRepository = new DevolucionRepository(entityManager);
    }

    public Devolucion guardar(Devolucion devolucion) {
        return ejecutarEnTransaccion(
                () -> devolucionRepository.guardar(devolucion)
        );
    }

    public Devolucion buscarPorId(Long id) {
        return devolucionRepository.buscarPorId(id);
    }

    public List<Devolucion> listarTodos() {
        return devolucionRepository.listarTodos();
    }

    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            devolucionRepository.eliminar(id);
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
