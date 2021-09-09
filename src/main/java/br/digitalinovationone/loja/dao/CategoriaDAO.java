package br.digitalinovationone.loja.dao;

import br.digitalinovationone.loja.model.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDAO {
    private EntityManager entityManager;

    public CategoriaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Categoria categoria){
        this.entityManager.persist(categoria);
    }

    public void atualizar(Categoria categoria){
        //é usado merge pois ao object entrar no estado detached ele não atualiza, o merge volta para managed
        this.entityManager.merge(categoria);
    }

    public void deletar(Categoria categoria){
        //é usado merge pois ao object entrar no estado detached ele não atualiza, o merge volta para managed
        categoria = entityManager.merge(categoria);
        this.entityManager.remove(categoria);
    }


}
