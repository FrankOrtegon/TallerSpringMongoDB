package com.sofka.biblioteca.service;

import com.sofka.biblioteca.dto.RecursoDTO;
import com.sofka.biblioteca.dto.RespuestaDTO;
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

    public RespuestaDTO consultarRecurso(String id){
        var recurso = repositorioRecurso.findById(id).get();
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setDisponible(recurso.getDisponible());
        respuestaDTO.setFecha(recurso.getFecha());
        if (!recurso.getDisponible()) {
            respuestaDTO.setDescripcion("El recurso no se encuentra disponible su fecha de prestamo fue "+recurso.getFecha());
            return respuestaDTO;
        }else{
            respuestaDTO.setDescripcion("El recurso "+recurso.getNombreRecurso()+" se encuentra disponible");
            respuestaDTO.setFecha(null);
            return respuestaDTO;
        }
    }

   public RespuestaDTO prestarRecurso(String id) {
       RespuestaDTO respuestaDTO = new RespuestaDTO();
       Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el recurso"));
       if (recurso.getDisponible()) {
           recurso.setDisponible(false);
           recurso.setFecha(objSDF.format(objDate));
           repositorioRecurso.save(recurso);
           respuestaDTO.setDisponible(false);
           respuestaDTO.setFecha(objSDF.format(objDate));
           respuestaDTO.setDescripcion("Recurso obtenido");

           return respuestaDTO;
       }

       respuestaDTO.setFecha(objSDF.format(objDate));
       respuestaDTO.setDescripcion("Recurso no esta disponible");
       repositorioRecurso.save(recurso);
       return respuestaDTO;

   }

    public RespuestaDTO devolverRecurso(String id) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el recurso"));
        if(!recurso.getDisponible()){
            recurso.setDisponible(true);
            recurso.setFecha(objSDF.format(objDate));
            repositorioRecurso.save(recurso);
            respuestaDTO.setDisponible(true);
            respuestaDTO.setFecha(objSDF.format(objDate));
            respuestaDTO.setDescripcion("Recurso devuelto con exito");

            return respuestaDTO;
        }
        respuestaDTO.setFecha(objSDF.format(objDate));
        respuestaDTO.setDescripcion("El recurso ya esta en el inventario");
        return respuestaDTO;
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
