/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.shiro.cdi.custom;

import com.josue.shiro.cdi.persistence.FakeAccountDAO;
import com.josue.shiro.cdi.persistence.JPAEntity;
import javax.inject.Inject;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 *
 * @author Josue
 */
public class CDIAwareJPARealm extends AuthenticatingRealm {

    //Here we can inject other beans because 'CDIAwareJPARealm' is CDI aware (see CustomEnvironmentLoaderListener)
    @Inject
    FakeAccountDAO persistence;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;

        //Make use of JPA
        JPAEntity entity = persistence.fetchUserFromDb("uuid-1234");

        if (entity != null) {
            return new SimpleAuthenticationInfo(entity.getUuid(), entity.getPassword(), getName());
        } else {
            return null;
        }
    }

}
