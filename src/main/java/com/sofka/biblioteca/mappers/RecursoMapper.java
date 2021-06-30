package com.sofka.biblioteca.mappers;


import com.sofka.biblioteca.dto.RecursoDTO;
import com.sofka.biblioteca.model.Recurso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {

    public Recurso fromDTO(RecursoDTO recursoDTO) {
        Recurso recurso = new Recurso();
        recurso.setId(recursoDTO.getId());
        recurso.setNombreRecurso(recursoDTO.getNombreRecurso());
        recurso.setFecha(recursoDTO.getFecha());
        recurso.setDisponible(recursoDTO.getDisponible());
        recurso.setCategoria(recursoDTO.getCategoria());
        recurso.setTipoRecurso(recursoDTO.getTipoRecurso());
        return recurso;
    }


    public RecursoDTO fromModel(Recurso recurso) {
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(recurso.getId());
        recursoDTO.setNombreRecurso(recurso.getNombreRecurso());
        recursoDTO.setFecha(recurso.getFecha());
        recursoDTO.setDisponible(recurso.getDisponible());
        recursoDTO.setTipoRecurso(recurso.getTipoRecurso());
        recursoDTO.setCategoria(recurso.getCategoria());
        return recursoDTO;
    }

    public List<RecursoDTO> fromCollectionList(List<Recurso> collection ) {
        if(collection == null) {
            return null;
        }
        List<RecursoDTO> list = new ArrayList<>(collection.size());
        Iterator listTrack = collection.iterator();

        while (listTrack.hasNext()) {
            Recurso recurso = (Recurso) listTrack.next();
            list.add(fromModel(recurso));
        }
        return list;
    }


}
