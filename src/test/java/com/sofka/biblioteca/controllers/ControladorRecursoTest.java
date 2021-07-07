package com.sofka.biblioteca.controllers;

import com.sofka.biblioteca.dto.RecursoDTO;
import com.sofka.biblioteca.service.ServicioCRUD;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


import static org.mockito.Mockito.doReturn;
class ControladorRecursoTest {

    @MockBean
    private ServicioCRUD servicioCRUD;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRecursos() throws Exception{
        //Arrange
        var recurso1 = new RecursoDTO();
        recurso1.setId("60db30202deddb25ca8010f2");
        recurso1.setNombreRecurso("amor en tiempos oscuros");
        recurso1.setTipoRecursoId("libro");
        recurso1.setFecha("12/10/2021");
        recurso1.setDisponible(true);
        recurso1.setCategoriaId("51db30202deddb25ca8010f5");

        var recurso2 = new RecursoDTO();
        recurso2.setId("61db30202deddb25ca8010f9");
        recurso2.setNombreRecurso("el viejo y el mar");
        recurso2.setTipoRecursoId("revista");
        recurso2.setFecha("12/10/2021");
        recurso2.setDisponible(true);
        recurso2.setCategoriaId("51db30202deddb25ca8010f5");

        doReturn(Lists.newArrayList(recurso1,recurso2)).when(servicioCRUD).obtenerTodos(); //creamos el mock

        //Act && Assert
        mockMvc.perform(get("/recurso/lists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("60db30202deddb25ca8010f2")))
                .andExpect(jsonPath("$[0].nombre", is("amor en tiempos oscuros")))
                .andExpect(jsonPath("$[0].tipoRecurso", is("libro")))
                .andExpect(jsonPath("$[1].id", is("61db30202deddb25ca8010f9")))
                .andExpect(jsonPath("$[1].nombre", is("el viejo y el mar")))
                .andExpect(jsonPath("$[1].tipoRecurso", is("revista")));

    }

    @Test
    public void postRecurso() throws Exception{
        //Arrange
        RecursoDTO recursoPost = new RecursoDTO();
        recursoPost.setId("61db30202deddb25ca8010f9");
        recursoPost.setNombreRecurso("amor en tiempos oscuros");
        recursoPost.setTipoRecursoId("libro");
        recursoPost.setDisponible(true);
        recursoPost.setCategoriaId("51db30202deddb25ca8010f5");

        RecursoDTO recursoReturn = new RecursoDTO();
        recursoReturn.setId("61db30202deddb25ca8010f9");
        recursoReturn.setNombreRecurso("amor en tiempos oscuros");
        recursoReturn.setTipoRecursoId("libro");
        recursoReturn.setDisponible(true);
        recursoReturn.setCategoriaId("51db30202deddb25ca8010f5");

        doReturn(recursoReturn).when(servicioCRUD).crear(any());

        //Act && Assert
        mockMvc.perform(post("/recurso/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recursoPost)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect(jsonPath("$.nombre", is("amor en tiempos oscuros")))
                .andExpect(jsonPath("$.tipoRecurso", is("libro")))
                .andExpect(jsonPath("$.id", is("61db30202deddb25ca8010f9")));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}