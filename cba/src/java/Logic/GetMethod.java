/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import static Logic.Variables.sql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author 02948
 */
public class GetMethod {

    static final Logger logger = Logger.getLogger(GetMethod.class.getName());

    public static void main(String[] args) {
        GetMethod g = new GetMethod();
        g.Get_Perameter("VENDOR_ID", "PO_VENDORS", "VENDOR_NUMBER", "1537");
    }
    private String ans;
    private int ans2;

    public String Get_PONumber(int id) {
        int no = id;
        String strI = Integer.toString(no);
        strI = StringUtils.leftPad(strI, 3, "0");
        Calendar now = Calendar.getInstance();   // Gets the current date and time
        String year = Integer.toString(now.get(Calendar.YEAR));
        strI = "WO/" + year + "/2" + strI;
        return strI;
    }

    public String Get_BillNumber() {
        int no = Get_Seq("BILL_NO", "CBA_BILL_MST");
        String strI = Integer.toString(no);
        return strI;
    }

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
        return x;
    }

    public int Get_NextSTG(int act, int sid) {
        Connection con = DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int y = 0;
        try {
            /*This Method for get next stage id from current stage and cid*/
            logger.log(Level.INFO, "sid : = {0}act ={1}", new Object[]{sid, act});
            /* This Method for get next stage id based on current stage */
            sql = "SELECT NSTG_ID FROM CBA_STG_DT WHERE STG_ID = " + sid + " AND ACT_ID = " + act;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                y = rs.getInt(1);
            }
            logger.log(Level.INFO, "Next Stage :{0}", y);

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
        return y;
    }

    public String Get_NextUID(int nsid, String pono, String uid) {
        logger.log(Level.SEVERE, "CALLED NXUID METHOD " + "NSID : {0}UID : {1}PONO : {2}", new Object[]{nsid, uid, pono});
        ResultSet rs = null;
        String nuid = "";
        String unit = "";
        String role = "";
        String creator = "";
        try {
            if (nsid == 1) {
                //HOD
                logger.log(Level.SEVERE, "IN");
                creator = Get_Perameter("USER_ID", "CBA_WO_MST", "PO_NO", pono);
                nuid = Get_Perameter(creator, "REVIEW_ID", "CBA_USER_MST");
            } else if (nsid == 2) {
                //FINANCE
                role = "FIN";
                creator = Get_Perameter("USER_ID", "CBA_WO_MST", "PO_NO", pono);
                unit = Get_Perameter(creator, "UNIT", "CBA_USER_MST");
                sql = "select user_id from cba_user_role where role_id = '" + role + "' and unit = '" + unit + "'";
                rs = Get_rs(sql);
                while (rs.next()) {
                    nuid = rs.getString(1);
                }
            } else if (nsid == 3) {
                //UNIT HEAD
                role = "UHA";
                creator = Get_Perameter("USER_ID", "CBA_WO_MST", "PO_NO", pono);
                unit = Get_Perameter(creator, "UNIT", "CBA_USER_MST");
                sql = "select user_id from cba_user_role where role_id = '" + role + "' and unit = '" + unit + "'";
                rs = Get_rs(sql);
                while (rs.next()) {
                    nuid = rs.getString(1);
                }
            } else if (nsid == 14) {
                role = "DHA";
                creator = Get_Perameter("USER_ID", "CBA_WO_MST", "PO_NO", pono);
                unit = Get_Perameter(creator, "UNIT", "CBA_USER_MST");
                sql = "select user_id from cba_user_role where role_id = '" + role + "' and unit = '" + unit + "'";
                rs = Get_rs(sql);
                while (rs.next()) {
                    nuid = rs.getString(1);
                }
            } else if (nsid == 16) {
                role = "ARA";
                creator = Get_Perameter("USER_ID", "CBA_WO_MST", "PO_NO", pono);
                unit = Get_Perameter(creator, "UNIT", "CBA_USER_MST");
                sql = "select user_id from cba_user_role where role_id = '" + role + "' and unit = '" + unit + "'";
                rs = Get_rs(sql);
                while (rs.next()) {
                    nuid = rs.getString(1);
                }
            } else if (nsid == 4 || nsid == 5 || nsid == 13) {
                //UPDATE
                nuid = Get_Perameter("USER_ID", "CBA_WO_MST", "PO_NO", pono);
            } else {
                //UPDATE
                nuid = Get_Perameter("USER_ID", "CBA_WO_MST", "PO_NO", pono);
            }
            logger.log(Level.INFO, "Next User :{0}", nuid);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : {0}", ex);
        } finally {

        }
        if ("".equals(nuid)) {
            nuid = "02948";
        }
        return nuid;
    }

    public String Get_BillNextUID(int nsid, String bill, String uid) {
        ResultSet rs = null;
        String nuid = "";
        String unit = "";
        String role = "";
        String creator = "";
        try {
            if (nsid == 7 || nsid == 18) {
                //HOD
                nuid = Get_Perameter(uid, "REVIEW_ID", "CBA_USER_MST");
            } else if (nsid == 8) {
                //FINANCE
                role = "FIN";
                creator = Get_Perameter("USER_ID", "CBA_BILL_MST", "BILL_NO", bill);
                unit = Get_Perameter(creator, "UNIT", "CBA_USER_MST");
                sql = "select user_id from cba_user_role where role_id = '" + role + "' and unit = '" + unit + "'";
                rs = Get_rs(sql);
                while (rs.next()) {
                    nuid = rs.getString(1);
                }
            } else if (nsid == 9) {
                //UNIT HEAD
                role = "UHA";
                creator = Get_Perameter("USER_ID", "CBA_BILL_MST", "BILL_NO", bill);
                unit = Get_Perameter(creator, "UNIT", "CBA_USER_MST");
                sql = "select user_id from cba_user_role where role_id = '" + role + "' and unit = '" + unit + "'";
                rs = Get_rs(sql);
                while (rs.next()) {
                    nuid = rs.getString(1);
                }
            } else if (nsid == 15) {
                //UNIT HEAD
                role = "DHA";
                creator = Get_Perameter("USER_ID", "CBA_BILL_MST", "BILL_NO", bill);
                unit = Get_Perameter(creator, "UNIT", "CBA_USER_MST");
                sql = "select user_id from cba_user_role where role_id = '" + role + "' and unit = '" + unit + "'";
                rs = Get_rs(sql);
                while (rs.next()) {
                    nuid = rs.getString(1);
                }
            } else if (nsid == 10 || nsid == 11 || nsid == 12) {
                nuid = Get_Perameter("USER_ID", "CBA_BILL_MST", "BILL_NO", bill);
            } else {
                nuid = Get_Perameter("USER_ID", "CBA_BILL_MST", "BILL_NO", bill);
            }
            logger.log(Level.INFO, "Next User :{0}", nuid);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : {0}", ex);
        }
        logger.log(Level.SEVERE, "Reaction NUID : {0}", nuid);
        return nuid;
    }

}
