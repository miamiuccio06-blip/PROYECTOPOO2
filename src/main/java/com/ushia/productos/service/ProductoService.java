package com.ushia.productos.service;

import com.ushia.productos.model.Producto;
import com.ushia.productos.repository.ProductoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Supplier; //ermite representar una operación que no recibe parámetros, pero devuelve un resultado.

public class ProductoService {

    private final ProductoRepository productoRepository;
    private final EntityManager entityManager;

    public ProductoService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.productoRepository = new ProductoRepository(entityManager);
    }

    public Producto guardar(Producto producto) {
        return ejecutarEnTransaccion(
                () -> productoRepository.guardar(producto) //l;e pasamos un lambda, no recibe parametros, pero reconoce a prodcuto como su parametro
                //se popne la oeracion guardar para que se quede como una transaccion
                //cuando ejecuta llega a operacion.get, se ejecuta lambda y luegol guardar
                //asi no se guardane rrores, se verifica primero y luiego a3
        );
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.buscarPorId(id);
    }

    public List<Producto> listarTodos() {
        return productoRepository.listarTodos(); //ver todoas los productos en  ujetsra base de datos
    }

    public void eliminar(Long id) {
        ejecutarEnTransaccion(() -> {
            productoRepository.eliminar(id);
            return null;
        });
    }

    //EJECUTA UNa operacion en la ase de dagos y si hay error los corrige
    //solo lo usa la clase, se usa cualquier tipo de valiable
    //no necesita parametros y devuelve un tipo de variable que puede cambiar
    private <T> T ejecutarEnTransaccion(Supplier<T> operacion) {

        EntityTransaction transaccion = entityManager.getTransaction();

        try {
            transaccion.begin(); //inicia la transaccion

            T resultado = operacion.get(); //ejecuta el lambda recibido como parametro

            transaccion.commit(); //confirma los cambios

            return resultado;

        } catch (RuntimeException e) { //error

            if (transaccion.isActive()) { //comprube si transaccion esta activa, proque tiene que estarloi paa revertirla
                transaccion.rollback(); //la reviete
            }
            throw e; //lanza el error porque se gestiona
        }
    }
}
