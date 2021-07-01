package com.sofka.biblioteca.dto;

import java.util.Date;

public class RecursoDTO {

    private String id;
    private boolean disponible;
    private String fecha;
    private String nombreRecurso;
    private String categoriaId;
    private String tipoRecursoId;

    public RecursoDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {

        this.disponible = disponible;
    }

   public String getFecha() {

        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getTipoRecursoId() {
        return tipoRecursoId;
    }

    public void setTipoRecursoId(String tipoRecursoId) {
        this.tipoRecursoId = tipoRecursoId;
    }
}
