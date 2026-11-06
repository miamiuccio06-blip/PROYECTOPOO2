package com.ushia.clientes.strategy;

import com.ushia.clientes.model.Cliente;
import com.ushia.clientes.model.ItemRecomendacion;
import com.ushia.clientes.model.PerfilCliente;
import com.ushia.productos.model.Producto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RecomendacionPorPresupuesto implements EstrategiaRecomendacion {

    @Override
    // Recomienda productos que se encuentren dentro del presupuesto del cliente
    public List<ItemRecomendacion> recomendar(
            Cliente cliente,
            List<Producto> productos) {

        List<ItemRecomendacion> recomendaciones = new ArrayList<>();

        if (cliente == null || productos == null) {
            return recomendaciones;
        }

        PerfilCliente perfil = cliente.getPerfil();

        if (perfil == null) {
            return recomendaciones;
        }

        BigDecimal minimo = perfil.getPresupuestoMinimo();
        BigDecimal maximo = perfil.getPresupuestoMaximo();

        if (minimo == null || maximo == null) {
            return recomendaciones;
        }

        // Evalúa el precio de cada producto frente al presupuesto
        for (Producto producto : productos) {

            BigDecimal precio = BigDecimal.valueOf(producto.getPrecioVenta());

            if (precio.compareTo(minimo) >= 0 &&
                    precio.compareTo(maximo) <= 0) {

                ItemRecomendacion item = new ItemRecomendacion();

                item.setProducto(producto);
                item.setPuntuacion(BigDecimal.valueOf(5.0));
                item.setMotivo("Producto dentro del presupuesto del cliente");

                recomendaciones.add(item);
            }
        }

        return recomendaciones;
    }
}