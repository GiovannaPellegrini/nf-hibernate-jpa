package br.digithink.nf.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_categorias")
public class Categoria {

    public Categoria() {
    }

    public Categoria(String nome){
        this.id = new CategoriaId(nome, "CATEGORIA");
    }

    public String getNome(){
        return this.id.getNome();
    }

    @EmbeddedId
    private CategoriaId id;
}
