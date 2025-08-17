package com.uade.tpo.gimnasio.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @Column(nullable = false)
    private Integer duracionMin; // ejemplo: 60
}