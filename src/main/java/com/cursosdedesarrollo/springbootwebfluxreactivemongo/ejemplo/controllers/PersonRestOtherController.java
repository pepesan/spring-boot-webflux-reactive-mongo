package com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.controllers;

import com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.domain.Person;
import com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.repositories.ReactivePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/persons")
public class PersonRestOtherController {
    @Autowired
    private ReactivePersonRepository reactiveMongoRepository;


    @PostMapping
    private Mono<ResponseEntity<Person>> addPerson(@Valid @RequestBody Person person) {
        return reactiveMongoRepository.save(person)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @GetMapping("/{id}")
    public Mono<Person> getPersonById(@PathVariable(value = "id") String id) {
        return reactiveMongoRepository.findById(id);
        /*
    public Mono<ResponseEntity<Person>> getPersonById(@PathVariable(value = "id") String id) {
        return personRepository.findById(id)
                .map(person -> ResponseEntity.ok(person))
                .defaultIfEmpty(ResponseEntity.notFound().build());

        */
    }


    @PutMapping("/{id}")
    public Mono<ResponseEntity<Person>> updatePersonById(@PathVariable(value = "id") String id,
                                                   @Valid @RequestBody Person person) {
        return reactiveMongoRepository.findById(id)
                .flatMap(existingPerson -> {
                    existingPerson.setName(person.getName());
                    return reactiveMongoRepository.save(existingPerson);
                })
                .map(updatedPerson -> new ResponseEntity<>(updatedPerson, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Person>> deletePerson(@PathVariable(value = "id") String id) {

        return reactiveMongoRepository.findById(id)
                .flatMap(person ->
                        reactiveMongoRepository.delete(person)
                                .then(
                                        Mono.just(
                                                new ResponseEntity<Person>(person, HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Se mandan las personas desde eventos mandados desde el servidor (Server Sent Events -SSE)
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> streamAllPersons() {
        return reactiveMongoRepository.findAll();
    }


}
