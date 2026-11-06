
package com.ushia.ventas.service;

import com.ushia.ventas.model.Venta;
import com.ushia.ventas.repository.VentaRepository;

import jakarta.persistence.EntityManager;

import java.util.List;

public class ConsultaVentaService {

    private final VentaRepository ventaRepository;

    public ConsultaVentaService(EntityManager entityManager) {
        this.ventaRepository = new VentaRepository(entityManager);
    }

    // Busca una venta mediante su identificador.
    public Venta buscarPorId(Long id) {
        return ventaRepository.buscarPorId(id);
    }

    // Obtiene todas las ventas registradas.
    public List<Venta> listarTodos() {
        return ventaRepository.listarTodos();
    }
}
