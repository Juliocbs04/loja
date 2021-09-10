package br.digitalinovationone.loja.dao;

import br.digitalinovationone.loja.model.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDAO {
    private EntityManager entityManager;

    public ProdutoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Produto produto){
        this.entityManager.persist(produto);
    }

    public Produto buscarPorId(Long id){
        return this.entityManager.find(Produto.class,id);
    }
    //usando JPQL
    public List<Produto> listarProdutos(){
        String jpql= "SELECT p FROM Produto p";
        return this.entityManager.createQuery(jpql, Produto.class).getResultList();
    }


}
