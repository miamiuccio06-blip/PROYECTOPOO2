
package com.ushia.clientes.repository;

import com.ushia.clientes.model.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteRepository {

    private final EntityManager entityManager;

    // Recibe el EntityManager para trabajar con la base de datos.
    public ClienteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Guarda un cliente nuevo o actualiza uno existente.
    public Cliente guardar(Cliente cliente) {

        if (cliente.getId() == null) {
            entityManager.persist(cliente); // Registra un cliente nuevo.
            return cliente;
        }

        return entityManager.merge(cliente); // Actualiza un cliente existente.
    }

    // Busca un cliente mediante su identificador.
    public Cliente buscarPorId(Long id) {
        return entityManager.find(Cliente.class, id);
    }

    // Obtiene todos los clientes registrados.
    public List<Cliente> listarTodos() {

        return entityManager.createQuery(
                "SELECT c FROM Cliente c", Cliente.class
        ).getResultList();
    }

    // Busca un cliente mediante su DNI.
    public Cliente buscarPorDni(String dni) {

        List<Cliente> resultados = entityManager.createQuery(
                "SELECT c FROM Cliente c WHERE c.dni = :dni",
                Cliente.class
        ).setParameter("dni", dni).getResultList();

        return resultados.isEmpty() ? null : resultados.get(0);
    }

    // Busca un cliente mediante su correo electrónico.
    public Cliente buscarPorCorreo(String correo) {

        List<Cliente> resultados = entityManager.createQuery(
                "SELECT c FROM Cliente c WHERE c.correo = :correo",
                Cliente.class
        ).setParameter("correo", correo).getResultList();

        return resultados.isEmpty() ? null : resultados.get(0);
    }

    // Obtiene únicamente los clientes que se encuentran activos.
    public List<Cliente> listarActivos() {

        return entityManager.createQuery(
                "SELECT c FROM Cliente c WHERE c.activo = true",
                Cliente.class
        ).getResultList();
    }

    // Obtiene los clientes que se encuentran inactivos.
    public List<Cliente> listarInactivos() {

        return entityManager.createQuery(
                "SELECT c FROM Cliente c WHERE c.activo = false",
                Cliente.class
        ).getResultList();
    }
}
