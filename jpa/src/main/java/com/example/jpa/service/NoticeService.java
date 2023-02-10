package com.example.jpa.service;

import com.example.jpa.domain.Notice;
import com.example.jpa.request.NoticeRequestDto;
import com.example.jpa.response.NoticeResponseDto;
import com.example.jpa.response.ResponseDto;

public interface NoticeService {

    /**
     * 게시글 전체 조회
     */
    ResponseDto<Object> getNoticeAllList();
    /**
     * 게시글 상세조회
     */
    NoticeResponseDto getNoticeList(Long id);
    /**
     * 게시글 등록
     *
     * @return
     */
    NoticeResponseDto createNotice(NoticeRequestDto requestDto);
    /**
     * 게시글 수정
     */
    NoticeResponseDto modifyNotice(Long id, NoticeRequestDto requestDto);
    /**
     * 게시글 삭제
     */
    Long deleteNotice(Long id);
}
