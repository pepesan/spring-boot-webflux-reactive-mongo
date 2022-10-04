package com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "personas")
public class Person {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
}
