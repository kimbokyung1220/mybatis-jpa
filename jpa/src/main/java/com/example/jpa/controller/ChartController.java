package com.example.jpa.controller;

import com.example.jpa.response.ChartResponseDto;
import com.example.jpa.response.ResponseDto;
import com.example.jpa.service.ChartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ChartController {
    private final ChartService chartService;

    @GetMapping(value = "/grid")
    public ResponseDto<List<ChartResponseDto>> getGridAllList() {
        return chartService.getGridAllList();
    }
}
