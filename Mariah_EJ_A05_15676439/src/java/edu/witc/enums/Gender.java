/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.enums;

/**
 *
 * @author 15676439
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    UNKNOWN("Unknown");

    private final String displayValue;

    private Gender(final String value) {
        displayValue = value;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}