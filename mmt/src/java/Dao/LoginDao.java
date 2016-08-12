/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.LoginBean;

import Interface.LoginInter;
import Logic.DBmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class LoginDao implements LoginInter {

    static final Logger logger = Logger.getLogger(DBmanager.class.getName());
    private String ans = "";
    private String sql = "";

    @Override
    public String authenticateUser(LoginBean bean) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "select b.user_id,b.pass,a.is_active from mmt_user_mst a,mmt_user_pass b "
                    + " where a.user_id = b.user_id and b.user_id =trim(?) and b.pass =(?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getUSER_ID());
            ps.setString(2, bean.getPASS());
            rs = ps.executeQuery();
            if (rs.next()) {
                if ("Y".equals(rs.getString("IS_ACTIVE"))) {
                    ans = "SUCCESS";
                } else {
                    ans = "Expired";
                }
            } else {
                ans = "Invalid user credentials";
            }
//            logger.log(Level.SEVERE, "LOGIN QUERY : " + sql);
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

        return ans;
    }

    @Override
    public void addLoginHistory(LoginBean bean) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            sql = "insert into mmt_login_history(seq,user_id,login_time) values (?,?,sysdate)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, bean.getSEQ());
            ps.setString(2, bean.getUSER_ID());
            ps.executeQuery();
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
                logger.log(Level.SEVERE, "Exception : " + ex);
            } catch (SQLException ex1) {
                Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);

            }
        }

    }

    @Override
    public void updateLoginHistory(LoginBean bean) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            sql = "UPDATE mmt_login_history SET LOGOUT_TIME = sysdate WHERE SEQ = ? AND USER_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, bean.getSEQ());
            ps.setString(2, bean.getUSER_ID());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);

            }
        }

    }

    @Override
    public ArrayList<LoginBean> getLoginHistory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<LoginBean> getLoginHistory(String user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUserName(LoginBean bean) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ans = "";
            sql = "select full_name from mmt_user_mst where user_id = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getUSER_ID());
            rs = ps.executeQuery();
            while (rs.next()) {
                ans = rs.getString(1);
            }
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

        return ans;
    }

    @Override
    public ArrayList<LoginBean> getUserByRole(String role) {
        ArrayList<LoginBean> ans = new ArrayList<LoginBean>();
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "SELECT a.full_name, a.user_id, b.role_id"
                    + "  FROM mmt_user_mst a, mmt_user_role b"
                    + " WHERE (a.user_id = b.user_id) AND b.role_id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, role);
            rs = ps.executeQuery();
            while (rs.next()) {
                LoginBean b = new LoginBean();
                b.setUSER_NAME(rs.getString(1));
                b.setUSER_ID(rs.getString(2));
                ans.add(b);
            }
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

        return ans;
    }
}
