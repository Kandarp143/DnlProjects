/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class GetMethod {

    static final Logger logger = Logger.getLogger(GetMethod.class.getName());
    private String ans;
    private int ans2;
    private String sql;
    
    public int Get_Seq(String column, String table) {
        Connection con = DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            /*This Method for auto increment logic for all table*/
            ans2 = 0;
            sql = "SELECT NVL(MAX(to_number(" + column + ")),0) FROM " + table;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ans2 = rs.getInt(1);
            }
            ans2 = ++ans2;
            logger.log(Level.INFO, "Sequence : {0} Table : {1}", new Object[]{ans2, table});
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
        return ans2;
    }

    public String Get_CurrentDate() {
        /* This Method for get current date */
        ans = "";
        SimpleDateFormat simpledatafo = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        Date newDate = new Date();
        ans = simpledatafo.format(newDate);
        logger.log(Level.INFO, "Current Date : {0}", ans);
        return ans;
    }

    public ResultSet Get_rs(String sql) throws SQLException {
        Connection con = DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        return rs;
    }

    public String Get_Perameter(String uid, String column, String table) {
        Connection con = DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String x = "";
        try {
            /*This Method for select 1 column form any table : unit , reviwer id ,prod id ,role etc*/
            sql = "SELECT " + column + "  FROM  " + table + " WHERE USER_ID = '" + uid + "'";
            // logger.log(Level.INFO, "SQL:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getString(1);
            }
            logger.log(Level.INFO, "Perameter : {0}  Value : {1}", new Object[]{column, x});
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
        return x;
    }

    public String Get_Perameter(String column, String table, String attr, String value) {
        Connection con = DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String x = "";
        try {
            /*This Method for select 1 column form any table : unit , reviwer id ,prod id ,role etc*/
            sql = "SELECT " + column + "  FROM  " + table + " WHERE " + attr + " = '" + value + "'";
            // logger.log(Level.INFO, "SQL:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getString(1);
            }
            logger.log(Level.INFO, "Perameter : {0}  Value : {1}", new Object[]{column, x});
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
        return x;
    }

}
