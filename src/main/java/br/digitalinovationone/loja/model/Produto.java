package br.digitalinovationone.loja.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="produtos")
@Getter
@Setter
@ToString
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private LocalDate dataCadastro = LocalDate.now();
    @Enumerated(EnumType.STRING) // para cadastrar o nome da constante
    private Categoria categoria;

}
