package com.example.jpa.repository;

import com.example.jpa.domain.Notice;
import com.example.jpa.request.NoticeRequestDto;
import com.example.jpa.response.NoticeResponseDto;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class NoticeRepositoryTest {
    @Autowired
    NoticeRepository noticeRepository;

    @After
    public void cleanup() {
        noticeRepository.deleteAll();
    }

    @DisplayName("[Repo] - save ")
    @Test
    public void 게시글_저장_테스트() throws Exception {
        String title = "테스트 게시글";
        String writer = "테스트 작성자";
        String content = "테스트 내용";
        String password = "1234";

        Notice save = noticeRepository.save(Notice.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .password(password)
                .build());

        Optional<Notice> result = noticeRepository.findById(save.getId());

        assertThat(result.get().getTitle()).isEqualTo(title);
        assertThat(result.get().getWriter()).isEqualTo(writer);
        assertThat(result.get().getContent()).isEqualTo(content);
        assertThat(result.get().getPassword()).isEqualTo(password);

//        assertThat(notice.getTitle()).isEqualTo("테스트 게시글");
    }

    @DisplayName("[Repo] - delete ")
    @Test
    public void 게시글_삭제_테스트() {
        
        String title = "테스트 게시글";
        String writer = "테스트 작성자";
        String content = "테스트 내용";
        String password = "1234";

        Notice save = noticeRepository.save(Notice.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .password(password)
                .build());

        Optional<Notice> result = noticeRepository.findById(save.getId());

        noticeRepository.deleteById(result.get().getId());
    }
    

}