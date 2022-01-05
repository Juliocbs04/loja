package br.digitalinovationone.loja.dao;

import br.digitalinovationone.loja.model.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDAO {
    private EntityManager entityManager;

    public ClienteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Cliente c){
        this.entityManager.persist(c);
    }

    public Cliente buscarPorId(Long id){
        return this.entityManager.find(Cliente.class,id);
    }
    //usando JPQL
    public List<Cliente> listarClientes(){
        String jpql= "SELECT p FROM Cliente p";
        return this.entityManager.createQuery(jpql, Cliente.class).getResultList();
    }

}
