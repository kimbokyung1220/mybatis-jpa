package com.example.jpa.service.notice;

import com.example.jpa.request.notice.NoticeRequestDto;
import com.example.jpa.response.notice.NoticeResponseDto;
import com.example.jpa.response.CustomResponseDto;

public interface NoticeService {

    /**
     * 게시글 전체 조회
     */
    CustomResponseDto<Object> getNoticeAllList();
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
