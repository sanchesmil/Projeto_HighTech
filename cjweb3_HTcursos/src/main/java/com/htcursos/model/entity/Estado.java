package com.htcursos.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Estado {
	
	@Id
	@SequenceGenerator(name="seq_uf",initialValue=1 )
	@GeneratedValue(generator="seq_uf", strategy=GenerationType.AUTO)
	private Long id;
	
	private String uf;
	
	//@OneToMany -> Um estado p/ muitas cidades (List) 
	//mappedBy ->  //mappedBy � a propriedade (atributo) da outra classe (cidade) que faz refer�ncia a esta classe
    //FetchType.EAGER -> Toda vez que o objeto 'estado' for carregado na mem�ria, o Hibernate tbm carregar� as cidades
	//  (ANCIOSO)        na mem�ria, que por sua vez carregar�o os 'usu�rios' vinculados a ela, e assim sucessivamente
	//					 sobrecarregando a mem�ria. Isso pode ser desnecess�rio se eu quiser somente o nome do estado. 						 do estado.
	//FetchType.LAZY  -> Toda vez que o objeto 'estado' for carregado na mem�ria, o Hibernate N�O carregar� as cidades e
    // 	(PREGUI�OSO)     ficar� com a lista de cidades = null. Se, em algum momento, alguem chamar a propriedade 'cidades'
	//					 o Hibernate far� uma consulta tardia, ou seja, sob demanda.
	@OneToMany(mappedBy="estado", fetch=FetchType.LAZY)  	
	private List<Cidade> cidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<Cidade> getCidade() {
		return cidade;
	}

	public void setCidade(List<Cidade> cidade) {
		this.cidade = cidade;
	}

}
