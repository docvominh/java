package com.vominh.example.simplebank.controller;

import com.vominh.example.simplebank.entity.AccountEntity;
import com.vominh.example.simplebank.repository.IAccountRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController extends GenericCRUDController<AccountEntity, Integer> {

    public AccountController(IAccountRepo accountRepo) {
        super(accountRepo);
    }

    @PutMapping(value = "/{id}/update")
    public ResponseEntity update(@PathVariable int id, @RequestBody AccountEntity entity) {
        var optional = repository.findById(id);
        if (optional.isPresent()) {
            var account = optional.get();
            account.setBalance(entity.getBalance());
            account.setAccountType(entity.getAccountType());
            repository.save(account);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Entity not found");
        }
    }

}
