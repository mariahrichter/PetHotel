/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.data;

import edu.witc.business.Employee;
import edu.witc.utility.DateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Owner
 */
public class AdminDb {
    
    public static int insertEmployee(Employee employee) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO employee (first_name, last_name, user_name, role_type_id, salt, "
                + "hash_pass, date_added) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getUserName());
            ps.setInt(4, employee.getRoleTypeId());
            ps.setString(5, employee.getSalt());
            ps.setString(6, employee.getHash());
            ps.setDate(7, DateUtil.getSqlDate());
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }
    
}
