package com.ushia.ventas.repository;

import com.ushia.ventas.model.Comprobante;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ComprobanteRepository {

    private final EntityManager entityManager; //usado para gestionar los objetos en postgres

    public ComprobanteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Comprobante guardar(Comprobante comprobante) {

        if (comprobante.getId() == null) {
            entityManager.persist(comprobante); //registra un comprobante nuevo
            return comprobante;
        }

        return entityManager.merge(comprobante); //actualiza un comprobante
    }

    public Comprobante buscarPorId(Long id) {
        return entityManager.find(Comprobante.class, id); //busca un comprobante por su id
    }

    public List<Comprobante> listarTodos() {

        return entityManager.createQuery(
                "SELECT c FROM Comprobante c", Comprobante.class //consulta todos los comprobantes
        ).getResultList();
    }

    public void eliminar(Long id) {

        Comprobante comprobante = buscarPorId(id);

        if (comprobante != null) {
            entityManager.remove(comprobante); //elimina el comprobante
        }
    }
}
