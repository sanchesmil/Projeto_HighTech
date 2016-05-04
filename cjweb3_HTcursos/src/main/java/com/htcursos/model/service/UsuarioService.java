package com.htcursos.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.htcursos.model.dao.DAOException;
import com.htcursos.model.dao.UsuarioDAO;
import com.htcursos.model.entity.Usuario;

//Lógica do Sistema / Validação de regras de negócio

@Service
public class UsuarioService {

	// Esta anotação faz o Spring injetar um UsuarioDAO quando o UsuarioService
	// for instanciado
	@Inject
	private UsuarioDAO usuarioDAO;

	public void salvar(Usuario u) throws ServiceException {
		// Validação dos dados é feita aqui ...

		try {

			if (u.getNome() == null || u.getNome() == "") {
				throw new ServiceException("O usuário não pode estar sem nome!");
			}

			usuarioDAO.salvar(u);
		} catch (DAOException causa) {
			throw new ServiceException("Não foi possível salvar! ", causa);
		}
	}

	public List<Usuario> buscarTodos() {

		return usuarioDAO.buscaTodos();
	}

}
