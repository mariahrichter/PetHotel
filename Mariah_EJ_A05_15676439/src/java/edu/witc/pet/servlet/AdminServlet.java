/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.pet.servlet;

import edu.witc.business.Employee;
import edu.witc.business.Pet;
import edu.witc.business.PetType;
import edu.witc.business.RoleType;
import edu.witc.business.StateType;
import static edu.witc.data.AdminDb.insertEmployee;
import edu.witc.data.PetDb;
import edu.witc.data.TypeDb;
import edu.witc.utility.PasswordUtil;
import edu.witc.utility.ValidatorUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Owner
 */
public class AdminServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @param url
     * 
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
                
            getServletContext().getRequestDispatcher(url)
                .forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String actionRequest = request.getParameter("actionRequest");
        String url = "";

        loadPageData(request);
        
        switch (actionRequest) {
            case "manageTypes":
                //goes to manage types page
                url = "/admin/manage_types.jsp";
                break;
            case "manageEmployees":
                //goes to admin page
                url = "/admin/manage_employees.jsp";
                break;
            case "add_another_petType":
                //adds new pet type
                url = addNewPetType(request, response);
                break;
            case "show_petType_edit":
                //goes to edit pet type page
                url = showPetTypeEdit(request, response);
                break;
            case "add_another_stateType":
                //adds another state
                url = addNewState(request, response);
                break;
            case "show_stateType_edit":
                //goes to edit state page
                url = showStateEdit(request, response);
                break;
            case "show_pet_list":
                //petlist
                url = getPetList(request, response);
                break;
                
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    
    private String getPetList(HttpServletRequest request, HttpServletResponse response){
        String url = "/admin/manage_pet_list.jsp";
        HttpSession session = request.getSession();
        
        List<Pet> petList = PetDb.getAllPets();
        session.setAttribute("petList", petList);
    
        return url;
    }
    
    private String addNewPetType(HttpServletRequest request, HttpServletResponse response){
        String url = "/admin/manage_types.jsp";
        String newShortDesc = request.getParameter("new_short_desc");
        String newLongDesc = request.getParameter("new_long_desc");
        
        if(ValidatorUtil.hasText(newShortDesc) && ValidatorUtil.hasText(newLongDesc)){
            
            PetType newPetType = new PetType(-1, newShortDesc, newLongDesc, true);
            TypeDb.insertPetType(newPetType);
            List<PetType> petType = TypeDb.getAllPets();
            request.getSession().setAttribute("petType", petType);
            
        }
        else{
            String message = "please complete the form";
            request.setAttribute("message", message);
            url="/admin/manage_types.jsp";
        
        }       
    
        return url;
    }
    
    private String addNewState(HttpServletRequest request, HttpServletResponse response){
        String url = "/admin/manage_types.jsp";
        String newShortDesc = request.getParameter("new_short_desc");
        String newLongDesc = request.getParameter("new_long_desc");
        
        if(ValidatorUtil.hasText(newShortDesc) && ValidatorUtil.hasText(newLongDesc)){
            
            StateType newState = new StateType(-1, newShortDesc, newLongDesc, true);
            TypeDb.insertState(newState);
            List<StateType> stateType = TypeDb.getAllStates();
            request.getSession().setAttribute("stateType", stateType);
            
        }
        else{
            String message = "please complete the form";
            request.setAttribute("message", message);
            url="/admin/manage_types.jsp";
        
        }
        
    
        return url;
    }
    private String showPetTypeEdit(HttpServletRequest request, HttpServletResponse response){
        String url = "/admin/edit_pet_type.jsp";
        String petTypeIdString = request.getParameter("pet_type_id");
        int petTypeId = Integer.parseInt(petTypeIdString);
        HttpSession session = request.getSession();
        
        PetType petType = TypeDb.getPetTypeById(petTypeId);
        session.setAttribute("petType", petType);
        
        return url;
    }
     private String showStateEdit(HttpServletRequest request, HttpServletResponse response){
        String url = "/admin/edit_state.jsp";
        String stateIdString = request.getParameter("state_id");
        int stateId = Integer.parseInt(stateIdString);
        HttpSession session = request.getSession();
        
        StateType stateList = TypeDb.getStateById(stateId);
        session.setAttribute("stateList", stateList);
        
        return url;
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String actionRequest = request.getParameter("actionRequest");
        String url = "";
         //get the session 
        HttpSession session = request.getSession();
                
        switch (actionRequest) {
            case "update_pet_type":
                //updates pet type
                url = updatePetType(request, response);
                break;
            case "update_state":
                //updates pet type
                url = updateState(request, response);
                break;
            case "generate_password":
                url = generatePassword(request, response);
                break;
            case "clear_details":
                session.removeAttribute("firstName");
                session.removeAttribute("lastName");
                session.removeAttribute("roleTypeLongDesc");
                session.removeAttribute("password");
                url = "/admin/manage_employees.jsp";
                break;
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    
    
    private String updatePetType(HttpServletRequest request, HttpServletResponse response){
        String url = "/admin/manage_types.jsp";
        String petTypeIdString = request.getParameter("pet_type_id");
        int petTypeId = Integer.parseInt(petTypeIdString);
        String newShortDesc = request.getParameter("short_desc");
        String newLongDesc = request.getParameter("long_desc");
        
        HttpSession session = request.getSession();

        
        if(ValidatorUtil.hasText(newShortDesc) && ValidatorUtil.hasText(newLongDesc)){
            
            PetType updatePetType = new PetType(petTypeId, newShortDesc, newLongDesc, true);
            TypeDb.updatePetType(updatePetType);
            List<PetType> petType = TypeDb.getAllPets();
            request.getSession().setAttribute("petType", petType);
        }
        else{
            String message = "please complete the form";
            request.setAttribute("message", message);
            
            url="manage_types.jsp";
        
        }
        return url;
    }

    private String updateState(HttpServletRequest request, HttpServletResponse response){
        String url = "/admin/manage_types.jsp";
        String stateIdString = request.getParameter("state_id");
        int stateId = Integer.parseInt(stateIdString);
        String newShortDesc = request.getParameter("short_desc");
        String newLongDesc = request.getParameter("long_desc");
        
        if(ValidatorUtil.hasText(newShortDesc) && ValidatorUtil.hasText(newLongDesc)){
            
            StateType updateState = new StateType(stateId, newShortDesc, newLongDesc, true);
            TypeDb.updateStateType(updateState);
            List<StateType> stateList = TypeDb.getAllStates();
            request.getSession().setAttribute("stateList", stateList);
        }
        else{
            String message = "please complete the form";
            request.setAttribute("message", message);
            
            url="manage_types.jsp";
        
        }
        return url;
    }
    
    private String generatePassword(HttpServletRequest request, HttpServletResponse response) {
        String url = "/admin/manage_employees.jsp";
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String userName = request.getParameter("user_name");
        String selectedRole = request.getParameter("select_role");
        int roleTypeId;
        roleTypeId = Integer.parseInt(selectedRole);
        Boolean isValidForm = true;
        String message;
        String resultsMessage;
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("userName", userName);
        request.setAttribute("selectedRole", selectedRole);
        HttpSession session = request.getSession();

        if (selectedRole.equals("default")) {
            isValidForm = false;
        }

        if (!ValidatorUtil.hasText(firstName)) {
            isValidForm = false;
        }

        if (!ValidatorUtil.hasText(lastName)) {
            isValidForm = false;

        }
        if (!ValidatorUtil.hasText(userName)) {
            isValidForm = false;

        }

        if (isValidForm == false) {

            message = "please complete the form";
            request.setAttribute("message", message);

            url = "/admin/manage_employees.jsp";
        } else {
            String password = "";
            String salt;
            String passwordCompare;
            String hashCompare;
            try {
                password = PasswordUtil.generatePassword();
                salt = PasswordUtil.getSalt();
                passwordCompare = salt.concat(password);
                hashCompare = PasswordUtil.hashPassword(passwordCompare);

            } catch (NoSuchAlgorithmException ex) {
                hashCompare = ex.getMessage();
                salt = ex.getMessage();
            }

            request.setAttribute("hashCompare", hashCompare);

            Employee employee = new Employee(firstName, lastName, userName, roleTypeId, salt, hashCompare);           
            //insert the employe into the database
            insertEmployee(employee);
            
            //get long desc of role type
            String roleTypeLongDesc = getRoleType(request, roleTypeId);
            session.setAttribute("roleTypeLongDesc", roleTypeLongDesc);
            
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("password", password);
            request.setAttribute("salt", salt);

            if (hashCompare.equals(employee.getHash())) {
                resultsMessage = "Password Comparison worked.";
                request.setAttribute("resultsMessage", resultsMessage);
            } else {
                resultsMessage = "Error. Password Comparison Failed.";
                request.setAttribute("resultsMessage", resultsMessage);
            }
        }

        return url;

    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private String getRoleType(HttpServletRequest request, int value) {
        List<RoleType> roleType = (List<RoleType>) request.getSession().getAttribute("roleType");
        String currentType = "";
        for (RoleType one : roleType) {
            if (one.getRoleId() == value) {
                currentType = one.getLongDesc();
            }
        }

        return currentType;
    }
    
    private void loadPageData(HttpServletRequest request) {

        List<PetType> petType = TypeDb.getAllPets();
        List<RoleType> roleType = TypeDb.getAllRoleTypes();

        HttpSession session = request.getSession();

        session.setAttribute("roleType", roleType);
        session.setAttribute("petType", petType);

    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>





}
