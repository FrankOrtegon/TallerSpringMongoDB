package com.sofka.biblioteca.dto;

public class TipoRecursoDTO {

    private String tipoRecursoId;
    private String nombreTipoRecurso;


    public TipoRecursoDTO() {
    }

    public String getTipoRecursoId() {
        return tipoRecursoId;
    }

    public void setTipoRecursoId(String tipoRecursoId) {
        this.tipoRecursoId = tipoRecursoId;
    }

    public String getNombreTipoRecurso() {
        return nombreTipoRecurso;
    }

    public void setNombreTipoRecurso(String nombreTipoRecurso) {
        this.nombreTipoRecurso = nombreTipoRecurso;
    }

}
