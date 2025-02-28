package com.example.msasbnews.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ChattingDto {
    private String nickName;
    private boolean guest;
    private String message;
    private LocalDateTime sendAt;

    @Builder
    public ChattingDto(String nickName, boolean guest, String message, LocalDateTime sendAt) {
        this.nickName = nickName;
        this.guest = guest;
        this.message = message;
        this.sendAt = sendAt;
    }
}
