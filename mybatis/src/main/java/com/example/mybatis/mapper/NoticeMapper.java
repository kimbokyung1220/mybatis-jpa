package com.example.mybatis.mapper;

import com.example.mybatis.model.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    /**
     * 게시글 전체조회
     */
    List<NoticeVO> getNoticeAllList();
    /**
     * 게시글 상세조회
     */
    NoticeVO getNoticeList(Long id);
    /**
     * 게시글 등록
     */
    void createNotice(NoticeVO vo);

    /**
     * 게시글 수정
     */
    void modifyNotice(NoticeVO vo);

    /**
     * 게시글 삭제
     */
    void deleteNotice(Long id);
}
