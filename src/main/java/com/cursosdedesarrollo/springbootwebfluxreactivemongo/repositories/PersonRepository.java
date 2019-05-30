package com.cursosdedesarrollo.springbootwebfluxreactivemongo.repositories;

import com.cursosdedesarrollo.springbootwebfluxreactivemongo.domain.Person;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
    @Query("{'name': ?0}")
    Person findByName(final String name);
}
