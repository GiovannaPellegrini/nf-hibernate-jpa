package br.digithink.nf.main;

import java.util.List;

import javax.persistence.EntityManager;

import br.digithink.nf.dao.ProdutoDao;
import br.digithink.nf.model.Produto;
import br.digithink.nf.util.JPAUtil;

// Consulta dinâmica
public class ConsultasAvancadas2 {
    
    public static void main(String[] args) {
        
        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(em);

        String nomeProduto = "Camisa";
        String nomeCategoria = "VESTUÁRIO";
        String valorInicial = "50";
        String valorFinal = "500";

        List<Produto> produtos = produtoDao.buscarPorParametros(nomeProduto, nomeCategoria, valorInicial, valorFinal);

        produtos.forEach(p -> System.out.println(p));
        //produtos.forEach(System.out::println()); Faz a mesma coisa que o forEach acima
    
        em.close();

    }

}
