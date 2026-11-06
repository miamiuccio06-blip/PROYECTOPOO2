package com.ushia.clientes.strategy;

import com.ushia.clientes.model.Cliente;
import com.ushia.clientes.model.HistorialCompra;
import com.ushia.clientes.model.ItemRecomendacion;
import com.ushia.productos.model.Producto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RecomendacionPorHistorial implements EstrategiaRecomendacion {

    @Override
    // Genera recomendaciones considerando el historial de compras
    public List<ItemRecomendacion> recomendar(
            Cliente cliente,
            List<Producto> productos) {

        List<ItemRecomendacion> recomendaciones = new ArrayList<>();

        if (cliente == null || productos == null) {
            return recomendaciones;
        }

        // Recorre las compras anteriores del cliente
        for (HistorialCompra historial : cliente.getHistorialCompras()) {

            // Obtiene los productos de la venta anterior
            if (historial.getVenta() != null &&
                    historial.getVenta().getDetalles() != null) {

                for (Producto productoComprado :
                        historial.getVenta().getDetalles().stream()
                                .map(detalle -> detalle.getProducto())
                                .toList()) {

                    // Busca productos relacionados por nombre
                    for (Producto producto : productos) {

                        if (producto.getId() != null &&
                                productoComprado.getId() != null &&
                                !producto.getId().equals(productoComprado.getId()) &&
                                producto.getNombre() != null &&
                                productoComprado.getNombre() != null &&
                                producto.getNombre().equalsIgnoreCase(
                                        productoComprado.getNombre())) {

                            ItemRecomendacion item = new ItemRecomendacion();

                            item.setProducto(producto);
                            item.setPuntuacion(BigDecimal.valueOf(4.5));
                            item.setMotivo("Relacionado con compras anteriores");

                            recomendaciones.add(item);
                        }
                    }
                }
            }
        }

        return recomendaciones;
    }
}