package br.digithink.nf.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_notas_fiscais")
public class NotaFiscal {

    public NotaFiscal(){
    }

    public NotaFiscal(Long id) {
        this.id = id;
    }

    public NotaFiscal(Cliente cliente) {
        this.cliente = cliente;
    }

    public NotaFiscal(Cliente cliente, BigDecimal valorTotal) {
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private LocalDate data = LocalDate.now();

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @Getter @Setter
    private BigDecimal valorTotal;

    @Getter @Setter                                                //EAGER carrega
    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL, fetch = FetchType.EAGER)//Atributo que corresponde com o relacionamento da classe (sempre na chave estrangeira)
    private List<ItemNotaFiscal> itens = new ArrayList<>();


    public void adicionarItem(ItemNotaFiscal item){
        item.setNotaFiscal(this);
        itens.add(item);
    }

    public void calculaTotalNotaFiscal(){

        BigDecimal valorTotal = BigDecimal.ZERO;
        for(ItemNotaFiscal item : this.itens){
            valorTotal = valorTotal.add(item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())));
        }
        this.valorTotal = valorTotal;

    }
}