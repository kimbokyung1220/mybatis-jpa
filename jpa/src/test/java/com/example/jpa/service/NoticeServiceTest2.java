package com.example.jpa.service;

import com.example.jpa.domain.Notice;
import com.example.jpa.exception.CustomException;
import com.example.jpa.repository.NoticeRepository;
import com.example.jpa.response.NoticeResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.jpa.exception.ErrorCode.NOT_FOUND_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


/**
 * Notice 서비스 테스트 코드
 * 상세조회   성공 테스트
 * 상세조회   실패 테스트 - 해당아이디가 존재하지 않음
 * 등록     성공 테스트
 * 등록     실패 테스트
 * 수정     성공 테스트
 * 수정     실패 테스트
 * 삭제     성공 테스트
 * 삭제     실패 테스트
 */
@SpringBootTest
class NoticeServiceTest2 {

    @InjectMocks // Mock 객체가 주입될 클래스
    private NoticeServiceImpl noticeService;

    @Mock // 가짜 객체
    private NoticeRepository noticeRepository;

    @AfterEach
    void cleanData() {
        System.out.println("cleanData");
        noticeRepository.deleteAll();
    }

    @DisplayName("게시글 상세조회 성공 테스트")
    @Test
    void 게시글_상세조회_테스트() {
        Notice notice = saveData();
        Long id = notice.getId();

        given(noticeRepository.findById(id)).willReturn(Optional.ofNullable(notice));
        // when
        NoticeResponseDto selectData = noticeService.getNoticeList(notice.getId());
        // then
        Assertions.assertThat(selectData.getId()).isEqualTo(id);
    }
    @DisplayName("게시글이 존재하지 않을경우")
    @Test
    void 게시글이_존재하지_않을경우() {
        Long id = 123L;

        given(noticeRepository.findById(anyLong())).willReturn(Optional.empty());

        NoSuchElementException noSuchElementException = assertThrows(NoSuchElementException.class, ()-> noticeService.deleteNotice(id));

        assertEquals(NOT_FOUND_ID.getMessage(), noSuchElementException.getMessage());
    }
    @Transactional
    @Rollback(value = false)
    Notice saveData() {

        Notice notice = Notice.builder()
                .title("제목입니다.")
                .writer("영희")
                .content("내용입니다.")
                .password("1234")
                .build();
        noticeRepository.save(notice);

        return Notice.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .writer(notice.getWriter())
                .content(notice.getContent())
                .password(notice.getPassword())
                .build();
    }

    @Transactional
    @Rollback(value = false)
    void saveDataList(int data) {
        Notice notice = null;

        for (int i = 0; i < data; i++) {
            notice = Notice.builder()
                    .title("제목입니다." + i)
                    .writer("작성자" + i)
                    .content("내용입니다." + i)
                    .password("1234" + i)
                    .build();
            noticeRepository.save(notice);
        }
    }
}