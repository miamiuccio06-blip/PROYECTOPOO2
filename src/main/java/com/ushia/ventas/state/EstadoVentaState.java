package com.ushia.ventas.state;

import com.ushia.ventas.model.Venta;

public interface EstadoVentaState { //define los comportamientos permitidos segun el estado de la venta

    void pagar(Venta venta);

    void anular(Venta venta);
}
