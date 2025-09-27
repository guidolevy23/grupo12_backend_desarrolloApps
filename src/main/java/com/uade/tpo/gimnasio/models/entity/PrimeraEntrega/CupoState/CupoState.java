package com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.CupoState;

import com.uade.tpo.gimnasio.models.entity.Course;

public interface CupoState {
    void reservar(Course course);
    void cancelar(Course course);
    String mostrarEstado();
}
