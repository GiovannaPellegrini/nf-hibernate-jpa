package br.digithink.nf.main;

import java.util.List;

import javax.persistence.EntityManager;

import br.digithink.nf.dao.NotaFiscalDao;
import br.digithink.nf.util.JPAUtil;
import br.digithink.nf.vo.RelatorioFaturamento;

public class ConsultasAvancadas {
    
    public static void main(String[] args) {
        
        // Consulta 1: Total faturado
        EntityManager em = JPAUtil.getEntityManager();
        NotaFiscalDao notaFiscalDao = new NotaFiscalDao(em);
        System.out.println("Total faturado: " + notaFiscalDao.totalFaturado());

        
        // Consulta 2: Relatório de faturamento
        List<RelatorioFaturamento> relatorio = notaFiscalDao.relatorioDeFaturamento();
        System.out.println("\n\n##########RELATÓRIO DE VENDAS##########");
        System.out.println("Produto - Qtd - Última venda");
        
        relatorio.forEach(relat -> {
            System.out.println(relat.getNomeProduto() + "-" + relat.getQuantidade() + "data:" + relat.getDataUltimaVenda());
        });
    
        em.close();

    }

}
