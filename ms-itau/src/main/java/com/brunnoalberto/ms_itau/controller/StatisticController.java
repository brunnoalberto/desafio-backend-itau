package com.brunnoalberto.ms_itau.controller;

import com.brunnoalberto.ms_itau.controller.dto.statistic.StatisticResponseDTO;
import com.brunnoalberto.ms_itau.service.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping()
    public ResponseEntity<StatisticResponseDTO> generateStatistics() {
        return ResponseEntity.status(HttpStatus.OK).body(statisticService.generateStatistics());
    }

}
