
package com.ushia.ventas.state;

import com.ushia.ventas.model.Venta;

public class VentaPendienteState implements EstadoVentaState { //representa una venta que todavia no ha sido pagada

    @Override
    public void pagar(Venta venta) {
        venta.setEstado("PAGADA"); //cambia el estado cuando se confirma el pago
    }

    @Override
    public void anular(Venta venta) {
        venta.setEstado("ANULADA"); //permite anular una venta pendiente
    }
}
