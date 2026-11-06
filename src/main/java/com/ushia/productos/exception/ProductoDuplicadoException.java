package com.ushia.productos.exception;

public class ProductoDuplicadoException extends RuntimeException {

    public ProductoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}//cuando intentamos registrar un producto con un código que ya existe.
