package com.ushia.productos.repository;

import com.ushia.productos.model.MovimientoInventario;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MovimientoInventarioRepository {

    private final EntityManager entityManager;

    public MovimientoInventarioRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public MovimientoInventario guardar(MovimientoInventario movimiento) {

        if (movimiento.getId() == null) {
            entityManager.persist(movimiento);
            return movimiento;
        }

        return entityManager.merge(movimiento);
    }

    public MovimientoInventario buscarPorId(Long id) {
        return entityManager.find(MovimientoInventario.class, id);
    }

    public List<MovimientoInventario> listarTodos() {

        return entityManager.createQuery(
                "SELECT m FROM MovimientoInventario m",
                MovimientoInventario.class
        ).getResultList();
    }

    public void eliminar(Long id) {

        MovimientoInventario movimiento = buscarPorId(id);

        if (movimiento != null) {
            entityManager.remove(movimiento);
        }
    }
}
