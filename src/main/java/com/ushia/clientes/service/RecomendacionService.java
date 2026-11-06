package com.ushia.clientes.service;

import com.ushia.clientes.model.Recomendacion;
import com.ushia.clientes.repository.RecomendacionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class RecomendacionService {

    private final RecomendacionRepository recomendacionRepository;
    private final EntityManager entityManager;

    public RecomendacionService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.recomendacionRepository =
                new RecomendacionRepository(entityManager);
    }

    // Guarda o actualiza una recomendación
    public Recomendacion guardar(Recomendacion recomendacion) {
        return ejecutarEnTransaccion(
                () -> recomendacionRepository.guardar(recomendacion));
    }

    // Busca una recomendación por su identificador
    public Recomendacion buscarPorId(Long id) {
        return recomendacionRepository.buscarPorId(id);
    }

    // Obtiene todas las recomendaciones registradas
    public List<Recomendacion> listarTodos() {
        return recomendacionRepository.listarTodos();
    }

    // Obtiene las recomendaciones de un cliente
    public List<Recomendacion> buscarPorCliente(Long clienteId) {
        return recomendacionRepository.buscarPorCliente(clienteId);
    }

    // Obtiene la recomendación más reciente de un cliente
    public Recomendacion buscarUltimaPorCliente(Long clienteId) {
        return recomendacionRepository.buscarUltimaPorCliente(clienteId);
    }

    // Ejecuta operaciones que modifican información dentro de una transacción
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
