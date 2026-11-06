package com.ushia.ventas.strategy;

import com.ushia.ventas.model.Pago;

public interface EstrategiaPago { //define el comportamiento general para procesar pagos

    void procesarPago(Pago pago); //recibe un pago y ejecuta su procesamiento según la estrategia seleccionada
}