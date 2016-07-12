package br.com.solimar.view.user;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.solimar.business.UserBC;
import br.com.solimar.domain.User;


@Named
@ViewScoped
public class UserListMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UserBC userBC;
	
	private List<User> userList;
	
	private static final Logger logger =     Logger.getLogger(UserListMB.class);
	
	@PostConstruct
	private void init(){
		System.out.println("####### TEST ##########");
		logger.info("####### TEST LOG4J ##########");
		logger.warn("####### TEST LOG4J ##########");
		logger.error("####### TEST LOG4J ERRO ##########");

		
		
		listar(); 
	}

	
	private void listar(){
		setUserList(userBC.findAll());
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
