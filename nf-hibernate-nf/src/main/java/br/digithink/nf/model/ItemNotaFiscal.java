package br.digithink.nf.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_itens_nota_fiscal")
public class ItemNotaFiscal {

    public ItemNotaFiscal(){
    }
    
    public ItemNotaFiscal(Long id) {
        this.id = id;
    }

    public ItemNotaFiscal(Integer quantidade, NotaFiscal notaFiscal, Produto produto ) {
        this.produto = produto;
        this.notaFiscal = notaFiscal;
        this.precoUnitario = produto.getPreco();
        this.quantidade = quantidade;
    }

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY) //LAZY: não carrega ("preguiçoso")
    private Produto produto;
    
    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private NotaFiscal notaFiscal;
    
    @Getter @Setter
    private BigDecimal precoUnitario;
    
    @Getter @Setter
    private Integer quantidade;
}