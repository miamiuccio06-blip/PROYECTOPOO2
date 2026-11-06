package com.ushia.productos.factory;

import com.ushia.productos.model.MovimientoInventario;
import com.ushia.productos.model.TipoMovimiento;

import java.time.LocalDateTime;

public class MovimientoInventarioFactory {

    public MovimientoInventario crearMovimiento(
            TipoMovimiento tipo,
            LocalDateTime fecha,
            String observacion) {

        return new MovimientoInventario(
                null,
                tipo,
                fecha,
                observacion
        );
    }
}
