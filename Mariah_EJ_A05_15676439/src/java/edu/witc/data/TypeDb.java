/* 
 * Copyright 2018 Rene Bylander at WITC
 * Updated on : Nov 9, 2018, 11:32 AM
 */

package edu.witc.data;

import edu.witc.business.DispositionType;
import edu.witc.business.Pet;
import edu.witc.business.PetType;
import edu.witc.business.RoleType;
import edu.witc.business.StateType;
import edu.witc.data.ConnectionPool;
import edu.witc.data.DbHelper;
import edu.witc.utility.DateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rene Bylander
 * @created Nov 6, 2017
 */
public class TypeDb {

    public static List<DispositionType> getAllDispositions(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DispositionType> dispoList = new ArrayList<>();
        
        String sql = "SELECT id, short_desc, long_desc, active "
                + "FROM disposition_type";
        try{
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                DispositionType dispoType = new DispositionType();
                dispoType.setDispoId(rs.getInt("id"));
                dispoType.setShortDesc(rs.getString("short_desc"));
                dispoType.setLongDesc(rs.getString("long_desc"));
                dispoType.setActive(rs.getBoolean("active"));
                dispoList.add(dispoType);                   
            }
        }
        catch(SQLException e){
            System.out.println("getAllDispositions: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return dispoList;
    }
    
    public static List<StateType> getAllStates(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<StateType> stateList = new ArrayList<>();
        
        String sql = "SELECT id, short_desc, long_desc, active "
                + "FROM state_list";
        try{
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                StateType stateType = new StateType();
                stateType.setStateId(rs.getInt("id"));
                stateType.setShortDesc(rs.getString("short_desc"));
                stateType.setLongDesc(rs.getString("long_desc"));
                stateType.setActive(rs.getBoolean("active"));
                stateList.add(stateType);                   
            }
        }
        catch(SQLException e){
            System.out.println("getAllStates: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return stateList;
    }
    
    public static List<RoleType> getAllRoleTypes(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RoleType> roleList = new ArrayList<>();
        
        String sql = "SELECT id, short_desc, long_desc, active "
                + "FROM role_type";
        try{
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                RoleType roleType = new RoleType();
                roleType.setRoleId(rs.getInt("id"));
                roleType.setShortDesc(rs.getString("short_desc"));
                roleType.setLongDesc(rs.getString("long_desc"));
                roleType.setActive(rs.getBoolean("active"));
                roleList.add(roleType);                   
            }
        }
        catch(SQLException e){
            System.out.println("getAllDispositions: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return roleList;
    }
    
    public static List<PetType> getAllPets(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PetType> petList = new ArrayList<>();
        
        String sql = "SELECT id, short_desc, long_desc, active "
                + "FROM pet_type";
        try{
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                PetType petType = new PetType();
                petType.setPetTypeId(rs.getInt("id"));
                petType.setShortDesc(rs.getString("short_desc"));
                petType.setLongDesc(rs.getString("long_desc"));
                petType.setActive(rs.getBoolean("active"));
                petList.add(petType);                   
            }
        }
        catch(SQLException e){
            System.out.println("getAllDispositions: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return petList;
    }

    public static int insertPetType(PetType petType) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO pet_type (short_desc, long_desc, active, date_added) "
                + "VALUES (?, ?, ?, ?)";
        try {
            
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, petType.getShortDesc());
            ps.setString(2, petType.getLongDesc());
            ps.setBoolean(3, petType.isActive());
            ps.setDate(4, DateUtil.getSqlDate());
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static int insertState(StateType stateType) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO state_list (short_desc, long_desc, active, date_added) "
                + "VALUES (?, ?, ?, ?)";
        try {
            
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, stateType.getShortDesc());
            ps.setString(2, stateType.getLongDesc());
            ps.setBoolean(3, stateType.isActive());
            ps.setDate(4, DateUtil.getSqlDate());
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }   
    }
    
    public static int updatePetType(PetType petType) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE pet_type SET "
                + "short_desc = ?, "
                + "long_desc = ?, "
                + "active = ? "
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, petType.getShortDesc());
            ps.setString(2, petType.getLongDesc());
            ps.setBoolean(3, petType.isActive());
            ps.setInt(4, petType.getPetTypeId());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
        public static int updateStateType(StateType stateType) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE state_list SET "
                + "short_desc = ?, "
                + "long_desc = ?, "
                + "active = ? "
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, stateType.getShortDesc());
            ps.setString(2, stateType.getLongDesc());
            ps.setBoolean(3, stateType.isActive());
            ps.setInt(4, stateType.getStateId());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
        
    public static PetType getPetTypeById(int petTypeId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        PetType petType = null;
        
        String sql = "SELECT * FROM pet_type "
                + "WHERE id = ?";
        try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, petTypeId);
            rs = ps.executeQuery();
            while(rs.next()){
                petType = new PetType();
                petType.setPetTypeId(rs.getInt("id"));
                petType.setShortDesc(rs.getString("short_desc"));
                petType.setLongDesc(rs.getString("long_desc"));
                petType.setActive(rs.getBoolean("active"));
            }
        }
        catch(SQLException e){
            System.out.println("getPetType: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return petType;
    }     
    
    public static StateType getStateById(int stateId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        StateType stateType = null;
        
        String sql = "SELECT * FROM state_list "
                + "WHERE id = ?";
        try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, stateId);
            rs = ps.executeQuery();
            while(rs.next()){
                stateType = new StateType();
                stateType.setStateId(rs.getInt("id"));
                stateType.setShortDesc(rs.getString("short_desc"));
                stateType.setLongDesc(rs.getString("long_desc"));
                stateType.setActive(rs.getBoolean("active"));
            }
        }
        catch(SQLException e){
            System.out.println("getPetType: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return stateType;
    }     
}//end of class
