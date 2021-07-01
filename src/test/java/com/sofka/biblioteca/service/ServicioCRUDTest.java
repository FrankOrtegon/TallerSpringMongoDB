package com.sofka.biblioteca.service;

import com.sofka.biblioteca.dto.RecursoDTO;
import com.sofka.biblioteca.model.Recurso;
import com.sofka.biblioteca.repositories.RepositorioCategoria;
import com.sofka.biblioteca.repositories.RepositorioRecurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ServicioCRUDTest {
    @MockBean
    private RepositorioRecurso repositorioRecurso;
    @MockBean
    private RepositorioCategoria repositorioCategoria;
    @Autowired
    private ServicioCRUD servicioCRUD;
    @Autowired
    private ServicioRecurso servicioRecurso;

    @Test
    @DisplayName("Test findAll Success")
    public void obtenerRecursos(){
        var recurso1 = new Recurso();
        recurso1.setId("123");
        recurso1.setNombreRecurso("crimen y castigo");
        recurso1.setDisponible(false);
        recurso1.setTipoRecursoId("12345");
        recurso1.setCategoriaId("14");
        recurso1.setFecha("12/10/2021");

        var recurso2 = new Recurso();
        recurso2.setId("122");
        recurso2.setNombreRecurso("Cancion de hielo y fuego");
        recurso2.setDisponible(false);
        recurso2.setCategoriaId("12345");
        recurso2.setTipoRecursoId("20");
        recurso2.setFecha("15/10/2021");

        var lista = new ArrayList<Recurso>();
        lista.add(recurso1);
        lista.add(recurso2);
        Mockito.when(repositorioRecurso.findAll()).thenReturn(lista);
        var resultado = servicioCRUD.listarRecursos();

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals(recurso2.getNombreRecurso(), resultado.get(1).getNombreRecurso());
        Assertions.assertEquals(recurso1.getNombreRecurso(), resultado.get(0).getNombreRecurso());


    }

    @Test
    @DisplayName("Test save Success")
    public void crearRecurso(){

        var recurso2 = new Recurso();
        recurso2.setId("123");
        recurso2.setNombreRecurso("crimen y castigo");
        recurso2.setDisponible(false);
        recurso2.setTipoRecursoId("12345");
        recurso2.setCategoriaId("14");
        recurso2.setFecha("12/10/2021");

        var recurso1 = new RecursoDTO();
        recurso1.setId("123");
        recurso1.setNombreRecurso("crimen y castigo");
        recurso1.setDisponible(false);
        recurso1.setTipoRecursoId("12345");
        recurso1.setCategoriaId("14");
        recurso1.setFecha("12/10/2021");

        Mockito.when(repositorioRecurso.save(any())).thenReturn(recurso2);

        var resultado = servicioCRUD.crear(recurso1);
        Assertions.assertEquals(recurso1.getTipoRecursoId(), resultado.getTipoRecursoId());
        Assertions.assertEquals(recurso1.getNombreRecurso(), resultado.getNombreRecurso());
    }

    @Test
    @DisplayName("Test findById Success")
    public void buscarRecurso(){

        var  recurso2 = new Recurso();
        recurso2.setId("123");
        recurso2.setNombreRecurso("crimen y castigo");
        recurso2.setDisponible(false);
        recurso2.setTipoRecursoId("12345");
        recurso2.setCategoriaId("14");
        recurso2.setFecha("12/10/2021");


        Mockito.when(repositorioRecurso.findById(any())).thenReturn(java.util.Optional.of(recurso2));
        var resultado = servicioRecurso.consultarRecurso("123");
        Assertions.assertEquals(recurso2.getDisponible(), resultado.isDisponible());
    }
}