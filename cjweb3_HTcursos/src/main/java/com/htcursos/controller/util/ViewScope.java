package com.htcursos.controller.util;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/* Escopo de visualiza��o (View Scope) introduzido a partir do JSF 2
 * Semelhante a uma SESSION mas com recursos p/ poupar mem�ria
 * Foi poss�vel manter managed beans num escopo maior do que requisi��o e menor do que sess�o. 
 *          Isso resolveu a maioria dos problemas relacionados  a ajax, uso inadequado do escopo de sess�o
 *          e idas e vindas desnecess�rias ao banco de dados.
 * Obs.: Toda vez que for necess�rio criar um objeto de escopo ele deve implementar a interface Scope
 */
public class ViewScope implements Scope {

	public Object get(String name, ObjectFactory<?> objectFactory) {
		if (FacesContext.getCurrentInstance().getViewRoot()  != null) {
			
		        	Map<String, Object> viewMap = FacesContext.getCurrentInstance()	.getViewRoot().getViewMap();

			if (viewMap.containsKey(name)) {
				return viewMap.get(name);
			} else {
				Object object = objectFactory.getObject();
				viewMap.put(name, object);
				return object;
			}
		} else {
			return null;
		}
	}

	public Object remove(String name) {
		
		if(FacesContext.getCurrentInstance().getViewRoot() != null){
			return FacesContext.getCurrentInstance().getViewRoot().getViewMap()
		
				.remove(name);
		}else{
			return null;
		}
	}

	public String getConversationId() {
		return null;
	}

	public void registerDestructionCallback(String name, Runnable callback) {
		// Not supported
	}

	public Object resolveContextualObject(String key) {
		return null;
	}
}