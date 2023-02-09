package com.example.jpa.repository;

import com.example.jpa.domain.Notice;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class NoticeRepositoryTest {
    @Autowired
    NoticeRepository noticeRepository;

    @After
    public void cleanup() {
        noticeRepository.deleteAll();
    }

    @Test
    public void 게시글_전체목록_불러오기() {
        String title = "테스트 게시글";
        String writer = "테스트 작성자";
        String content = "테스트 내용";
        String password = "테스트 비번";

        noticeRepository.save(Notice.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .password(password)
                .build());

        List<Notice> noticeList = noticeRepository.findAll();

        Notice notice = noticeList.get(0);
       // assert(notice.getTitle()).isEqualTo
    }

}