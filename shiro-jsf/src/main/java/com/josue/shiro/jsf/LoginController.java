/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.shiro.jsf;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@Named
@RequestScoped
public class LoginController {
 
    String username;
    String password;
    boolean rememberMe = false;
 
    private static final Logger log = Logger.getLogger(LoginController.class
            .getName());
 
    public String authenticate() {
 
        // Example using most common scenario of username/password pair:
        UsernamePasswordToken token = new UsernamePasswordToken(username,
                password);
 
        // "Remember Me" built-in:
        token.setRememberMe(rememberMe);
 
        Subject currentUser = SecurityUtils.getSubject();
 
        log.log(Level.INFO, "Submitting login with username of {0} and password of {1}", new Object[]{username, password});
 
        try {
            currentUser.login(token);
        } catch (Exception e) {
            // Could catch a subclass of AuthenticationException if you like
            log.warning(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage("Login Failed: " + e.getMessage(), e
                            .toString()));
            return "/login.xhtml";
        }
        return "/secured/dashboard.xhtml?faces-redirect=true";
 
    }
 
    public String logout() {
 
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.logout();
        } catch (Exception e) {
            log.warning(e.toString());
        }
        return "/index.xhtml?faces-redirect=true";
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public boolean getRememberMe() {
        return rememberMe;
    }
 
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
 
}