package com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.CupoState;

import com.uade.tpo.gimnasio.models.entity.Course;

public class CupoCerradoState implements CupoState {
    @Override
    public void reservar(Course course) {
        throw new IllegalStateException("Reservas cerradas para esta clase.");
    }

    @Override
    public void cancelar(Course course) {
        throw new IllegalStateException("No se pueden cancelar reservas, la clase está cerrada.");
    }

    @Override
    public String mostrarEstado() {
        return "Reservas cerradas";
    }
}
