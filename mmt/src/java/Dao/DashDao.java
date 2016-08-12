/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.DDBean;
import Bean.DashBean;
import Logic.RptSQL;
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
public class DashDao {

    static final Logger logger = Logger.getLogger(ItemDao.class.getName());
    private String sql;

    public ArrayList<DashBean> getStockSummary() {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DashBean> itb = new ArrayList<DashBean>();
        try {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "CALLING GET STOCKED SUMMARY");
            sql = RptSQL.SQL1;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DashBean bean = new DashBean();
                bean.setOPEN_QTY(rs.getFloat(1));
                bean.setQTY(rs.getFloat(2));
                bean.setCUN_QTY(rs.getFloat(3));
                bean.setRE_QTY(rs.getFloat(4));
                bean.setIN_TRANSIT_LOC(rs.getString(12));
                if (rs.getString(5) != null) {
                    bean.setITEM_CODE(rs.getString(5));
                    bean.setITEM_DESC(rs.getString(6));
                    bean.setLOC_ID(rs.getString(7));
                } else {
                    bean.setITEM_CODE(rs.getString(9));
                    bean.setITEM_DESC(rs.getString(10));
                    bean.setLOC_ID(rs.getString(11));
                }

                //  Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "IN_TRANSIT_LOC: " + bean.getIN_TRANSIT_LOC());
                itb.add(bean);
//                Collections.sort(itb, new Logic.ItemBeanComp_ICODE());
            }
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "SUMMARY RECORD: " + itb.size());
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);

            }
        }
        return itb;
    }

    public ArrayList<DashBean> Dash2_1() {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DashBean> itb = new ArrayList<DashBean>();
        try {
            sql = RptSQL.SQL5_1;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DashBean bean = new DashBean();
                bean.setITEM_DESC(rs.getString(1));
                bean.setLOC_ID(rs.getString(2));
                bean.setQTY(rs.getFloat(3));
                itb.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
        return itb;
    }

    public ArrayList<DashBean> Dash2_2() {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DashBean> itb = new ArrayList<DashBean>();
        try {
            sql = RptSQL.SQL5_2;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DashBean bean = new DashBean();
                bean.setITEM_DESC(rs.getString(1));
                bean.setLOC_ID(rs.getString(2));
                bean.setQTY(rs.getFloat(3));
                itb.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
        return itb;
    }

    public ArrayList<DashBean> Dash2_3() {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DashBean> itb = new ArrayList<DashBean>();
        try {
            sql = RptSQL.SQL5_3;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DashBean bean = new DashBean();
                bean.setITEM_DESC(rs.getString(1));
                bean.setLOC_ID(rs.getString(2));
                bean.setQTY(rs.getFloat(3));
                itb.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
        return itb;
    }

    public ArrayList<DashBean> Dash3_1() {
        Connection con = Logic.DBmanager.GetProdAppsConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DashBean> db = new ArrayList<DashBean>();
        try {
            sql = RptSQL.SQL6;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DashBean bean = new DashBean();
                bean.setCUS_NAME(rs.getString(1));
                bean.setTOTAL_RECV(rs.getFloat(2));
                bean.setRECIEPT_NO(rs.getString(3));
                bean.setUOM(rs.getString(4));
                db.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
        return db;
    }

    public ArrayList<DDBean> Dash3_CusList() {
        Connection con = Logic.DBmanager.GetProdAppsConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DDBean> db = new ArrayList<DDBean>();
        try {
            sql = RptSQL.SQL6_1;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DDBean bean = new DDBean();
                bean.setCus_name(rs.getString(1));
                bean.setCus_no(rs.getString(2));
                db.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
        logger.log(Level.SEVERE, "Total Customer" + db.size());
        return db;
    }

    public ArrayList<DashBean> Dash3_Customer_Collection(String cusno) {
        Connection con = Logic.DBmanager.GetProdAppsConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DashBean> db = new ArrayList<DashBean>();
        try {
            sql = RptSQL.SQL6_3;
            ps.setString(1, cusno);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DashBean bean = new DashBean();
                bean.setINV_NO(rs.getString(1));
                bean.setCUS_NAME(rs.getString(2));
                bean.setCUS_NO(rs.getString(3));
                bean.setTOTAL_RECV(rs.getFloat(4));
                bean.setGLDATE(rs.getString(5));
                bean.setRECIEPT_NO(rs.getString(6));
                bean.setUOM(rs.getString(9));
                db.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
        return db;
    }

    public ArrayList<DashBean> Dash_vsMMT() {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DashBean> itb = new ArrayList<DashBean>();
        try {
            sql = RptSQL.SQL_VS_MMT;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DashBean bean = new DashBean();
                bean.setITEM_DESC(rs.getString(1));
                bean.setLOC_ID(rs.getString(2));
                bean.setLOT_NO(rs.getString(3));
                bean.setQTY(rs.getFloat(4));
                itb.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
        return itb;

    }

    public ArrayList<DashBean> Dash_vsEDNL() {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DashBean> itb = new ArrayList<DashBean>();
        try {
            sql = RptSQL.SQL_VS_EDNL;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DashBean bean = new DashBean();
                bean.setITEM_DESC(rs.getString(1));
                bean.setLOC_ID(rs.getString(2));
                bean.setLOT_NO(rs.getString(3));
                bean.setQTY(rs.getFloat(4));
                itb.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
        return itb;
    }

    public static void main(String[] args) {
        ArrayList<DashBean> vsAns = new ArrayList<>();
        DashDao ddao2 = new DashDao();
        vsAns = ddao2.Dash_EdnlVSmmt();
    }

    public ArrayList<DashBean> Dash_EdnlVSmmt() {
        Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE,
                "Exception : CALLED");
        ArrayList<DashBean> vsErp = new ArrayList<>();
        ArrayList<DashBean> vsMmt = new ArrayList<>();
        ArrayList<DashBean> vsAns = new ArrayList<>();
        DashDao ddao2 = new DashDao();
        vsMmt = ddao2.Dash_vsMMT();
        vsErp = ddao2.Dash_vsEDNL();

        for (DashBean mmt : vsMmt) {
            for (DashBean erp : vsErp) {
                if (mmt.getITEM_DESC().equals(erp.getITEM_DESC())
                        && mmt.getLOC_ID().equals(erp.getLOC_ID())
                        && mmt.getLOT_NO().equals(erp.getLOT_NO())) {
//                    Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE,
//                            "Exception : TRUE" + mmt.getITEM_DESC() + ";" + erp.getITEM_DESC());
                    DashBean bean = new DashBean();
                    bean.setITEM_DESC(mmt.getITEM_DESC());
                    bean.setLOC_ID(mmt.getLOC_ID());
                    bean.setLOT_NO(mmt.getLOT_NO());
                    bean.setQTY(mmt.getQTY());
                    bean.setRE_QTY(erp.getQTY());
                    vsAns.add(bean);
                }
            }
        }
//        for (DashBean erp : vsErp) {
//            for (DashBean mmt : vsMmt) {
//                if (mmt.getITEM_DESC().equals(mmt.getITEM_DESC())
//                        && mmt.getLOC_ID().equals(erp.getLOC_ID())
//                        && mmt.getLOT_NO().equals(erp.getLOT_NO())) {
//                } else {
//                    DashBean bean = new DashBean();
//                    bean.setITEM_DESC(erp.getITEM_DESC());
//                    bean.setLOC_ID(erp.getLOC_ID());
//                    bean.setLOT_NO(erp.getLOT_NO());
//                    bean.setQTY(0);
//                    bean.setRE_QTY(erp.getQTY());
//                    vsAns.add(bean);
//                }
//            }
//        }

        return vsAns;
    }
}
