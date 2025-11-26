package com.uade.tpo.gimnasio.services.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.tpo.gimnasio.dto.news.NewsDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class NewsExternalService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    private final String EXTERNAL_NEWS_URL =
            "https://raw.githubusercontent.com/roccomoresi/ritmo-news/refs/heads/main/external-news.json";

    public List<NewsDTO> fetchExternalNews() {

        try {
            // Traemos el JSON desde GitHub RAW
            String json = restTemplate.getForObject(EXTERNAL_NEWS_URL, String.class);

            // Parseamos el JSON correctamente como lista de Map
            List<Map<String, Object>> lista = mapper.readValue(
                    json,
                    new TypeReference<List<Map<String, Object>>>() {}
            );

            // Mapeamos cada item al DTO
            return lista.stream()
                    .map(this::mapToDto)
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Error leyendo noticias externas: " + e.getMessage(), e);
        }
    }

    private NewsDTO mapToDto(Map<String, Object> map) {
        return new NewsDTO(
                map.get("id").toString(),
                map.get("titulo").toString(),
                map.get("descripcion").toString(),
                map.get("imagenUrl").toString(),
                map.get("fecha").toString(),
                map.get("tipo").toString()
        );
    }
}



