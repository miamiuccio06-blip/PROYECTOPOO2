
package com.ushia.ventas.service;

import com.ushia.ventas.model.Venta;
import com.ushia.ventas.repository.VentaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier; // Permite representar una operación que no recibe parámetros, pero devuelve un resultado.

public class VentaService {

    private final VentaRepository ventaRepository;
    private final EntityManager entityManager;

    public VentaService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.ventaRepository = new VentaRepository(entityManager);
    }

    public Venta guardar(Venta venta) {
        return ejecutarEnTransaccion(
                () -> ventaRepository.guardar(venta)
                // Le pasamos un lambda que ejecuta el guardado de la venta.
                // La operación se realiza dentro de una transacción.
        );
    }

    public Venta buscarPorId(Long id) {
        return ventaRepository.buscarPorId(id);
    }

    public List<Venta> listarTodos() {
        return ventaRepository.listarTodos(); // Obtiene todas las ventas registradas.
    }

    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            ventaRepository.eliminar(id);
            return null;
        });
    }

    // Ejecuta una operación en la base de datos mediante una transacción.
    // Si ocurre un error, revierte los cambios realizados.
    // Es privado porque solamente lo utiliza esta clase.
    // Utiliza un tipo genérico para poder devolver diferentes tipos de resultados.

    private <T> T ejecutarEnTransaccion(Supplier<T> operacion) {

        EntityTransaction transaccion = entityManager.getTransaction();

        try {

            transaccion.begin(); // Inicia la transacción.

            T resultado = operacion.get(); // Ejecuta el lambda recibido como parámetro.

            transaccion.commit(); // Confirma los cambios en la base de datos.

            return resultado;

        } catch (RuntimeException e) {

            if (transaccion.isActive()) { // Comprueba si la transacción continúa activa.
                transaccion.rollback(); // Revierte los cambios si ocurrió un error.
            }

            throw e; // Lanza nuevamente el error para que pueda gestionarse.
        }
    }
}
