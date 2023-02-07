package com.example.mybatis.service;

import com.example.mybatis.mapper.NoticeMapper;
import com.example.mybatis.model.NoticeVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService{
    final private NoticeMapper noticeMapper;

    /**
     * 게시글 전체조회
     */
    @Override
    public List<NoticeVO> getNoticeAllList() {
        return noticeMapper.getNoticeAllList();
    }

    /**
     * 게시글 상세조회
     */
    @Override
    public NoticeVO getNoticeList(Long id) {
        return noticeMapper.getNoticeList(id);
    }

    /**
     * 게시글 등록
     */
    @Override
    public void createNotice(NoticeVO vo) {
        noticeMapper.createNotice(vo);
    }

    /**
     * 게시글 수정
     */
    @Override
    public void modifyNotice(NoticeVO vo) {
        noticeMapper.modifyNotice(vo);
    }

    /**
     * 게시글 삭제
     */
    @Override
    public void deleteNotice(Long id) {
        noticeMapper.deleteNotice(id);

    }
}
