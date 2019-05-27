package com.cursosdedesarrollo.springbootwebfluxreactivemongo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClientConfigurer;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootWebfluxReactiveMongoApplicationTests {

    private WebTestClient webClient;
    @Before
    public void setUp() {
        this.webClient= WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:8080")
                .build();
    }


    @Test
    public void testObtenerListaPersonas() {
        byte[] resultado = webClient
                .get().uri("/api/persons")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().returnResult().getResponseBody();
        System.out.format("%s: %s%n", "La lista de personas:",  new String(resultado));
    }
}
