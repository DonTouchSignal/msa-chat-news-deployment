package com.example.msasbnews.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

//네이버 뉴스 api
@Data
public class NewsDto {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<NewsItem> items;

    @Data
    public static class NewsItem {
        private String title;
        @JsonProperty("originallink")
        private String originallink;
        private String link;
        private String description;
        private String pubDate;
    }
}
