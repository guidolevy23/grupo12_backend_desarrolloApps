package com.uade.tpo.gimnasio.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {

    @GetMapping
    public List<Map<String, Object>> obtenerNoticias() {

        String BASE_URL = "http://localhost:8080/api/images/";

        return List.of(
            Map.of(
                "id", "1",
                "titulo", "Promo Black Friday",
                "descripcion", "50% off en membresías solo hoy",
                "imagenUrl", BASE_URL + "imagen1.jpg",
                "fecha", "2025-11-01",
                "tipo", "promo"
            ),
            Map.of(
                "id", "2",
                "titulo", "Nueva Sala de Musculación",
                "descripcion", "Equipamiento nuevo, más espacio.",
                "imagenUrl", BASE_URL + "imagen2.jpg",
                "fecha", "2025-11-15",
                "tipo", "novedad"
            ),
            Map.of(
                "id", "3",
                "titulo", "Funcional Outdoor",
                "descripcion", "Entrenamiento en Parque Las Heras.",
                "imagenUrl", BASE_URL + "imagen3.jpg",
                "fecha", "2025-11-26",
                "tipo", "evento"
            ),
            Map.of(
                "id", "4",
                "titulo", "Carrera 5K",
                "descripcion", "Libre para socios.",
                "imagenUrl", BASE_URL + "imagen4.jpg",
                "fecha", "2025-12-05",
                "tipo", "evento"
            ),
            Map.of(
                "id", "5",
                "titulo", "Promo Familiar 2x1",
                "descripcion", "Entrená con un familiar durante noviembre.",
                "imagenUrl", BASE_URL + "imagen5.jpg",
                "fecha", "2025-11-28",
                "tipo", "promo"
            )
        );
    }
}
