package com.sofka.biblioteca.dto;

import java.util.List;

public class RespuestaCategoriaDTO {

    private String categoria;
    private List<RecursoDTO> recursosCategoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<RecursoDTO> getRecursosCategoria() {
        return recursosCategoria;
    }

    public void setRecursosCategoria(List<RecursoDTO> recursosCategoria) {
        this.recursosCategoria = recursosCategoria;
    }
}
