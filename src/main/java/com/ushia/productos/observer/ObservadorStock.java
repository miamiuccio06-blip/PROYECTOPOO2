package com.ushia.productos.observer;

//define el metodo actualizar
public interface ObservadorStock {

    void actualizar(Long idProducto, int stockActual); //lo que hace un observador cuandxo se recibe una noti

}
