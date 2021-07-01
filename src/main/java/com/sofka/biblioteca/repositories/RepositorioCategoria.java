package com.sofka.biblioteca.repositories;

import com.sofka.biblioteca.model.Categoria;
import com.sofka.biblioteca.model.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RepositorioCategoria extends MongoRepository<Categoria, String> {


}
