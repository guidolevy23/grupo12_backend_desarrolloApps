package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {
    // último OTP generado y no usado (para validar)
    Optional<OtpCode> findTopByEmailAndUsadoFalseOrderByIdDesc(String email);
}