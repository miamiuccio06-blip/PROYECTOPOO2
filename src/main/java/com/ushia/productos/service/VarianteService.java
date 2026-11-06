package com.ushia.productos.service;

import com.ushia.productos.model.VarianteProducto;
import com.ushia.productos.repository.VarianteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier;

public class VarianteService {

    private final VarianteRepository varianteRepository;
    private final EntityManager entityManager;

    public VarianteService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.varianteRepository = new VarianteRepository(entityManager);
    }

    public VarianteProducto guardar(VarianteProducto variante) {
        return ejecutarEnTransaccion(
                () -> varianteRepository.guardar(variante)
        );
    }

    public VarianteProducto buscarPorId(Long id) {
        return varianteRepository.buscarPorId(id);
    }

    public List<VarianteProducto> listarTodas() {
        return varianteRepository.listarTodos();
    }

    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            varianteRepository.eliminar(id);
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
