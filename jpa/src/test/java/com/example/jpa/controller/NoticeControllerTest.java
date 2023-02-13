package com.example.jpa.controller;

import com.example.jpa.domain.Notice;
import com.example.jpa.repository.NoticeRepository;
import com.example.jpa.request.NoticeRequestDto;
import com.example.jpa.response.NoticeResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 어떤값이 주어지고(given), 무엇을 했을때(when), 어떤 값을 원한다(then)
 */

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class NoticeControllerTest {

    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    private NoticeRepository noticeRepository;

    @AfterEach
    public void cleanData() {
        noticeRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 상세조회")
    void 게시글_상세조회_테스트() throws Exception {
        // given
        // 1. 데이터를 저장
        NoticeResponseDto initData = saveData();
        Long id = initData.getId();

        // when
        mvc.perform(post("/jpa/notice/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andDo(print());

    }


    @Test
    @DisplayName("게시글 생성")
    void 게시글_생성_테스트() throws Exception {
        // given
        String title = "제목 입니다";
        String writer = "작성자 입니다";
        String content = "내용 입니다";
        String password = "1234";
        NoticeRequestDto requestDto = new NoticeRequestDto(title, writer, content, password);

        // when
        /**
         * mvc.perform() 메소드는 MockMvcRequestBuilders를 매개변수로 받아, ResultActions를 return하는 메소드
         * MockMvcRequestBuilders를 반환하는 정적 메소드로는 post(), get(), put(), delete() 등이 존재
         */
        mvc.perform(post("/jpa/notice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is(notNullValue())))
                .andDo(print());

    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void 게시글_수정_테스트() throws Exception {
        // given
        // 1. 데이터를 저장
        NoticeResponseDto initData = saveData();
        Long id = initData.getId();

        String title = "제목 수정 입니다";
        String writer = "영희";
        String content = "내용 수정 입니다";
        String password = "4322";
        NoticeRequestDto requestDto = new NoticeRequestDto(title, writer, content, password);

        // when
        ResultActions resultActions = mvc.perform(put("/jpa/notice/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()); //로그를 출력하는 등의 행동

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.data[0].id").value(id))
                .andExpect(jsonPath("$.data.data[0].writer").value("영희"));

    }

    @DisplayName("게시글 삭제")
    @Test
    void 게시글_삭제_테스트() throws Exception {
        // given
        // 1. 데이터를 저장
        NoticeResponseDto initData = saveData();
        Long id = initData.getId();

        // when
        ResultActions resultActions = mvc.perform(delete("/jpa/notice/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        // todo: repo로 id값 확인
        Optional<Notice> deleteData = noticeRepository.findById(id);

        //todo 삭제값 확인
        assertThat(deleteData).isEqualTo(Optional.empty());
    }

    @Transactional
    @Rollback(value = false)
    NoticeResponseDto saveData() {

        Notice notice = Notice.builder()
                .title("제목입니다.")
                .writer("영희")
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