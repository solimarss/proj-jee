package br.com.solimar.business;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.solimar.domain.User;
import br.com.solimar.persistence.UserDAO;

@Stateless
public class UserBC implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserDAO userDAO;
	
	//@Inject
	//private Logger log;
	
	public List<User> findAll(){
		//log.fine("findAll");
		
		
		return userDAO.findAll();
	}
	
	public User find(Long id){
		//log.info("find");
		return userDAO.find(id);
	}
	
	public void insert(User u){
		userDAO.persist(u);
	}
	
	public User update(User u){
		return userDAO.update(u);
	}

}
