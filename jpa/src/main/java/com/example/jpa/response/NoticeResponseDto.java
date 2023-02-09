package com.example.jpa.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeResponseDto {
    private Long id ;
    private String title;
    private String writer;
    private String content;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
