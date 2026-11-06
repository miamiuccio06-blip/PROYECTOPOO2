package com.ushia.clientes.strategy;

import com.ushia.clientes.model.Cliente;
import com.ushia.clientes.model.ItemRecomendacion;
import com.ushia.clientes.model.PreferenciaCliente;
import com.ushia.clientes.model.PreferenciaEstilo;
import com.ushia.productos.model.Producto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RecomendacionPorPreferencias implements EstrategiaRecomendacion {

    @Override
    // Genera recomendaciones considerando las preferencias del cliente
    public List<ItemRecomendacion> recomendar(
            Cliente cliente,
            List<Producto> productos) {

        List<ItemRecomendacion> recomendaciones = new ArrayList<>();

        if (cliente == null || productos == null) {
            return recomendaciones;
        }

        // Recorre las preferencias registradas del cliente
        for (PreferenciaCliente preferencia : cliente.getPreferencias()) {

            for (PreferenciaEstilo estilo : preferencia.getEstilos()) {

                // Compara el estilo preferido con el nombre del producto
                for (Producto producto : productos) {

                    if (producto.getNombre() != null &&
                            producto.getNombre().toLowerCase()
                                    .contains(estilo.getEstilo().toLowerCase())) {

                        ItemRecomendacion item = new ItemRecomendacion();

                        item.setProducto(producto);
                        item.setPuntuacion(BigDecimal.valueOf(5.0));
                        item.setMotivo("Coincide con el estilo preferido: "
                                + estilo.getEstilo());

                        recomendaciones.add(item);
                    }
                }
            }
        }

        return recomendaciones;
    }
}