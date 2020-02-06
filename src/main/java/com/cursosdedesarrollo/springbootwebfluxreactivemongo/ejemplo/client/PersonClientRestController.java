package com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.client;

import com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.domain.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/persons/client")
public class PersonClientRestController {
    private static final String API_MIME_TYPE = "application/json";
    private static final String API_BASE_URL = "http://localhost:8080";
    private static final String USER_AGENT = "Spring 5 WebClient";
    @GetMapping
    public Flux<Person> clienteListado(){
        WebClient webClient = WebClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, API_MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
        return webClient.get()
                .uri("/api/persons")
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Person.class));
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Person>> clienteBorrado(@PathVariable(value = "id") String id){
        WebClient webClient = WebClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, API_MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
        return webClient.get()
                .uri("/api/persons/"+id)
                .exchange()
                .flatMap(response -> response.toEntity(Person.class));
    }

}
