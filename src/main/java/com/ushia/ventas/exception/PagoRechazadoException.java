package com.ushia.ventas.exception;

public class PagoRechazadoException extends RuntimeException { //todos los que pueden generar un error se muestran, no se pone throw a cada rato

    public PagoRechazadoException(String mensaje) {
        super(mensaje);
    }
} //si un pago no puede procesarse o es rechazado