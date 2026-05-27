package com.brunnoalberto.ms_itau.service;

import com.brunnoalberto.ms_itau.controller.dto.statistic.StatisticResponseDTO;
import com.brunnoalberto.ms_itau.domain.Transaction;
import com.brunnoalberto.ms_itau.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

@Service
public class StatisticService {

    private final TransactionRepository transactionRepository;

    public StatisticService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public StatisticResponseDTO generateStatistics() {
        var now = OffsetDateTime.now();
        var past60Seconds = now.minusSeconds(60);

        var processedList = transactionRepository.listAll().stream()
                .filter(t -> t.getDataHora().isAfter(past60Seconds) && t.getDataHora().isBefore(now))
                .toList();

        int count = processedList.size();

        if (count == 0) {
            return new StatisticResponseDTO(0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal min = processedList.get(0).getValor();
        BigDecimal max = processedList.get(0).getValor();

        for (Transaction t : processedList) {
            BigDecimal val = t.getValor();
            sum = sum.add(val);
            if (val.compareTo(min) < 0) min = val;
            if (val.compareTo(max) > 0) max = val;
        }

        BigDecimal avg = sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);

        return new StatisticResponseDTO(count, sum, avg, min, max);
    }

}
