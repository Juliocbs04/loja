package br.digitalinovationone.loja.testes;

import br.digitalinovationone.loja.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroProduto {
    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Xiami Redmi");
        celular.setDescricao("Muito Legal");
        celular.setPreco(new BigDecimal("800"));

        //Não da pra instanciar new EntityManagerFactory
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("loja");
        //Para criar um EntityManager precisamos de um EntityManagerFactory uma fábrica de Entitys que cria isto
        EntityManager entityManager = factory.createEntityManager();
        System.out.println(celular);
        //Se não colocar/iniciar uma transação com a CONF resource_LOCAL ele não vai inserir
        //Transação iniciada
        entityManager.getTransaction().begin();
        entityManager.flush();
        entityManager.persist(celular);
        //Transação commitada
        entityManager.getTransaction().commit();
        //Fechar o entytyManager
        entityManager.close();
        //entityManager.

    }
}
