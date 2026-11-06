package com.ushia.productos.observer;

import java.util.ArrayList;
import java.util.List;

//administra observadores y envia notis

public class GestorSuscriptoresStock {

    private final List<ObservadorStock> observadores; //con final observadore sismepre hace referencia a la misma lista

    public GestorSuscriptoresStock() {
        this.observadores = new ArrayList<>(); //inicialia la lista
    }

    public void agregarObservador(ObservadorStock observador) {

        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void eliminarObservador(ObservadorStock observador) {

        observadores.remove(observador);
    }

    public void notificarObservadores(Long idProducto, int stockActual) {

        for (ObservadorStock observador : observadores) {

            observador.actualizar(idProducto, stockActual); //cada clase que implementa observador puede actualizarse

        }
    }
}
