package com.example.jpa.service;

import com.example.jpa.response.notice.NoticeResponseDto;
import com.example.jpa.service.notice.NoticeServiceImpl;
import org.assertj.core.api.Assertions;
import com.example.jpa.domain.Notice;
import com.example.jpa.repository.NoticeRepository;
import com.example.jpa.request.notice.NoticeRequestDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 *  Notice 서비스 테스트 코드
 *  상세조회   성공 테스트
 *  상세조회   실패 테스트 - 해당아이디가 존재하지 않음
 *   등록     성공 테스트
 *   등록     실패 테스트
 *   수정     성공 테스트
 *   수정     실패 테스트
 *   삭제     성공 테스트
 *   삭제     실패 테스트
 */

@SpringBootTest
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NoticeServiceTest1 {
    @Autowired
    private NoticeServiceImpl noticeService;
    @Autowired
    private NoticeRepository noticeRepository;

    @AfterEach
    void cleanData() {
        System.out.println("cleanData");
        noticeRepository.deleteAll();;
    }


    @Test
    void getNoticeAllListTest() {
        System.out.println("getNoticeAllListTest");
    }

    @Test
    @DisplayName("게시글 상세조회 성공 테스트")
    void 게시글_상세조회_테스트() {
        System.out.println("getNoticeListTest");
        Long id = 100L;

        // given
        NoticeResponseDto init = saveData();
        Optional<Notice> initData = noticeRepository.findById(init.getId());

        // when
        NoticeResponseDto selectData = noticeService.getNoticeList(initData.get().getId());

        // then
        Assertions.assertThat(initData.get().getId()).isEqualTo(selectData.getId());
        
    }
    @Test
    @DisplayName("게시글 아이디가 없는경우 예외처리")
    void 게시글_아이디가_없는경우_예외처리() throws Exception {
        /**
         * NoSuchElementException
         */
        Long id = 150L;
//        Assertions.assertThatThrownBy(() -> noticeRepository.findById(id))
//                .isInstanceOf(NoSuchElementException.class);

        Assertions.assertThatThrownBy(() -> noticeService.deleteNotice(id))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 게시글을 찾지 못했습니다.")

        ;
    }

    @Test
    @DisplayName("게시글 등록 성공 테스트")
    void 게시글_등록_테스트() {

        //given
        String title = "제목 입니다";
        String writer = "작성자 입니다";
        String content = "내용 입니다";
        String password = "1234";
        NoticeRequestDto requestDto = new NoticeRequestDto(title, writer, content, password);

        // when
        NoticeResponseDto Response = noticeService.createNotice(requestDto);

        // then
        Long id = Response.getId();
        Optional<Notice> testResult = noticeRepository.findById(id);
        
        Assertions.assertThat(testResult.get().getTitle()).isEqualTo(title);
        Assertions.assertThat(testResult.get().getWriter()).isEqualTo(writer);
        Assertions.assertThat(testResult.get().getContent()).isEqualTo(content);
        Assertions.assertThat(testResult.get().getPassword()).isEqualTo(password);

    }

    @DisplayName("게시글 수정")
    @Test
    void 게시글_수정_테스트() throws Exception{
        System.out.println("modifyNoticeTest");

        // given
        String title = "제목 수정 입니다";
        String writer = "작성자 수정 입니다";
        String content = "내용 수정 입니다";
        String password = "4321";
        NoticeRequestDto requestDto = new NoticeRequestDto(title, writer, content, password);

        // 1. 데이터 저장
        NoticeResponseDto initData = saveData();
        // 2. 수정 전 데이터
        Optional<Notice> updateBF = noticeRepository.findById(initData.getId());
        // when
        // 3. 수정
        noticeService.modifyNotice(updateBF.get().getId(), requestDto);
        // 4. 수정 후 데이터
        Optional<Notice> updateAT = noticeRepository.findById(updateBF.get().getId());

        // then => 수정 전, 후 아이디는 같고, 데이터는 다르다
        Assertions.assertThat(updateBF.get().getId()).isEqualTo(updateAT.get().getId());
        Assertions.assertThat(updateBF.get().getTitle()).isNotEqualTo(updateAT.get().getTitle());
        Assertions.assertThat(updateBF.get().getWriter()).isNotEqualTo(updateAT.get().getWriter());
        Assertions.assertThat(updateBF.get().getContent()).isNotEqualTo(updateAT.get().getContent());
        Assertions.assertThat(updateBF.get().getPassword()).isNotEqualTo(updateAT.get().getPassword());
    }

    @DisplayName("게시글 삭제")
    @Test
    void 게시글_삭제_테스트() {
        
        // given
        // 1. 데이터를 저장
        NoticeResponseDto initData = saveData();
        // 2. 삭제 전 데이터 조회
        Optional<Notice> deleteBF = noticeRepository.findById(initData.getId());
        
        // when
        // 3. 데이터 삭제
        noticeService.deleteNotice(deleteBF.get().getId());
        Optional<Notice> deleteAT = noticeRepository.findById(initData.getId());

        //then
        Assertions.assertThat(deleteAT).isEmpty();
    }

    @Transactional
    @Rollback(value = false)
    NoticeResponseDto saveData() {

        Notice notice = Notice.builder()
                .title("제목입니다.")
                .writer("작성자")
                .content("내용입니다.")
                .password("1234")
                .build();
        noticeRepository.save(notice);

        return NoticeResponseDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .writer(notice.getWriter())
                .content(notice.getContent())
                .password(notice.getPassword())
                .build();
    }
}