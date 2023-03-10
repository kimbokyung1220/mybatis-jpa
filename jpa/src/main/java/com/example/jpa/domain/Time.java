package com.example.jpa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Time {
    @JsonFormat(timezone="Asia/Seoul")
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonFormat(timezone="Asia/Seoul")
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
