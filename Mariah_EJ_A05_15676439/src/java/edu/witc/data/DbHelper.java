/* 
 * Copyright 2018 Rene Bylander at WITC
 * Updated on : Nov 9, 2018, 11:32 AM
 */

package edu.witc.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Rene Bylander
 * @created Oct 2, 2017
 */
public class DbHelper {
    
    public static void closeStatement(Statement s){
        try{
            if(s != null){
                s.close();
            }
        }
        catch(SQLException e){
            System.err.println(e);
        }
    }
    public static void closePreparedStatement(PreparedStatement ps){
        try{
            if(ps != null){
                ps.close();
            }
        }
        catch(SQLException e){
            System.err.println(e);
        }
    }
    public static void closeResultSet(ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }
        catch(SQLException e){
            System.err.println(e);
        }
    }


}//end of class
