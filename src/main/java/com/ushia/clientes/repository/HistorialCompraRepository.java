package com.ushia.clientes.repository;

import com.ushia.clientes.model.HistorialCompra;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HistorialCompraRepository {

    private final EntityManager entityManager;

    // Recibe el EntityManager para trabajar con la base de datos.
    public HistorialCompraRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Guarda un historial nuevo o actualiza uno existente.
    public HistorialCompra guardar(HistorialCompra historial) {

        if (historial.getId() == null) {
            entityManager.persist(historial);
            return historial;
        }

        return entityManager.merge(historial);
    }

    // Busca un historial mediante su identificador.
    public HistorialCompra buscarPorId(Long id) {
        return entityManager.find(HistorialCompra.class, id);
    }

    // Obtiene todos los historiales de compra.
    public List<HistorialCompra> listarTodos() {

        return entityManager.createQuery(
                "SELECT h FROM HistorialCompra h",
                HistorialCompra.class
        ).getResultList();
    }

    // Obtiene el historial de compras de un cliente.
    // Los resultados se ordenan desde la compra más reciente.
    public List<HistorialCompra> buscarPorCliente(Long clienteId) {

        return entityManager.createQuery(
                "SELECT h FROM HistorialCompra h " +
                        "WHERE h.cliente.id = :clienteId " +
                        "ORDER BY h.fechaCompra DESC",
                HistorialCompra.class
        ).setParameter("clienteId", clienteId).getResultList();
    }

    // Busca el historial asociado a una venta específica.
    public HistorialCompra buscarPorVenta(Long ventaId) {

        List<HistorialCompra> resultados = entityManager.createQuery(
                "SELECT h FROM HistorialCompra h " +
                        "WHERE h.venta.id = :ventaId",
                HistorialCompra.class
        ).setParameter("ventaId", ventaId).getResultList();

        return resultados.isEmpty() ? null : resultados.get(0);
    }
}
