package com.ushia.productos.repository;

import com.ushia.productos.model.Producto;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductoRepository {

    private final EntityManager entityManager; //usado para los objetos en post

    public ProductoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Producto guardar(Producto producto) {

        if (producto.getId() == null) {
            entityManager.persist(producto); //registra una entidad nueva
            return producto;
        }

        return entityManager.merge(producto); //actualiza una entidad
    }

    public Producto buscarPorId(Long id) {
        return entityManager.find(Producto.class, id); //busca entidad por su id
    }

    public List<Producto> listarTodos() {

        return entityManager.createQuery(
                "SELECT p FROM Producto p", Producto.class //hace una consulta con jpql, consultas en jpa, como un for each
        ).getResultList();
    }

    public void eliminar(Long id) {

        Producto producto = buscarPorId(id);

        if (producto != null) {
            entityManager.remove(producto); //elimina
        }
    }
}
