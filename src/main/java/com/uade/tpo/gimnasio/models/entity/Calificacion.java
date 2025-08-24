package com.uade.tpo.gimnasio.models.entity;

import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Turno;
import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "calificaciones",
       uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id","turno_id"}))
@Getter @Setter @NoArgsConstructor
public class Calificacion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    private Turno turno;

    @Min(1) @Max(5)
    @Column(nullable = false)
    private int estrellas;

    @Size(max = 300)
    private String comentario;
}
