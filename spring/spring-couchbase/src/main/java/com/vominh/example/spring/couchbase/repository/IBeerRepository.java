package com.vominh.example.spring.couchbase.repository;

import com.vominh.example.spring.couchbase.data.Beer;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

//@ViewIndexed(designDoc = "beer", viewName = "all")
public interface IBeerRepository extends CouchbaseRepository<Beer, String> {
    List<Beer> findByNameContains(String name);

    @Query("#{#n1ql.selectEntity} where #{#n1ql.filter} ")
    List<Beer> findAll(Pageable page);

    @Query("#{#n1ql.selectEntity} WHERE country = 'United States' AND #{#n1ql.filter}")
    List<Beer> findBeers();
}
