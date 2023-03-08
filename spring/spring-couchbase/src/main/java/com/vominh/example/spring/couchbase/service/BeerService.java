package com.vominh.example.spring.couchbase.service;

import com.vominh.example.spring.couchbase.data.Beer;
import com.vominh.example.spring.couchbase.repository.IBeerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService {
    private static final Logger logger = LoggerFactory.getLogger(BeerService.class);
    private final IBeerRepository repo;

    public BeerService(IBeerRepository repo) {
        this.repo = repo;
    }

    public Beer getBeer(String id) {
        var optional = repo.findById(id);
        return optional.orElse(null);

    }

    public List<Beer> findBeers(int size) {
        return repo.findAll(PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "name")));
    }

    public List<Beer> findBeers(String name) {
        return repo.findByNameContains(name);
    }

    public Beer save(Beer beer) {
        return repo.save(beer);
    }

    public void clear() {
        repo.deleteAll();
    }

    public List<Beer> findAll() {
        return (List<Beer>) repo.findAll();
    }

    public void delete(String id) {
        repo.findById(id);
    }

    public Beer update(String id, Beer beer) {
        var optional = repo.findById(id);

        if (optional.isPresent()) {
            beer.setId(id);
            repo.save(beer);
        }

        return null;
    }

    public Beer findById(String id) {
        var optional = repo.findById(id);
        return optional.orElse(null);
    }
}
