package com.ushia.ventas.service;

import com.ushia.ventas.model.Pago;
import com.ushia.ventas.repository.PagoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class PagoService {

    private final PagoRepository pagoRepository;
    private final EntityManager entityManager;

    public PagoService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.pagoRepository = new PagoRepository(entityManager);
    }

    public Pago guardar(Pago pago) {
        return ejecutarEnTransaccion(
                () -> pagoRepository.guardar(pago)
        );
    }

    public Pago buscarPorId(Long id) {
        return pagoRepository.buscarPorId(id);
    }

    public List<Pago> listarTodos() {
        return pagoRepository.listarTodos();
    }

    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            pagoRepository.eliminar(id);
            return null;
        });
    }

    // Ejecuta una operación de persistencia dentro de una transacción.
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
