package com.ushia.productos.strategy;

import com.ushia.productos.model.LoteInventario;

import java.util.List;

public class EstrategiaPromedioPonderado
        implements EstrategiaValorizacionInventario {

    @Override
    public double calcularCostoUnitario(List<LoteInventario> lotes) {

        int cantidadTotal = 0;
        double costoTotal = 0;

        for (LoteInventario lote : lotes) {

            if (lote.getCantidadDisponible() > 0) {

                cantidadTotal += lote.getCantidadDisponible();

                costoTotal += lote.getCantidadDisponible()
                        * lote.getCostoUnitario();
            }
        }

        if (cantidadTotal == 0) {
            throw new IllegalArgumentException(
                    "No existen unidades disponibles para calcular el promedio"
            );
        }

        return costoTotal / cantidadTotal;
    }
}
