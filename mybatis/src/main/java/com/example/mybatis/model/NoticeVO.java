package com.example.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeVO {

    private Long id;

    private String title;

    private String writer;

    private String content;

    private int password;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
