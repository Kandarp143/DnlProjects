/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.LoginBean;
import Interface.LoginInter;
import Logic.DBmanager;
import static Logic.Variables.sql;
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

    @Override
    public String authenticateUser(LoginBean bean) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "select b.user_id,b.pass,a.is_active from cba_user_mst a,cba_user_pass b "
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
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : {0}", ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);

            }
        }

        return ans;
    }

    @Override
    public void addLoginHistory(LoginBean bean) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            sql = "insert into cba_login_history(seq,user_id,login_time) values (?,?,sysdate)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, bean.getSEQ());
            ps.setString(2, bean.getUSER_ID());
            ps.executeQuery();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : {0}", ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);

            }
        }

    }

    @Override
    public void updateLoginHistory(LoginBean bean) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            sql = "UPDATE cba_login_history SET LOGOUT_TIME = sysdate WHERE SEQ = ? AND USER_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, bean.getSEQ());
            ps.setString(2, bean.getUSER_ID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : {0}", ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);

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
    public LoginBean getUserDetail(LoginBean bean) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        LoginBean ans = null;
        try {
            sql = "select user_id,full_name,user_typ,email_id,unit,acc_role from cba_user_mst where user_id = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getUSER_ID());
            rs = ps.executeQuery();
            while (rs.next()) {
                ans = new LoginBean();
                ans.setUSER_ID(rs.getString(1));
                ans.setUSER_NAME(rs.getString(2));
                ans.setUSER_TYP(rs.getString(3));
                ans.setEMAIL(rs.getString(4));
                ans.setUNIT(rs.getString(5));
                ans.setACC_ROLE(rs.getString(6));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : {0}", ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);

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
                    + "  FROM cba_user_mst a, cba_user_role b"
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
            logger.log(Level.SEVERE, "Exception : {0}", ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);

            }
        }

        return ans;
    }

    @Override
    public boolean changePassword(LoginBean bean) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        boolean ans = true;
        LoginDao l = new LoginDao();
        String uname = l.getUserDetail(bean).getUSER_NAME();
        if (!uname.isEmpty()) {
            try {
                sql = "update cba_user_pass set pass= ? where user_id = ? and pass = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, bean.getNEW_PASS());
                ps.setString(2, bean.getUSER_ID());
                ps.setString(3, bean.getPASS());
                ps.executeQuery();
                ans = true;
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);
                ans = false;
            } finally {
                try {
                    ps.close();
                    con.close();
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Exception : {0}", ex);
                }
            }
        } else {
            ans = false;
        }
        return ans;
    }
}
