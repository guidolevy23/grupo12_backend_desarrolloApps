package com.uade.tpo.gimnasio.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "asistencias")
@Getter @Setter @NoArgsConstructor
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) 
    @NotNull
    private User usuario;

    @ManyToOne(optional = true)
    @JoinColumn(name = "turno_id", nullable = true)
    private Turno turno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    @NotNull
    private Course course;  // 🔄 antes: Clase clase

    @Column(nullable = false)
    private Instant checkInAt;
}
