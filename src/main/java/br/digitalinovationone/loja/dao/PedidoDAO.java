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

    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return entityManager.createQuery(jpql, BigDecimal.class).
                getSingleResult();
    }

    public List<Object[]> relatorioDeVendas(){
        String jpql = "SELECT produto.nome, " +
                "SUM(item.quantidade), MAX(pedido.data) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome ORDER BY item.quantidade DESC";
        return entityManager.createQuery(jpql, Object[].class).
            getResultList();

    }


}
