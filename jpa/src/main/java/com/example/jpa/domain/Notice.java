package com.example.jpa.domain;

import com.example.jpa.request.NoticeRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Notice extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String writer;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int password;

    public void update(NoticeRequestDto requestDto) {
//        this.title = requestDto.getTitle() != null ? requestDto.getTitle() : this.title;
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }

}
