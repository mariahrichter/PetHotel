/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.business;

import java.io.Serializable;

/**
 *
 * @author Owner
 */
public class RoleType implements Serializable {
    private int roleId;
    private String shortDesc;
    private String longDesc;
    private boolean active;
    
    
    public RoleType(){
       
    }//end of constructor
    
    public RoleType(int id, String shortDesc, String longDesc, boolean isActive){
        this.roleId = id;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.active = isActive;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int id) {
        this.roleId = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
