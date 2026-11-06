package com.ushia.clientes.service;

import com.ushia.clientes.model.Cliente;
import com.ushia.clientes.model.ClienteSegmento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class SegmentacionClienteService {

    private final EntityManager entityManager;

    public SegmentacionClienteService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Guarda o actualiza un segmento de clientes
    public ClienteSegmento guardar(ClienteSegmento segmento) {
        return ejecutarEnTransaccion(() -> {
            if (segmento.getId() == null) {
                entityManager.persist(segmento);
                return segmento;
            }

            return entityManager.merge(segmento);
        });
    }

    // Busca un segmento por su identificador
    public ClienteSegmento buscarPorId(Long id) {
        return entityManager.find(ClienteSegmento.class, id);
    }

    // Obtiene todos los segmentos registrados
    public List<ClienteSegmento> listarTodos() {
        return entityManager.createQuery(
                        "SELECT s FROM ClienteSegmento s",
                        ClienteSegmento.class)
                .getResultList();
    }

    // Asigna un cliente a un segmento determinado
    public void asignarSegmento(Long clienteId, Long segmentoId) {
        ejecutarEnTransaccion(() -> {

            Cliente cliente = entityManager.find(Cliente.class, clienteId);
            ClienteSegmento segmento =
                    entityManager.find(ClienteSegmento.class, segmentoId);

            if (cliente == null || segmento == null) {
                throw new IllegalArgumentException(
                        "El cliente o el segmento no existe.");
            }

            cliente.setSegmento(segmento);
            return null;
        });
    }

    // Obtiene los clientes que pertenecen a un segmento
    public List<Cliente> listarClientesPorSegmento(Long segmentoId) {
        return entityManager.createQuery(
                        "SELECT c FROM Cliente c " +
                                "WHERE c.segmento.id = :segmentoId",
                        Cliente.class)
                .setParameter("segmentoId", segmentoId)
                .getResultList();
    }

    // Elimina un segmento si no tiene clientes asignados
    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            ClienteSegmento segmento = buscarPorId(id);

            if (segmento != null) {
                if (!segmento.getClientes().isEmpty()) {
                    throw new IllegalStateException(
                            "No se puede eliminar un segmento que tiene clientes asignados.");
                }

                entityManager.remove(segmento);
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
