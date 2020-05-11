/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.pet.servlet;

import edu.witc.business.Customer;
import edu.witc.business.Pet;
import edu.witc.business.PetType;
import edu.witc.data.CustomerDb;
import edu.witc.data.PetDb;
import static edu.witc.data.PetDb.insertPet;
import edu.witc.data.TypeDb;
import edu.witc.utility.DateUtil;
import static edu.witc.utility.DateUtil.DateFormats.ISO_LOCAL_DATE;
import edu.witc.utility.ValidatorUtil;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 15676439
 */
public class PetServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @param url url forwarding to
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
        String url;
        
        //get the session 
        HttpSession session = request.getSession();

        loadPageData(request);

        switch (actionRequest) {
            case "human":
                //goes to customer page
                url = "/customer.jsp";
                break;
            case "manage":
                //goes to manage page
                url = "/error_404.jsp";
                break;
            case "searchCustomers":
                url = showCustomers(request, response);
                break;
            case "viewPets":
                url = getPetList(request, response);
                break;
            case "editCustomer":
                url = getEditCustomer(request, response);
                break;
            case "editPet":
                url = getEditPet(request, response);
                break;
            case "anotherCustomer":
                session.removeAttribute("customer");
                url = "/index.jsp";
                break;
            case "anotherPet":
                session.removeAttribute("pet");
                url = "/pet.jsp";
                break;
            default:
                url = "/error_404.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
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

        switch (actionRequest) {
            case "addCustomer":
                url = doCustomer(request, response);
                break;
            case "addPet":
                url = doPet(request, response);
                break;

        }

        //getServletContext().getRequestDispatcher(url).forward(request, response);
        processRequest(request, response, url);
    }

    private String showCustomers(HttpServletRequest request, HttpServletResponse response) {
        String url = "/manage_customer.jsp";
        String phone;
        String lastName;
        String message;
        String selectedSearch = request.getParameter("search");

        //get the session 
        HttpSession session = request.getSession();

        session.removeAttribute("customer");
        session.removeAttribute("pet");
        session.removeAttribute("pets");
        session.removeAttribute("lastName");
        session.removeAttribute("phone");
        List<Customer> customerList;

        switch (selectedSearch) {
            case "phone":
                phone = request.getParameter("searchTextBox");
                if (validPhone(phone)) {
                    customerList = CustomerDb.getCustomersByPhoneNumber(phone);
                    
                    if (customerList.size() > 0) {
                        session.setAttribute("customerList", customerList);
                    } else {
                        url = "/customer.jsp";
                        session.setAttribute("phone", phone);
                    }
                    
                } else {
                    message = "Not valid phone";
                    request.setAttribute("message", message);
                }   break;
            case "lastName":
                lastName = request.getParameter("searchTextBox");
                if (ValidatorUtil.isAlphabetic(lastName)) {
                    customerList = CustomerDb.getCustomersByLastName(lastName);
                    
                    if (customerList.size() > 0) {
                        session.setAttribute("customerList", customerList);
                    } else {
                        url = "/customer.jsp";
                        session.setAttribute("lastName", lastName);
                    }
                } else {
                    message = "Not valid last name";
                    request.setAttribute("message", message);
                }   break;
            case "allCustomers":
                customerList = CustomerDb.getAllCustomers();
                session.setAttribute("customerList", customerList);
                break;
            default:
                break;
        }

        return url;
    }

    private String showPets(HttpServletRequest request, HttpServletResponse response, int customerId) {
        String url = "/manage_pet.jsp";

        //get the session 
        HttpSession session = request.getSession();

        session.removeAttribute("pet");
        session.removeAttribute("pets");

        List<Pet> petList = PetDb.getPetsByCustomerId(customerId);

        if (petList.isEmpty()) {
            url = "/pet.jsp";
        } else if (petList.size() == 1) {
            Pet pet = petList.get(0);
            session.setAttribute("pet", pet);
            int petTypeId = pet.getPetTypeId();
            int dispoTypeId = pet.getDispoId();
            String selectedGender = pet.getGender();
            session.setAttribute("petTypeId", petTypeId);
            session.setAttribute("dispoTypeId", dispoTypeId);
            session.setAttribute("selectedGender", selectedGender);
            url = "/pet.jsp";
        } else {
            session.setAttribute("petList", petList);
        }

        return url;
    }
    private String getPetList(HttpServletRequest request, HttpServletResponse response){
        String url;
        String customerIdString = request.getParameter("customer_id");
        int customerId = Integer.parseInt(customerIdString);
        HttpSession session = request.getSession();
        
        Customer customer = CustomerDb.getCustomerById(customerId);
        customer.setCustomerId(customerId);
        
        session.setAttribute("customer", customer);
       
        url = showPets(request, response, customerId);
        
        return url;
    }
    private String getEditCustomer(HttpServletRequest request, HttpServletResponse response) {
        String url = "/customer.jsp";
        String customerIdString = request.getParameter("customer_id");
        //get the session 
        HttpSession session = request.getSession();

        if (customerIdString.equals("")) {
            url = "/customer.jsp";
        } else {
            int customerId = Integer.parseInt(customerIdString);
            Customer customer = CustomerDb.getCustomerById(customerId);
            int stateTypeId = customer.getState();
            session.setAttribute("customer", customer);
            session.setAttribute("customerIdString", customerIdString);
            session.setAttribute("stateTypeId", stateTypeId);
        }

        return url;
    }

    private String getEditPet(HttpServletRequest request, HttpServletResponse response) {
        String url = "/pet.jsp";
        String petIdString = request.getParameter("pet_id");
        //get the session 
        HttpSession session = request.getSession();

        int petId = Integer.parseInt(petIdString);
        Pet pet = PetDb.getPetById(petId);
        int petTypeId = pet.getPetTypeId();
        int dispoTypeId = pet.getDispoId();
        String selectedGender = pet.getGender();
        session.setAttribute("pet", pet);
        session.setAttribute("petIdString", petIdString);
        session.setAttribute("petTypeId", petTypeId);
        session.setAttribute("dispoTypeId", dispoTypeId);
        session.setAttribute("selectedGender", selectedGender);

        return url;
    }

    private String doCustomer(HttpServletRequest request, HttpServletResponse response) {

        //set url to the customer page
        String url = "/customer.jsp";

        //get the values of the data entered on customer page
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String streetAddress = request.getParameter("street_address");
        String city = request.getParameter("city");
        String selectedState = request.getParameter("state");
        String postalCode = request.getParameter("postal_code");
        String comments = request.getParameter("customer_comments");

        //create message variable to set error message
        String message;

        //set the attributes for all fields
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("phone", phone);
        request.setAttribute("email", email);
        request.setAttribute("streetAddress", streetAddress);
        request.setAttribute("city", city);
        request.setAttribute("selectedState", selectedState);
        request.setAttribute("postalCode", postalCode);
        request.setAttribute("comments", comments);

        Customer customer;

        //get the session 
        HttpSession session = request.getSession();

        //create variable for state id
        int stateTypeId = 0;

        //create bool to validate form
        Boolean isValidForm = true;

        //validate first name
        if (!ValidatorUtil.hasText(firstName)) {
            isValidForm = false;
        }

        //validate last name
        if (!ValidatorUtil.hasText(lastName)) {
            isValidForm = false;
        }

        //validate street address
        if (!ValidatorUtil.hasText(streetAddress)) {
            isValidForm = false;
        }

        //validate city
        if (!ValidatorUtil.hasText(city)) {
            isValidForm = false;
        }

        //validate postal code
        if (!ValidatorUtil.hasText(postalCode)) {
            isValidForm = false;
        }

        //validate email if blank set to Not on File 
        String hasEmail;

        if (ValidatorUtil.isValidEmail(email)) {
            hasEmail = email;
        } else {
            hasEmail = "Not on File";
        }

        //validate email if has text
        if (!ValidatorUtil.hasText(hasEmail)) {
            isValidForm = false;
        }

        //validate the selected state
        if (selectedState.equals("default")) {
            isValidForm = false;

        } else {
            stateTypeId = Integer.parseInt(selectedState);
            request.setAttribute("stateTypeId", stateTypeId);
        }

        //validate the length of phone
        if (!validPhone(phone)) {
            isValidForm = false;
        }

        //if the form is valid
        if (isValidForm) {
            String customerIdString;
            //format phone
            String formattedPhone = ValidatorUtil.replaceNonNumerics(phone);
            
          try{
            customerIdString = session.getAttribute("customerIdString").toString();
          }
          catch(Exception e){
              customerIdString = "";
          }

            if (customerIdString.equals("")) {
                //creat customer object
                customer = new Customer(firstName, lastName, formattedPhone, hasEmail, streetAddress, city, stateTypeId, postalCode, comments);
                //insert customer into database
                int customerId = CustomerDb.insertCustomer(customer);

                //get the customer id
                if (customerId > 0) {
                    customer.setCustomerId(customerId);
                    session.setAttribute("customer", customer);

                }
                url = "/pet.jsp";
            } else {

                customer = new Customer(Integer.parseInt(customerIdString), firstName, lastName, formattedPhone, hasEmail, streetAddress, city, stateTypeId, postalCode, comments);
                int rowsEffected = CustomerDb.updateCustomer(customer);

                if (rowsEffected > 0) {
                    session.setAttribute("customer", customer);

                }

                url = showPets(request, response, Integer.parseInt(customerIdString));

            }

        } else {
            //if there is an error onn the form
            message = "Please complete the form";
            request.setAttribute("message", message);
        }

        return url;
    }

    private String doPet(HttpServletRequest request, HttpServletResponse response) {

        //set url for pet page
        String url = "/pet.jsp";

        //get the value of inputed data on pet page
        String petName = request.getParameter("pet_name");
        String selectedGender = request.getParameter("select_gender");
        String birth = request.getParameter("pet_birth");
        String selectedPetType = request.getParameter("pet_type");
        String selectedDisposition = request.getParameter("select_dispo");
        String kennelCoughDate = request.getParameter("kennel_cough_date");
        String comments = request.getParameter("pet_comments");

        Pet pet;
        //set breed to blank
        String breed = "";

        //create message variable to set error message
        String message;

        //set the attributes for all fields
        request.setAttribute("petName", petName);
        request.setAttribute("birth", birth);
        request.setAttribute("selectedPetType", selectedPetType);
        request.setAttribute("selectedGender", selectedGender);
        request.setAttribute("selectedDisposition", selectedDisposition);
        request.setAttribute("kennelCoughDate", kennelCoughDate);
        request.setAttribute("comments", comments);
        HttpSession session = request.getSession();

        //set pet type id and dispo type id
        int petTypeId = 0;
        int dispoTypeId = 0;

        //create LocalDate variable for kennel cough
        LocalDate kennelCoughDateLocal = null;

        //create variable to validate the form
        Boolean isValidForm = true;

        //validate pet name 
        if (!ValidatorUtil.isAlphabetic(petName)) {
            isValidForm = false;
        }
        
        //validate birth 
        if (birth.length() > 7) {
            isValidForm = false;
        }

        //validate the selected gender
        if (selectedGender.equals("default")) {
            isValidForm = false;
        }

        //validate the selcted disposition
        if (selectedDisposition.equals("default")) {
            isValidForm = false;
        } else {
            dispoTypeId = Integer.parseInt(selectedDisposition);
            request.setAttribute("dispoTypeId", dispoTypeId);
        }

        //validate the selcted pet type
        if (selectedPetType.equals("defaul")) {
            isValidForm = false;
        } else {
            petTypeId = Integer.parseInt(selectedPetType);
            request.setAttribute("petTypeId", petTypeId);
        }

        //validate the kennel cough date
        try {
            kennelCoughDateLocal = DateUtil.getLocalDateFromString(kennelCoughDate, ISO_LOCAL_DATE);
            if (kennelCoughDateLocal.isAfter(LocalDate.now())) {
                isValidForm = false;
            }
        } catch (Exception e) {
            isValidForm = false;
        }

        //if the form is all valid this runs
        if (isValidForm) {
            
            String petIdString;
            
        try{
            petIdString = session.getAttribute("petIdString").toString();
          }
          catch(Exception e){
              petIdString = "";
          }

            //get customer object
            Customer customer = (Customer) session.getAttribute("customer");

            //create pet object
            if (petIdString.equals("")) {

                pet = new Pet(customer.getCustomerId(), petName, selectedGender, petTypeId, birth, dispoTypeId, kennelCoughDateLocal, breed, comments);
                //insert the pet into the database
                insertPet(pet);

                //get the long description of pet type to display on results page
                String petTypeLongDesc = getPetType(request, pet.getPetTypeId());
                session.setAttribute("petTypeLongDesc", petTypeLongDesc);

                
                session.setAttribute("pet", pet);
                
                url = showPets(request, response, customer.getCustomerId());
                
                
                
            } else {

                pet = new Pet(Integer.parseInt(petIdString), customer.getCustomerId(), petName, selectedGender, petTypeId, birth, dispoTypeId, kennelCoughDateLocal, breed, comments);
                int rowsEffected = PetDb.updatePet(pet);
                
                url = showPets(request, response, customer.getCustomerId());

                if (rowsEffected > 0) {
                    session.setAttribute("pet", pet);

                }
            }

        } else {
            
            if (birth.length() > 7) {
                
                birth = "";
                message = "Birthday must be in MM/YYYY format.";
                request.setAttribute("birth", birth);
                request.setAttribute("message", message);
        }else{
            //if there is an error on the form
            message = "Please complete the form";
            request.setAttribute("message", message);
            }
        }

        return url;
    }

    private boolean validPhone(String phone) {
        boolean validPhone = false;

        phone = ValidatorUtil.replaceNonNumerics(phone);
        if (phone.length() == 10) {
            if (ValidatorUtil.isPositiveWholeNumber(phone)) {
                validPhone = true;
            }
        }

        return validPhone;
    }

    //get the long description of pet type
    private String getPetType(HttpServletRequest request, int value) {
        List<PetType> petType = (List<PetType>) request.getSession().getAttribute("petType");
        String currentType = "";
        for (PetType one : petType) {
            if (one.getPetTypeId() == value) {
                currentType = one.getLongDesc();
            }
        }

        return currentType;
    }

    //load the page data for the drop down lists
    private void loadPageData(HttpServletRequest request) {

        List<PetType> petType = TypeDb.getAllPets();

        HttpSession session = request.getSession();

        session.setAttribute("petType", petType);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
