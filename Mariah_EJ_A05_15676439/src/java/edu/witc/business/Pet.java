/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.business;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author 15676439
 */
public class Pet implements Serializable{
    private int customerId;
    private int petId;
    private String petName;
    private String gender;
    private int petTypeId;
    private String birth;
    private int dispoId;
    private LocalDate kennelCoughDate;
    private String breed;
    private String comments;
    private Boolean active;
    private String petTypeShortDesc;
    private String dispoTypeShortDesc;

    public Pet(){
        
    }//end of contructor
    
    //for inserting pet
    public Pet(int customerId, String petName, String gender, int petTypeId, String birth, int dispoId, LocalDate kennelCoughDate, String breed, String comments){
        this.customerId = customerId;
        this.petName = petName;
        this.gender = gender;
        this.petTypeId = petTypeId;
        this.birth = birth;
        this.dispoId = dispoId;
        this.kennelCoughDate = kennelCoughDate;
        this.breed = breed;
        this.comments = comments;
        
    }
    
    //update pet
    public Pet(int petId, int customerId, String petName, String gender, int petTypeId, String birth, int dispoId, LocalDate kennelCoughDate, String breed, String comments){
        this.petId = petId;
        this.customerId = customerId;
        this.petName = petName;
        this.gender = gender;
        this.petTypeId = petTypeId;
        this.birth = birth;
        this.dispoId = dispoId;
        this.kennelCoughDate = kennelCoughDate;
        this.breed = breed;
        this.comments = comments;
        
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getPetId() {
        return petId;
    }

    public String getPetName() {
        return petName;
    }

    public String getGender() {
        return gender;
    }

    public int getPetTypeId() {
        return petTypeId;
    }

    public String getBirth() {
        return birth;
    }

    public int getDispoId() {
        return dispoId;
    }

    public LocalDate getKennelCoughDate() {
        return kennelCoughDate;
    }

    public String getBreed() {
        return breed;
    }

    public String getComments() {
        return comments;
    }

    public Boolean getActive() {
        return active;
    }

    public String getPetTypeShortDesc() {
        return petTypeShortDesc;
    }

    public String getDispoTypeShortDesc() {
        return dispoTypeShortDesc;
    }

    
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPetTypeId(int petTypeId) {
        this.petTypeId = petTypeId;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setDispoId(int dispoId) {
        this.dispoId = dispoId;
    }

    public void setKennelCoughDate(LocalDate kennelCoughDate) {
        this.kennelCoughDate = kennelCoughDate;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setPetTypeShortDesc(String petTypeShortDesc) {
        this.petTypeShortDesc = petTypeShortDesc;
    }

    public void setDispoTypeShortDesc(String dispoTypeShortDesc) {
        this.dispoTypeShortDesc = dispoTypeShortDesc;
    }
    
    

    


}//end of class

