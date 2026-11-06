package com.ushia.clientes.service;

import com.ushia.clientes.model.FavoritoCliente;
import com.ushia.clientes.repository.FavoritoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class FavoritosService {

    private final FavoritoRepository favoritoRepository;
    private final EntityManager entityManager;

    public FavoritosService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.favoritoRepository = new FavoritoRepository(entityManager);
    }

    // Registra un producto como favorito de un cliente
    public FavoritoCliente guardar(FavoritoCliente favorito) {
        return ejecutarEnTransaccion(() -> {

            Long clienteId = favorito.getCliente().getId();
            Long productoId = favorito.getProducto().getId();

            // Evita registrar dos veces el mismo producto para un cliente
            if (favoritoRepository.existePorClienteYProducto(
                    clienteId, productoId)) {
                throw new IllegalArgumentException(
                        "El producto ya se encuentra en favoritos.");
            }

            return favoritoRepository.guardar(favorito);
        });
    }

    // Busca un favorito por su identificador
    public FavoritoCliente buscarPorId(Long id) {
        return favoritoRepository.buscarPorId(id);
    }

    // Obtiene todos los favoritos registrados
    public List<FavoritoCliente> listarTodos() {
        return favoritoRepository.listarTodos();
    }

    // Obtiene los productos favoritos de un cliente
    public List<FavoritoCliente> buscarPorCliente(Long clienteId) {
        return favoritoRepository.buscarPorCliente(clienteId);
    }

    // Comprueba si un producto ya es favorito de un cliente
    public boolean existePorClienteYProducto(
            Long clienteId, Long productoId) {
        return favoritoRepository.existePorClienteYProducto(
                clienteId, productoId);
    }

    // Elimina un producto de favoritos
    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            favoritoRepository.eliminar(id);
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
