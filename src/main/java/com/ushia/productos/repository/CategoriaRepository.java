package com.ushia.productos.repository;

import com.ushia.productos.model.CategoriaProducto;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoriaRepository {

    private final EntityManager entityManager;

    public CategoriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public CategoriaProducto guardar(CategoriaProducto categoria) {

        if (categoria.getId() == null) {
            entityManager.persist(categoria);
            return categoria;
        }

        return entityManager.merge(categoria);
    }

    public CategoriaProducto buscarPorId(Long id) {
        return entityManager.find(CategoriaProducto.class, id);
    }

    public List<CategoriaProducto> listarTodos() {

        return entityManager.createQuery(
                "SELECT c FROM CategoriaProducto c",
                CategoriaProducto.class
        ).getResultList();
    }

    public void eliminar(Long id) {

        CategoriaProducto categoria = buscarPorId(id);

        if (categoria != null) {
            entityManager.remove(categoria);
        }
    }
}
