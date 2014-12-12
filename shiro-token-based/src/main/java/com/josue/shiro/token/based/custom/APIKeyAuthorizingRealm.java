/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.shiro.token.based.custom;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 *
 * @author Josue
 */
public class APIKeyAuthorizingRealm extends AuthenticatingRealm {

    public APIKeyAuthorizingRealm() {
        //IMPORTANT... IT DEFINES WHICH AuthenticationToken CAN BE AUTHENTICATED
        setAuthenticationTokenClass(APIKeyAuthenticationToken.class);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        AuthenticationInfo info = null;
        if ("123".equals(token.getPrincipal())) {
            info = new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
        }
        return info;
    }

}
