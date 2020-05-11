/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.business;

/**
 *
 * @author Owner
 */
public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String userName;
    private int roleTypeId;
    private String salt;
    private String hash;
    private Boolean active;

    
    public Employee(){
    
    }

    public Employee(int employeeId, String firstName, String lastName, Boolean active, String salt, String hash) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.salt = salt;
        this.hash = hash;
    }
    
    //for inserting
     public Employee(String firstName, String lastName, String userName, int roleTypeId, String salt, String hash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.roleTypeId = roleTypeId;
        this.salt = salt;
        this.hash = hash;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getActive() {
        return active;
    }

    public String getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(int roleTypeId) {
        this.roleTypeId = roleTypeId;
    }
    
    
    
}
