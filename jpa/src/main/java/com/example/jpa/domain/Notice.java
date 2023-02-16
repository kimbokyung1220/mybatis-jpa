package com.example.jpa.domain;

import com.example.jpa.request.notice.NoticeRequestDto;
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
    private String password;

    public void update(NoticeRequestDto requestDto) {
        this.title = requestDto.getTitle() != null ? requestDto.getTitle() : this.title;
        this.writer = requestDto.getWriter() != null ? requestDto.getWriter() : this.writer;
        this.content = requestDto.getContent() != null ? requestDto.getContent() : this.content;
        this.password = requestDto.getPassword() != null ? requestDto.getPassword() : this.password;
    }

}
