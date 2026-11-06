package com.ushia.ventas.service;

import com.ushia.ventas.model.Promocion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PromocionService {

    private final EntityManager entityManager;

    public PromocionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Promocion guardar(Promocion promocion) {

        EntityTransaction transaccion = entityManager.getTransaction();

        try {
            transaccion.begin();

            if (promocion.getId() == null) {
                entityManager.persist(promocion);
            } else {
                promocion = entityManager.merge(promocion);
            }

            transaccion.commit();

            return promocion;

        } catch (RuntimeException e) {

            if (transaccion.isActive()) {
                transaccion.rollback();
            }

            throw e;
        }
    }

    public Promocion buscarPorId(Long id) {
        return entityManager.find(Promocion.class, id);
    }

    // Consulta todas las promociones registradas.
    public java.util.List<Promocion> listarTodos() {

        return entityManager.createQuery(
                "SELECT p FROM Promocion p", Promocion.class
        ).getResultList();
    }
}
