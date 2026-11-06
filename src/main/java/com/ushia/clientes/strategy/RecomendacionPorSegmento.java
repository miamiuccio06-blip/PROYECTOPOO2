package com.ushia.clientes.strategy;

import com.ushia.clientes.model.Cliente;
import com.ushia.clientes.model.ItemRecomendacion;
import com.ushia.productos.model.Producto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RecomendacionPorSegmento implements EstrategiaRecomendacion {

    @Override
    // Genera recomendaciones según el segmento al que pertenece el cliente
    public List<ItemRecomendacion> recomendar(
            Cliente cliente,
            List<Producto> productos) {

        List<ItemRecomendacion> recomendaciones = new ArrayList<>();

        if (cliente == null || productos == null ||
                cliente.getSegmento() == null) {
            return recomendaciones;
        }

        String segmento = cliente.getSegmento().getNombre();

        // Recorre los productos para evaluar su relación con el segmento
        for (Producto producto : productos) {

            ItemRecomendacion item = new ItemRecomendacion();

            item.setProducto(producto);
            item.setPuntuacion(BigDecimal.valueOf(3.0));
            item.setMotivo("Recomendación para el segmento: " + segmento);

            recomendaciones.add(item);
        }

        return recomendaciones;
    }
}