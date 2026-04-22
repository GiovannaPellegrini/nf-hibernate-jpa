package br.digithink.nf.dao;

import javax.persistence.EntityManager;

import br.digithink.nf.model.Categoria;

public class CategoriaDao {
    
    
    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    private EntityManager em;

    public void cadastrar(Categoria categoria){
        em.persist(categoria);
    }

}
