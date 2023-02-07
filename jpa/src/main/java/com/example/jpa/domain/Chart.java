package com.example.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Chart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String basicDate; //날짜
    @Column(nullable = false) 
    private int impCnt; //노출수
    @Column(nullable = false)
    private int clickCnt; //클릭수
    @Column(nullable = false)
    private double convCnt; //전환율(클릭수 / 노출수)
    @Column(nullable = false)
    private int sellCost; //판매금액
    @Column(nullable = false)
    private int adSpend; //광고비

}
