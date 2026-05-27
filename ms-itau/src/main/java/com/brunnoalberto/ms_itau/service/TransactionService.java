package com.brunnoalberto.ms_itau.service;

import com.brunnoalberto.ms_itau.config.Database;
import com.brunnoalberto.ms_itau.domain.Transaction;
import com.brunnoalberto.ms_itau.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void create(Transaction transaction) {
        transactionRepository.create(transaction);
    }

    public List<Transaction> listAll() {
        return transactionRepository.listAll();
    }

    public void deleteAll() {
        transactionRepository.deleteAll();
    }

}
