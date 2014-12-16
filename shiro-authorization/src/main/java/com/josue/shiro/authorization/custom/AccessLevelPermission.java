/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.shiro.authorization.custom;

import com.josue.shiro.authorization.auth.RoleLevel;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.authz.Permission;

/**
 *
 * @author Josue
 */
public class AccessLevelPermission implements Permission {

    private final Map<Object, RoleLevel> accessLevel;

    public AccessLevelPermission() {
        accessLevel = new HashMap<>();
    }

    public AccessLevelPermission(Object key, RoleLevel expectedPerm) {
        accessLevel = new HashMap<>();
        accessLevel.put(key, expectedPerm);
    }

    @Override
    public boolean implies(Permission p) {
        if (!(p instanceof AccessLevelPermission)) {
            return false;
        }
        AccessLevelPermission permission = (AccessLevelPermission) p;

        for (Map.Entry<Object, RoleLevel> entry : permission.getAccessLevels().entrySet()) {
            Object domain = entry.getKey();
            RoleLevel level = entry.getValue();

            if (this.getAccessLevels().containsKey(domain)) {
                RoleLevel thisLevel = this.getAccessLevels().get(domain);
                if (thisLevel.compareTo(level) >= 0) {
                    return true;
                }
            }
        }
        return false;

    }

    public Map<Object, RoleLevel> getAccessLevels() {
        return accessLevel;
    }

    public void addAccessLevel(String domain, RoleLevel perm) {
        accessLevel.put(domain, perm);
    }

}
