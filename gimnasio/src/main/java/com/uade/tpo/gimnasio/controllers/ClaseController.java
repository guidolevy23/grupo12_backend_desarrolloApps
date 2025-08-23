package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.models.entity.Disciplina;
import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Clase;
import com.uade.tpo.gimnasio.models.entity.Sede;
import com.uade.tpo.gimnasio.services.ClaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    private final ClaseService claseService;

    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    // Listar todas las clases
    @GetMapping
    public List<Clase> listar() {
        return claseService.listarClases();
    }

    // Obtener clase por ID
    @GetMapping("/{id}")
    public ResponseEntity<Clase> obtener(@PathVariable Long id) {
        return claseService.obtenerClase(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Filtrar clases por sede y disciplina
    @GetMapping("/filtrar")
    public List<Clase> filtrar(@RequestParam Sede sede, @RequestParam Disciplina disciplina) {
        return claseService.filtrarClases(sede, disciplina);
    }
}

