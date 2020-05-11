/* 
 * Copyright 2017 Rene Bylander at WITC
 */

package edu.witc.business;

import java.io.Serializable;

/**
 * @author Rene Bylander
 * @created Sep 14, 2017
 */
public class DispositionType implements Serializable{
    private int dispoId;
    private String shortDesc;
    private String longDesc;
    private boolean active;
    
    
    public DispositionType(){
       
    }//end of constructor
    
    public DispositionType(int id, String shortDesc, String longDesc, boolean isActive){
        this.dispoId = id;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.active = isActive;
    }

    public void setDispoId(int dispoId) {
        this.dispoId = dispoId;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public int getDispoId() {
        return dispoId;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

  



}//end of class
