package com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.controllers;

import com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.domain.Person;
import com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/persons")
public class PersonRestController {


    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    private Mono<Page<Person>> getAllPersons(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return Mono.just(personRepository.findAll(PageRequest.of(page,size)));
    //private Flux<ResponseEntity<Person>> getAllPersons() {
    /*
        return personRepository.findAll()
                .map(ResponseEntity::ok)
                //.map(persons -> ResponseEntity.ok(persons))
                .defaultIfEmpty(ResponseEntity.notFound().build());

     */
    }
}
