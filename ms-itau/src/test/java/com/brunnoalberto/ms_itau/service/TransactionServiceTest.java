package com.brunnoalberto.ms_itau.service;

import com.brunnoalberto.ms_itau.domain.Transaction;
import com.brunnoalberto.ms_itau.repository.TransactionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    @DisplayName("Deve salvar uma Transação valida")
    void shouldSaveTransaction_whenDataIsValid() {
        // Cenário
        Transaction transaction = new Transaction();
        transaction.setValor(BigDecimal.valueOf(159.99));
        transaction.setDataHora(OffsetDateTime.now());

        // Ação
        transactionService.create(transaction);

        // Validação
        verify(transactionRepository, times(1)).create(transaction);
    }

    @Test
    @DisplayName("Deve retornar uma lista de Transações quando houver transações salvas")
    void shouldReturnTransactionList_whenTransactionsExist() {
        // Cenário
        Transaction transaction1 = new Transaction();
        transaction1.setValor(BigDecimal.valueOf(159.99));
        transaction1.setDataHora(OffsetDateTime.now());

        Transaction transaction2 = new Transaction();
        transaction2.setValor(BigDecimal.valueOf(359.99));
        transaction2.setDataHora(OffsetDateTime.now());

        List<Transaction> fakeTransactionDb = List.of(transaction1, transaction2);

        when(transactionRepository.listAll()).thenReturn(fakeTransactionDb);

        // Ação
        List<Transaction> results = transactionRepository.listAll();

        // Validação
        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals(BigDecimal.valueOf(159.99), results.get(0).getValor());
        assertEquals(BigDecimal.valueOf(359.99), results.get(1).getValor());

        verify(transactionRepository, times(1)).listAll();
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia de Transações quando não houver transações salvas")
    void shouldReturnTransactionEmptyList_whenTransactionsNotExist() {
        // Cenário
        when(transactionRepository.listAll()).thenReturn(List.of());

        // Ação
        List<Transaction> results = transactionService.listAll();

        // Validação
        assertNotNull(results);
        assertTrue(results.isEmpty());

        verify(transactionRepository, times(1)).listAll();
    }

    @Test
    @DisplayName("Deve deletar todas as Transações salvas")
    void shouldDeleteAllTransactions_whenTransactionsExist() {
        // Cenário (não tem ação)

        // Ação
        transactionService.deleteAll();

        // Validação
        verify(transactionRepository, times(1)).deleteAll();
    }

}