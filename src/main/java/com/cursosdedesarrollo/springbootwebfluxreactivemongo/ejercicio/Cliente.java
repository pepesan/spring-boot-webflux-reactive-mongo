package com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejercicio;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Document(collection = "clientes")
public class Cliente {
    @Id
    public String id;

    @NotNull
    @NotBlank
    public String nombre;

    public String direccion;

    public String tlf;

    public String correo;

}
