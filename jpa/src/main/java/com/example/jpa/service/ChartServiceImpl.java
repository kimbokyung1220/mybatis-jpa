package com.example.jpa.service;

import com.example.jpa.domain.Chart;
import com.example.jpa.repository.ChartRepository;
import com.example.jpa.response.ChartResponseDto;
import com.example.jpa.response.NoticeResponseDto;
import com.example.jpa.response.ResponseDto;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ChartServiceImpl implements ChartService {

    private final ChartRepository chartRepository;
    @Override
    public ResponseDto<List<ChartResponseDto>> getGridAllList() {

        List<Chart> chartList = chartRepository.findAllByOrderByIdDesc();
        List<ChartResponseDto> chartResponseDtoList = new ArrayList<>();

        for(Chart chart : chartList) {
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

        return ResponseDto.success(chartResponseDtoList,"grid 조회");
    }
}
