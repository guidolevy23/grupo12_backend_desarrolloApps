package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
