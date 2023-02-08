package com.example.jpa.controller;

import com.example.jpa.request.NoticeRequestDto;
import com.example.jpa.response.ResponseDto;
import com.example.jpa.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/jpa")
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
     * 게시글 상세 조회
     */
    @PostMapping(value = "/notice/{id}")
    public ResponseDto<Object> getNoticeList(@PathVariable Long id) {
        return noticeService.getNoticeList(id);
    }

    /**
     * 게시글 등록
     */
    @PostMapping(value = "/notice")
    public ResponseDto<Object> createNotice(@Valid @RequestBody NoticeRequestDto requestDto) {
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
