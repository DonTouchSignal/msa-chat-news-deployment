package com.example.msasbnews.controller;

import com.example.msasbnews.dto.NewsDto;
import com.example.msasbnews.service.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/stock")
    public Mono<NewsDto> getStockNews(@RequestParam String name) {
        return newsService.searchStockNews(name);
    }

    @GetMapping("/crypto")
    public Mono<NewsDto> getCryptoNews(@RequestParam String name) {
        return newsService.searchCryptoNews(name);
    }
}
