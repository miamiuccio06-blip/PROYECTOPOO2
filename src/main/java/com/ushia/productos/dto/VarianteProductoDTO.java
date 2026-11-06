
package com.ushia.productos.dto;

public class VarianteProductoDTO {

    private Long id;

    private Long productoId;
    private String productoNombre;

    private Long tallaId;
    private String tallaNombre;

    private Long colorId;
    private String colorNombre;

    private String codigoSKU;

    public VarianteProductoDTO() {
    }

    public VarianteProductoDTO(Long id, Long productoId,
                               String productoNombre, Long tallaId,
                               String tallaNombre, Long colorId,
                               String colorNombre, String codigoSKU) {

        this.id = id;
        this.productoId = productoId;
        this.productoNombre = productoNombre;
        this.tallaId = tallaId;
        this.tallaNombre = tallaNombre;
        this.colorId = colorId;
        this.colorNombre = colorNombre;
        this.codigoSKU = codigoSKU;
    }

    // Getters y Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getProductoId() { return productoId; }

    public void setProductoId(Long productoId) { this.productoId = productoId; }

    public String getProductoNombre() { return productoNombre; }

    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }

    public Long getTallaId() { return tallaId; }

    public void setTallaId(Long tallaId) { this.tallaId = tallaId; }

    public String getTallaNombre() { return tallaNombre; }

    public void setTallaNombre(String tallaNombre) { this.tallaNombre = tallaNombre; }

    public Long getColorId() { return colorId; }

    public void setColorId(Long colorId) { this.colorId = colorId; }

    public String getColorNombre() { return colorNombre; }

    public void setColorNombre(String colorNombre) { this.colorNombre = colorNombre; }

    public String getCodigoSKU() { return codigoSKU; }

    public void setCodigoSKU(String codigoSKU) { this.codigoSKU = codigoSKU; }
}
