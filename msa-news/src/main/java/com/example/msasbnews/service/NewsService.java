package com.example.msasbnews.service;

import com.example.msasbnews.dto.NewsDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import lombok.RequiredArgsConstructor;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class NewsService {
    @Qualifier("naverWebClient")
    private final WebClient naverWebClient;

    //주식 뉴스 검색
    public Mono<NewsDto> searchStockNews(String name) {
        return naverWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/news.json")
                        .queryParam("query", name+"주식")
                        .queryParam("display", 20)
                        .build())
                .retrieve()
                .bodyToMono(NewsDto.class);
    }

    //코인 뉴스 검색
    public Mono<NewsDto> searchCryptoNews(String name) {
        return naverWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/news.json")
                        .queryParam("query", name+"코인")
                        .queryParam("display", 20)
                        .build())
                .retrieve()
                .bodyToMono(NewsDto.class);
    }
}

