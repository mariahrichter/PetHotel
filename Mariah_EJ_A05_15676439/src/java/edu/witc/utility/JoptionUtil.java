/* 
 * Copyright 2017 Rene Bylander at WITC
 */

package edu.witc.utility;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * @author Rene Bylander
 * @created Jan 25, 2017
 */
public class JoptionUtil extends JOptionPane{
 
  
    public static void displayMessage(Component parent, String message, String title, int messageType){
               
        JOptionPane.showMessageDialog(parent, message, title, messageType);
    }
    
    public static int getConfirmation(Component parent, String message, String title, int option, int messageType){
    
        int userAnswer;
        userAnswer = JOptionPane.showConfirmDialog(parent, message, title, option, messageType);
        
        return userAnswer;    
    }

    public static String getUserInput(Component parent, String message, String title, int messageType) {
        String input;
        
        input = JOptionPane.showInputDialog(parent, message, title, messageType);
        return input;
    }
    
    public static int getCustomOption(String message, String title, int defaultOption, 
            int messageType, Object[] options, String initialOption){
        
        int chosenOption;
        //set your chosenOption variable equal to the button the end-user clicks
        chosenOption = JOptionPane.showOptionDialog(null, message, 
                title, defaultOption, messageType, null, options, initialOption); 
        
        return chosenOption;
    
    }

}//end of class