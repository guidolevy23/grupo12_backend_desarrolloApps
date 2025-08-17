package com.uade.tpo.gimnasio.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "otp_codes")
@Getter @Setter @NoArgsConstructor
public class OtpCode {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email @NotBlank
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 10)
    private String codigo;

    @Column(nullable = false)
    private Instant expiraEn;

    @Column(nullable = false)
    private boolean usado = false;

    @Column(nullable = false)
    private int intentos = 0;
}
