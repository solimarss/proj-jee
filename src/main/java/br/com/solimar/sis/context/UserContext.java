package br.com.solimar.sis.context;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.solimar.domain.User;
 
@SuppressWarnings("serial")
@Named
@SessionScoped
public class UserContext implements Serializable{
     
     
    private User user;
 
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
     
}
