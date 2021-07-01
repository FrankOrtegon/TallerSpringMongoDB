package com.sofka.biblioteca.service;

import com.sofka.biblioteca.dto.CategoriaDTO;
import com.sofka.biblioteca.dto.RecursoDTO;
import com.sofka.biblioteca.mappers.CategoriaMapper;
import com.sofka.biblioteca.mappers.RecursoMapper;
import com.sofka.biblioteca.model.Categoria;
import com.sofka.biblioteca.model.Recurso;
import com.sofka.biblioteca.repositories.RepositorioCategoria;
import com.sofka.biblioteca.repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCRUD {

    @Autowired
    RepositorioRecurso repositorioRecurso;
    @Autowired
    RepositorioCategoria repositorioCategoria;

    RecursoMapper mapper = new RecursoMapper();
    CategoriaMapper mapperCategoria = new CategoriaMapper();

    public List<RecursoDTO> obtenerTodos() {
        List<Recurso> recursos = (List<Recurso>) repositorioRecurso.findAll();
        return mapper.fromCollectionList(recursos);
    }

    public RecursoDTO obtenerPorId(String id) {
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromModel(recurso);
    }


    public RecursoDTO crear(RecursoDTO recursoDTO) {
        Recurso recurso = mapper.fromDTO(recursoDTO);
        return mapper.fromModel(repositorioRecurso.save(recurso));
    }

    public RecursoDTO modificar(RecursoDTO recursoDTO) {
        Recurso recurso = mapper.fromDTO(recursoDTO);
        repositorioRecurso.findById(recurso.getId()).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromModel(repositorioRecurso.save(recurso));
    }

    public void borrar(String id) {
        repositorioRecurso.deleteById(id);
    }

    //Servicios CRUD de categoria

    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO){
        var categoria = repositorioCategoria.save(mapperCategoria.fromDTO(categoriaDTO));
        return mapperCategoria.fromModel(categoria);
    }

    public CategoriaDTO modificarCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = mapperCategoria.fromDTO(categoriaDTO);
        repositorioCategoria.findById(categoria.getCategoriaId()).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        return mapperCategoria.fromModel(repositorioCategoria.save(categoria));
    }

    public void eliminarCategoria(String id){
        repositorioCategoria.deleteById(id);
    }

    //Servicios CRUD de TipoRecurso


}
