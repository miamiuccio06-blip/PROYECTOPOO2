package com.ushia.ventas.repository;

import com.ushia.ventas.model.DetalleVenta;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DetalleVentaRepository {

    private final EntityManager entityManager; //usado para gestionar los objetos en postgres

    public DetalleVentaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public DetalleVenta guardar(DetalleVenta detalleVenta) {

        if (detalleVenta.getId() == null) {
            entityManager.persist(detalleVenta); //registra un detalle nuevo
            return detalleVenta;
        }

        return entityManager.merge(detalleVenta); //actualiza un detalle
    }

    public DetalleVenta buscarPorId(Long id) {
        return entityManager.find(DetalleVenta.class, id); //busca un detalle por su id
    }

    public List<DetalleVenta> listarTodos() {

        return entityManager.createQuery(
                "SELECT d FROM DetalleVenta d", DetalleVenta.class //consulta todos los detalles
        ).getResultList();
    }

    public void eliminar(Long id) {

        DetalleVenta detalleVenta = buscarPorId(id);

        if (detalleVenta != null) {
            entityManager.remove(detalleVenta); //elimina el detalle
        }
    }
}
