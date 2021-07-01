package com.sofka.biblioteca.mappers;

import com.sofka.biblioteca.dto.TipoRecursoDTO;
import com.sofka.biblioteca.model.TipoRecurso;

import java.util.List;
import java.util.stream.Collectors;

public class TipoRecursoMapper {

    public TipoRecurso fromDTO(TipoRecursoDTO tipoRecursoDTO){
        TipoRecurso tipoRecurso = new TipoRecurso();
        tipoRecurso.setTipoRecursoId(tipoRecursoDTO.getTipoRecursoId());
        tipoRecurso.setNombreTipoRecurso(tipoRecursoDTO.getNombreTipoRecurso());
        return tipoRecurso;
    }

    public TipoRecursoDTO fromModel(TipoRecurso tipoRecurso){
        TipoRecursoDTO tipoRecursoDTO = new TipoRecursoDTO();
        tipoRecursoDTO.setTipoRecursoId(tipoRecurso.getTipoRecursoId());
        tipoRecursoDTO.setNombreTipoRecurso(tipoRecurso.getNombreTipoRecurso());
        return tipoRecursoDTO;
    }

    public List<TipoRecursoDTO> listaTipoRecurso(List<TipoRecurso> listaTipoRecurso){
        if(listIsNull(listaTipoRecurso)){
            return null;
        }
        return listaTipoRecurso.stream().map(tr-> fromModel(tr)).collect(Collectors.toList());
    }

    private Boolean listIsNull(List<TipoRecurso> list){
        return list == null;
    }
}
