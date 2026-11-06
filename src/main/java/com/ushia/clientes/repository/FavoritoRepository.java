package com.ushia.clientes.repository;

import com.ushia.clientes.model.FavoritoCliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class FavoritoRepository {

    private final EntityManager entityManager;

    // Recibe el EntityManager para trabajar con la base de datos.
    public FavoritoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Guarda un favorito nuevo o actualiza uno existente.
    public FavoritoCliente guardar(FavoritoCliente favorito) {

        if (favorito.getId() == null) {
            entityManager.persist(favorito);
            return favorito;
        }

        return entityManager.merge(favorito);
    }

    // Busca un favorito mediante su identificador.
    public FavoritoCliente buscarPorId(Long id) {
        return entityManager.find(FavoritoCliente.class, id);
    }

    // Obtiene todos los favoritos registrados.
    public List<FavoritoCliente> listarTodos() {

        return entityManager.createQuery(
                "SELECT f FROM FavoritoCliente f",
                FavoritoCliente.class
        ).getResultList();
    }

    // Obtiene los productos favoritos de un cliente.
    public List<FavoritoCliente> buscarPorCliente(Long clienteId) {

        return entityManager.createQuery(
                "SELECT f FROM FavoritoCliente f " +
                        "WHERE f.cliente.id = :clienteId",
                FavoritoCliente.class
        ).setParameter("clienteId", clienteId).getResultList();
    }

    // Comprueba si un cliente ya tiene un producto como favorito.
    public boolean existePorClienteYProducto(Long clienteId, Long productoId) {

        Long cantidad = entityManager.createQuery(
                        "SELECT COUNT(f) FROM FavoritoCliente f " +
                                "WHERE f.cliente.id = :clienteId " +
                                "AND f.producto.id = :productoId",
                        Long.class
                )
                .setParameter("clienteId", clienteId)
                .setParameter("productoId", productoId)
                .getSingleResult();

        return cantidad > 0;
    }

    // Elimina un favorito mediante su identificador.
    public void eliminar(Long id) {

        FavoritoCliente favorito = buscarPorId(id);

        if (favorito != null) {
            entityManager.remove(favorito);
        }
    }
}
