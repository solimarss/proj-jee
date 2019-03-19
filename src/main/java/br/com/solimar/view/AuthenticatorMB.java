package br.com.solimar.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.solimar.domain.Role;
import br.com.solimar.domain.User;
import br.com.solimar.sis.context.UserContext;
 
 
@SuppressWarnings("serial")
@Named
@RequestScoped
public class AuthenticatorMB  implements Serializable{
 
 
    private String password;
    private String username;
    @Inject
    private UserContext userContext;
     
     
    public String logar(){
        System.out.println("logar: "+username);
        try {
 
            if (username.equals("admin")) {
                User user = new User();
                user.setPassword(password);
                user.setUsername(username);
                Role role = new Role();
                role.setName("ROLE_ADMIN");
                role.setId(1L);
                List<Role> roles = new ArrayList<Role>();
                roles.add(role);
                user.setRoles(roles);
                loginSpringSecurity(user);
            } else {
                throw new IllegalArgumentException(
                        "Erro: username ou password incorretos!");
            }
 
            return "/pages/index.jsf?faces-redirect=true";
        } catch (IllegalArgumentException ex) {
            message(ex.getMessage());
        }
        return "";
    }
 
 
     
     
    public String getPassword() {
        return password;
    }
 
 
    public void setPassword(String password) {
        this.password = password;
    }
 
 
    public String getUsername() {
        return username;
    }
 
 
    public void setUsername(String username) {
        this.username = username;
    }
 
 
     
    private void loginSpringSecurity(User user) {
        userContext.setUser(user);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getUsername(), null, user.getRoles());
        token.setDetails(user);
 
        SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.getContext().setAuthentication(token);
    }
 
    public String logout() {
        userContext.setUser(null);
        SecurityContextHolder.clearContext();
        return "/security/login.jsf?faces-redirect=true";
    }
 
    private void message(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(message));
    }
     
     
}