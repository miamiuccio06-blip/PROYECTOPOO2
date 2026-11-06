package com.ushia.ventas.service;

import com.ushia.ventas.model.Descuento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DescuentoService {

    private final EntityManager entityManager;

    public DescuentoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Descuento guardar(Descuento descuento) {

        EntityTransaction transaccion = entityManager.getTransaction();

        try {
            transaccion.begin();

            if (descuento.getId() == null) {
                entityManager.persist(descuento);
            } else {
                descuento = entityManager.merge(descuento);
            }

            transaccion.commit();

            return descuento;

        } catch (RuntimeException e) {

            if (transaccion.isActive()) {
                transaccion.rollback();
            }

            throw e;
        }
    }

    public Descuento buscarPorId(Long id) {
        return entityManager.find(Descuento.class, id);
    }

    public void eliminar(Long id) {

        EntityTransaction transaccion = entityManager.getTransaction();

        try {
            transaccion.begin();

            Descuento descuento = entityManager.find(Descuento.class, id);

            if (descuento != null) {
                entityManager.remove(descuento);
            }

            transaccion.commit();

        } catch (RuntimeException e) {

            if (transaccion.isActive()) {
                transaccion.rollback();
            }

            throw e;
        }
    }
}
