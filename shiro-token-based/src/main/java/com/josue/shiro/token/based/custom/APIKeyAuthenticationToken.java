/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.shiro.token.based.custom;

import org.apache.shiro.authc.AuthenticationToken;

/**
 *
 * @author Josue
 */
public class APIKeyAuthenticationToken implements AuthenticationToken {

    private final String token;

    public APIKeyAuthenticationToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
