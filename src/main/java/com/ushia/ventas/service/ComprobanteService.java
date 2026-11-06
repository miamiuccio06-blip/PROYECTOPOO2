package com.ushia.ventas.service;

import com.ushia.ventas.model.Comprobante;
import com.ushia.ventas.repository.ComprobanteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;
    private final EntityManager entityManager;

    public ComprobanteService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.comprobanteRepository = new ComprobanteRepository(entityManager);
    }

    public Comprobante guardar(Comprobante comprobante) {
        return ejecutarEnTransaccion(
                () -> comprobanteRepository.guardar(comprobante)
        );
    }

    public Comprobante buscarPorId(Long id) {
        return comprobanteRepository.buscarPorId(id);
    }

    public List<Comprobante> listarTodos() {
        return comprobanteRepository.listarTodos();
    }

    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            comprobanteRepository.eliminar(id);
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
