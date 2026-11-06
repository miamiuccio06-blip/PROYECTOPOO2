package com.ushia.clientes.service;

import com.ushia.clientes.model.InteraccionCliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class InteraccionClienteService {

    private final EntityManager entityManager;

    public InteraccionClienteService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Registra o actualiza una interacción del cliente
    public InteraccionCliente guardar(InteraccionCliente interaccion) {
        return ejecutarEnTransaccion(() -> {
            if (interaccion.getId() == null) {
                entityManager.persist(interaccion);
                return interaccion;
            }

            return entityManager.merge(interaccion);
        });
    }

    // Busca una interacción por su identificador
    public InteraccionCliente buscarPorId(Long id) {
        return entityManager.find(InteraccionCliente.class, id);
    }

    // Obtiene todas las interacciones registradas
    public List<InteraccionCliente> listarTodos() {
        return entityManager.createQuery(
                        "SELECT i FROM InteraccionCliente i",
                        InteraccionCliente.class)
                .getResultList();
    }

    // Obtiene las interacciones de un cliente ordenadas por fecha
    public List<InteraccionCliente> buscarPorCliente(Long clienteId) {
        return entityManager.createQuery(
                        "SELECT i FROM InteraccionCliente i " +
                                "WHERE i.cliente.id = :clienteId " +
                                "ORDER BY i.fechaHora DESC",
                        InteraccionCliente.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }

    // Obtiene las interacciones relacionadas con un producto
    public List<InteraccionCliente> buscarPorProducto(Long productoId) {
        return entityManager.createQuery(
                        "SELECT i FROM InteraccionCliente i " +
                                "WHERE i.producto.id = :productoId " +
                                "ORDER BY i.fechaHora DESC",
                        InteraccionCliente.class)
                .setParameter("productoId", productoId)
                .getResultList();
    }

    // Elimina una interacción por su identificador
    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            InteraccionCliente interaccion = buscarPorId(id);

            if (interaccion != null) {
                entityManager.remove(interaccion);
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
