package com.uade.tpo.gimnasio.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    @NotNull
    private Sede branch; // sede del gimnasio (ej: Palermo, Belgrano)

    private Estado estado = Estado.OPEN;

    @NotBlank
    @NotNull
    private String professor;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Reserva> reservas;

    Boolean notified = false;

    public enum Estado {
        OPEN,
        CANCELLED
    }
}
