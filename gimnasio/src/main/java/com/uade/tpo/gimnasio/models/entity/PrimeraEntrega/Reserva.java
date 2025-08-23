package com.uade.tpo.gimnasio.models.entity.PrimeraEntrega;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "reservas",
       uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id","turno_id"}))
@Getter @Setter @NoArgsConstructor
public class Reserva {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @NotNull
    private Usuario usuario;

    @ManyToOne(optional = false) @NotNull
    private Turno turno;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.CONFIRMADA;

    public enum Estado { CONFIRMADA, CANCELADA, EXPIRADA }
}
