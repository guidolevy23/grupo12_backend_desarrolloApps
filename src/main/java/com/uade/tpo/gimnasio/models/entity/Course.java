package com.uade.tpo.gimnasio.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;


 @NotNull
    private String branch; // sede del gimnasio (ej: Palermo, Belgrano)

    @NotBlank
    @NotNull
    private String professor;

    private LocalDateTime startsAt;

    private LocalDateTime endsAt;
}
