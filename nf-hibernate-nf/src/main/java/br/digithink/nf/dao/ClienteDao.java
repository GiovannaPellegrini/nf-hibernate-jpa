package br.digithink.nf.dao;

import javax.persistence.EntityManager;

import br.digithink.nf.model.Cliente;

public class ClienteDao {
    
    EntityManager em;

    public ClienteDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Cliente cliente){
        em.persist(cliente);
    } 

    public Cliente buscarPorId(Long id){
        return em.find(Cliente.class, id);
    }


}
