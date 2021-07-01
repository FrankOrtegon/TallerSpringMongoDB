package com.sofka.biblioteca.controllers;

import com.sofka.biblioteca.dto.CategoriaDTO;
import com.sofka.biblioteca.dto.RecursoDTO;
import com.sofka.biblioteca.service.ServicioCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bibliotecacrud")
public class ControladorCRUD {
    @Autowired
    ServicioCRUD servicioCRUD;

    //  Controlador del recurso
    @GetMapping("/{id}")
    public ResponseEntity<RecursoDTO> findbyId(@PathVariable("id") String id) {
        return new ResponseEntity<>(servicioCRUD.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<RecursoDTO>> findAll() {
        return new ResponseEntity<>(servicioCRUD.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<RecursoDTO> create(@RequestBody RecursoDTO recursoDTO) {
        return new ResponseEntity<>(servicioCRUD.crear(recursoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<RecursoDTO> update(@RequestBody RecursoDTO recursoDTO) {
        if (recursoDTO.getId() != null) {
            return new ResponseEntity<>(servicioCRUD.modificar(recursoDTO), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            servicioCRUD.borrar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Controlador de categoria

    @PostMapping("/crearcategoria")
    public ResponseEntity<CategoriaDTO> crearCategoria(@RequestBody CategoriaDTO categoriaDTO){
        var respuesta = servicioCRUD.crearCategoria(categoriaDTO);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/modificarcategoria")
    public ResponseEntity<CategoriaDTO> modificarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getCategoriaId() != null) {
            return new ResponseEntity<>(servicioCRUD.modificarCategoria(categoriaDTO), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    }

