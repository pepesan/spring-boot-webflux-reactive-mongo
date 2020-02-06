package com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.repositories;

import com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.domain.Person;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
    @Query("{'name': ?0}")
    Person findByName(final String name);
}
