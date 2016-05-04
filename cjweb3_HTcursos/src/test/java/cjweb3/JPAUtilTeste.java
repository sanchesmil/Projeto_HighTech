package cjweb3;

import javax.persistence.EntityManager;

import com.htcursos.model.entity.Usuario;
import com.htcursos.model.util.JPAUtil;

public class JPAUtilTeste {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.abreConexao();
		 
		 Usuario u = new Usuario();
		 u.setNome("Pablo");
		 u.setEmail("pablo@gmail.com");
		 u.setSenha("123456");
		 
		 em.getTransaction().begin();
		 em.persist(u);
		 em.getTransaction().commit();
	}

}
