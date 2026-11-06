package com.ushia.ventas.exception;

public class VentaNoEncontradaException extends RuntimeException { //todos los que pueden generar un error se muestran, no se pone throw a cada rato

    public VentaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
} //si se intenta consultar o modificar una venta que no existe