package com.htcursos.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.htcursos.model.dao.DAOException;
import com.htcursos.model.dao.UsuarioDAO;
import com.htcursos.model.entity.Usuario;

//L�gica do Sistema / Valida��o de regras de neg�cio

@Service
public class UsuarioService {

	// Esta anota��o faz o Spring injetar um UsuarioDAO quando o UsuarioService
	// for instanciado
	@Inject
	private UsuarioDAO usuarioDAO;

	public void salvar(Usuario u) throws ServiceException {
		// Valida��o dos dados � feita aqui ...

		try {

			if (u.getNome() == null || u.getNome() == "") {
				throw new ServiceException("O usu�rio n�o pode estar sem nome!");
			}

			usuarioDAO.salvar(u);
		} catch (DAOException causa) {
			throw new ServiceException("N�o foi poss�vel salvar! ", causa);
		}
	}

	public List<Usuario> buscarTodos() {

		return usuarioDAO.buscaTodos();
	}

}
