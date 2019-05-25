package com.cursosdedesarrollo.springbootwebfluxreactivemongo.repositories;

import com.cursosdedesarrollo.springbootwebfluxreactivemongo.domain.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
}
