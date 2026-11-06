package com.ushia.ventas.service;

import com.ushia.ventas.model.Venta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProcesadorVentaService {

    private final VentaService ventaService;
    private final CalculoTotalService calculoTotalService;
    private final EntityManager entityManager;

    public ProcesadorVentaService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.ventaService = new VentaService(entityManager);
        this.calculoTotalService = new CalculoTotalService();
    }

    // Prepara los importes y registra la venta.
    public Venta procesar(Venta venta) {

        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula.");
        }

        if (venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La venta debe tener productos.");
        }

        BigDecimal subtotal = calculoTotalService.calcularSubtotal(
                venta.getDetalles()
        );

        BigDecimal descuento = venta.getDescuentoTotal();

        BigDecimal total = calculoTotalService.calcularTotal(
                subtotal,
                descuento
        );

        venta.setSubtotal(subtotal);
        venta.setDescuentoTotal(
                descuento == null ? BigDecimal.ZERO : descuento
        );
        venta.setTotal(total);

        if (venta.getFechaHora() == null) {
            venta.setFechaHora(LocalDateTime.now());
        }

        return ejecutarEnTransaccion(
                () -> ventaService.guardar(venta)
        );
    }

    // Controla la transacción del proceso completo.
    private <T> T ejecutarEnTransaccion(
            java.util.function.Supplier<T> operacion) {

        EntityTransaction transaccion = entityManager.getTransaction();

        try {
            transaccion.begin();

            T resultado = operacion.get();

            transaccion.commit();

            return resultado;

        } catch (RuntimeException e) {

            if (transaccion.isActive()) {
                transaccion.rollback();
            }

            throw e;
        }
    }
}
