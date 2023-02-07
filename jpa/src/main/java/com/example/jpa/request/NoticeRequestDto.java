package com.example.jpa.request;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class NoticeRequestDto {
        @NotNull
        private String title;
        @NotNull
        private String writer;
        @NotNull
        private String content;
        @NotNull
        private int password;
}
