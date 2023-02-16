package com.example.jpa.service.chart;

import com.example.jpa.domain.Chart;
import com.example.jpa.repository.ChartRepository;
import com.example.jpa.response.chart.ChartResponseDto;
import com.example.jpa.response.CustomResponseDto;
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
    public CustomResponseDto<List<ChartResponseDto>> getGridAllList() {

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

        return CustomResponseDto.success(chartResponseDtoList, "grid 조회");
    }

}
