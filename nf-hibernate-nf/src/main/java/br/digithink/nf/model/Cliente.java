package br.digithink.nf.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_clientes", uniqueConstraints = {@UniqueConstraint(columnNames = "cpf")})
public class Cliente {

    public Cliente(){
    }
    
    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(String nome, String cpf) {
        this.dadosPessoais = new DadosPessoais(nome, cpf);
    }

    public String getNome(){
        return dadosPessoais.getNome();
    }

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Getter
    @Embedded
    DadosPessoais dadosPessoais;
}
