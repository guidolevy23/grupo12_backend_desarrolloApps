package com.uade.tpo.gimnasio.repositories;


import com.uade.tpo.gimnasio.models.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    List<Noticia> findByActivaTrueOrderByPublicadaEnDesc();
}
