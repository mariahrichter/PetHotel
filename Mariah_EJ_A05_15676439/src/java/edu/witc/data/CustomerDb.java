/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.data;

import edu.witc.business.Customer;
import edu.witc.business.DispositionType;
import edu.witc.utility.DateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 15676439
 */
public class CustomerDb {

    public static int insertCustomer(Customer customer) {
        int customerId = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO customer (first_name, last_name, phone, email, street_address, "
                + "city, state_id, postal_code, comments, active, date_added) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getPhone());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getStreetAddress());
            ps.setString(6, customer.getCity());
            ps.setInt(7, customer.getState());
            ps.setString(8, customer.getPostalCode());
            ps.setString(9, customer.getComments());
            ps.setBoolean(10, true);
            ps.setDate(11, DateUtil.getSqlDate());
            
            customerId = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    customerId = rs.getInt(1);
                            }
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        return customerId;
    }
    
        public static int updateCustomer(Customer customer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE customer SET "
                + "first_name = ?, "
                + "last_name = ?, "
                + "phone = ?, "
                + "email = ?, "
                + "street_address = ?, "
                + "city = ?, "
                + "state_id = ?, "
                + "postal_code = ?, "
                + "comments = ?, "
                + "date_modified = ? "
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getPhone());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getStreetAddress());
            ps.setString(6, customer.getCity());
            ps.setInt(7, customer.getState());
            ps.setString(8, customer.getPostalCode());
            ps.setString(9, customer.getComments());
            ps.setDate(10, DateUtil.getSqlDate());
            ps.setInt(11, customer.getCustomerId());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static List<Customer> getAllCustomers(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> customerList = new ArrayList<>();
        
        String sql = "SELECT customer.id, customer.first_name, customer.last_name, customer.phone, customer.email, "
                + "customer.street_address, customer.city, customer.postal_code, customer.active, "
                + "state_list.short_desc "
                + "FROM customer "
                + "JOIN state_list ON customer.state_id = state_list.id "
                + "ORDER BY customer.id";
        try{
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setStreetAddress(rs.getString("street_address"));
                customer.setCity(rs.getString("city"));
                customer.setPostalCode(rs.getString("postal_code"));
                customer.setActive(rs.getBoolean("active"));
                customer.setStateShortDesc(rs.getString("short_desc"));
                customerList.add(customer);                   
            }
        }
        catch(SQLException e){
            System.out.println("getAllCustomers: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return customerList;
    }
    
        public static List<Customer> getCustomersByLastName(String searchTextBox){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> customerList = new ArrayList<>();
        
        String sql = "SELECT customer.id, customer.first_name, customer.last_name, customer.phone, customer.email, "
                + "customer.street_address, customer.city, customer.postal_code, customer.comments, customer.active, "
                + "state_list.short_desc "
                + "FROM customer "
                + "JOIN state_list ON customer.state_id = state_list.id "
                + "WHERE customer.last_name = ?";
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, searchTextBox);
            rs = ps.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setStreetAddress(rs.getString("street_address"));
                customer.setCity(rs.getString("city"));
                customer.setPostalCode(rs.getString("postal_code"));
                customer.setComments(rs.getString("comments"));
                customer.setActive(rs.getBoolean("active"));
                customer.setStateShortDesc(rs.getString("short_desc"));
                customerList.add(customer);                   
            }
        }
        catch(SQLException e){
            System.out.println("getAllCustomers: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return customerList;
    }
        
    public static List<Customer> getCustomersByPhoneNumber(String searchTextBox){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> customerList = new ArrayList<>();
        
        String sql = "SELECT customer.id, customer.first_name, customer.last_name, customer.phone, customer.email, "
                + "customer.street_address, customer.city, customer.postal_code, customer.comments, customer.active, "
                + "state_list.short_desc "
                + "FROM customer "
                + "JOIN state_list ON customer.state_id = state_list.id "
                + "WHERE customer.phone = ?";
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, searchTextBox);
            rs = ps.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setStreetAddress(rs.getString("street_address"));
                customer.setCity(rs.getString("city"));
                customer.setPostalCode(rs.getString("postal_code"));
                customer.setComments(rs.getString("comments"));
                customer.setActive(rs.getBoolean("active"));
                customer.setStateShortDesc(rs.getString("short_desc"));
                customerList.add(customer);                   
            }
        }
        catch(SQLException e){
            System.out.println("getAllCustomers: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return customerList;
    }
    
        public static Customer getCustomerById(Integer customerId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        
        String sql = "SELECT customer.id, customer.first_name, customer.last_name, customer.phone, customer.email, "
                + "customer.street_address, customer.city, customer.postal_code, customer.comments, customer.active, "
                + "customer.state_id, state_list.short_desc "
                + "FROM customer "
                + "JOIN state_list ON customer.state_id = state_list.id "
                + "WHERE customer.id = ?";
        try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            while(rs.next()){
                customer = new Customer();
                customer.setCustomerId(rs.getInt("id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setStreetAddress(rs.getString("street_address"));
                customer.setCity(rs.getString("city"));
                customer.setPostalCode(rs.getString("postal_code"));
                customer.setComments(rs.getString("comments"));
                customer.setActive(rs.getBoolean("active"));
                customer.setState(rs.getInt("state_id"));
                customer.setStateShortDesc(rs.getString("short_desc"));
                                 
            }
        }
        catch(SQLException e){
            System.out.println("getAllCustomers: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return customer;
    }
        
        
}