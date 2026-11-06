package com.ushia.productos.strategy;

import com.ushia.productos.model.LoteInventario;

import java.util.Comparator;
import java.util.List;

public class EstrategiaFIFO implements EstrategiaValorizacionInventario {

    @Override
    //busca el lote mas antiguo que tenga
    public double calcularCostoUnitario(List<LoteInventario> lotes){ //recibe una l8sta de loteinventario

        return lotes.stream() //Convierte nuestra lista en un flujo de elementos que podemos procesar mediante operaciones
                .filter(lote -> lote.getCantidadDisponible() > 0)
                .min(Comparator.comparing(LoteInventario::getFechaIngreso)) //busca elemento minimo, compara con la fecha ngresada a ver si es la mas antiguas
                .orElseThrow(() ->
                        new IllegalArgumentException("No existen lotes disponibles")
                )
                .getCostoUnitario();
    }
}
