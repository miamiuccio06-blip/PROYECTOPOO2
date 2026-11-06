package com.ushia.clientes.mapper;

import com.ushia.clientes.model.Cliente;
import com.ushia.clientes.model.Recomendacion;

import com.ushia.clientes.dto.ClienteDTO;
import com.ushia.clientes.dto.RecomendacionDTO;

public class ClienteMapper {

    // Convierte una entidad Cliente en un ClienteDTO
    public ClienteDTO toDTO(Cliente cliente) {

        ClienteDTO dto = new ClienteDTO();

        dto.setId(cliente.getId());
        dto.setNombres(cliente.getNombres());
        dto.setApellidos(cliente.getApellidos());
        dto.setDni(cliente.getDni());
        dto.setCorreo(cliente.getCorreo());
        dto.setTelefono(cliente.getTelefono());
        dto.setFechaRegistro(cliente.getFechaRegistro());
        dto.setActivo(cliente.isActivo());

        // Obtiene el ID del segmento relacionado con el cliente
        if (cliente.getSegmento() != null) {
            dto.setSegmentoId(cliente.getSegmento().getId());
        }

        return dto;
    }

    // Convierte un ClienteDTO en una entidad Cliente
    public Cliente toEntity(ClienteDTO dto) {

        Cliente cliente = new Cliente();

        cliente.setId(dto.getId());
        cliente.setNombres(dto.getNombres());
        cliente.setApellidos(dto.getApellidos());
        cliente.setDni(dto.getDni());
        cliente.setCorreo(dto.getCorreo());
        cliente.setTelefono(dto.getTelefono());
        cliente.setFechaRegistro(dto.getFechaRegistro());
        cliente.setActivo(dto.isActivo());

        // La relación con ClienteSegmento se establece posteriormente
        // mediante el servicio, utilizando segmentoId.

        return cliente;
    }

    // Convierte una entidad Recomendacion en un RecomendacionDTO
    public RecomendacionDTO toDTO(Recomendacion recomendacion) {

        RecomendacionDTO dto = new RecomendacionDTO();

        dto.setId(recomendacion.getId());
        dto.setFechaGeneracion(recomendacion.getFechaGeneracion());
        dto.setEstrategiaUtilizada(recomendacion.getEstrategiaUtilizada());
        dto.setEstado(recomendacion.getEstado());

        // Obtiene el ID del cliente asociado a la recomendación
        if (recomendacion.getCliente() != null) {
            dto.setClienteId(recomendacion.getCliente().getId());
        }

        return dto;
    }

    // Convierte un RecomendacionDTO en una entidad Recomendacion
    public Recomendacion toEntity(RecomendacionDTO dto) {

        Recomendacion recomendacion = new Recomendacion();

        recomendacion.setId(dto.getId());
        recomendacion.setFechaGeneracion(dto.getFechaGeneracion());
        recomendacion.setEstrategiaUtilizada(dto.getEstrategiaUtilizada());
        recomendacion.setEstado(dto.getEstado());

        // La relación con Cliente se establece posteriormente
        // mediante el servicio, utilizando clienteId.

        return recomendacion;
    }
}
