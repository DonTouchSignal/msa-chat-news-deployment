package com.example.msasbnews.service;

import com.example.msasbnews.dto.ChattingDto;
import com.example.msasbnews.entity.ChattingEntity;
import com.example.msasbnews.repository.ChattingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChattingService {

    @Autowired
    private ChattingRepository chattingRepository;

    public List<ChattingDto> allChatting(){
        //JPA를 통해서 데이터 획득
        List<ChattingEntity> chat = chattingRepository.findAll();
        return chat.stream()
                .map(c -> ChattingDto.builder()
                        .nickName(c.getNickName())
                        .guest(c.getNickName() == null)
                        .message(c.getMessage())
                        .sendAt(c.getSendAt())
                        .build())
                .collect(Collectors.toList());
    }

    public ChattingDto saveMessage(ChattingDto dto) {
        ChattingEntity entity = ChattingEntity.builder()
                .nickName(dto.getNickName())
                .message(dto.getMessage())
                .sendAt(LocalDateTime.now())
                .build();

        chattingRepository.save(entity);
        return dto;
    }

}