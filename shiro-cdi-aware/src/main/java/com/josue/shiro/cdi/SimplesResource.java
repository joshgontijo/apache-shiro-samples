/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.shiro.cdi;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("/")
@RequestScoped
public class SimplesResource {

    @GET
    @Produces("text/plain")
    @Path("unsecured")
    public String unsecuredText() {
        return "UNSECURED RESOURCE !";
    }

    @GET
    @Produces("text/plain")
    @Path("secured")
    public String securedText() {
        Subject currentUser = SecurityUtils.getSubject();

        String status = "SECURED RESOURCE !\n";
        status += "IS AUTHENTICATED: " + currentUser.isAuthenticated() + "\n";
        status += "PRINCIPAL : " + currentUser.getPrincipal() + "\n";
        status += "HAS ROLE 'ADMIN' : " + currentUser.hasRole("ADMIN") + "\n";
        status += "HAS ROLE 'USER' : " + currentUser.hasRole("USER") + "\n";

        return status;
    }

}
