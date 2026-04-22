package br.digithink.nf.dao;

import javax.persistence.EntityManager;

import br.digithink.nf.model.ItemNotaFiscal;

public class ItemNotaFiscalDao {
    
    EntityManager em;

    public  ItemNotaFiscalDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar(ItemNotaFiscal itemNotaFiscal){
        em.persist(itemNotaFiscal);
    }

}
