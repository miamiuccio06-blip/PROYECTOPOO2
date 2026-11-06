package com.ushia.productos.exception;

public class VarianteDuplicadaException extends RuntimeException { //todos los que pueden generar un error se muestran, no se pone throw acada rato

    public VarianteDuplicadaException(String mensaje) {
        super(mensaje);
    }
} //si una combinacion e talla y color ya esta registrado para ese producto
