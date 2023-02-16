package com.example.jpa.controller.chart;

import com.example.jpa.response.chart.ChartResponseDto;
import com.example.jpa.response.CustomResponseDto;
import com.example.jpa.service.chart.ChartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ChartController {
    private final ChartService chartService;

    @GetMapping(value = "/grid")
    public CustomResponseDto<List<ChartResponseDto>> getGridAllList() {
        return chartService.getGridAllList();
    }

}
