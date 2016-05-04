package cjweb3;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.dao.DAOException;
import com.htcursos.model.dao.UsuarioDAO;
import com.htcursos.model.entity.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
// Classe do Spring que vai rodar os testes
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml" })
// Caminho do arquivo de configuração
// Gerenciador de transação criado no 'applicationContext.xml' que configura os
// testes.
// Neste caso, o teste fará COMMIT no banco, ou seja, não fará Rollback(false)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TestUsuarioDAO {

	// Injeção de dependência do Spring
	@Inject
	UsuarioDAO usuarioDAO;

	@Test
	public void testSalvar() throws DAOException {

		Usuario u = new Usuario();
		u.setNome("Maria da Silva");
		u.setEmail("mariasilva@gmail.com");
		u.setSenha("asdfg");

		usuarioDAO.salvar(u);

	}

	@Test
	public void testBuscarTodos() {

		List<Usuario> usuarios = usuarioDAO.buscaTodos();

		assertTrue(usuarios != null);
	}

	@Test
	public void testBuscarPorId() {
		Usuario usuario = usuarioDAO.buscaPorId(52L);

		assertTrue(usuario != null);
	}

	@Test
	public void testExcluir() {

		Usuario u = usuarioDAO.buscaPorId(552L);

		usuarioDAO.excluir(u);

		assertTrue(usuarioDAO.buscaPorId(u.getId()) == null);
	}
}
