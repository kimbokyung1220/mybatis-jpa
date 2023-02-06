package com.example.jpa.controller;

import com.example.jpa.request.NoticeRequestDto;
import com.example.jpa.response.NoticeResponseDto;
import com.example.jpa.response.ResponseDto;
import com.example.jpa.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class NoticeController {

    final private NoticeService noticeService;

    /**
     * 게시글 전체 조회
     */
    @GetMapping(value = "/notice")
    public ResponseDto<Object> getNoticeAllList() {
        return noticeService.getNoticeAllList();
    }

    /**
     * 게시글 등록
     */
    @PostMapping(value = "/notice")
    public ResponseDto<Object> createNotice(@RequestBody NoticeRequestDto requestDto) {
        noticeService.createNotice(requestDto);
        return noticeService.getNoticeAllList();
    }

    /**
     * 게시글 수정
     */
    @PutMapping(value = "/notice/{id}")
    public ResponseDto<Object> modifyNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto) {
        return noticeService.modifyNotice(id, requestDto);
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping(value = "/notice/{id}")
    public ResponseDto<Object> deleteNotice(@PathVariable Long id) {
        return noticeService.deleteNotice(id);
    }
}
