package com.sofka.biblioteca.controllers;

import com.sofka.biblioteca.dto.RecursoDTO;
import com.sofka.biblioteca.dto.RespuestaRecursoDTO;
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
    public ResponseEntity<RespuestaRecursoDTO> consultarRecurso(@PathVariable("id") String id){
        RespuestaRecursoDTO respuesta = servicioRecurso.consultarRecurso(id);
        if(respuesta != null){
            return  new ResponseEntity(respuesta, HttpStatus.OK);
        }
        return new ResponseEntity(respuesta, HttpStatus.NOT_FOUND);

    }

    @PutMapping("/prestar/{id}")
    public ResponseEntity<RespuestaRecursoDTO>prestarRecurso(@PathVariable("id") String id){
        RespuestaRecursoDTO respuesta = servicioRecurso.prestarRecurso(id);
        if(respuesta != null){
            return new ResponseEntity(respuesta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


    @PutMapping("/devolver/{id}")
    public ResponseEntity<RespuestaRecursoDTO> devolverRecurso(@PathVariable("id") String id){
        RespuestaRecursoDTO respuesta = servicioRecurso.devolverRecurso(id);
        if(respuesta != null){
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }




}
