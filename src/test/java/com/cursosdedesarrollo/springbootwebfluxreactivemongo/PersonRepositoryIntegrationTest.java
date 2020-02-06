package com.cursosdedesarrollo.springbootwebfluxreactivemongo;

import com.cursosdedesarrollo.springbootwebfluxreactivemongo.ejemplo.repositories.PersonRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

// TODO: corregir test de integración
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryIntegrationTest{
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    PersonRepository personRepository;
    /*
    @Test
    public void it_should_save_user() {
        Person person= new Person() ;
        person.setName("Pepe");
        person = entityManager.persistAndFlush(person);
        final String id = person.getId();
        Mono<Person> persisted=personRepository.findById(person.getId());
        Assertions.assertThat(persisted).isNotNull();
        persisted.subscribe(persona ->{
            Assertions.assertThat(persona).isNotNull();
            Assertions.assertThat(persona.getId()).isEqualTo(id);
            entityManager.remove(persona);
        });
    }

     */
}