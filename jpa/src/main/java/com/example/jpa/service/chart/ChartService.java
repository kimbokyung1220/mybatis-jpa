package com.example.jpa.service.chart;

import com.example.jpa.response.chart.ChartResponseDto;
import com.example.jpa.response.CustomResponseDto;

import java.util.List;

public interface ChartService {

    CustomResponseDto<List<ChartResponseDto>> getGridAllList();

}
