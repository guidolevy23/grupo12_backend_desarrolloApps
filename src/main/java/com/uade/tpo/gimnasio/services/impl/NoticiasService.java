package com.uade.tpo.gimnasio.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NoticiasService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String EXTERNAL_NEWS_URL =
        "https://raw.githubusercontent.com/roccomoresi/ritmo-news/refs/heads/main/external-news.json";

    public List<Map<String, Object>> obtenerNoticias() {
        ResponseEntity<List> resp =
            restTemplate.getForEntity(EXTERNAL_NEWS_URL, List.class);
        return resp.getBody();
    }
}

