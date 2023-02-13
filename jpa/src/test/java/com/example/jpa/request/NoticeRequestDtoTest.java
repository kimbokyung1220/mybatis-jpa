package com.example.jpa.request;

import com.example.jpa.controller.NoticeController;
import com.example.jpa.domain.Notice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.validation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Valid 유효성 테스트코드
 */

@SpringBootTest
@AutoConfigureMockMvc
class NoticeRequestDtoTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    protected ObjectMapper mapper;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Test
    void validateNoticeRequestDtoTest() {
        String title = " ";
        String writer = " ";
        String content = " ";
        String password = "12";

        NoticeRequestDto requestDto = new NoticeRequestDto(title, writer, content, password);

        Set<ConstraintViolation<NoticeRequestDto>> violations = validator.validate(requestDto);

        Iterator<ConstraintViolation<NoticeRequestDto>> iterator = violations.iterator();
        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<NoticeRequestDto> next = iterator.next();
            messages.add(next.getMessage());
            System.out.println("message = " + next.getMessage());
        }
        Assertions.assertThat(messages).contains("4~8자리 숫자로만 가능합니다.", "제목을 입력해 주세요", "작성자를 입력해 주세요", "제목을 입력해 주세요");
    }

    @Test
    @DisplayName("valid 테스트")
    void validMockMvcTest() throws Exception {
        String title = null;
        String writer = null;
        String content = null;
        String password = "12";

        NoticeRequestDto requestDto = new NoticeRequestDto(title, writer, content, password);

        // given
        MvcResult result = mvc.perform(post("/jpa/notice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn()
        ;

        String message = result.getResolvedException().getMessage();

        Assertions.assertThat(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(message).contains("4~8자리 숫자로만 가능합니다.", "제목을 입력해 주세요", "작성자를 입력해 주세요", "제목을 입력해 주세요");

    }
}