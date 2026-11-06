package com.ushia.ventas.repository;

import com.ushia.ventas.model.Pago;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PagoRepository {

    private final EntityManager entityManager; //usado para gestionar los objetos en postgres

    public PagoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Pago guardar(Pago pago) {

        if (pago.getId() == null) {
            entityManager.persist(pago); //registra un pago nuevo
            return pago;
        }

        return entityManager.merge(pago); //actualiza un pago
    }

    public Pago buscarPorId(Long id) {
        return entityManager.find(Pago.class, id); //busca un pago por su id
    }

    public List<Pago> listarTodos() {

        return entityManager.createQuery(
                "SELECT p FROM Pago p", Pago.class //consulta todos los pagos
        ).getResultList();
    }

    public void eliminar(Long id) {

        Pago pago = buscarPorId(id);

        if (pago != null) {
            entityManager.remove(pago); //elimina el pago
        }
    }
}
