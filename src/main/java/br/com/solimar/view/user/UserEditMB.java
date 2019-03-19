package br.com.solimar.view.user;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.solimar.business.UserBC;
import br.com.solimar.domain.User;

@Named
@javax.faces.view.ViewScoped
public class UserEditMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UserBC userBC;
	//@Inject
	//private Logger log;

	private User user;
	@Inject
	private FacesContext facesContext;

	@PostConstruct
	private void init() {
		//log.info("[init]");

		String paramId = facesContext.getExternalContext()
				.getRequestParameterMap().get("id");

		if (paramId == null) {
			user = new User();
		} else {
			user = userBC.find(Long.parseLong(paramId));
		}

	}

	public void save() {
		//log.info("[save]");
		//log.info("[save] id: "+user.getId());
		//log.info("[save] username: "+user.getUsername());
		//log.info("[save] password: "+user.getPassword());
		

		if (user.getId() == null) {
			insert();
		} else {
			update();
		}

		FacesContext.getCurrentInstance().addMessage(
				"Sucesso",
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Salvo com sucesso", "Sucesso"));

	}

	private void update() {
		//log.info("[update]");
		userBC.update(user);
	}

	private void insert() {
		//log.info("[insert]");
		userBC.insert(user);
	}

	public User getUser() {
		//log.info("[getUser]");
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
