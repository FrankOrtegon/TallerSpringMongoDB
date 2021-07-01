package com.sofka.biblioteca.repositories;

import com.sofka.biblioteca.model.TipoRecurso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioTipoRecurso extends MongoRepository<TipoRecurso, String> {

}
