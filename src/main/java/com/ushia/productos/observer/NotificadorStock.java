package com.ushia.productos.observer;

//implementa la interfaz y recibe notis
public class NotificadorStock implements ObservadorStock {

    @Override
    public void actualizar(Long idProducto, int stockActual) {

        System.out.println(
                "Notificación: El producto con ID " +
                        idProducto + " tiene actualmente " +
                        stockActual + " unidades."
        ); //notifica la info e un producto asi se sabe si algo cambia y se actualiza
    }
}
