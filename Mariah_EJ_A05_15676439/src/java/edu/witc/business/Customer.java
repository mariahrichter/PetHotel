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
public class Customer implements Serializable{
    private int customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String streetAddress;
    private String city;
    private int state;
    private String postalCode;
    private String comments;
    private String formattedPhone;
    private Boolean active;
    private String stateShortDesc;
    
    public Customer() {
        
    }//end of contructor

    //inserting customer
    public Customer(String firstName, String lastName, String phone, String email, String streetAddress, 
            String city, int state, String postalCode, String comments){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.comments = comments;

    }
        //displaying customer list
        public Customer(int customerId, String firstName, String lastName, String phone, String email, String streetAddress, 
            String city, int state, String postalCode, String comments, Boolean active, String stateShortDesc){
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.comments = comments;
        this.active = active;
        this.stateShortDesc = stateShortDesc;
    }
    
        //updating customer
        public Customer(int customerId, String firstName, String lastName, String phone, String email, String streetAddress, 
            String city, int state, String postalCode, String comments){
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.comments = comments;

    }    
        
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFormattedPhone(){
        formattedPhone = phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        
        return formattedPhone;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public int getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getComments() {
        return comments;
    }

    public Boolean getActive() {
        return active;
    }

    public String getStateShortDesc() {
        return stateShortDesc;
    }
    

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setFormattedPhone(String formattedPhone) {
        this.formattedPhone = formattedPhone;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setStateShortDesc(String stateShortDesc) {
        this.stateShortDesc = stateShortDesc;
    }

    
}//end of class