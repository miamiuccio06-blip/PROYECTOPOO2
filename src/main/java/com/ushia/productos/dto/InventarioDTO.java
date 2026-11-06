package com.ushia.productos.dto;

public class InventarioDTO {

    private Long id;

    private Long varianteId;
    private String codigoSKU; //significa codigo unico dentro de un inventario
    private String productoNombre;
    private String tallaNombre;
    private String colorNombre;

    private Long ubicacionId;
    private String ubicacionNombre;

    private int stockActual;
    private int stockMinimo;

    public InventarioDTO() {
    }

    public InventarioDTO(Long id, Long varianteId, String codigoSKU,
                         String productoNombre, String tallaNombre,
                         String colorNombre, Long ubicacionId,
                         String ubicacionNombre, int stockActual,
                         int stockMinimo) {

        this.id = id;
        this.varianteId = varianteId;
        this.codigoSKU = codigoSKU;
        this.productoNombre = productoNombre;
        this.tallaNombre = tallaNombre;
        this.colorNombre = colorNombre;
        this.ubicacionId = ubicacionId;
        this.ubicacionNombre = ubicacionNombre;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVarianteId() {
        return varianteId;
    }

    public void setVarianteId(Long varianteId) {
        this.varianteId = varianteId;
    }

    public String getCodigoSKU() {
        return codigoSKU;
    }

    public void setCodigoSKU(String codigoSKU) {
        this.codigoSKU = codigoSKU;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public String getTallaNombre() {
        return tallaNombre;
    }

    public void setTallaNombre(String tallaNombre) {
        this.tallaNombre = tallaNombre;
    }

    public String getColorNombre() {
        return colorNombre;
    }

    public void setColorNombre(String colorNombre) {
        this.colorNombre = colorNombre;
    }

    public Long getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Long ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public String getUbicacionNombre() {
        return ubicacionNombre;
    }

    public void setUbicacionNombre(String ubicacionNombre) {
        this.ubicacionNombre = ubicacionNombre;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}
