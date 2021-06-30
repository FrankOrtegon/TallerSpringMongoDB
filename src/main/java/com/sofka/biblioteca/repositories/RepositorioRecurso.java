package com.sofka.biblioteca.repositories;

import com.sofka.biblioteca.model.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRecurso extends MongoRepository<Recurso,String> {

}
