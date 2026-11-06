package com.ushia.productos.repository;

import com.ushia.productos.model.VarianteProducto;
import jakarta.persistence.EntityManager;

import java.util.List;

public class VarianteRepository {

    private final EntityManager entityManager;

    public VarianteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public VarianteProducto guardar(VarianteProducto variante) {

        if (variante.getId() == null) {
            entityManager.persist(variante);
            return variante;
        }

        return entityManager.merge(variante);
    }

    public VarianteProducto buscarPorId(Long id) {
        return entityManager.find(VarianteProducto.class, id);
    }

    public List<VarianteProducto> listarTodos() {

        return entityManager.createQuery(
                "SELECT v FROM VarianteProducto v",
                VarianteProducto.class
        ).getResultList();
    }

    public void eliminar(Long id) {

        VarianteProducto variante = buscarPorId(id);

        if (variante != null) {
            entityManager.remove(variante);
        }
    }
}
