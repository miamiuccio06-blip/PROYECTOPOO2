package com.ushia.clientes.service;

import com.ushia.clientes.model.Cliente;
import com.ushia.clientes.repository.ClienteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EntityManager entityManager;

    // Recibe el EntityManager y crea el repositorio correspondiente.
    public ClienteService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.clienteRepository = new ClienteRepository(entityManager);
    }

    // Registra un cliente nuevo o actualiza uno existente.
    public Cliente guardar(Cliente cliente) {
        return ejecutarEnTransaccion(
                () -> clienteRepository.guardar(cliente)
        );
    }

    // Busca un cliente mediante su identificador.
    public Cliente buscarPorId(Long id) {
        return clienteRepository.buscarPorId(id);
    }

    // Obtiene todos los clientes registrados.
    public List<Cliente> listarTodos() {
        return clienteRepository.listarTodos();
    }

    // Busca un cliente mediante su DNI.
    public Cliente buscarPorDni(String dni) {
        return clienteRepository.buscarPorDni(dni);
    }

    // Busca un cliente mediante su correo electrónico.
    public Cliente buscarPorCorreo(String correo) {
        return clienteRepository.buscarPorCorreo(correo);
    }

    // Obtiene únicamente los clientes activos.
    public List<Cliente> listarActivos() {
        return clienteRepository.listarActivos();
    }

    // Obtiene únicamente los clientes inactivos.
    public List<Cliente> listarInactivos() {
        return clienteRepository.listarInactivos();
    }

    // Cambia el estado de un cliente a inactivo.
    // No elimina físicamente su información de la base de datos.
    public Cliente desactivar(Long id) {

        return ejecutarEnTransaccion(() -> {

            Cliente cliente = clienteRepository.buscarPorId(id);

            if (cliente == null) {
                throw new IllegalArgumentException(
                        "No existe un cliente con el ID indicado."
                );
            }

            cliente.setActivo(false);

            return clienteRepository.guardar(cliente);
        });
    }

    // Cambia el estado de un cliente a activo.
    public Cliente activar(Long id) {

        return ejecutarEnTransaccion(() -> {

            Cliente cliente = clienteRepository.buscarPorId(id);

            if (cliente == null) {
                throw new IllegalArgumentException(
                        "No existe un cliente con el ID indicado."
                );
            }

            cliente.setActivo(true);

            return clienteRepository.guardar(cliente);
        });
    }

    // Ejecuta una operación dentro de una transacción.
    // Si ocurre un error, revierte los cambios realizados.
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
