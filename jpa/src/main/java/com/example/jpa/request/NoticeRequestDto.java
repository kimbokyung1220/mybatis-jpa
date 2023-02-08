package com.example.jpa.request;


import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class NoticeRequestDto {
        private static final String pwPattern = "^(?=.*\\d).{4,8}$";

        @NotNull(message = "제목을 입력해 주세요")
        private String title;
        @NotNull(message = "작성자를 입력해 주세요")
        private String writer;
        @NotNull(message = "내용을 입력해 주세요")
        private String content;
        @NotNull(message = "비밀번호를 입력해 주세요")
        @Pattern(regexp = pwPattern, message="4~8자리 숫자로만 가능합니다.")
        private String password;
}
