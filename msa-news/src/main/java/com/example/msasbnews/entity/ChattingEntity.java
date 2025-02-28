package com.example.msasbnews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

/**
 * 더미데이터
 INSERT INTO chat_message (user_id, message, send_at) VALUES (1, 'Hello!', '2024-02-11 10:00:00');
 INSERT INTO chat_message (user_id, message, send_at) VALUES (NULL, 'Nice to meet you~', '2024-02-11 10:01:00');
 INSERT INTO chat_message (user_id, message, send_at) VALUES (2, 'The weather is nice today', '2024-02-11 10:02:00');
 INSERT INTO chat_message (user_id, message, send_at) VALUES (NULL, 'Yes, it is really nice weather!', '2024-02-11 10:03:00');
 INSERT INTO chat_message (user_id, message, send_at) VALUES (1, 'Have a great day everyone~', '2024-02-11 10:04:00');
 */

@Entity
@Table(name="Chat_Message")
@Data
@NoArgsConstructor
@ToString
public class ChattingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column
    private String nickName ;

    @Column(nullable = false)
    private String message;

    private LocalDateTime sendAt;

    @Builder
    public ChattingEntity(String nickName, String message, LocalDateTime sendAt) {
        this.nickName = nickName;
        this.message = message;
        this.sendAt = sendAt;
    }
}

