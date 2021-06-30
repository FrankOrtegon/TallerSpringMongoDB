package com.sofka.biblioteca.controllers;

import com.sofka.biblioteca.dto.RecursoDTO;
import com.sofka.biblioteca.repositories.RepositorioRecurso;
import com.sofka.biblioteca.service.ServicioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class ControladorRecurso {
    @Autowired
    ServicioRecurso servicioRecurso;

    @GetMapping("/consultar/{id}")
    public ResponseEntity<String> consultarRecurso(@PathVariable String id){
        var respuesta = servicioRecurso.consultarRecurso(id);
        if(respuesta != null){
            return  new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);

    }

    @PutMapping("/prestar/{id}")
    public ResponseEntity<String>prestarRecurso(@PathVariable String id){
        var respuesta = servicioRecurso.prestarRecurso(id);
        if(respuesta != null){
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<String> devolverRecurso(@PathVariable String id){
        var respuesta = servicioRecurso.devolverRecurso(id);
        if(respuesta != null){
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoDTO> findbyId(@PathVariable("id") String id) {
        return new ResponseEntity<>(servicioRecurso.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<RecursoDTO>> findAll() {
        return new ResponseEntity<>(servicioRecurso.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<RecursoDTO> create(@RequestBody RecursoDTO recursoDTO) {
        return new ResponseEntity<>(servicioRecurso.crear(recursoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<RecursoDTO> update(@RequestBody RecursoDTO recursoDTO) {
        if (recursoDTO.getId() != null) {
            return new ResponseEntity<>(servicioRecurso.modificar(recursoDTO), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            servicioRecurso.borrar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
