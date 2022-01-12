package br.digitalinovationone.loja.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="categorias")
public class Categoria {

    //Exemplo de chave composta
    @EmbeddedId
    private CategoriaId categoriaId;
    public Categoria(){

    }

    public Categoria(String nome){
        this.categoriaId= new CategoriaId(nome, "xpto");
    }

    public String getNome(){
        return this.categoriaId.getNome();
    }

    public String getTipo(){
        return this.categoriaId.getTipo();
    }



}
