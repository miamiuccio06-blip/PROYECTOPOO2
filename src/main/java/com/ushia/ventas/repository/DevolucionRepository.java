
package com.ushia.ventas.repository;

import com.ushia.ventas.model.Devolucion;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DevolucionRepository {

    private final EntityManager entityManager; //usado para gestionar los objetos en postgres

    public DevolucionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Devolucion guardar(Devolucion devolucion) {

        if (devolucion.getId() == null) {
            entityManager.persist(devolucion); //registra una devolución nueva
            return devolucion;
        }

        return entityManager.merge(devolucion); //actualiza una devolución
    }

    public Devolucion buscarPorId(Long id) {
        return entityManager.find(Devolucion.class, id); //busca una devolución por su id
    }

    public List<Devolucion> listarTodos() {

        return entityManager.createQuery(
                "SELECT d FROM Devolucion d", Devolucion.class //consulta todas las devoluciones
        ).getResultList();
    }

    public void eliminar(Long id) {

        Devolucion devolucion = buscarPorId(id);

        if (devolucion != null) {
            entityManager.remove(devolucion); //elimina la devolución
        }
    }
}
