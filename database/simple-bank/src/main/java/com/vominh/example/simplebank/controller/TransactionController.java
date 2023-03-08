package com.vominh.example.simplebank.controller;

import com.vominh.example.simplebank.request.WithDrawRequest;
import com.vominh.example.simplebank.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }


    @PostMapping(value = "/withdraw")
    public ResponseEntity create(@RequestBody WithDrawRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.withDraw(request));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransaction(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity all() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
}

