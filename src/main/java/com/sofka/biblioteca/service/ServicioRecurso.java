package com.sofka.biblioteca.service;

import com.sofka.biblioteca.dto.RecursoDTO;
import com.sofka.biblioteca.dto.RespuestaCategoriaDTO;
import com.sofka.biblioteca.dto.RespuestaRecursoDTO;
import com.sofka.biblioteca.dto.RespuestaTipoRecursoDTO;
import com.sofka.biblioteca.mappers.RecursoMapper;
import com.sofka.biblioteca.model.Recurso;
import com.sofka.biblioteca.repositories.RepositorioCategoria;
import com.sofka.biblioteca.repositories.RepositorioRecurso;
import com.sofka.biblioteca.repositories.RepositorioTipoRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ServicioRecurso {

    @Autowired
    RepositorioRecurso repositorioRecurso;
    @Autowired
    RepositorioCategoria repositorioCategoria;
    @Autowired
    RepositorioTipoRecurso repositorioTipoRecurso;

    RecursoMapper mapper = new RecursoMapper();

    private Date objDate = new Date();
    private String strDateFormat = "hh: mm a dd-MMM-aaaa";
    private SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

    public RespuestaRecursoDTO consultarRecurso(String id){
        var recurso = repositorioRecurso.findById(id).get();
        RespuestaRecursoDTO respuestaRecursoDTO = new RespuestaRecursoDTO();
        respuestaRecursoDTO.setDisponible(recurso.getDisponible());
        respuestaRecursoDTO.setFecha(recurso.getFecha());
        if (!recurso.getDisponible()) {
            respuestaRecursoDTO.setDescripcion("El recurso no se encuentra disponible su fecha de prestamo fue "+recurso.getFecha());
            return respuestaRecursoDTO;
        }else{
            respuestaRecursoDTO.setDescripcion("El recurso "+recurso.getNombreRecurso()+" se encuentra disponible");
            respuestaRecursoDTO.setFecha(null);
            return respuestaRecursoDTO;
        }
    }

   public RespuestaRecursoDTO prestarRecurso(String id) {
       RespuestaRecursoDTO respuestaRecursoDTO = new RespuestaRecursoDTO();
       Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el recurso"));
       if (recurso.getDisponible()) {
           recurso.setDisponible(false);
           recurso.setFecha(objSDF.format(objDate));
           repositorioRecurso.save(recurso);
           respuestaRecursoDTO.setDisponible(false);
           respuestaRecursoDTO.setFecha(objSDF.format(objDate));
           respuestaRecursoDTO.setDescripcion("Recurso obtenido");

           return respuestaRecursoDTO;
       }

       respuestaRecursoDTO.setFecha(objSDF.format(objDate));
       respuestaRecursoDTO.setDescripcion("Recurso no esta disponible");
       repositorioRecurso.save(recurso);
       return respuestaRecursoDTO;

   }

    public RespuestaRecursoDTO devolverRecurso(String id) {
        RespuestaRecursoDTO respuestaRecursoDTO = new RespuestaRecursoDTO();
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el recurso"));
        if(!recurso.getDisponible()){
            recurso.setDisponible(true);
            recurso.setFecha(objSDF.format(objDate));
            repositorioRecurso.save(recurso);
            respuestaRecursoDTO.setDisponible(true);
            respuestaRecursoDTO.setFecha(objSDF.format(objDate));
            respuestaRecursoDTO.setDescripcion("Recurso devuelto con exito");

            return respuestaRecursoDTO;
        }
        respuestaRecursoDTO.setFecha(objSDF.format(objDate));
        respuestaRecursoDTO.setDescripcion("El recurso ya esta en el inventario");
        return respuestaRecursoDTO;
    }

    public RespuestaCategoriaDTO consultarPorCategoria(String categoriaId) {
        RespuestaCategoriaDTO respuestaCategorias = new RespuestaCategoriaDTO();
        var categoria = repositorioCategoria.findById(categoriaId).get();
        var list = repositorioRecurso.findRecursoBycategoriaId(categoriaId);
        respuestaCategorias.setRecursosCategoria(mapper.fromCollectionList(list));
        respuestaCategorias.setCategoria(categoria.getNombreCategoria());
        return respuestaCategorias;
    }

    public RespuestaTipoRecursoDTO consultarPorTipoRecurso(String tipoRecursoId) {
        RespuestaTipoRecursoDTO respuestaTipoRecursos = new RespuestaTipoRecursoDTO();
        var tipoRecurso = repositorioTipoRecurso.findById(tipoRecursoId).get();
        var list = repositorioRecurso.findRecursoBytipoRecursoId(tipoRecursoId);
        respuestaTipoRecursos.setListTipoRecurso(mapper.fromCollectionList(list));
        respuestaTipoRecursos.setTipoRecurso(tipoRecurso.getNombreTipoRecurso());
        return respuestaTipoRecursos;
    }


}
