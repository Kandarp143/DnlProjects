/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.WFBean;
import Interface.WFInter;
import Logic.DBmanager;
import Logic.GetMethod;
import static Logic.Variables.sql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class WFDao implements WFInter {

    static final Logger logger = Logger.getLogger(DBmanager.class.getName());

    @Override
    public void addUserAct(WFBean bean) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            sql = "insert into CBA_ACT_HISTORY (USER_ID,ACT_ID,PO_NUMBER,ACT_DATE) values (?,?,?,sysdate)";
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getUSER_ID());
            ps.setInt(2, bean.getACT_ID());
            ps.setString(3, bean.getPO_NUMBER());
            ps.executeQuery();
            con.commit();
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
    public void addTranaction(WFBean bean, Boolean isWO) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            if (isWO) {
                sql = "insert into CBA_TRAN_WO (TRAN_ID, PO_NUMBER, STG_C, STG_N, ACT_ID, USER_ID, ACT_DATE, CMT, NXT_UID)"
                        + " values(?,?,?,?,?,?,sysdate,?,?)";
            } else {
                sql = "insert into CBA_TRAN_BILL(TRAN_ID, BILL_NO, STG_C, STG_N, ACT_ID, USER_ID, ACT_DATE, CMT, NXT_UID)"
                        + " values(?,?,?,?,?,?,sysdate,?,?)";
            }

            ps = con.prepareStatement(sql);
            ps.setInt(1, bean.getTRAN_ID());
            if (isWO) {
                ps.setString(2, bean.getPO_NUMBER());
            } else {
                ps.setString(2, bean.getBILL_NO());
            }
            ps.setInt(3, bean.getSTG_C());
            ps.setInt(4, bean.getSTG_N());
            ps.setInt(5, bean.getACT_ID());
            ps.setString(6, bean.getUSER_ID());
            ps.setString(7, bean.getCMT());
            ps.setString(8, bean.getNXT_UID());
            ps.executeQuery();
            logger.log(Level.SEVERE, "Tranaction added for tran_id : {0}", bean.getTRAN_ID());
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
    public void updateTranFlag(WFBean bean, Boolean isWO) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String table;
        String status;
        int y = 0;
        if (isWO) {
            table = "CBA_TRAN_WO";
            sql = "select max(tran_id) from " + table + " where po_number = '" + bean.getPO_NUMBER() + "'";
        } else {
            table = "CBA_TRAN_BILL";
            sql = "select max(tran_id) from " + table + " where bill_no = " + bean.getBILL_NO();
        }
        try {
            /*This Method for for update flag of tran_mst before nxt tran insert*/
            Logic.GetMethod g = new GetMethod();
            rs = g.Get_rs(sql);
            while (rs.next()) {
                y = rs.getInt(1);
            }
            if (bean.getACT_ID() == 3) {
                status = "R";
            } else {
                status = "Y";
            }
            sql = "update " + table + " set is_comp = '" + status + "' where tran_id =  " + y;
            rs = g.Get_rs(sql);
            logger.log(Level.INFO, "Tran_Flag Updated for id  :{0}", y);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : {0}", ex);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);

            }
        }

    }

}
