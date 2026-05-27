package com.brunnoalberto.ms_itau.repository;

import com.brunnoalberto.ms_itau.config.Database;
import com.brunnoalberto.ms_itau.domain.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TransactionRepository {

    private final Database database;

    public TransactionRepository(Database database) {
        this.database = database;
    }

    public void create(Transaction transaction) {
        database.inMemoryDatabase.put(UUID.randomUUID(), transaction);
        //database.put(UUID.randomUUID(), transaction);
    }

    public List<Transaction> listAll() {
        return new ArrayList<>(database.inMemoryDatabase.values());
    }

    public void deleteAll() {
        database.inMemoryDatabase.clear();
    }

}
