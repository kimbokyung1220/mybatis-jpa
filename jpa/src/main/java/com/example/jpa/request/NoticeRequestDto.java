package com.example.jpa.request;


import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
public class NoticeRequestDto {
        @NotNull
        private String title;
        @NotNull
        private String writer;
        @NotNull
        private String content;
        @Min(value=4, message="4자리 이상으로 입력해주세요.")
        private int password;
}
