/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.shiro.authorization;

import com.josue.shiro.authorization.auth.RoleLevel;
import com.josue.shiro.authorization.custom.AccessLevelPermission;
import java.util.logging.Logger;
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
@Path("secured")
@RequestScoped
public class SimplesResource {

    @GET
    @Produces("text/plain")
    @Path("user")
    public String unsecuredText() {
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isPermitted(new AccessLevelPermission("uuid-doc-123-TODO-check-if-OK", RoleLevel.LEVEL_1))) {
            LOG.info("CustomPermission LEVEL_1 Not permited");
        }
        if (!currentUser.isPermitted(new AccessLevelPermission("uuid-123", RoleLevel.LEVEL_2))) {
            LOG.info("CustomPermission LEVEL_2 Not permited");
        }
        if (!currentUser.isPermitted(new AccessLevelPermission("uuid-123", RoleLevel.ADMIN))) {
            LOG.info("CustomPermission ADMIN Not permited");
        }

        String status = "USER RESOURCE !\n";
        status += "IS AUTHENTICATED: " + currentUser.isAuthenticated() + "\n";
        status += "PRINCIPAL : " + currentUser.getPrincipal() + "\n";
        status += "HAS ROLE 'ADMIN' : " + currentUser.hasRole("ADMIN") + "\n";
        status += "HAS ROLE 'USER' : " + currentUser.hasRole("USER") + "\n";

        return status;
    }
    private static final Logger LOG = Logger.getLogger(SimplesResource.class.getName());

    @GET
    @Produces("text/plain")
    @Path("admin")
    public String securedText() {
        Subject currentUser = SecurityUtils.getSubject();

        String status = "ADMIN RESOURCE !\n";
        status += "IS AUTHENTICATED: " + currentUser.isAuthenticated() + "\n";
        status += "PRINCIPAL : " + currentUser.getPrincipal() + "\n";
        status += "HAS ROLE 'ADMIN' : " + currentUser.hasRole("ADMIN") + "\n";
        status += "HAS ROLE 'USER' : " + currentUser.hasRole("USER") + "\n";

        return status;
    }

}
