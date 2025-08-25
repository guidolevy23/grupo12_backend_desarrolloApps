package com.uade.tpo.gimnasio.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "sedes")
@Getter @Setter @NoArgsConstructor
public class Sede {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false)
    private String nombre;

    @NotBlank @Column(nullable = false)
    private String direccion;

    private Double lat;
    private Double lng;
}