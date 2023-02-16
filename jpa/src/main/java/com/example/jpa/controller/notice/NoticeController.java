package com.example.jpa.controller.notice;

import com.example.jpa.request.notice.NoticeRequestDto;
import com.example.jpa.response.notice.NoticeResponseDto;
import com.example.jpa.response.CustomResponseDto;
import com.example.jpa.service.notice.NoticeServiceImpl;
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
    public CustomResponseDto<Object> getNoticeAllList() {
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
    public CustomResponseDto<Object> createNotice(@Valid @RequestBody NoticeRequestDto requestDto) {
        noticeService.createNotice(requestDto);
        return noticeService.getNoticeAllList();
    }

    /**
     * 게시글 수정
     */
    @PutMapping(value = "/notice/{id}")
    public CustomResponseDto<Object> modifyNotice(@PathVariable Long id, @RequestBody @Valid NoticeRequestDto requestDto) {
        noticeService.modifyNotice(id, requestDto);
        return CustomResponseDto.success(getNoticeAllList(), "Notice_id [" + id + "번] " + " ===> 수정 성공");
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping(value = "/notice/{id}")
    public CustomResponseDto<Object> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return CustomResponseDto.success(getNoticeAllList(), "Notice_id [" + id + "번] " + " ===> 삭제 성공");
    }
}
