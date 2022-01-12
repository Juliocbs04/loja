package br.digitalinovationone.loja.testes;

import br.digitalinovationone.loja.dao.ClienteDAO;
import br.digitalinovationone.loja.dao.PedidoDAO;
import br.digitalinovationone.loja.dao.ProdutoDAO;
import br.digitalinovationone.loja.model.*;
import br.digitalinovationone.loja.util.JPAUtil;
import br.digitalinovationone.loja.vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import javax.xml.bind.SchemaOutputResolver;
import java.math.BigDecimal;
import java.util.List;

public class CadastroPedido {
    public static void main(String[] args) {
        adicionarProduto();


        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        Produto produto = dao.buscarPorId(1l);
        ClienteDAO clienteDAO = new ClienteDAO(entityManager);
        Cliente cliente = clienteDAO.buscarPorId(1l);

        entityManager.getTransaction().begin();


        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));
        PedidoDAO pedidoDao=  new PedidoDAO(entityManager);
        pedidoDao.cadastrar(pedido);

        entityManager.getTransaction().commit();

        BigDecimal valor = pedidoDao.valorTotalVendido();
        System.out.println("Valor Total dos pedidos: "+valor);

        List<RelatorioVendasVo> relatorio = pedidoDao.relatorioDeVendas();
        relatorio.forEach(System.out::println);


    }

    private static void adicionarProduto() {
        //Primeiro é necessário persistir a categoria para depois adicionar o Produto...
        Categoria celulares = new Categoria("CELULARES");
        EntityManager entityManager = JPAUtil.getEntityManager();
        //CategoriaDAO daoCategoria = new CategoriaDAO(entityManager);


        Cliente cliente = new Cliente("Julio", "00000000");

        entityManager.getTransaction().begin();
        //daoCategoria.cadastrar(celulares);
        //Transação commitada
        entityManager.persist(celulares);
        //após o persis ele fica de olho se tem mudanças no object celulares
        //celulares.setNome("XPTO");
        entityManager.getTransaction().commit();

        Produto celular = new Produto();
        celular.setNome("Xiami Redmi");
        celular.setDescricao("Muito Legal");
        celular.setPreco(new BigDecimal("800"));
        celular.setCategoria(celulares);

        //Não da pra instanciar new EntityManagerFactory
        //Para criar um EntityManager precisamos de um EntityManagerFactory uma fábrica de Entitys que cria isto
        //EntityManager entityManager = JPAUtil.getEntityManager();
        //System.out.println(celular);
        //Se não colocar/iniciar uma transação com a CONF resource_LOCAL ele não vai inserir
        //Transação iniciada
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        ClienteDAO clienteDAO = new ClienteDAO(entityManager);
        entityManager.getTransaction().begin();
        dao.cadastrar(celular);
        clienteDAO.cadastrar(cliente);
        //Transação commitada
        entityManager.getTransaction().commit();
        //Fechar o entityManager
        entityManager.close();
    }
}
