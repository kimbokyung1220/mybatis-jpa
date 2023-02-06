package com.example.jpa.domain;

import com.example.jpa.request.NoticeRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Notice extends Time{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "notice_id")
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
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }

}
