/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.WorkBean;
import Bean.WorkItemBean;
import static Dao.WorkDao.logger;
import Logic.DBmanager;
import static Logic.Variables.sql;
import java.net.UnknownHostException;
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
public class BudgetDao {

    public static void main(String[] args) {
        BudgetDao d = new BudgetDao();
        d.getBudgetValue("CFD/CEP/NIT-0120/13-14", "05");
        d.isBudegeted("1");
    }

    public float getBudgetValue(String project, String task) {
        PreparedStatement ps = null;
        Connection dbcon = null;
        ResultSet rs = null;
        float budget = 0;
        try {
            dbcon = DBmanager.getInvcon();
            String sql = "SELECT project_no, task_no, budget_amount\n"
                    + "  FROM xx_dnl_proj_budget_mst bgt\n"
                    + " WHERE bgt.project_no = '" + project + "'\n"
                    + "    AND    bgt.task_no = '" + task + "'\n"
                    + "       AND bgt.project_status = 'W'\n"
                    + "       AND bgt.sub_task_no IS NULL";
            ps = dbcon.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    budget = rs.getFloat(3);
                }
            } else {
                rs = null;
                sql = "SELECT project_no, task_no, budget_amount\n"
                        + "  FROM xx_dnl_proj_budget_mst bgt\n"
                        + " WHERE bgt.project_no = '" + project + "'\n"
                        + "    AND    bgt.task_no is null \n"
                        + "       AND bgt.project_status = 'W'\n"
                        + "       AND bgt.sub_task_no IS NULL";
                ps = dbcon.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    budget = rs.getFloat(3);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BudgetDao.class.getName()).log(Level.SEVERE, "Expection : " + ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(BudgetDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                dbcon.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        Logger.getLogger(BudgetDao.class.getName()).log(Level.SEVERE, "Budget : " + budget);
        return budget;
    }

    public boolean isBudegeted(String bill) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ans = true;
        ArrayList< WorkItemBean> item = new ArrayList< WorkItemBean>();
        try {
            sql = "SELECT   SUM (val) val, plant, cc, proj, task, bill_no\n"
                    + "    FROM cba_bill_item\n"
                    + "   WHERE bill_no = '" + bill + "'\n"
                    + "GROUP BY plant, cc, proj, task, bill_no";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkItemBean b = new WorkItemBean();
                b.setVAL(rs.getFloat(1));
                b.setPLANT(rs.getString(2));
                b.setCC(rs.getString(3));
                b.setPROJ(rs.getString(4));
                b.setTASK(rs.getString(5));
                b.setBILL_ID(rs.getString(6));
                item.add(b);
            }
            BudgetDao bd = new BudgetDao();
            for (WorkItemBean b : item) {
                logger.log(Level.SEVERE, "Line Val :" + b.getVAL()
                        + " Budget Val :" + bd.getBudgetValue(b.getPROJ(), b.getTASK()));
                if (b.getVAL() > bd.getBudgetValue(b.getPROJ(), b.getTASK())) {
                    ans = false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);

            }
        }
        Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "is Budgeted :" + ans);
        return ans;
    }

}
