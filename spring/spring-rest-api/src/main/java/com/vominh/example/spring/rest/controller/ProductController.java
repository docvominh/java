package com.vominh.example.spring.rest.controller;

import com.vominh.example.spring.rest.entity.ProductEntity;
import com.vominh.example.spring.rest.repository.IProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final IProductRepo repo;

    public ProductController(IProductRepo repo) {
        this.repo = repo;
    }

    @GetMapping(value = "/all")
    public ResponseEntity all() {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity create(@RequestBody ProductEntity entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(entity));
    }

    @PutMapping(value = "/{id}/update")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody ProductEntity entity) {
        var optional = repo.findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            repo.save(entity);
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Entity not found");
        }
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity delete(@PathVariable Integer id) {
        repo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
