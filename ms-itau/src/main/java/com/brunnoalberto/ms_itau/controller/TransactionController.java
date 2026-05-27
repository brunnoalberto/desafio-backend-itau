package com.brunnoalberto.ms_itau.controller;

import com.brunnoalberto.ms_itau.controller.dto.transaction.TransactionRequestDTO;
import com.brunnoalberto.ms_itau.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    public final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping()
    public ResponseEntity<List<TransactionRequestDTO>> listAll() {
        var response = transactionService.listAll()
                .stream()
                .map(TransactionRequestDTO::fromDomain)
                .toList();

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PostMapping()
    public ResponseEntity<Void> create(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {
        transactionService.create(transactionRequestDTO.toDomain());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAll() {
        transactionService.deleteAll();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
