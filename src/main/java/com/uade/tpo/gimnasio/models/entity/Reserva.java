package com.uade.tpo.gimnasio.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(
    name = "reservas",
    uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "course_id"})
)
@Getter @Setter @NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull
    private User usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    @NotNull
    private Course course;

    @OneToOne
    @JoinColumn(name = "asistencia_id")
    private Asistencia asistencia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.CONFIRMADA;
    
    @Column(nullable = false)
    private boolean checkedIn = false;

    public enum Estado {
        CONFIRMADA,
        CANCELADA,
        EXPIRADA
    }
}
