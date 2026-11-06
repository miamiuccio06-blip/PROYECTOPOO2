package com.ushia.productos.strategy;

import com.ushia.productos.model.LoteInventario;

import java.util.Comparator;
import java.util.List;

public class EstrategiaLIFO implements EstrategiaValorizacionInventario {

    @Override
    public double calcularCostoUnitario(List<LoteInventario> lotes) {

        return lotes.stream()
                .filter(lote -> lote.getCantidadDisponible() > 0)
                .max(Comparator.comparing(LoteInventario::getFechaIngreso))
                .orElseThrow(() ->
                        new IllegalArgumentException("No existen lotes disponibles")
                )
                .getCostoUnitario();
    }
}
