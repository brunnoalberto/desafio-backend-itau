package com.brunnoalberto.ms_itau.controller.dto.statistic;

import java.math.BigDecimal;

public record StatisticResponseDTO(
            int count,
            BigDecimal sum,
            BigDecimal avg,
            BigDecimal min,
            BigDecimal max
    ) {
}
