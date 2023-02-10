package com.example.jpa.controller;

import com.example.jpa.domain.Notice;
import com.example.jpa.repository.NoticeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(value = {NoticeController.class})
@ExtendWith(MockitoExtension.class)
//@AutoConfigureWebMvc // 이 어노테이션을 통해 MockMvc를 Builder 없이 주입받을 수 있음
class NoticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    // NoticeController에서 잡고 있는 Bean 객체에 대해 Mock 형태의 객체를 생성해줌
//    @MockBean
    NoticeRepository noticeRepository;

    @BeforeEach
    void setup() {
        Notice notice = new Notice(100L, "테스트 제목", "테스트 작성자", "테스트 내용", "1234");
        given(noticeRepository.save(notice)).willReturn(notice); // 저장
        given(noticeRepository.findById(100L)).willReturn(Optional.of(notice)); // 상세조회
        given(noticeRepository.findAllByOrderByIdDesc()).willReturn(List.of(notice)); // 전체조회
    }

//    @Test
//    @DisplayName("[TEST] Notice  id(100) - 성공")
//    void getNoticeListTest() {
//        mockMvc.perform(post("/api/notice/{id}", 100L))
//                .andExpect(status().isOk());
//    }
}