package br.com.hdservices2.test.pessoa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.hdservices.model.Pessoa;
import br.com.hdservices2.test.base.BaseTest;

/**
 * Classe de testes unitários para o {@link Pessoa}
 * 
 * @author Rodrigo Alves (rdg.alvess@gmail.com)
 * @date 04/10/2015
 *
 */
public class PessoaTest extends BaseTest {

	private Pessoa pessoa;

	@Before
	public void before() {
		super.before();
		pessoa = new Pessoa();
	}

	@After
	public void after() {
		super.after();
		pessoa = null;
	}

	@Test
	public void salvarPessoaComSucesso() {
		
		em.getTransaction().begin();
		
		pessoa.setMatricula("0001");
		pessoa.setNome("Rodrigo Alves da Silva");
		pessoa.setEmail("rdg.alvess@gmail.com");
		pessoa.setArea("TI");
		pessoa.setPerfil("Administrador");
		pessoa.setSexo("M");
		pessoa.setStatus("Ativo");
		
		em.persist(pessoa);
		
		em.getTransaction().commit();
	}

}
