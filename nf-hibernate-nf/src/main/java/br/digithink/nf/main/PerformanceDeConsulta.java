package br.digithink.nf.main;

import javax.persistence.EntityManager;

import br.digithink.nf.dao.NotaFiscalDao;
import br.digithink.nf.model.NotaFiscal;
import br.digithink.nf.util.JPAUtil;

public class PerformanceDeConsulta {
    public static void main(String[] args) {
        
        EntityManager em = JPAUtil.getEntityManager();

        NotaFiscalDao notaFiscalDao = new NotaFiscalDao(em);

        NotaFiscal notaFiscal = notaFiscalDao.buscaNotaFiscalComCliente(1L);

        //System.out.println("Data de Emissão: "+notaFiscal.getData());

        System.out.println("Cliente: "+notaFiscal.getCliente().getNome());

        em.close();

        //System.out.println("Quantidade de itens: " + notaFiscal.getItens().size());

    }
}
