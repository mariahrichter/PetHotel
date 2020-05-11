

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.data;

import edu.witc.business.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Owner
 */
public class EmployeeDb {
    
    public static Employee getEmployeeByUsername(String username){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
        
        String sql = "SELECT id, first_name, last_name, user_name, role_type_id, "
                + "salt, hash_pass, active "
                + "FROM employee "
                + "WHERE user_name = ?";
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next()){
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setUserName(rs.getString("user_name"));
                employee.setRoleTypeId(rs.getInt("role_type_id"));
                employee.setSalt(rs.getString("salt"));
                employee.setHash(rs.getString("hash_pass"));
                employee.setActive(rs.getBoolean(1));
            }
        }
        catch(SQLException e){
            System.out.println("getEmployee: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return employee;
    }
    
}
