package com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.CupoState;

import com.uade.tpo.gimnasio.models.entity.Course;

public class CupoDisponiblesState implements CupoState {
    @Override
    public void reservar(Course course) {
        if (course.getCuposDisponibles() > 0) {
            course.setCuposDisponibles(course.getCuposDisponibles() - 1);
            if (course.getCuposDisponibles() == 0) {
                course.setEstadoCupo(Course.EstadoCupo.LLENO);
            }
        }
    }

    @Override
    public void cancelar(Course course) {
        if (course.getCuposDisponibles() < course.getCuposTotales()) {
            course.setCuposDisponibles(course.getCuposDisponibles() + 1);
        }
    }

    @Override
    public String mostrarEstado() {
        return "Cupos disponibles";
    }
}
