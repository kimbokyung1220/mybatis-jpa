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
    public NoticeResponseDto getNoticeList(Long id) {

        // notice가 존재하는지 여부
        Notice notice = isPresentNotice(id);
        if(notice == null) {
            throw new NoSuchElementException(ErrorCode.NOT_FOUND_ID.getMessage());
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

        return noticeList;
    }

    /**
     * 게시글 등록
     */
    @Override
    @Transactional
    public NoticeResponseDto createNotice(NoticeRequestDto requestDto) {

        // Todo: modelMapper로 변경해보기
        Notice notice = Notice.builder()
                .title(requestDto.getTitle())
                .writer(requestDto.getWriter())
                .content(requestDto.getContent())
                .password(requestDto.getPassword())
                .build();
         noticeRepository.save(notice);

        NoticeResponseDto responseDto = NoticeResponseDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .writer(notice.getWriter())
                .content(notice.getContent())
                .password(notice.getPassword())
                .createdAt(notice.getCreatedAt())
                .modifiedAt(notice.getModifiedAt())
                .build();

         return responseDto;
    }

    /**
     * 게시글 수정
     */
    @Override
    @Transactional
    public NoticeResponseDto modifyNotice(Long id, NoticeRequestDto requestDto) {

        // notice가 존재하는지 여부
        Notice notice = isPresentNotice(id);
        if(notice == null) {
            throw new NoSuchElementException(ErrorCode.NOT_FOUND_ID.getMessage());
        }
        // 게시글 수정
        notice.update(requestDto);

        NoticeResponseDto noticeResponseDto = NoticeResponseDto.builder()
                .title(notice.getTitle())
                .writer(notice.getWriter())
                .content(notice.getContent())
                .password(notice.getPassword())
                .createdAt(notice.getCreatedAt())
                .modifiedAt(notice.getModifiedAt())
                .build();
      
        return noticeResponseDto;
    }

    @Override
    public Long deleteNotice(Long id) {
        
        // notice가 존재하는지 여부
        Notice notice = isPresentNotice(id);
        if(notice == null) {
            throw new NoSuchElementException(ErrorCode.NOT_FOUND_ID.getMessage());
        }
        // 게시글 삭제
        noticeRepository.deleteById(id);

        return id;
    }


    @Transactional(readOnly = true)
    public Notice isPresentNotice(Long id) {
        Optional<Notice> notice = noticeRepository.findById(id);
        return notice.orElse(null);
    }
}
