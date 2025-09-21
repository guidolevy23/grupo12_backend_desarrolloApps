package com.uade.tpo.gimnasio.models.entity.PrimeraEntrega;

import com.uade.tpo.gimnasio.models.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "asistencias")
@Getter @Setter @NoArgsConstructor
public class Asistencia {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @NotNull
    private User usuario;

    @ManyToOne(optional = false) @NotNull
    private Turno turno;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    @Column(nullable = false)
    private Instant checkInAt;



}