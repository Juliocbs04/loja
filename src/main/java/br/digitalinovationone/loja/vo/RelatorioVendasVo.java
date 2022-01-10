package br.digitalinovationone.loja.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class RelatorioVendasVo {
    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDate dataUltimaVenda;
}
