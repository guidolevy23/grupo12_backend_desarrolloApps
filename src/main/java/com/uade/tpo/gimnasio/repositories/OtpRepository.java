package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.Otp;
import com.uade.tpo.gimnasio.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OtpRepository extends JpaRepository<Otp, Long>  {
    Optional<Otp> findByUser(User user);
}
