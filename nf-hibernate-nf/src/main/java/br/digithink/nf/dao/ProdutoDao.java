package br.digithink.nf.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.google.common.base.Strings;

import br.digithink.nf.model.Produto;
import br.digithink.nf.util.DigithinkUtil;

public class ProdutoDao {
    
    //Construtor
    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    private EntityManager em;

    public void cadastrar(Produto produto){
        em.persist(produto);
    }

    public void atualizar(Produto produto) {
        em.merge(produto); // MERGE: busca e mescla com o que está trabalhando
    }

    public void remover(Produto produto) {
        produto = em.merge(produto);
        em.remove(produto);
    }

    public Produto buscarPorId(Long idProduto) {
        return em.find(Produto.class, idProduto);
    }

    public List<Produto> buscarTodos() {
        String jqpl = "SELECT p FROM Produto p"; // Consulta
        return em.createQuery(jqpl, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nomeProduto) {
        String jqpl = "SELECT p FROM Produto p WHERE p.nome LIKE :nome "; // Vincula em uma variável
        return em.createQuery(jqpl, Produto.class)
        .setParameter("nome", "%"+nomeProduto+"%")
        .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nomeCategoria) {
        String jqpl = "SELECT p FROM Produto p WHERE p.categoria.nome = :nomeCategoria ";
        return em.createQuery(jqpl, Produto.class)
        .setParameter("nomeCategoria",nomeCategoria)
        .getResultList();
    }

    public Produto buscarProdutoDistinto(String nomeProduto) {
        String jqpl = "SELECT p FROM Produto p WHERE p.nome LIKE :nome ";
        return em.createQuery(jqpl, Produto.class)
        .setParameter("nome", "%"+nomeProduto+"%")
        .setMaxResults(1) //Retorna apenas um resultado
        .getSingleResult();
    }

    public List<Produto> buscarPorParametros(String nomeProduto, String nomeCategoria, String valorInicial, String valorFinal) {
        
        String jpql = "SELECT p FROM Produto p WHERE 1=1";

        // Se for vazio ou nulo
        if (!Strings.isNullOrEmpty(nomeProduto))  jpql += " AND LOWER(p.nome) LIKE :nomeProduto ";
        if (!Strings.isNullOrEmpty(nomeCategoria)) jpql += " AND LOWER(p.categoria.nome) LIKE :nomeCategoria ";
        if (!Strings.isNullOrEmpty(valorInicial.toString())) jpql += " AND p.preco >= :valorInicial ";
        if (!Strings.isNullOrEmpty(valorFinal.toString())) jpql += " AND p.preco <= :valorFinal ";

        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class); //Consulta

        if (!Strings.isNullOrEmpty(nomeProduto)) query.setParameter("nomeProduto", "%"+nomeProduto.toLowerCase()+"%");
        if (!Strings.isNullOrEmpty(nomeCategoria)) query.setParameter("nomeCategoria", "%"+nomeCategoria.toLowerCase()+"%");
        if (!Strings.isNullOrEmpty(valorInicial)) query.setParameter("valorInicial", new BigDecimal(valorInicial));
        if (!Strings.isNullOrEmpty(valorFinal)) query.setParameter("valorFinal", new BigDecimal(valorFinal));
        return query.getResultList();
    }

    public List<Produto> buscarPorParametrosComCriteria(String nomeProduto, BigDecimal preco) {

        CriteriaBuilder builder = em.getCriteriaBuilder(); // criador de critérios
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> produto = query.from(Produto.class); //(raíz, classe principal)

        Predicate filtros = builder.and();

        if(!DigithinkUtil.ehVazio(nomeProduto)) filtros = builder.and(builder.equal(produto.get("nome"), nomeProduto ));
        if(preco!=null) filtros = builder.and(builder.equal(produto.get("preco"), preco)); 

        query.where(filtros);

        return em.createQuery(query).getResultList();

    }
}
