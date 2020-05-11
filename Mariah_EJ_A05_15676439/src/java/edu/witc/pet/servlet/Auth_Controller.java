/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.pet.servlet;

import edu.witc.business.Employee;
import edu.witc.data.EmployeeDb;
import edu.witc.utility.PasswordUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Owner
 */
public class Auth_Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
private void processRequest(HttpServletRequest request, 
            HttpServletResponse response, String url) throws ServletException, IOException{
    //sendRedirect does NOT resubmit on a post BUT needed data MUST be in a session object
    //sendRedirect's url cannot start with a slash.
        if("POST".equalsIgnoreCase(request.getMethod())){
            if(url.startsWith("/"))
                url = url.substring(1);

            response.sendRedirect(url);
           
        }
        else{
             getServletContext().getRequestDispatcher(url)
                    .forward(request, response);
        }
    
    
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
        
        switch(actionRequest){
            case "auth_login":
                url = authLogin(request, response);
                break;
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    private String authLogin(HttpServletRequest request, HttpServletResponse response) {
        String url;
        HttpSession session = request.getSession();
        boolean isValid = false;
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        Employee employee = EmployeeDb.getEmployeeByUsername(userName);
        

        if (employee == null) {
            url = "/login_error.jsp";
        } else {
            String salt;
            String passwordCompare;
            String hashCompare;
            try {
                salt = employee.getSalt();
                passwordCompare = salt.concat(password);
                hashCompare = PasswordUtil.hashPassword(passwordCompare);

            } catch (NoSuchAlgorithmException ex) {
                hashCompare = ex.getMessage();
            }
            
            if (hashCompare.equals(employee.getHash())) {
                url = "/admin/index.jsp";
            } else {

                url = "/login_error.jsp";
            }
            
            

        }
        return url;
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
