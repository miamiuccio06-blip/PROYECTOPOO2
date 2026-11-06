package com.ushia.clientes.service;

import com.ushia.clientes.model.PreferenciaCliente;
import com.ushia.clientes.repository.PreferenciaClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class PreferenciaService {

    private final PreferenciaClienteRepository preferenciaRepository;
    private final EntityManager entityManager;

    public PreferenciaService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.preferenciaRepository =
                new PreferenciaClienteRepository(entityManager);
    }

    // Guarda o actualiza las preferencias de un cliente
    public PreferenciaCliente guardar(PreferenciaCliente preferencia) {
        return ejecutarEnTransaccion(
                () -> preferenciaRepository.guardar(preferencia));
    }

    // Busca una preferencia por su identificador
    public PreferenciaCliente buscarPorId(Long id) {
        return preferenciaRepository.buscarPorId(id);
    }

    // Obtiene todas las preferencias registradas
    public List<PreferenciaCliente> listarTodos() {
        return preferenciaRepository.listarTodos();
    }

    // Obtiene las preferencias de un cliente específico
    public List<PreferenciaCliente> buscarPorCliente(Long clienteId) {
        return preferenciaRepository.buscarPorCliente(clienteId);
    }

    // Elimina las preferencias registradas
    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            preferenciaRepository.eliminar(id);
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
