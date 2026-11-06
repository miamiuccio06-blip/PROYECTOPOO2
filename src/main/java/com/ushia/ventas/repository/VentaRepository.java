package com.ushia.ventas.repository;

import com.ushia.ventas.model.Venta;
import jakarta.persistence.EntityManager;

import java.util.List;

public class VentaRepository {

    private final EntityManager entityManager; //usado para gestionar los objetos en postgres

    public VentaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Venta guardar(Venta venta) {

        if (venta.getId() == null) {
            entityManager.persist(venta); //registra una entidad nueva
            return venta;
        }

        return entityManager.merge(venta); //actualiza una entidad
    }

    public Venta buscarPorId(Long id) {
        return entityManager.find(Venta.class, id); //busca una venta por su id
    }

    public List<Venta> listarTodos() {

        return entityManager.createQuery(
                "SELECT v FROM Venta v", Venta.class //consulta todas las ventas
        ).getResultList();
    }

    public void eliminar(Long id) {

        Venta venta = buscarPorId(id);

        if (venta != null) {
            entityManager.remove(venta); //elimina la venta
        }
    }
}
