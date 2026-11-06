package com.ushia.ventas.service;

import com.ushia.ventas.model.Cupon;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CuponService {

    private final EntityManager entityManager;

    public CuponService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Comprueba si el cupón puede utilizarse.
    public boolean validar(Cupon cupon, String codigo) {

        if (cupon == null) {
            return false;
        }

        if (!cupon.getCodigo().equalsIgnoreCase(codigo)) {
            return false;
        }

        if (!cupon.isActivo()) {
            return false;
        }

        if (cupon.getUsosActuales() >= cupon.getUsosMaximos()) {
            return false;
        }

        return true;
    }

    // Registra un nuevo uso del cupón.
    public Cupon registrarUso(Long id) {

        EntityTransaction transaccion = entityManager.getTransaction();

        try {
            transaccion.begin();

            Cupon cupon = entityManager.find(Cupon.class, id);

            if (cupon == null) {
                throw new IllegalArgumentException("El cupón no existe.");
            }

            if (!cupon.isActivo()
                    || cupon.getUsosActuales() >= cupon.getUsosMaximos()) {
                throw new IllegalStateException("El cupón no puede utilizarse.");
            }

            cupon.setUsosActuales(cupon.getUsosActuales() + 1);

            entityManager.merge(cupon);

            transaccion.commit();

            return cupon;

        } catch (RuntimeException e) {

            if (transaccion.isActive()) {
                transaccion.rollback();
            }

            throw e;
        }
    }

    public Cupon buscarPorId(Long id) {
        return entityManager.find(Cupon.class, id);
    }
}
