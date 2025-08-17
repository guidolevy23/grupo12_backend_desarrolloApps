package com.uade.tpo.gimnasio.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    private String nombre;

    private String fotoUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol = Rol.SOCIO;

    public enum Rol { SOCIO, STAFF, ADMIN }
}
