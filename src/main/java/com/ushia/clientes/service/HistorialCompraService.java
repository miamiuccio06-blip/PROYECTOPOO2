package com.ushia.clientes.service;

import com.ushia.clientes.model.HistorialCompra;
import com.ushia.clientes.repository.HistorialCompraRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class HistorialCompraService {

    private final HistorialCompraRepository historialRepository;
    private final EntityManager entityManager;

    public HistorialCompraService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.historialRepository =
                new HistorialCompraRepository(entityManager);
    }

    // Registra una compra en el historial del cliente
    public HistorialCompra guardar(HistorialCompra historial) {
        return ejecutarEnTransaccion(
                () -> historialRepository.guardar(historial));
    }

    // Busca un registro del historial por su identificador
    public HistorialCompra buscarPorId(Long id) {
        return historialRepository.buscarPorId(id);
    }

    // Obtiene todos los registros del historial
    public List<HistorialCompra> listarTodos() {
        return historialRepository.listarTodos();
    }

    // Obtiene las compras realizadas por un cliente
    public List<HistorialCompra> buscarPorCliente(Long clienteId) {
        return historialRepository.buscarPorCliente(clienteId);
    }

    // Busca el registro asociado a una venta
    public HistorialCompra buscarPorVenta(Long ventaId) {
        return historialRepository.buscarPorVenta(ventaId);
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
