package com.brunnoalberto.ms_itau.controller.dto.transaction;

import com.brunnoalberto.ms_itau.domain.Transaction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionRequestDTO(
        @NotNull(message = "O campo 'valor' não pode ser vazio.")
        @PositiveOrZero(message = "O campo 'valor' tem que ser igual ou maior que 0.")
        BigDecimal valor,

        @NotNull(message = "O campo 'dataHora' não pode ser vazio.")
        @PastOrPresent(message = "O campo 'datahora' não pode ser futuro.")
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
