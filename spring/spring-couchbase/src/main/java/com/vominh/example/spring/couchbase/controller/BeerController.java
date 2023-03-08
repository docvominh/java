package com.vominh.example.spring.couchbase.controller;

import com.vominh.example.spring.couchbase.data.Beer;
import com.vominh.example.spring.couchbase.service.BeerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beer")
@CrossOrigin()
public class BeerController {
    private static final Logger log = LoggerFactory.getLogger(BeerController.class);

    private final BeerService service;

    public BeerController(BeerService service) {
        this.service = service;
    }

    @GetMapping("")
    public String home() {
        return "fuckyou";
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable String id) {
        Beer app = service.findById(id);
        if (app == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(app);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Beer app) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(app));
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable String id, @RequestBody Beer app) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, app));
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/finds")
    public ResponseEntity find(int size) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findBeers(size));
    }

    @GetMapping("/find")
    public ResponseEntity find(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findBeers(name));
    }
}
