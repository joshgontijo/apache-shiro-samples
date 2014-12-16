/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.shiro.cdi.persistence;

/**
 *
 * @author Josue
 */
//@Entity
public class JPAEntity {

//    @Id
    private String uuid;
    private String name;
    private String password;

    public JPAEntity() {
    }

    public JPAEntity(String uuid, String name, String password) {
        this.uuid = uuid;
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
