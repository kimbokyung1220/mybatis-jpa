package com.example.jpa.service;

import com.example.jpa.request.NoticeRequestDto;
import com.example.jpa.response.ResponseDto;

public interface NoticeService {

    /**
     * 게시글 전체 조회
     */
    ResponseDto<Object> getNoticeAllList();
    /**
     * 게시글 등록
     */
    void createNotice(NoticeRequestDto requestDto);
    /**
     * 게시글 수정
     */
    ResponseDto<Object> modifyNotice(Long id, NoticeRequestDto requestDto);
    /**
     * 게시글 삭제
     */
    ResponseDto<Object> deleteNotice(Long id);
}
