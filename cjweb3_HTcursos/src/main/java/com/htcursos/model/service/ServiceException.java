package com.htcursos.model.service;

import com.htcursos.model.dao.DAOException;

public class ServiceException extends Exception {
	
	

	public ServiceException(String mensagem) {
		super(mensagem);
	}

	public ServiceException(String mensagem, DAOException causa) {
		super(mensagem, causa);
	}

}
