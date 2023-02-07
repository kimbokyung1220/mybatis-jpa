package com.example.jpa.repository;

import com.example.jpa.domain.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartRepository extends JpaRepository <Chart, Long> {
}
