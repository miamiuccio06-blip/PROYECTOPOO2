package com.ushia.productos.service;

import com.ushia.productos.model.CategoriaProducto;
import com.ushia.productos.repository.CategoriaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final EntityManager entityManager;

    public CategoriaService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.categoriaRepository = new CategoriaRepository(entityManager);
    }

    public CategoriaProducto guardar(CategoriaProducto categoria) {
        return ejecutarEnTransaccion(
                () -> categoriaRepository.guardar(categoria)
        );
    }

    public CategoriaProducto buscarPorId(Long id) {
        return categoriaRepository.buscarPorId(id);
    }

    public List<CategoriaProducto> listarTodas() {
        return categoriaRepository.listarTodos();
    }

    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            categoriaRepository.eliminar(id);
            return null;
        });
    }

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
