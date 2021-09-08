package br.digitalinovationone.loja.dao;

import br.digitalinovationone.loja.model.Produto;

import javax.persistence.EntityManager;

public class ProdutoDAO {
    private EntityManager entityManager;

    public ProdutoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Produto produto){
        this.entityManager.persist(produto);
    }


}
