package com.example.jpa.request;

import lombok.Getter;

@Getter
public class NoticeRequestDto {
        private String title;
        private String writer;
        private String content;
        private int password;
}
