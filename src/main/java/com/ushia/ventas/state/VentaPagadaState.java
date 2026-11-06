package com.ushia.ventas.state;

import com.ushia.ventas.model.Venta;

public class VentaPagadaState implements EstadoVentaState { //representa una venta cuyo pago ya fue confirmado

    @Override
    public void pagar(Venta venta) {
        throw new IllegalStateException(
                "La venta ya se encuentra pagada."
        ); //evita procesar nuevamente el pago
    }

    @Override
    public void anular(Venta venta) {
        throw new IllegalStateException(
                "No se puede anular directamente una venta pagada."
        ); //las ventas pagadas deben seguir el proceso de devolucion
    }
}
