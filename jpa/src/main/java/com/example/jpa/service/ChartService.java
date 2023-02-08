package com.example.jpa.service;

import com.example.jpa.response.ChartResponseDto;
import com.example.jpa.response.ResponseDto;

import java.io.IOException;
import java.util.List;

public interface ChartService {

    ResponseDto<List<ChartResponseDto>> getGridAllList();

}
