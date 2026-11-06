package com.ushia.productos.repository;

import com.ushia.productos.model.Inventario;
import jakarta.persistence.EntityManager;

import java.util.List;

public class InventarioRepository {

    private final EntityManager entityManager;

    public InventarioRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Inventario guardar(Inventario inventario) {

        if (inventario.getId() == null) {
            entityManager.persist(inventario);
            return inventario;
        }

        return entityManager.merge(inventario);
    }

    public Inventario buscarPorId(Long id) {
        return entityManager.find(Inventario.class, id);
    }

    public List<Inventario> listarTodos() {

        return entityManager.createQuery(
                "SELECT i FROM Inventario i",
                Inventario.class
        ).getResultList();
    }

    public void eliminar(Long id) {

        Inventario inventario = buscarPorId(id);

        if (inventario != null) {
            entityManager.remove(inventario);
        }
    }
}
