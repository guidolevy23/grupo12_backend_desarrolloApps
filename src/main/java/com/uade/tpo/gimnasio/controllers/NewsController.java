package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.news.NewsDTO;
import com.uade.tpo.gimnasio.services.impl.NewsExternalService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsExternalService newsExternalService;

    public NewsController(NewsExternalService newsExternalService) {
        this.newsExternalService = newsExternalService;
    }

    @GetMapping
    public List<NewsDTO> obtenerNoticias() {
        return newsExternalService.fetchExternalNews();
    }
}
