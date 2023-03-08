package com.vominh.example.simplebank.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class GenericCRUDController<T, ID> {
    protected final JpaRepository<T, ID> repository;

    public GenericCRUDController(JpaRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable ID id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity all() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @PostMapping(value = "/create")
    public ResponseEntity create(@RequestBody T entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

//    @PutMapping(value = "/{id}/update")
//    public ResponseEntity update(@PathVariable ID id, @RequestBody T entity) {
//        var optional = repository.findById(id);
//        if (optional.isPresent()) {
//            repository.save(entity);
//            return ResponseEntity.status(HttpStatus.OK).body(entity);
//        } else {
//            return ResponseEntity.status(HttpStatus.OK).body("Entity not found");
//        }
//    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity delete(@PathVariable ID id) {
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
