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
public class PetType implements Serializable{
    private int petTypeId;
    private String shortDesc;
    private String longDesc;
    private boolean active;
    
    
    public PetType(){
       
    }//end of constructor
    
    public PetType(int id, String shortDesc, String longDesc, boolean isActive){
        this.petTypeId = id;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.active = isActive;
    }

    public int getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(int dispoId) {
        this.petTypeId = dispoId;
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