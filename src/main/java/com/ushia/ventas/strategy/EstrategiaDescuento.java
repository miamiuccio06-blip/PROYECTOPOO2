package com.ushia.ventas.strategy;

import java.math.BigDecimal;

public interface EstrategiaDescuento { //define el comportamiento general para calcular descuentos

    BigDecimal calcularDescuento(BigDecimal subtotal); //recibe el subtotal y devuelve el monto del descuento
}
