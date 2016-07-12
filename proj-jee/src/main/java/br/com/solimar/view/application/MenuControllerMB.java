package br.com.solimar.view.application;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@RequestScoped
@ManagedBean
public class MenuControllerMB {
	
	//@Inject
	//private Logger log;
	
	
	public String getItemCssClass(String viewId) {
		FacesContext context = FacesContext.getCurrentInstance();
		String currentViewId = context.getViewRoot().getViewId();
		
		//viewId = "/pages/" + viewId + ".xhtml";
		viewId = "/pages/" + viewId;
		
		//log.info("[getItemCssClass] currentViewId: "+currentViewId);
		
		return currentViewId.contains(viewId) ? "is-selected" : null;
	}

}
