/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.shiro.authorization.custom;

import com.josue.shiro.authorization.auth.RoleLevel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 * @author Josue
 */
public class CustomRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Authorizer aut;
        JdbcRealm realm;
        WildcardPermission wilcard;
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String foundPassword = Arrays.toString(upToken.getPassword());
        if (foundPassword == null) {
            throw new AuthenticationException("No account found for username  " + upToken.getUsername());
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(upToken.getPrincipal(), foundPassword, getName());

        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String principalUsername = (String) getAvailablePrincipal(principals);

        //FETCH FROM DATABASE.. OR SO.
        RoleLevel fetchedPermission = RoleLevel.LEVEL_1;
        String fetchedDomainName = "uuid-doc-123-TODO-check-if-OK";
        // ... multiple permissions map

        AccessLevelPermission perm = new AccessLevelPermission();
        perm.addAccessLevel(fetchedDomainName, fetchedPermission);

        Set<Permission> permissions = new HashSet<>();
        permissions.add(perm);

        info.setObjectPermissions(permissions);
        //TODO each map key is mapped as a role, jus a example of use
        info.setRoles(new HashSet<>(Arrays.asList(fetchedDomainName)));
        return info;
    }
}
