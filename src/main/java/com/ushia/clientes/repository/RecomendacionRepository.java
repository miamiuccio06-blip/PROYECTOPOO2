package com.ushia.clientes.repository;

import com.ushia.clientes.model.Recomendacion;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RecomendacionRepository {

    private final EntityManager entityManager;

    // Recibe el EntityManager para trabajar con la base de datos.
    public RecomendacionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Guarda una recomendación nueva o actualiza una existente.
    public Recomendacion guardar(Recomendacion recomendacion) {

        if (recomendacion.getId() == null) {
            entityManager.persist(recomendacion);
            return recomendacion;
        }

        return entityManager.merge(recomendacion);
    }

    // Busca una recomendación mediante su identificador.
    public Recomendacion buscarPorId(Long id) {
        return entityManager.find(Recomendacion.class, id);
    }

    // Obtiene todas las recomendaciones registradas.
    public List<Recomendacion> listarTodos() {

        return entityManager.createQuery(
                "SELECT r FROM Recomendacion r",
                Recomendacion.class
        ).getResultList();
    }

    // Obtiene las recomendaciones de un cliente.
    // Las más recientes aparecen primero.
    public List<Recomendacion> buscarPorCliente(Long clienteId) {

        return entityManager.createQuery(
                "SELECT r FROM Recomendacion r " +
                        "WHERE r.cliente.id = :clienteId " +
                        "ORDER BY r.fechaGeneracion DESC",
                Recomendacion.class
        ).setParameter("clienteId", clienteId).getResultList();
    }

    // Obtiene la recomendación más reciente de un cliente.
    public Recomendacion buscarUltimaPorCliente(Long clienteId) {

        List<Recomendacion> resultados = entityManager.createQuery(
                        "SELECT r FROM Recomendacion r " +
                                "WHERE r.cliente.id = :clienteId " +
                                "ORDER BY r.fechaGeneracion DESC",
                        Recomendacion.class
                )
                .setParameter("clienteId", clienteId)
                .setMaxResults(1)
                .getResultList();

        return resultados.isEmpty() ? null : resultados.get(0);
    }
}
