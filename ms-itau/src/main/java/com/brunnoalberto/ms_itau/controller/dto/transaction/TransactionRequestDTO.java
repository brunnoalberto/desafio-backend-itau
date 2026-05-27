package com.brunnoalberto.ms_itau.controller.dto.transaction;

import com.brunnoalberto.ms_itau.domain.Transaction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionRequestDTO(
        @NotNull()
        @PositiveOrZero()
        BigDecimal valor,

        @NotNull()
        @PastOrPresent()
        OffsetDateTime dataHora
    ) {

    public Transaction toDomain() {
        return new Transaction(
                valor,
                dataHora
        );
    }

    public static TransactionRequestDTO fromDomain(Transaction transaction) {
        return new TransactionRequestDTO(
                transaction.getValor(),
                transaction.getDataHora()
        );
    }

}
