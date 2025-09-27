package com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.CupoState;

import com.uade.tpo.gimnasio.models.entity.Course;

public class CupoLlenoState implements CupoState {
    @Override
    public void reservar(Course course) {
        throw new IllegalStateException("No se pueden reservar más cupos, la clase está llena.");
    }

    @Override
    public void cancelar(Course course) {
        course.setCuposDisponibles(course.getCuposDisponibles() + 1);
        course.setEstadoCupo(Course.EstadoCupo.DISPONIBLE);
    }

    @Override
    public String mostrarEstado() {
        return "Clase llena";
    }
}

