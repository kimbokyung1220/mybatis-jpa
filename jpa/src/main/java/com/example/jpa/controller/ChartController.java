package com.example.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChartController {
    @GetMapping(value = "/test")
    public String test() {
        return "aef";
    }
}
