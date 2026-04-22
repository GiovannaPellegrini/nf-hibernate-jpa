package br.digithink.nf.vo;

import java.time.LocalDate;

import lombok.Getter;

public class RelatorioFaturamento {

    public RelatorioFaturamento(String nomeProduto, Long quantidade, LocalDate dataUltimaVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.dataUltimaVenda = dataUltimaVenda;
    }

    @Getter
    private String nomeProduto;

    @Getter
    private Long quantidade;

    @Getter
    private LocalDate dataUltimaVenda;
    
}