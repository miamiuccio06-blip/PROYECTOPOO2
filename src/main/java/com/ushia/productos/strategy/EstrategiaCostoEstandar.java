package com.ushia.productos.strategy;

import com.ushia.productos.model.LoteInventario;

import java.util.List;

public class EstrategiaCostoEstandar
        implements EstrategiaValorizacionInventario {

    private final double costoEstandar;

    public EstrategiaCostoEstandar(double costoEstandar) {

        if (costoEstandar < 0) {
            throw new IllegalArgumentException(
                    "El costo estándar no puede ser negativo"
            );
        }

        this.costoEstandar = costoEstandar;
    }

    @Override
    public double calcularCostoUnitario(List<LoteInventario> lotes) {

        return costoEstandar;
    }
}
