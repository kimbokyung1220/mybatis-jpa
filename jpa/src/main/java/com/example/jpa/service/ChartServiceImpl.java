package com.example.jpa.service;

import com.example.jpa.domain.Chart;
import com.example.jpa.repository.ChartRepository;
import com.example.jpa.response.ChartResponseDto;
import com.example.jpa.response.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChartServiceImpl implements ChartService{


    private final ChartRepository chartRepository;

    @Transactional(readOnly = true)
    public ResponseDto<List<ChartResponseDto>> getGridAllList() {

        List<Chart> chartList = chartRepository.findAllByOrderByIdDesc();
        List<ChartResponseDto> chartResponseDtoList = new ArrayList<>();

        for (Chart chart : chartList) {
            chartResponseDtoList.add(
                    ChartResponseDto.builder()
                            .id(chart.getId())
                            .basicDate(chart.getBasicDate())
                            .impCnt(chart.getImpCnt())
                            .clickCnt(chart.getClickCnt())
                            .convCnt(chart.getConvCnt())
                            .sellCost(chart.getSellCost())
                            .adSpend(chart.getAdSpend())
                            .build()
            );
        }

        return ResponseDto.success(chartResponseDtoList, "grid 조회");
    }

}
