package com.brunnoalberto.ms_itau.config;

import com.brunnoalberto.ms_itau.domain.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class Database {

    public final HashMap<UUID, Transaction> inMemoryDatabase;

    public Database() {
        this.inMemoryDatabase = new HashMap<>();
    }

}
