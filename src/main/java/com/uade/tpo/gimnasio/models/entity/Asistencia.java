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

    @OneToOne(optional = false)
    @NotNull
    private Reserva reserva;  // 🔄 antes: Clase clase

    @Column(nullable = false)
    private Instant checkInAt;
}
