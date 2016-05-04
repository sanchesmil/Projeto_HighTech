package com.htcursos.model.dao;

public class DAOException extends Exception {

	public DAOException(String mensagem, Exception causa) {
		super(mensagem, causa);
	}

}
