package cjweb3;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.htcursos.model.dao.DAOException;
import com.htcursos.model.dao.UsuarioDAO;
import com.htcursos.model.entity.Usuario;

public class TestSpring {

	@Test
	public void testSpring() throws DAOException{	 
		//Carregando o Arquivo XML que possui o Bean Registrado e 
		 //construíndo o contexto do Spring
		 ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		 
		 //Adquire a instância do Bean gerenciado pelo contexto do Spring
		 UsuarioDAO usuarioDAO = context.getBean(UsuarioDAO.class);
		
		 Usuario u = new Usuario();
		 u.setNome("ralphi");
		 u.setEmail("ralphi@gmail.com");
		 u.setSenha("123456");
		 
		 usuarioDAO.salvar(u);
	}

}
