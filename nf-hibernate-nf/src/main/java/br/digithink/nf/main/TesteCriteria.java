package br.digithink.nf.main;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.digithink.nf.dao.ProdutoDao;
import br.digithink.nf.model.Produto;
import br.digithink.nf.util.JPAUtil;

public class TesteCriteria {
    public static void main(String[] args) {
        
        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);

        List<Produto> produtos = produtoDao.buscarPorParametrosComCriteria(null, new BigDecimal("1200.00"));

        produtos.forEach(p -> System.out.println(p));

    }
}
