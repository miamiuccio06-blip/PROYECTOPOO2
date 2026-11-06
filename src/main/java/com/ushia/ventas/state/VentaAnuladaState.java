package com.ushia.ventas.state;

import com.ushia.ventas.model.Venta;

public class VentaAnuladaState implements EstadoVentaState { //representa una venta que ha sido anulada

    @Override
    public void pagar(Venta venta) {
        throw new IllegalStateException(
                "No se puede pagar una venta anulada."
        ); //impide realizar pagos de ventas anuladas
    }

    @Override
    public void anular(Venta venta) {
        throw new IllegalStateException(
                "La venta ya se encuentra anulada."
        ); //evita anular una venta mas de una vez
    }
}
