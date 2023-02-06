package com.example.jpa.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeResponseDto {
    private String title;
    private String writer;
    private String content;
    private int password;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
