package com.example.jpa.request;

import com.sun.istack.NotNull;

public class ChartRequestDto {
    @NotNull
    private Long id;
    @NotNull
    private String basicDate; //날짜
    @NotNull
    private int impCnt; //노출수
    @NotNull
    private int clickCnt; //클릭수
    @NotNull
    private double convCnt; //전환율(클릭수 / 노출수)
    @NotNull
    private int sellCost; //판매금액
    @NotNull
    private int adSpend; //광고비
}
