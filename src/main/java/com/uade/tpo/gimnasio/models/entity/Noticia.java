package com.uade.tpo.gimnasio.models.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "noticias")
@Getter @Setter @NoArgsConstructor
public class Noticia {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private String imagenUrl;

    private Instant publicadaEn;

    private Boolean activa = true;
}
