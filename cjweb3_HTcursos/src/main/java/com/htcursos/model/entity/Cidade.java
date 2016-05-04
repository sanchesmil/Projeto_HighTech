package com.htcursos.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Cidade {

	@Id
	@SequenceGenerator(name="seq_cid",initialValue=1 )
	@GeneratedValue(generator="seq_cid", strategy=GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	@JoinColumn //Coluna que vai possuir valor de Chave Estrangeira
	@ManyToOne  //Muitas cidades p/ um estado
	private Estado estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
