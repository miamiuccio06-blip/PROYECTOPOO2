package com.ushia.clientes.strategy;

import com.ushia.clientes.model.Cliente;
import com.ushia.clientes.model.ItemRecomendacion;
import com.ushia.productos.model.Producto;

import java.util.List;

// Define el comportamiento común de todas las estrategias de recomendación
public interface EstrategiaRecomendacion {

    // Genera recomendaciones de productos para un cliente
    List<ItemRecomendacion> recomendar(
            Cliente cliente,
            List<Producto> productos
    );
}