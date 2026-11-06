package com.ushia.ventas.strategy;

import com.ushia.ventas.model.Pago;
import com.ushia.ventas.model.MetodoPago;
import com.ushia.ventas.model.EstadoPago;

import java.time.LocalDateTime;

public class PagoEfectivoStrategy implements EstrategiaPago { //implementa el procesamiento de pagos en efectivo

    @Override
    public void procesarPago(Pago pago) {

        if (pago == null) { //comprueba que exista un pago para procesar
            throw new IllegalArgumentException("El pago no puede ser nulo.");
        }

        if (pago.getMonto() == null || pago.getMonto().signum() <= 0) { //comprueba que el monto sea positivo
            throw new IllegalArgumentException("El monto del pago debe ser mayor que cero.");
        }

        pago.setMetodoPago(MetodoPago.EFECTIVO); //establece el método de pago
        pago.setEstadoPago(EstadoPago.APROBADO); //registra el pago como aprobado
        pago.setFechaPago(LocalDateTime.now()); //registra la fecha y hora del pago
    }
}