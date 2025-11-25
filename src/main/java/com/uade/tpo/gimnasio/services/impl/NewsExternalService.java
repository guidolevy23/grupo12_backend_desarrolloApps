package com.uade.tpo.gimnasio.services.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.tpo.gimnasio.dto.news.NewsDTO;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class NewsExternalService {

    public List<NewsDTO> fetchExternalNews() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getResourceAsStream("/external-news.json");

            return mapper.readValue(is, new TypeReference<List<NewsDTO>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo noticias externas", e);
        }
    }
}
