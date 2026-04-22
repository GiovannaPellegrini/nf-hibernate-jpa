package br.digithink.nf.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Computador extends Produto {
    

    public Computador() {
    }

    public Computador(String processador, String potencia) {
        this.processador = processador;
        this.potencia = potencia;
    }

    @Getter @Setter
    private String processador;

    @Getter @Setter
    private String potencia;
}
