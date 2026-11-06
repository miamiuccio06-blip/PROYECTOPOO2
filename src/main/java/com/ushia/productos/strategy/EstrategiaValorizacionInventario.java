package com.ushia.productos.strategy;

import com.ushia.productos.model.LoteInventario;
import java.util.List;

public interface EstrategiaValorizacionInventario {

    double calcularCostoUnitario(List<LoteInventario> lotes);
}
