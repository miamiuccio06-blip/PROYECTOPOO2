package com.ushia.clientes.service;

import com.ushia.clientes.model.Cliente;
import com.ushia.clientes.model.ListaDeseos;
import com.ushia.clientes.model.ItemListaDeseos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class ListaDeseosService {

    private final EntityManager entityManager;

    public ListaDeseosService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Guarda o actualiza una lista de deseos
    public ListaDeseos guardar(ListaDeseos lista) {
        return ejecutarEnTransaccion(() -> {
            if (lista.getId() == null) {
                entityManager.persist(lista);
                return lista;
            }

            return entityManager.merge(lista);
        });
    }

    // Busca una lista de deseos por su identificador
    public ListaDeseos buscarPorId(Long id) {
        return entityManager.find(ListaDeseos.class, id);
    }

    // Obtiene todas las listas de deseos
    public List<ListaDeseos> listarTodos() {
        return entityManager.createQuery(
                        "SELECT l FROM ListaDeseos l",
                        ListaDeseos.class)
                .getResultList();
    }

    // Obtiene las listas de deseos de un cliente
    public List<ListaDeseos> buscarPorCliente(Long clienteId) {
        return entityManager.createQuery(
                        "SELECT l FROM ListaDeseos l " +
                                "WHERE l.cliente.id = :clienteId",
                        ListaDeseos.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }

    // Agrega un producto a una lista de deseos existente
    public void agregarItem(Long listaId, ItemListaDeseos item) {
        ejecutarEnTransaccion(() -> {
            ListaDeseos lista = buscarPorId(listaId);

            if (lista == null) {
                throw new IllegalArgumentException(
                        "La lista de deseos no existe.");
            }

            lista.agregarItem(item);
            return null;
        });
    }

    // Retira un producto de una lista de deseos
    public void eliminarItem(Long listaId, Long itemId) {
        ejecutarEnTransaccion(() -> {
            ListaDeseos lista = buscarPorId(listaId);

            if (lista == null) {
                throw new IllegalArgumentException(
                        "La lista de deseos no existe.");
            }

            ItemListaDeseos item = lista.getItems().stream()
                    .filter(i -> i.getId().equals(itemId))
                    .findFirst()
                    .orElse(null);

            if (item != null) {
                lista.eliminarItem(item);
            }

            return null;
        });
    }

    // Elimina una lista de deseos y sus elementos asociados
    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            ListaDeseos lista = buscarPorId(id);

            if (lista != null) {
                entityManager.remove(lista);
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
