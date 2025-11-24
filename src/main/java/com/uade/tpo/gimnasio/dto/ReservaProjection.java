package com.uade.tpo.gimnasio.dto;

import com.uade.tpo.gimnasio.models.entity.Reserva;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "reservaFull", types = Reserva.class)
public interface ReservaProjection {

    Long getId();

    CourseProjection getCourse();

    Reserva.Estado getEstado();
}