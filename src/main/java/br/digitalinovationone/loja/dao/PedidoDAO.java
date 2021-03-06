package br.digitalinovationone.loja.dao;

import br.digitalinovationone.loja.model.Pedido;
import br.digitalinovationone.loja.vo.RelatorioVendasVo;

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

    public List<RelatorioVendasVo> relatorioDeVendas(){
        String jpql = "SELECT new br.digitalinovationone.loja.vo.RelatorioVendasVo(" +
                "produto.nome,"+
                "SUM(item.quantidade), " +
                "MAX(pedido.data)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome ORDER BY item.quantidade DESC";
        return entityManager.createQuery(jpql, RelatorioVendasVo.class).
            getResultList();

    }

    public Pedido buscarPedidoComCliente(Long id){
        return this.entityManager.createQuery("SELECT p from Pedido p JOIN FETCH p.cliente WHERE p.id=::id", Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }



}
