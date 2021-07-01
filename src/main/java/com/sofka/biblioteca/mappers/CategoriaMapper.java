package com.sofka.biblioteca.mappers;

import com.sofka.biblioteca.dto.CategoriaDTO;
import com.sofka.biblioteca.model.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaMapper {

    public Categoria fromDTO(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(categoriaDTO.getNombreCategoria());
        categoria.setCategoriaId(categoriaDTO.getCategoriaId());
        return categoria;
    }

    public CategoriaDTO fromModel(Categoria categoria){
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setCategoriaId(categoria.getCategoriaId());
        categoriaDTO.setNombreCategoria(categoria.getNombreCategoria());
        return categoriaDTO;
    }

    public List<CategoriaDTO> fromCollectionList(List<Categoria> listaCategorias){
        if(listIsNull(listaCategorias)){
            return null;
        }
        var listDTO = listaCategorias.stream().map(ca-> fromModel(ca)).collect(Collectors.toList());
        return listDTO;
    }

    private Boolean listIsNull(List<Categoria> list){
        return list == null;
    }
}
