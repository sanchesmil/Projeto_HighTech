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
// Anotação do Spring (obs.: se fosse trabalhar sem SPRING, a anotação seria
// @ManagerBean do JSF)
@ViewScoped // Ciclo de vida (tempo de vida deste objeto -> inicio, meio e fim)
public class UsuarioBean { // Esta classe faz a comunicação com a tela de
							// Usuario

	//Este objeto recebe os dados da tela, referentes a um usuario
	private Usuario usuario = new Usuario();
	
	//Variável local que recupera todos os usuarios, e que possui GET e SET
	private List<Usuario> listaDeUsuarios;

	@Inject
	private UsuarioService usuarioService;

	//@PostConstructor: Na construção deste Bean, a lista de usuários 
		//												é automaticamente carregada p/ a variável 'listaDeUsuarios'
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

			// Após salvar, limpa o objeto 'usuario' p/ enviar p/ tela
			usuario = new Usuario();
			
			//Após salvar, atualiza a lista de usuário na tela
			listaDeUsuarios = usuarioService.buscarTodos();

			// Lançar uma mensagem p/ tela
			//FacesContext: Recupera o contexto do JSF (a árvore de objetos desse JSF)
			FacesContext.getCurrentInstance().addMessage(	null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Usuário salvo com sucesso!", null));
		} catch (ServiceException e) {
			// Código da mensagem na tela
			FacesContext.getCurrentInstance().addMessage(	null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao salvar:  " + e.getMessage(), null));
		}
	}

}
