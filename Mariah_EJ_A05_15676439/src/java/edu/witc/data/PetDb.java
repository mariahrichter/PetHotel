/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.witc.data;

import edu.witc.business.Customer;
import edu.witc.business.DispositionType;
import edu.witc.business.Pet;
import edu.witc.business.PetType;
import edu.witc.utility.DateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 15676439
 */
public class PetDb {
        public static int insertPet(Pet pet) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO pet (customer_id, pet_name, gender, pet_type_id, month_year_born, "
                + "disposition_type_id, date_last_kennel_cough, breed, comments, active, date_added) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pet.getCustomerId());
            ps.setString(2, pet.getPetName());
            ps.setString(3, pet.getGender());
            ps.setInt(4, pet.getPetTypeId());
            ps.setString(5, pet.getBirth());
            ps.setInt(6, pet.getDispoId());
            ps.setDate(7, DateUtil.getSqlDate(pet.getKennelCoughDate()));
            ps.setString(8, pet.getBreed());
            ps.setString(9, pet.getComments());
            ps.setBoolean(10, true);
            ps.setDate(11, DateUtil.getSqlDate());
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }
        
public static int updatePet(Pet pet) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE pet SET "
                + "pet_name = ?, "
                + "gender = ?, "
                + "pet_type_id = ?, "
                + "month_year_born = ?, "
                + "disposition_type_id = ?, "
                + "date_last_kennel_cough = ?, "
                + "breed = ?, "
                + "comments = ?, "
                + "date_modified = ? "
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, pet.getPetName());
            ps.setString(2, pet.getGender());
            ps.setInt(3, pet.getPetTypeId());
            ps.setString(4, pet.getBirth());
            ps.setInt(5, pet.getDispoId());
            ps.setDate(6, DateUtil.getSqlDate(pet.getKennelCoughDate()));
            ps.setString(7, pet.getBreed());
            ps.setString(8, pet.getComments());
            ps.setDate(9, DateUtil.getSqlDate());
            ps.setInt(10, pet.getPetId());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

        public static List<Pet> getPetsByCustomerId(int customerId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pet> petList = new ArrayList<>();
        
               String sql = "SELECT pet.id, pet.pet_name, pet.gender, pet.month_year_born, pet.date_last_kennel_cough, "
                + "pet.comments, pet.active, "
                + "pet.pet_type_id, pet_type.short_desc, pet.disposition_type_id, disposition_type.short_desc "
                + "FROM pet "
                + "JOIN pet_type ON pet.pet_type_id = pet_type.id "
                + "JOIN disposition_type ON pet.disposition_type_id = disposition_type.id "
                + "WHERE pet.customer_id = ?";
        try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            while(rs.next()){
                Pet pet = new Pet();
                pet.setPetId(rs.getInt("id"));
                pet.setPetName(rs.getString("pet_name"));
                pet.setGender(rs.getString("gender"));
                pet.setBirth(rs.getString("month_year_born"));
                pet.setKennelCoughDate(DateUtil.getLocalDateFromSqlDate(rs.getDate("date_last_kennel_cough")));
                pet.setComments(rs.getString("comments"));
                pet.setActive(rs.getBoolean("active"));
                pet.setPetTypeId(rs.getInt("pet_type_id"));
                pet.setPetTypeShortDesc(rs.getString("pet_type.short_desc"));
                pet.setDispoId(rs.getInt("disposition_type_id"));
                pet.setDispoTypeShortDesc(rs.getString("disposition_type.short_desc"));
                petList.add(pet);                   
            }
        }
        catch(SQLException e){
            System.out.println("getAllPets: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return petList;
    }
        
    public static Pet getPetById(Integer petId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pet pet = null;
        
        String sql = "SELECT pet.id, pet.pet_name, pet.gender, pet.month_year_born, pet.date_last_kennel_cough, "
                + "pet.comments, pet.active, "
                + "pet.pet_type_id, pet_type.short_desc, pet.disposition_type_id, disposition_type.short_desc "
                + "FROM pet "
                + "JOIN pet_type ON pet.pet_type_id = pet_type.id "
                + "JOIN disposition_type ON pet.disposition_type_id = disposition_type.id "
                + "WHERE pet.id = ?";
        try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, petId);
            rs = ps.executeQuery();
            while(rs.next()){
                pet = new Pet();
                pet.setPetId(rs.getInt("id"));
                pet.setPetName(rs.getString("pet_name"));
                pet.setGender(rs.getString("gender"));
                pet.setBirth(rs.getString("month_year_born"));
                pet.setKennelCoughDate(DateUtil.getLocalDateFromSqlDate(rs.getDate("date_last_kennel_cough")));
                pet.setComments(rs.getString("comments"));
                pet.setActive(rs.getBoolean("active"));
                pet.setPetTypeId(rs.getInt("pet_type_id"));
                pet.setPetTypeShortDesc(rs.getString("pet_type.short_desc"));
                pet.setDispoId(rs.getInt("disposition_type_id"));
                pet.setDispoTypeShortDesc(rs.getString("disposition_type.short_desc"));
            }
        }
        catch(SQLException e){
            System.out.println("getPet: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return pet;
    }
    
    public static List<Pet> getAllPets(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pet> petList = new ArrayList<>();
        
               String sql = "SELECT pet.id, pet.pet_name, pet.gender, pet.month_year_born, pet.date_last_kennel_cough, "
                + "pet.comments, pet.active, "
                + "pet.pet_type_id, pet_type.short_desc, pet.disposition_type_id, disposition_type.short_desc "
                + "FROM pet "
                + "JOIN pet_type ON pet.pet_type_id = pet_type.id "
                + "JOIN disposition_type ON pet.disposition_type_id = disposition_type.id "
                + "ORDER BY pet_type.short_desc";
        try{
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Pet pet = new Pet();
                pet.setPetId(rs.getInt("id"));
                pet.setPetName(rs.getString("pet_name"));
                pet.setGender(rs.getString("gender"));
                pet.setBirth(rs.getString("month_year_born"));
                pet.setKennelCoughDate(DateUtil.getLocalDateFromSqlDate(rs.getDate("date_last_kennel_cough")));
                pet.setComments(rs.getString("comments"));
                pet.setActive(rs.getBoolean("active"));
                pet.setPetTypeId(rs.getInt("pet_type_id"));
                pet.setPetTypeShortDesc(rs.getString("pet_type.short_desc"));
                pet.setDispoId(rs.getInt("disposition_type_id"));
                pet.setDispoTypeShortDesc(rs.getString("disposition_type.short_desc"));
                petList.add(pet);                   
            }
        }
        catch(SQLException e){
            System.out.println("getAllPets: " + e);
            return null;
        }
        finally{
            DbHelper.closeResultSet(rs);
            DbHelper.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return petList;
    }
}

