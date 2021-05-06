package com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.repositories;

import com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.domain.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ReactivePersonRepository extends ReactiveMongoRepository<Person, String> {
    @Query("{'name': ?0}")
    Person findByName(final String name);
    // resultados
    Flux<List<Person>> findByNameOrderByLastName(String name, Pageable pageable);
}
