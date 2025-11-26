package com.uade.tpo.gimnasio.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/news")
public class NewsController {

    @GetMapping
    public List<Map<String, Object>> obtenerNoticias() {

        return List.of(
            Map.of(
                "id", "1",
                "titulo", "Promo Black Friday",
                "descripcion", "50% off en membresías solo hoy",
                "imagenUrl", "https://raw.githubusercontent.com/guidolevy23/grupo12_backend_desarrolloApps/main/src/main/resources/vista-lateral-mujer-haciendo-ejercicio-con-pesas.jpg",
                "fecha", "2025-11-01",
                "tipo", "promo"
            ),
            Map.of(
                "id", "3",
                "titulo", "Promo Familiar - 2x1 en Membresías",
                "descripcion", "¡Entrená con tu familia! Durante este mes, pagá una membresía y tu familiar directo entrena GRATIS. Válido para socios nuevos o renovaciones.",
                "imagenUrl", "https://raw.githubusercontent.com/guidolevy23/grupo12_backend_desarrolloApps/main/src/main/resources/jessica-rockowitz-5NLCaz2wJXE-unsplash.jpg",
                "fecha", "2025-11-26",
                "tipo", "promo"
            ),
            Map.of(
                "id", "4",
                "titulo", "Evento Especial: Clase de Funcional al Aire Libre",
                "descripcion", "Acompañanos este sábado en una clase intensa de Funcional al aire libre en el Parque Las Heras. Cupos limitados, se requiere reserva previa. ¡Traé tu botella de agua y vení a entrenar con buena energía!",
                "imagenUrl", "https://raw.githubusercontent.com/guidolevy23/grupo12_backend_desarrolloApps/main/src/main/resources/anastasiya-badun-psRjCLDez90-unsplash.jpg",
                "fecha", "2025-11-30",
                "tipo", "evento"
            ),
            Map.of(
                "id", "5",
                "titulo", "Carrera 5K RitmoFit - Edición Verano",
                "descripcion", "Sumate a nuestra Carrera 5K Edición Verano. Entrada libre para socios, hidratación incluida, música en vivo y premios para los primeros tres puestos. No requiere experiencia previa.",
                "imagenUrl", "https://raw.githubusercontent.com/guidolevy23/grupo12_backend_desarrolloApps/main/src/main/resources/miguel-a-amutio-TqOFeBqnqrI-unsplash.jpg",
                "fecha", "2025-12-05",
                "tipo", "evento"
            ),
            Map.of(
                "id", "6",
                "titulo", "Nueva Sala de Musculación en Sede Palermo",
                "descripcion", "Incorporamos una sala completamente renovada con máquinas de última generación, mayor espacio de trabajo y una zona exclusiva para ejercicios funcionales. Disponible desde el lunes 2 de diciembre.",
                "imagenUrl", "https://raw.githubusercontent.com/guidolevy23/grupo12_backend_desarrolloApps/main/src/main/resources/sven-mieke-MsCgmHuirDo-unsplash.jpg",
                "fecha", "2025-11-28",
                "tipo", "novedad"
            )
        );
    }
}


