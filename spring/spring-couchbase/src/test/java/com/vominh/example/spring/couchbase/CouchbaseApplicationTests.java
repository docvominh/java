package com.vominh.example.spring.couchbase;

import com.vominh.example.spring.couchbase.data.Beer;
import com.vominh.example.spring.couchbase.service.BeerService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CouchbaseApplicationTests {

    private static Logger log = LoggerFactory.getLogger(CouchbaseApplicationTests.class);

    @Autowired
    private BeerService service;

    //    @Test
    void contextLoads() {
//        service.clear();
        Beer beer = Beer.builder().name("Fanta").type("sweet").country("USA").build();
        beer = service.save(beer);
        log.info(beer.getId() + " - " + beer.getName());
    }

    @Test
    void findAll() {
        List<Beer> beers = service.findAll();
        for (Beer beer : beers) {
            log.info(beer.getId() + " - " + beer.getName());
        }
    }

}
