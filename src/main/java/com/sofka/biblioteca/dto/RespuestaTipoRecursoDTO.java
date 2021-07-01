package com.sofka.biblioteca.dto;

import java.util.List;

public class RespuestaTipoRecursoDTO {
    private String tipoRecurso;
    private List<RecursoDTO> listTipoRecurso;

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public List<RecursoDTO> getListTipoRecurso() {
        return listTipoRecurso;
    }

    public void setListTipoRecurso(List<RecursoDTO> listTipoRecurso) {
        this.listTipoRecurso = listTipoRecurso;
    }
}
