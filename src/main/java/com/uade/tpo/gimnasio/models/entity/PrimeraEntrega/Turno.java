package com.uade.tpo.gimnasio.models.entity.PrimeraEntrega;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "turnos")
@Getter @Setter @NoArgsConstructor
public class Turno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @NotNull
    private Clase clase;

    @NotNull
    @Column(nullable = false)
    private Instant inicio;

    @NotNull
    @Column(nullable = false)
    private Instant fin;

    @Column(nullable = false)
    private Integer cupoTotal;

    @Column(nullable = false)
    private Integer cupoDisponible;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.ACTIVO;

    public enum Estado { ACTIVO, CANCELADO }
}