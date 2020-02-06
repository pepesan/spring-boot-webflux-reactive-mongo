package com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejercicio;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends ReactiveMongoRepository<Cliente,String> {
}
