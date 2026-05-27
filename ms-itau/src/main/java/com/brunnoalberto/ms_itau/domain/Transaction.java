package com.brunnoalberto.ms_itau.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transaction {

    public BigDecimal valor;
    public OffsetDateTime dataHora;

    public Transaction() {}

    public Transaction(BigDecimal valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
