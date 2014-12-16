/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.shiro.cdi.persistence;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Josue
 */
@ApplicationScoped
public class FakeAccountDAO {

    public JPAEntity fetchUserFromDb(String uuid) {
        //run jpql or em.find(...)
        return new JPAEntity(uuid, "josue", "pass123");
    }

    public JPAEntity fetchRolesFromDb(String uuid) {
        //run jpql or em.find(...)
        return new JPAEntity(uuid, "josue", "pass123");
    }

}
