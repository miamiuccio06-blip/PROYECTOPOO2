package com.ushia.productos.dto;
//DTO son objetos creados solo con algunos atributos dxe las demas clases en el caso que no sean enceszarios todos los atributos

public class ProductoDTO {

    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precioVenta;
    private double precioCosto;

    private Long categoriaId;
    private String categoriaNombre;

    private Long temporadaId;
    private String temporadaNombre;

    private Long coleccionId;
    private String coleccionNombre;

    private String estado;

    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String codigo, String nombre,
                       String descripcion, double precioVenta,
                       double precioCosto, Long categoriaId,
                       String categoriaNombre, Long temporadaId,
                       String temporadaNombre, Long coleccionId,
                       String coleccionNombre, String estado) {

        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.precioCosto = precioCosto;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
        this.temporadaId = temporadaId;
        this.temporadaNombre = temporadaNombre;
        this.coleccionId = coleccionId;
        this.coleccionNombre = coleccionNombre;
        this.estado = estado;
    }

    // Getters y Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioVenta() { return precioVenta; }

    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public double getPrecioCosto() { return precioCosto; }

    public void setPrecioCosto(double precioCosto) { this.precioCosto = precioCosto; }

    public Long getCategoriaId() { return categoriaId; }

    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }

    public String getCategoriaNombre() { return categoriaNombre; }

    public void setCategoriaNombre(String categoriaNombre) { this.categoriaNombre = categoriaNombre; }

    public Long getTemporadaId() { return temporadaId; }

    public void setTemporadaId(Long temporadaId) { this.temporadaId = temporadaId; }

    public String getTemporadaNombre() { return temporadaNombre; }

    public void setTemporadaNombre(String temporadaNombre) { this.temporadaNombre = temporadaNombre; }

    public Long getColeccionId() { return coleccionId; }

    public void setColeccionId(Long coleccionId) { this.coleccionId = coleccionId; }

    public String getColeccionNombre() { return coleccionNombre; }

    public void setColeccionNombre(String coleccionNombre) { this.coleccionNombre = coleccionNombre; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
}
