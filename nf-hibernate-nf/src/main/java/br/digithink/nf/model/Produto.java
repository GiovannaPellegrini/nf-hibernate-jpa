package br.digithink.nf.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Table(name = "tb_produtos")
@Inheritance(strategy = InheritanceType.JOINED) //Herança
public class Produto {

    public Produto() {
    }
        
    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }
    
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Getter @Setter
    private String nome;
    
    @Getter @Setter
    private String descricao;
    
    @Getter @Setter
    private BigDecimal preco;

    @Getter @Setter
    @JoinColumn(name = "id_da_categoria", foreignKey = @ForeignKey(name = "fk_catgoria_produto"))
    @ManyToOne
    private Categoria categoria;


}
