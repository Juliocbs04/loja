package br.digitalinovationone.loja.dao;

import br.digitalinovationone.loja.model.Produto;
import org.hibernate.internal.build.AllowSysOut;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    public List<Produto> buscarPorNome(String nome){
        String jpql= "SELECT p FROM Produto p WHERE p.nome = :nome";

        return this.entityManager.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorCategoria(String nome){
        String jpql= "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";

        return this.entityManager.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return entityManager.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro){
        String jpql = "SELECT p from Produto p WHERE 1=1";
        if(nome!=null&&nome.trim().isEmpty()){
            jpql = "AND p.nome = :nome";
        }if(preco!=null){
            jpql = "AND p.preco = :preco";
        }if(dataCadastro!=null){
            jpql = "AND p.dataCadastro = :dataCadastro";
        }
        TypedQuery<Produto> query= entityManager.createQuery(jpql, Produto.class);
        if(nome!=null&&nome.trim().isEmpty()){
            query.setParameter("nome", nome);
        }if(preco!=null){
            query.setParameter("preco", preco);
        }if(dataCadastro!=null){
            query.setParameter("dataCadastro", dataCadastro);
        }

        return query.getResultList();
    }


    public List<Produto> buscarPorParametrosCriteria(String nome, BigDecimal preco, LocalDate dataCadastro){
        CriteriaBuilder builder =entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);

        Root<Produto> from = query.from(Produto.class);
        Predicate filtros= builder.and();
        if(nome!=null&& !nome.trim().isEmpty()){
            filtros = builder.and(filtros, builder.equal(from.get("nome"),nome));
        }if(preco!=null){
            filtros = builder.and(filtros, builder.equal(from.get("preco"),preco));
        }if(dataCadastro!=null){
            filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"),dataCadastro));
        }
        query.where(filtros);

        return entityManager.createQuery(query).getResultList();

    }


}
