package br.digitalinovationone.loja.dao;

import br.digitalinovationone.loja.model.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDAO {
    private EntityManager entityManager;

    public PedidoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Pedido pedido){
        this.entityManager.persist(pedido);
    }

    public Pedido buscarPorId(Long id){
        return this.entityManager.find(Pedido.class,id);
    }
    //usando JPQL
    public List<Pedido> listarPedidos(){
        String jpql= "SELECT p FROM Pedido p";
        return this.entityManager.createQuery(jpql, Pedido.class).getResultList();
    }

    public List<Pedido> buscarPorNome(String nome){
        String jpql= "SELECT p FROM Pedido p WHERE p.nome = :nome";

        return this.entityManager.createQuery(jpql, Pedido.class)
                .setParameter("nome", nome)
                .getResultList();
    }


}
