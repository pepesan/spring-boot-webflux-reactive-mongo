package com.cursosdedesarrollo.springbootwebfluxreactivemongo.controllers;

import com.cursosdedesarrollo.springbootwebfluxreactivemongo.domain.Person;
import com.cursosdedesarrollo.springbootwebfluxreactivemongo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonRestController {


    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    private Flux<Person> getAllPersons() {
        return personRepository.findAll();
    //private Flux<ResponseEntity<Person>> getAllPersons() {
    /*
        return personRepository.findAll()
                .map(ResponseEntity::ok)
                //.map(persons -> ResponseEntity.ok(persons))
                .defaultIfEmpty(ResponseEntity.notFound().build());

     */
    }

    @PostMapping
    private Mono<ResponseEntity<Person>> addPerson(@Valid @RequestBody Person person) {
        return personRepository.save(person)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @GetMapping("/{id}")
    public Mono<Person> getPersonById(@PathVariable(value = "id") String id) {
        return personRepository.findById(id);
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
        return personRepository.findById(id)
                .flatMap(existingPerson -> {
                    existingPerson.setName(person.getName());
                    return personRepository.save(existingPerson);
                })
                .map(updatedPerson -> new ResponseEntity<>(updatedPerson, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Person>> deletePerson(@PathVariable(value = "id") String id) {

        return personRepository.findById(id)
                .flatMap(person ->
                        personRepository.delete(person)
                                .then(Mono.just(new ResponseEntity<Person>(person, HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Se mandan las personas desde eventos mandados desde el servidor (Server Sent Events -SSE)
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> streamAllPersons() {
        return personRepository.findAll();
    }



}
