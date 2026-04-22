package br.digithink.nf.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.digithink.nf.model.NotaFiscal;
import br.digithink.nf.vo.RelatorioFaturamento;

public class NotaFiscalDao {
    
    EntityManager em;

    public  NotaFiscalDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar(NotaFiscal notaFiscal){
        em.persist(notaFiscal);
    }

    public BigDecimal totalFaturado(){
        String jpql = "SELECT SUM(nf.valorTotal) FROM NotaFiscal nf";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

// Modo Hard Code
//     public List<Object[]> relatorioDeFaturamento(){
//         String jpql = "SELECT prod.nome, "
//         + " SUM(item.quantidade) AS quantidade, "
//         + " MAX(nf.data) "
//         + " FROM NotaFiscal nf "
//         + " JOIN nf.itens item"
//         + " JOIN item.produto prod "
//         + " GROUP BY prod.nome "
//         + " ORDER BY  quantidade DESC ";

//         return em.createQuery(jpql, Object[].class).getResultList();
//     }
// }

    public List<RelatorioFaturamento> relatorioDeFaturamento(){
        String jpql = "SELECT new br.digithink.nf.vo.RelatorioFaturamento( "
        + " prod.nome,"
        + " SUM(item.quantidade) AS quantidade, "
        + " MAX(nf.data) ) "
        + " FROM NotaFiscal nf "
        + " JOIN nf.itens item"
        + " JOIN item.produto prod "
        + " GROUP BY prod.nome "
        + " ORDER BY  quantidade DESC ";

        return em.createQuery(jpql, RelatorioFaturamento.class).getResultList();
    }

    public NotaFiscal buscaNotaFiscalComCliente(Long id){
        String jpql = "SELECT nf FROM NotaFiscal nf JOIN FETCH nf.cliente WHERE nf.id = :id";
        return em.createQuery(jpql, NotaFiscal.class)
        .setParameter("id", id)
        .getSingleResult();
    }
}
