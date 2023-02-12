package com.example.jpa.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@NoArgsConstructor
public class NoticeRequestDto {
        private static final String pwPattern = "^(?=.*\\d).{4,8}$";

        @NotBlank(message = "제목을 입력해 주세요")
        private String title;
        @NotBlank(message = "작성자를 입력해 주세요")
        private String writer;
        @NotBlank(message = "내용을 입력해 주세요")
        private String content;
        @NotBlank(message = "비밀번호를 입력해 주세요")
        @Pattern(regexp = pwPattern, message="4~8자리 숫자로만 가능합니다.")
        private String password;


        public NoticeRequestDto(String title, String writer, String content, String password) {
                this.title = title;
                this.writer = writer;
                this.content = content;
                this.password = password;
        }
}
