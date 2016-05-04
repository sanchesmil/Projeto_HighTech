package com.htcursos.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.htcursos.model.entity.Usuario;


@Repository
public class UsuarioDAO {
	//Inje��o de depend�ncia do Entity Manager
    @PersistenceContext 
	EntityManager em;

	public UsuarioDAO() {
	}

	@Transactional
	public void salvar(Usuario u) throws DAOException {
		try {
			em.merge(u);
		} catch (Exception causa) { //Captura a exce��o do banco
			throw new DAOException("N�o foi poss�vel salvar! ", causa);
		}
	}
	
	@Transactional
	public void excluir(Usuario u){
		em.remove(u);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscaTodos(){
		Query consulta  = em.createQuery("select u from Usuario u"); //JPQL
		return consulta.getResultList();
	}
	
	public Usuario buscaPorId(Long id){
		return em.find(Usuario.class, id);
	}

}
