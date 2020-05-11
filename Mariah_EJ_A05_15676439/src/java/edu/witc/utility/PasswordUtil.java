/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 *
 * @author Owner
 */
public class PasswordUtil {

    public static String hashPassword(String password)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.reset();
        md.update(password.getBytes());
        byte[] mdArray = md.digest();
        StringBuilder sb = new StringBuilder(mdArray.length * 2);
        for (byte b : mdArray) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    public static String getSalt() {
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    public static String hashAndSaltPassword(String password)
            throws NoSuchAlgorithmException {
        String salt = getSalt();
        return hashPassword(password + salt);
    }

    public static void checkPasswordStrength(String password) throws Exception {
        if (password == null || password.trim().isEmpty()) {
            throw new Exception("Password cannot be empty.");
        } else if (password.length() < 8) {
            throw new Exception("Password is to short. "
                    + "Must be at least 8 characters long.");
        }
    }

    public static boolean validatePassword(String password) {
        try {
            checkPasswordStrength(password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public static String generatePassword(){
        String charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_-+!@#$%";
        String password = "";
        int start = 0;
        int stop = 0;
        int minLength = 8;
        
        for (int i = 0; i <= minLength; i++) {
                // get a random character from the chars string
                start = getRandomNumber(charSet.length());
                stop = start + 1;
                password += charSet.substring(start, stop);
        }
        
        return password;
    
    }
    private static int getRandomNumber(int maxValue){
        double randomNumber;
        randomNumber = Math.floor(Math.random() * maxValue);
        
        return (int)randomNumber;
    }

    /*  This code tests the functionality of this class.
     */
    public static void main(String[] args) {
        try {
            System.out.println("Hash for 'sesame':\n"
                    + hashPassword("sesame"));
            System.out.println("Random salt:\n"
                    + getSalt());
            System.out.println("Salted hash for 'sesame':\n"
                    + hashAndSaltPassword("sesame"));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }

        try {
            checkPasswordStrength("sesame1776");
            System.out.println("Password is valid.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
