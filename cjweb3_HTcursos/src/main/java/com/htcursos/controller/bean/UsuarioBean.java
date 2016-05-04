package com.htcursos.controller.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import com.htcursos.model.entity.Usuario;
import com.htcursos.model.service.ServiceException;
import com.htcursos.model.service.UsuarioService;

@Controller
// Anota��o do Spring (obs.: se fosse trabalhar sem SPRING, a anota��o seria
// @ManagerBean do JSF)
@ViewScoped // Ciclo de vida (tempo de vida deste objeto -> inicio, meio e fim)
public class UsuarioBean { // Esta classe faz a comunica��o com a tela de
							// Usuario

	//Este objeto recebe os dados da tela, referentes a um usuario
	private Usuario usuario = new Usuario();
	
	//Vari�vel local que recupera todos os usuarios, e que possui GET e SET
	private List<Usuario> listaDeUsuarios;

	@Inject
	private UsuarioService usuarioService;

	//@PostConstructor: Na constru��o deste Bean, a lista de usu�rios 
		//												� automaticamente carregada p/ a vari�vel 'listaDeUsuarios'
		@PostConstruct 
		public void init(){
			listaDeUsuarios = usuarioService.buscarTodos();
		}
		
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
		
	public List<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}
    
	public void salvar() {
		try {
			usuarioService.salvar(usuario);

			// Ap�s salvar, limpa o objeto 'usuario' p/ enviar p/ tela
			usuario = new Usuario();
			
			//Ap�s salvar, atualiza a lista de usu�rio na tela
			listaDeUsuarios = usuarioService.buscarTodos();

			// Lan�ar uma mensagem p/ tela
			//FacesContext: Recupera o contexto do JSF (a �rvore de objetos desse JSF)
			FacesContext.getCurrentInstance().addMessage(	null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Usu�rio salvo com sucesso!", null));
		} catch (ServiceException e) {
			// C�digo da mensagem na tela
			FacesContext.getCurrentInstance().addMessage(	null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao salvar:  " + e.getMessage(), null));
		}
	}

}
