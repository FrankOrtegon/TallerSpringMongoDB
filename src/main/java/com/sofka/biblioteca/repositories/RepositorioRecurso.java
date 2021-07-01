package com.sofka.biblioteca.repositories;

import com.sofka.biblioteca.model.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RepositorioRecurso extends MongoRepository<Recurso,String> {

    List<Recurso> findRecursoBycategoriaId(String categoriaId);
    List<Recurso> findRecursoBytipoRecursoId(String tipoRecursoId);
}
