package com.ushia.productos.service;

import com.ushia.productos.model.Proveedor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ProveedorService {

    private final EntityManager entityManager;

    public ProveedorService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Proveedor guardar(Proveedor proveedor) {

        EntityTransaction transaccion = entityManager.getTransaction();

        try {
            transaccion.begin();

            if (proveedor.getId() == null) {
                entityManager.persist(proveedor);
            } else {
                proveedor = entityManager.merge(proveedor);
            }

            transaccion.commit();

            return proveedor;

        } catch (RuntimeException e) {

            if (transaccion.isActive()) {
                transaccion.rollback();
            }

            throw e;
        }
    }

    public Proveedor buscarPorId(Long id) {
        return entityManager.find(Proveedor.class, id);
    }

    public List<Proveedor> listarTodos() {

        return entityManager.createQuery(
                "SELECT p FROM Proveedor p",
                Proveedor.class
        ).getResultList();
    }

    public void eliminar(Long id) {

        EntityTransaction transaccion = entityManager.getTransaction();

        try {
            transaccion.begin();

            Proveedor proveedor = entityManager.find(
                    Proveedor.class, id
            );

            if (proveedor != null) {
                entityManager.remove(proveedor);
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
