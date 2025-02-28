package com.example.msasbnews.controller;

import com.example.msasbnews.dto.ChattingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.example.msasbnews.service.ChattingService;
import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller  // @RestController 대신 @Controller 사용
public class ChattingController {

    private final ChattingService chattingService;
    private final SimpMessagingTemplate messagingTemplate;
    @Qualifier("userServiceWebClient")
    private final WebClient userServiceWebClient;

    @Autowired
    public ChattingController(
            ChattingService chattingService, 
            SimpMessagingTemplate messagingTemplate,
            @Qualifier("userServiceWebClient") WebClient userServiceWebClient) {
        this.chattingService = chattingService;
        this.messagingTemplate = messagingTemplate;
        this.userServiceWebClient = userServiceWebClient;
    }

    // WebSocket 메시지 처리
    @MessageMapping("/message")
    public void sendMessage(ChattingDto message, 
                          @Header(value = "Authorization", required = false) String accessToken,
                          @Header(value = "X-Auth-User", required = false) String userEmail) {
        // 로그인한 사용자인 경우
        if (accessToken != null && userEmail != null) {
            try {
                String userNickname = userServiceWebClient.get()
                    .uri("/user/nickname")
                    .header("X-Auth-User", userEmail)
                    .header("Authorization", accessToken)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
                message.setNickName(userNickname);
            } catch (Exception e) {
                // 닉네임 조회 실패 시 게스트로 처리
                message.setNickName("게스트" );
            }
        } else {
            // 비로그인 사용자는 게스트로 처리
            message.setNickName("게스트" );
        }
        
        // DB에 저장
        ChattingDto savedMessage = chattingService.saveMessage(message);
        
        // WebSocket으로 전송
        messagingTemplate.convertAndSend("/topic/messages", savedMessage);
    }

    
    @GetMapping("/chat/all")
    @ResponseBody
    public ResponseEntity<List<ChattingDto>> getChatHistory() {
        return ResponseEntity.ok(chattingService.allChatting());
    }
}
