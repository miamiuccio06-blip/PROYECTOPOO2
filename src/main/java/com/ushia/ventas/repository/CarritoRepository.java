package com.ushia.ventas.repository;

import com.ushia.ventas.model.Carrito;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CarritoRepository {

    private final EntityManager entityManager; //usado para gestionar los objetos en postgres

    public CarritoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Carrito guardar(Carrito carrito) {

        if (carrito.getId() == null) {
            entityManager.persist(carrito); //registra un carrito nuevo
            return carrito;
        }

        return entityManager.merge(carrito); //actualiza un carrito
    }

    public Carrito buscarPorId(Long id) {
        return entityManager.find(Carrito.class, id); //busca un carrito por su id
    }

    public List<Carrito> listarTodos() {

        return entityManager.createQuery(
                "SELECT c FROM Carrito c", Carrito.class //consulta todos los carritos
        ).getResultList();
    }

    public void eliminar(Long id) {

        Carrito carrito = buscarPorId(id);

        if (carrito != null) {
            entityManager.remove(carrito); //elimina el carrito
        }
    }
}
