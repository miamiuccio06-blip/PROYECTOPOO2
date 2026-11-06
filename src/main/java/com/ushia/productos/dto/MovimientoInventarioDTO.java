
package com.ushia.productos.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MovimientoInventarioDTO {

    private Long id;
    private String tipoMovimiento;
    private LocalDateTime fecha;
    private String observacion;

    private List<DetalleDTO> detalles;

    public MovimientoInventarioDTO() {
    }

    public MovimientoInventarioDTO(Long id, String tipoMovimiento,
                                   LocalDateTime fecha, String observacion,
                                   List<DetalleDTO> detalles) {

        this.id = id;
        this.tipoMovimiento = tipoMovimiento;
        this.fecha = fecha;
        this.observacion = observacion;
        this.detalles = detalles;
    }

    // Getters y Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTipoMovimiento() { return tipoMovimiento; }

    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public LocalDateTime getFecha() { return fecha; }

    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getObservacion() { return observacion; }

    public void setObservacion(String observacion) { this.observacion = observacion; }

    public List<DetalleDTO> getDetalles() { return detalles; }

    public void setDetalles(List<DetalleDTO> detalles) { this.detalles = detalles; }

    // Clase interna para transportar los detalles del movimiento

    public static class DetalleDTO {

        private Long varianteId;
        private String codigoSKU;
        private int cantidad;
        private double costoUnitario;

        public DetalleDTO() {
        }

        public DetalleDTO(Long varianteId, String codigoSKU,
                          int cantidad, double costoUnitario) {

            this.varianteId = varianteId;
            this.codigoSKU = codigoSKU;
            this.cantidad = cantidad;
            this.costoUnitario = costoUnitario;
        }

        public Long getVarianteId() { return varianteId; }

        public void setVarianteId(Long varianteId) { this.varianteId = varianteId; }

        public String getCodigoSKU() { return codigoSKU; }

        public void setCodigoSKU(String codigoSKU) { this.codigoSKU = codigoSKU; }

        public int getCantidad() { return cantidad; }

        public void setCantidad(int cantidad) { this.cantidad = cantidad; }

        public double getCostoUnitario() { return costoUnitario; }

        public void setCostoUnitario(double costoUnitario) { this.costoUnitario = costoUnitario; }
    }
}
