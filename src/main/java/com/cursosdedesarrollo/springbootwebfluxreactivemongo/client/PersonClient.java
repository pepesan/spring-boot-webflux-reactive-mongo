package com.cursosdedesarrollo.springbootwebfluxreactivemongo.client;

import com.cursosdedesarrollo.springbootwebfluxreactivemongo.domain.Person;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonClient {

    public static void main(String[] args) {
        WebClient client = WebClient.create("http://localhost:8080");

        Flux<Person> personFlux = client.get()
                .uri("/api/persons")
                .retrieve()
                .bodyToFlux(Person.class);

        personFlux.subscribe(System.out::println);


        Mono<Person> personMono = client.get()
                .uri("/api/persons/{id}", "5ce88569c311de26d9af0cdb")
                .retrieve()
                .bodyToMono(Person.class);

        personMono.subscribe(System.out::println);
    }
}
