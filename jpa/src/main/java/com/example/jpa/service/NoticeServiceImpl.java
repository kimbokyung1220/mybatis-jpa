package com.example.jpa.service;

import com.example.jpa.domain.Notice;
import com.example.jpa.exception.CustomException;
import com.example.jpa.exception.ErrorCode;
import com.example.jpa.repository.NoticeRepository;
import com.example.jpa.request.NoticeRequestDto;
import com.example.jpa.response.NoticeResponseDto;
import com.example.jpa.response.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService{
    final private NoticeRepository noticeRepository;

    /**
     * 게시글 전체 조회
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseDto<Object> getNoticeAllList() {

        // 최신글부터 조회
        List<Notice> noticeList = noticeRepository.findAllByOrderByIdDesc();
        List<NoticeResponseDto> noticeDtoAllList = new ArrayList<>();

        for(Notice notice : noticeList) {
            noticeDtoAllList.add(
                    NoticeResponseDto.builder()
                            .id(notice.getId())
                            .title(notice.getTitle())
                            .writer(notice.getWriter())
                            .content(notice.getContent())
                            .password(notice.getPassword())
                            .createdAt(notice.getCreatedAt())
                            .modifiedAt(notice.getModifiedAt())
                            .build()
            );
        }

        return ResponseDto.success(noticeDtoAllList,"전체조회를 성공했습니다.");
    }

    /**
     * 게시글 상세조회
     */
    @Override
    public ResponseDto<Object> getNoticeList(Long id) {

        // notice가 존재하는지 여부
        Notice notice = isPresentNotice(id);
        if(notice == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_ID);
        }
        NoticeResponseDto noticeList = NoticeResponseDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .writer(notice.getWriter())
                .content(notice.getContent())
                .password(notice.getPassword())
                .createdAt(notice.getCreatedAt())
                .modifiedAt(notice.getModifiedAt())
                .build();

        return ResponseDto.success(noticeList, "상세조회");
    }

    /**
     * 게시글 등록
     */
    @Override
    @Transactional
    public void createNotice(NoticeRequestDto requestDto) {
        // controller Advice
        Notice notice = Notice.builder()
                .title(requestDto.getTitle())
                .writer(requestDto.getWriter())
                .content(requestDto.getContent())
                .password(requestDto.getPassword())
                .build();

        noticeRepository.save(notice);
    }

    /**
     * 게시글 수정
     */
    @Override
    @Transactional
    public ResponseDto<Object> modifyNotice(Long id, NoticeRequestDto requestDto) {

        // notice가 존재하는지 여부
        Notice notice = isPresentNotice(id);
        if(notice == null) {
            throw new NoSuchElementException(ErrorCode.NOT_FOUND_ID.getMessage());
        }
        notice.update(requestDto);
        // 게시글 수정
        return ResponseDto.success(getNoticeAllList(), "Notice_id [" + id + "번] " + " ===> 수정 성공");
    }

    @Override
    public ResponseDto<Object> deleteNotice(Long id) {
        
        // notice가 존재하는지 여부
        Notice notice = isPresentNotice(id);
        if(notice == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_ID);
        }
        // 게시글 삭제
        noticeRepository.deleteById(id);
        return ResponseDto.success(getNoticeAllList(),"Notice_id [" + id + "번] " + " ===> 삭제 성공");
    }


    @Transactional(readOnly = true)
    public Notice isPresentNotice(Long id) {
        Optional<Notice> notice = noticeRepository.findById(id);
        return notice.orElse(null);
    }
}
