package com.sofka.biblioteca.service;

import com.sofka.biblioteca.dto.RecursoDTO;
import com.sofka.biblioteca.mappers.RecursoMapper;
import com.sofka.biblioteca.model.Recurso;
import com.sofka.biblioteca.repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ServicioRecurso {

    @Autowired
    RepositorioRecurso repositorioRecurso;
    RecursoMapper mapper = new RecursoMapper();

    private Date objDate = new Date();
    private String strDateFormat = "hh: mm a dd-MMM-aaaa";
    private SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

    public String consultarRecurso(String id){
        var recurso = repositorioRecurso.findById(id);
        if (recurso.get().getDisponible()) {
            return "Recurso disponible";
        }
        return "el recurso fue prestado en la fecha: "+ recurso.get().getFecha();
    }


    public String prestarRecurso(String id) {
        var recurso = repositorioRecurso.findById(id);
        if (recurso.get().getDisponible()) {
            recurso.get().setDisponible(false);
            recurso.get().setFecha(objSDF.format(objDate));
            repositorioRecurso.save(recurso.get());
            return "recurso obtenido";
        }
        return "el recurso no esta disponible";

    }

    public String devolverRecurso(String id) {
        var recurso = repositorioRecurso.findById(id);
        if(!recurso.get().getDisponible()){
            recurso.get().setDisponible(true);
            recurso.get().setFecha(objSDF.format(objDate));
            repositorioRecurso.save(recurso.get());
            return "recurso devuelto";
        }
        return "recurso ya esta en el inventario";

    }


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

}
