package com.ushia.clientes.repository;

import com.ushia.clientes.model.PreferenciaCliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PreferenciaClienteRepository {

    private final EntityManager entityManager;

    // Recibe el EntityManager para trabajar con la base de datos.
    public PreferenciaClienteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Guarda una preferencia nueva o actualiza una existente.
    public PreferenciaCliente guardar(PreferenciaCliente preferencia) {

        if (preferencia.getId() == null) {
            entityManager.persist(preferencia);
            return preferencia;
        }

        return entityManager.merge(preferencia);
    }

    // Busca una preferencia mediante su identificador.
    public PreferenciaCliente buscarPorId(Long id) {
        return entityManager.find(PreferenciaCliente.class, id);
    }

    // Obtiene todas las preferencias registradas.
    public List<PreferenciaCliente> listarTodos() {

        return entityManager.createQuery(
                "SELECT p FROM PreferenciaCliente p",
                PreferenciaCliente.class
        ).getResultList();
    }

    // Obtiene las preferencias asociadas a un cliente.
    public List<PreferenciaCliente> buscarPorCliente(Long clienteId) {

        return entityManager.createQuery(
                "SELECT p FROM PreferenciaCliente p " +
                        "WHERE p.cliente.id = :clienteId",
                PreferenciaCliente.class
        ).setParameter("clienteId", clienteId).getResultList();
    }

    // Elimina una preferencia si existe.
    public void eliminar(Long id) {

        PreferenciaCliente preferencia = buscarPorId(id);

        if (preferencia != null) {
            entityManager.remove(preferencia);
        }
    }
}
