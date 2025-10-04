package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.models.entity.Course;
import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Reserva;
import com.uade.tpo.gimnasio.repositories.CourseRepository;
import com.uade.tpo.gimnasio.repositories.ReservaRepository;
import com.uade.tpo.gimnasio.services.ReservaService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final CourseRepository courseRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository,
                              CourseRepository courseRepository) {
        this.reservaRepository = reservaRepository;
        this.courseRepository = courseRepository;
    }

@Override
@Transactional
public Reserva crearReserva(Reserva reserva) {
    // Traemos el Course vivo de la base
    Course course = courseRepository.findById(reserva.getCourse().getId())
            .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));




    courseRepository.save(course);

    // Asociamos el Course cargado a la reserva
    reserva.setCourse(course);

    return reservaRepository.save(reserva);
}




@Override
@Transactional
public void cancelarReserva(Long id) {
    Reserva reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));

    Course course = reserva.getCourse();

  
    courseRepository.save(course);

    reserva.setEstado(Reserva.Estado.CANCELADA);
    reservaRepository.save(reserva);
}


    @Override
    public List<Reserva> listarReservasPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }


}

