package com.ushia.clientes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(unique = true)
    private String dni;

    @Column(unique = true)
    private String correo;

    private String telefono;

    private LocalDateTime fechaRegistro;

    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "segmento_id")
    private ClienteSegmento segmento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DireccionCliente> direcciones = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreferenciaCliente> preferencias = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<HistorialCompra> historialCompras = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<InteraccionCliente> interacciones = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritoCliente> favoritos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListaDeseos> listasDeseos = new ArrayList<>();

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private PerfilCliente perfil;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Recomendacion> recomendaciones = new ArrayList<>();

    public Cliente() {
    }

    // Agrega una dirección y establece su relación con el cliente
    public void agregarDireccion(DireccionCliente direccion) {
        direcciones.add(direccion);
        direccion.setCliente(this);
    }

    // Agrega una preferencia y establece su relación con el cliente
    public void agregarPreferencia(PreferenciaCliente preferencia) {
        preferencias.add(preferencia);
        preferencia.setCliente(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public ClienteSegmento getSegmento() { return segmento; }
    public void setSegmento(ClienteSegmento segmento) { this.segmento = segmento; }

    public List<DireccionCliente> getDirecciones() { return direcciones; }
    public void setDirecciones(List<DireccionCliente> direcciones) { this.direcciones = direcciones; }

    public List<PreferenciaCliente> getPreferencias() { return preferencias; }
    public void setPreferencias(List<PreferenciaCliente> preferencias) { this.preferencias = preferencias; }

    public List<HistorialCompra> getHistorialCompras() { return historialCompras; }
    public void setHistorialCompras(List<HistorialCompra> historialCompras) { this.historialCompras = historialCompras; }

    public List<InteraccionCliente> getInteracciones() { return interacciones; }
    public void setInteracciones(List<InteraccionCliente> interacciones) { this.interacciones = interacciones; }

    public List<FavoritoCliente> getFavoritos() { return favoritos; }
    public void setFavoritos(List<FavoritoCliente> favoritos) { this.favoritos = favoritos; }

    public List<ListaDeseos> getListasDeseos() { return listasDeseos; }
    public void setListasDeseos(List<ListaDeseos> listasDeseos) { this.listasDeseos = listasDeseos; }

    public PerfilCliente getPerfil() { return perfil; }
    public void setPerfil(PerfilCliente perfil) {
        this.perfil = perfil;
        if (perfil != null) {
            perfil.setCliente(this);
        }
    }

    public List<Recomendacion> getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(List<Recomendacion> recomendaciones) { this.recomendaciones = recomendaciones; }
}