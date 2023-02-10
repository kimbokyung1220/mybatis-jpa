package com.example.jpa.controller;

import com.example.jpa.request.NoticeRequestDto;
import com.example.jpa.response.NoticeResponseDto;
import com.example.jpa.response.ResponseDto;
import com.example.jpa.service.NoticeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/jpa")
@AllArgsConstructor
public class NoticeController {

    final private NoticeServiceImpl noticeService;

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
    public NoticeResponseDto getNoticeList(@PathVariable Long id) {
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
    public ResponseDto<Object> modifyNotice(@PathVariable Long id, @RequestBody @Valid NoticeRequestDto requestDto) {
        noticeService.modifyNotice(id, requestDto);
        return ResponseDto.success(getNoticeAllList(), "Notice_id [" + id + "번] " + " ===> 수정 성공");
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping(value = "/notice/{id}")
    public ResponseDto<Object> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return ResponseDto.success(getNoticeAllList(), "Notice_id [" + id + "번] " + " ===> 삭제 성공");
    }
}
