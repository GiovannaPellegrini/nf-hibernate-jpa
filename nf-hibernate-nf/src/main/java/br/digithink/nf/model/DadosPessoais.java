package br.digithink.nf.model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable //Integrada (para quando tiver muitas informações)
public class DadosPessoais {
    
    public DadosPessoais() {
    }

    public DadosPessoais(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String cpf;
}
