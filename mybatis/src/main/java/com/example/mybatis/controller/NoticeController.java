package com.example.mybatis.controller;

import com.example.mybatis.model.NoticeVO;
import com.example.mybatis.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/mybatis")
public class NoticeController {
    final private NoticeService noticeService;

    /**
     * 게시글 전체조회
     */
    @GetMapping(value = "/notice")
    public List<NoticeVO> getNoticeAllList() {
        return noticeService.getNoticeAllList();
    }
    /**
     * 게시글 전체조회
     */
    @PostMapping(value = "/notice/{id}")
    public NoticeVO getNoticeList(@PathVariable Long id) {
        return noticeService.getNoticeList(id);
    }
    /**
     * 게시글 등록
     */
    @PostMapping(value = "/notice")
    public List<NoticeVO> createNotice(@RequestBody NoticeVO vo) {
        noticeService.createNotice(vo);
        return noticeService.getNoticeAllList();
    }
    /**
     * 게시글 수정
     */
    @PutMapping(value = "/notice")
    public List<NoticeVO> modifyNotice(@RequestBody NoticeVO vo) {
        noticeService.modifyNotice(vo);
        return noticeService.getNoticeAllList();
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping(value = "/notice/{id}")
    public List<NoticeVO> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return noticeService.getNoticeAllList();
    }
}
