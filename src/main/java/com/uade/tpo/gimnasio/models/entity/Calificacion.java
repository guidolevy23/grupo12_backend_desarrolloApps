package com.uade.tpo.gimnasio.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "calificaciones",
       uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "course_id"}))
@Getter @Setter @NoArgsConstructor
public class Calificacion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User usuario;

    @ManyToOne(optional = false)
    private Course course;

    @Min(1) @Max(5)
    @Column(nullable = false)
    private int estrellas;

    @Size(max = 300)
    private String comentario;
}
