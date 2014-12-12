/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.shiro.token.based.custom;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 *
 * @author Josue
 */
public class APIKeyAuthenticatingFilter extends AuthenticatingFilter {

    private static final String API_KEY = "ApiKey";

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        boolean loggedIn = executeLogin(request, response);
        return loggedIn;

//        if (apiToken.getPrincipal() == null) {
//            return validToken;
//        } else if (apiToken.getPrincipal().equals("123")) {
//            validToken = true;
//        }
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String token = httpRequest.getHeader(API_KEY);
        APIKeyAuthenticationToken apiToken = new APIKeyAuthenticationToken(token);
        return apiToken;
    }

    //Custom failure response
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            httpResponse.getWriter().write("INVALID TOKEN: " + token.getPrincipal());
        } catch (IOException ex) {
            Logger.getLogger(APIKeyAuthenticatingFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return true;
    }

}
