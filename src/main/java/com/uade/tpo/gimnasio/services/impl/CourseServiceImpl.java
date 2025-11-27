package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.models.entity.Course;
import com.uade.tpo.gimnasio.repositories.CourseRepository;
import com.uade.tpo.gimnasio.services.CourseService;
import com.uade.tpo.gimnasio.services.NotificationService;
import com.uade.tpo.gimnasio.services.ReservaService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Service
public class CourseServiceImpl implements CourseService {


    CourseRepository courseRepository;
    NotificationService notificationService;
    ReservaService reservaService;

    public CourseServiceImpl(CourseRepository courseRepository, NotificationService notificationService, ReservaService reservaService) {
        this.courseRepository = courseRepository;
        this.notificationService = notificationService;
        this.reservaService = reservaService;
    }

    @Override
    public Course cancel(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        course.setEstado(Course.Estado.CANCELLED);
        course.getReservas().forEach(reserva -> {
            reservaService.cancelarReserva(reserva);
            String body = String.format(
                    "Lamentamos informarte que tu clase %s, programada para el %s a las %s en %s, ha sido cancelada. " +
                            "Si ya abonaste la clase, el crédito será automáticamente devuelto a tu cuenta. " +
                            "Podés volver a reservar otra clase cuando quieras desde la app.",
                    course.getName(),
                    course.getStartsAt().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)),
                    course.getStartsAt().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)),
                    course.getBranch().getNombre()
            );
            notificationService.createNotification(reserva.getUsuario(),  "Tu clase ha sido cancelada", body);
        });
        return courseRepository.save(course);
    }
}
