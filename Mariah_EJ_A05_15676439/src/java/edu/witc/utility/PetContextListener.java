/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.utility;

import edu.witc.business.DispositionType;
import edu.witc.business.StateType;
import edu.witc.data.TypeDb;
import edu.witc.enums.Gender;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Owner
 */
public class PetContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        
        String customerServiceEmail = sc.getInitParameter("customerServiceEmail");
        sc.setAttribute("customerServiceEmail", customerServiceEmail);
        
        String copyrightYear = sc.getInitParameter("copyrightYear");
        sc.setAttribute("copyrightYear", copyrightYear);
        
        List<String> genderEnums = new ArrayList<>();
        List<DispositionType> dispoType = TypeDb.getAllDispositions();
        List<StateType> stateType = TypeDb.getAllStates();

        for (Gender gender : Gender.values()) {
            genderEnums.add(gender.toString());
        }

        sc.setAttribute("genderEnums", genderEnums);
        sc.setAttribute("dispoType", dispoType);
        sc.setAttribute("stateType", stateType);
        
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        
        ServletContext sc = event.getServletContext();
        
        sc.removeAttribute("genderEnums");
        sc.removeAttribute("dispoType");
        sc.removeAttribute("stateType");
    }
}
