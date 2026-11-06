package com.ushia.clientes.service;

import com.ushia.clientes.model.Cliente;
import com.ushia.clientes.model.PerfilCliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.function.Supplier;

public class PerfilClienteService {

    private final EntityManager entityManager;

    public PerfilClienteService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Guarda o actualiza el perfil de un cliente
    public PerfilCliente guardar(PerfilCliente perfil) {
        return ejecutarEnTransaccion(() -> {

            // Mantiene sincronizada la relación entre el cliente y su perfil
            if (perfil.getCliente() != null) {
                Cliente cliente = entityManager.find(
                        Cliente.class, perfil.getCliente().getId());

                if (cliente == null) {
                    throw new IllegalArgumentException(
                            "El cliente indicado no existe.");
                }

                cliente.setPerfil(perfil);
            }

            if (perfil.getId() == null) {
                entityManager.persist(perfil);
                return perfil;
            }

            return entityManager.merge(perfil);
        });
    }

    // Busca un perfil por su identificador
    public PerfilCliente buscarPorId(Long id) {
        return entityManager.find(PerfilCliente.class, id);
    }

    // Busca el perfil asociado a un cliente
    public PerfilCliente buscarPorCliente(Long clienteId) {
        return entityManager.createQuery(
                        "SELECT p FROM PerfilCliente p " +
                                "WHERE p.cliente.id = :clienteId",
                        PerfilCliente.class)
                .setParameter("clienteId", clienteId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    // Elimina un perfil por su identificador
    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            PerfilCliente perfil = buscarPorId(id);

            if (perfil != null) {
                entityManager.remove(perfil);
            }

            return null;
        });
    }

    // Ejecuta operaciones que modifican información dentro de una transacción
    private <T> T ejecutarEnTransaccion(Supplier<T> operacion) {
        EntityTransaction transaccion = entityManager.getTransaction();

        try {
            transaccion.begin();
            T resultado = operacion.get();
            transaccion.commit();
            return resultado;
        } catch (RuntimeException e) {
            if (transaccion.isActive()) {
                transaccion.rollback();
            }
            throw e;
        }
    }
}
