/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.business;

import java.io.Serializable;

/**
 *
 * @author 15676439
 */
public class StateType implements Serializable{
    private int stateId;
    private String shortDesc;
    private String longDesc;
    private boolean active;
    
    
    public StateType(){
       
    }//end of constructor
    
    public StateType(int id, String shortDesc, String longDesc, boolean isActive){
        this.stateId = id;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.active = isActive;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int dispoId) {
        this.stateId = dispoId;
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