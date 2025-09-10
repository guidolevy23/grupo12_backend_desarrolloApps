package com.uade.tpo.gimnasio.models.entity.PrimeraEntrega;

import com.uade.tpo.gimnasio.models.entity.Disciplina;
import com.uade.tpo.gimnasio.models.entity.Sede;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "clases")
@Getter @Setter @NoArgsConstructor
public class Clase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @NotNull
    private Disciplina disciplina;

    @ManyToOne(optional = false) @NotNull
    private Sede sede;

    @NotBlank @Column(nullable = false)
    private String profesor;

    private int cupo;

    private LocalDateTime fechaHora;

    @OneToMany(mappedBy = "clase")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "clase")
    private List<Asistencia>  asistencias;

    @Column(nullable = false)
    private Integer duracionMin; // ejemplo: 60
}