/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.WorkBean;
import Bean.WorkItemBean;
import Bean.WorkTaxBean;
import Bean.WorkTermBean;
import Interface.WoInter;
import Logic.GetMethod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class WorkDao implements WoInter {

    static final Logger logger = Logger.getLogger(WorkDao.class.getName());
    String sql;

    @Override
    public void CreateWO(WorkBean wo, ArrayList<WorkItemBean> woit, ArrayList<WorkTaxBean> wotx, WorkTermBean woterm) {
        Connection con = Logic.DBmanager.GetConnection();
        try {
            con.setAutoCommit(false);
            InsWO(wo, con);
            InsWOItem(woit, con);
            InsWORemain(woit, con);
            InsWOTax(wotx, con);
            InsWOTerm(woterm, con);
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Created WO :{0}", wo.getPO_NO());
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
            }
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void InsWO(WorkBean bean, Connection con) {
        GetMethod g = new GetMethod();
        PreparedStatement ps = null;

        sql = "insert into CBA_WO_MST (PO_NO ,CR_DATE,EXP_DATE,OU,USER_ID,SUP_ID,SITE,CUR,"
                + " STATUS,TYPE, WO_DESC,RET_AMT,VAL,TAX_VAL,TOTAL_VAL,PO_TITLE,ID,FR_DATE,DEP_AMT,VENDOR_ID)"
                + " values(?,sysdate,to_date(?,'MM/DD/YYYY HH:MI:SS AM'),?,?,?,?,?,?,?,?,ROUND(?,2),ROUND(?,2),"
                + "ROUND(?,2),ROUND(?,2),?,?,to_date(?,'MM/DD/YYYY HH:MI:SS AM'),ROUND(?,2),?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getPO_NO());
            ps.setString(2, bean.getEXP_DATE());
            ps.setString(3, bean.getOU());
            ps.setString(4, bean.getUSER_ID());
            ps.setString(5, bean.getSUP_ID());
            ps.setString(6, bean.getSITE());
            ps.setString(7, bean.getCUR());
            ps.setString(8, bean.getSTATUS());
            ps.setString(9, bean.getTYPE());
            ps.setString(10, bean.getWO_DESC());
            ps.setFloat(11, bean.getRET_AMT());
            ps.setFloat(12, bean.getVAL());
            ps.setFloat(13, bean.getTOTAL_TAX());
            ps.setFloat(14, bean.getTOTAL_VAL());
            ps.setString(15, bean.getPO_TITLE());
            ps.setInt(16, bean.getId());
            ps.setString(17, bean.getFR_DATE());
            ps.setFloat(18, bean.getDEP_AMT());
            ps.setInt(19, Integer.parseInt(g.Get_Perameter("VENDOR_ID", "PO_VENDORS", "VENDOR_NUMBER", bean.getSUP_ID().toString())));
            ps.executeUpdate();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "inserted  WO_MST WO_NO : {0}", bean.getPO_NO());
        } catch (SQLException ex) {
            try {
                con.rollback();
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "JDBC Transaction rolled back successfully");
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            } catch (SQLException ex1) {
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);

            }
        }

    }

    @Override
    public void InsWOItem(ArrayList<WorkItemBean> bean, Connection con) {
        PreparedStatement ps = null;
        sql = "insert into CBA_WO_ITEM (PO_NO,LINE_NO,ITEM_ID,ITEM_DESC,UOM,QTY,RATE,VAL,CMT,PLANT,PROJ,TASK)"
                + "values(?,?,?,?,?,ROUND(?,2),ROUND(?,2),ROUND(?,2),?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (WorkItemBean woit1 : bean) {
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "bean : " + woit1.getPROJ());
                ps.setString(1, woit1.getPO_NO());
                ps.setInt(2, woit1.getLINE_NO());
                ps.setString(3, woit1.getITEM_ID());
                ps.setString(4, woit1.getITEM_DESC());
                ps.setString(5, woit1.getUOM());
                ps.setFloat(6, woit1.getQTY());
                ps.setFloat(7, woit1.getRATE());
                ps.setFloat(8, woit1.getVAL());
                ps.setString(9, woit1.getCMT());
                ps.setString(10, woit1.getPLANT());
                ps.setString(11, woit1.getPROJ());
                ps.setString(12, woit1.getTASK());
                ps.addBatch();
            }
            ps.executeBatch();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "inserted WO_ITEM ");
        } catch (SQLException ex) {
            try {
                con.rollback();
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "JDBC Transaction rolled back successfully");
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            } catch (SQLException ex1) {
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);

            }
        }
    }

    @Override
    public void InsWORemain(ArrayList<WorkItemBean> bean, Connection con
    ) {
        PreparedStatement ps = null;
        sql = "insert into CBA_WO_RE (PO_NO,LINE_NO,ITEM_ID,ITEM_DESC,UOM,QTY,RATE,VAL,CMT,PLANT,ISCHECK,PROJ,TASK)"
                + "values(?,?,?,?,?,ROUND(?,2),ROUND(?,2),ROUND(?,2),?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (WorkItemBean woit1 : bean) {
                ps.setString(1, woit1.getPO_NO());
                ps.setInt(2, woit1.getLINE_NO());
                ps.setString(3, woit1.getITEM_ID());
                ps.setString(4, woit1.getITEM_DESC());
                ps.setString(5, woit1.getUOM());
                ps.setFloat(6, woit1.getQTY());
                ps.setFloat(7, woit1.getRATE());
                ps.setFloat(8, woit1.getVAL());
                ps.setString(9, woit1.getCMT());
                ps.setString(10, woit1.getPLANT());
                if (woit1.getQTY() == 0) {
                    ps.setString(11, "N");
                } else {
                    ps.setString(11, "Y");
                }
                ps.setString(12, woit1.getPROJ());
                ps.setString(13, woit1.getTASK());
                ps.addBatch();
            }
            ps.executeBatch();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "inserted WO_RE ");
        } catch (SQLException ex) {
            try {
                con.rollback();
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "JDBC Transaction rolled back successfully");
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            } catch (SQLException ex1) {
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);
            }
        }
    }

    @Override
    public void InsWOTax(ArrayList<WorkTaxBean> bean, Connection con
    ) {
        PreparedStatement ps = null;

        sql = "insert into CBA_WO_TAX (PO_NO ,TAX_TYPE ,TAX_VAL)"
                + "values(?,?,ROUND(?,2))";
        try {
            ps = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (WorkTaxBean wot1 : bean) {
                ps.setString(1, wot1.getPO_NO());
                ps.setString(2, wot1.getTAX_TYPE());
                ps.setFloat(3, wot1.getTAX_VAL());
                ps.addBatch();
            }
            ps.executeBatch();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "inserted WO_TAX");
        } catch (SQLException ex) {
            try {
                con.rollback();
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "JDBC Transaction rolled back successfully");
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            } catch (SQLException ex1) {
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);

            }
        }
    }

    @Override
    public void InsWOTerm(WorkTermBean bean, Connection con
    ) {

        PreparedStatement ps = null;
        sql = "insert into CBA_WO_TERM (PO_NO ,TERM_ID ,TERM_DESC )"
                + "values(?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getPO_NO());
            ps.setString(2, bean.getTERM_ID());
            ps.setString(3, bean.getTERM_DESC());
            ps.executeQuery();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "inserted WO_TERM ");
        } catch (SQLException ex) {
            try {
                con.rollback();
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "JDBC Transaction rolled back successfully");
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            } catch (SQLException ex1) {
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);

            }
        }
    }

    @Override
    public WorkBean getWO(String ponum
    ) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        WorkBean bean = new WorkBean();
        try {
            sql = "SELECT DISTINCT cba_wo_mst.po_no, TO_CHAR (cba_wo_mst.cr_date, 'DD-MON-YYYY'),"
                    + "                TO_CHAR (cba_wo_mst.exp_date, 'DD-MON-YYYY'),  TO_CHAR (cba_wo_mst.fr_date, 'DD-MON-YYYY'),"
                    + "                decode(cba_wo_mst.ou,'123','Nandesari'),cba_wo_mst.user_id,"
                    + "                cba_user_mst.full_name,cba_wo_mst.sup_id, po_vendors.vendor_name,"
                    + "                cba_wo_mst.site, cba_wo_mst.cur, cba_wo_mst.status,"
                    + "                cba_wo_mst.TYPE, cba_wo_mst.wo_desc, cba_wo_mst.ret_amt,"
                    + "                cba_wo_mst.val, cba_wo_mst.tax_val, cba_wo_mst.total_val,"
                    + "                cba_wo_mst.po_title,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE,cba_wo_mst.dep_amt"
                    + "           FROM cba_wo_mst,"
                    + "                cba_user_mst,"
                    + "                cba_user_mst cba_user_mst2,"
                    + "                po_vendors,PO_VENDOR_SITES_ALL"
                    + "          WHERE (    (cba_wo_mst.user_id = cba_user_mst.user_id)"
                    + "                 AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                    + "                 AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE  )"
                    + "                 AND (cba_wo_mst.po_no = '" + ponum + "')"
                    + "                ) ORDER BY cba_wo_mst.po_no";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                bean.setPO_NO(rs.getString(1));
                bean.setCR_DATE(rs.getString(2));
                bean.setEXP_DATE(rs.getString(3));
                bean.setFR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_ID(rs.getString(6));
                bean.setUSER_NAME(rs.getString(7));
                bean.setSUP_ID(rs.getString(8));
                bean.setSUP_NAME(rs.getString(9));
                bean.setSITE(rs.getString(10));
                bean.setCUR(rs.getString(11));
                bean.setSTATUS(rs.getString(12));
                bean.setTYPE(rs.getString(13));
                bean.setWO_DESC(rs.getString(14));
                bean.setRET_AMT(rs.getFloat(15));
                bean.setVAL(rs.getFloat(16));
                bean.setTOTAL_TAX(rs.getFloat(17));
                bean.setTOTAL_VAL(rs.getFloat(18));
                bean.setPO_TITLE(rs.getString(19));
                bean.setSITE_NAME(rs.getString(20));
                bean.setDEP_AMT(rs.getFloat(21));
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
        return bean;
    }

    @Override
    public ArrayList<WorkTaxBean> getWOTax(String ponum
    ) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<WorkTaxBean> bean = new ArrayList<WorkTaxBean>();
        try {
            sql = "SELECT cba_wo_tax.po_no,ja_in_tax_codes.tax_id, ja_in_tax_codes.tax_name,"
                    + "       ja_in_tax_codes.tax_descr, cba_wo_tax.tax_val"
                    + "  FROM cba_wo_tax, ja_in_tax_codes"
                    + " WHERE ((cba_wo_tax.tax_type = ja_in_tax_codes.tax_id)"
                    + " AND (cba_wo_tax.po_no = '" + ponum + "'))";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkTaxBean b = new WorkTaxBean();
                b.setPO_NO(rs.getString(1));
                b.setTAX_TYPE(rs.getString(2));
                b.setTAX_NAME(rs.getString(3));
                b.setTAX_VAL(rs.getFloat(5));
                bean.add(b);
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
        return bean;
    }

    @Override
    public ArrayList<WorkItemBean> getWOItem(String ponum
    ) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<WorkItemBean> bean = new ArrayList<WorkItemBean>();
        try {
            sql = "SELECT cba_wo_item.po_no,cba_wo_item.line_no,cba_wo_item.item_id, mtl_system_items.description, cba_wo_item.uom,"
                    + "       cba_wo_item.qty, cba_wo_item.rate, cba_wo_item.val, cba_wo_item.cmt,cba_wo_item.plant,cba_wo_item.proj,cba_wo_item.task"
                    + "  FROM cba_wo_item, mtl_system_items"
                    + "  WHERE ((cba_wo_item.item_id = mtl_system_items.segment1)"
                    + " AND (mtl_system_items.organization_id = 0)"
                    + " AND (cba_wo_item.po_no = '" + ponum + "')"
                    + " ) ORDER BY cba_wo_item.line_no,cba_wo_item.item_id ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkItemBean b = new WorkItemBean();
                b.setPO_NO(rs.getString(1));
                b.setLINE_NO(rs.getInt(2));
                b.setITEM_ID(rs.getString(3));
                b.setITEM_DESC(rs.getString(4));
                b.setUOM(rs.getString(5));
                b.setQTY(rs.getFloat(6));
                b.setRATE(rs.getFloat(7));
                b.setVAL(rs.getFloat(8));
                b.setCMT(rs.getString(9));
                b.setPLANT(rs.getString(10));
                b.setPROJ(rs.getString(11));
                b.setTASK(rs.getString(12));
                bean.add(b);
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

        return bean;
    }

    @Override
    public WorkTermBean getWOTerm(String pono
    ) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        WorkTermBean bean = new WorkTermBean();
        try {
            sql = "SELECT cba_wo_term.term_id, ap_terms.description"
                    + "  FROM cba_wo_term, ap_terms"
                    + " WHERE ((cba_wo_term.term_id = ap_terms.term_id)"
                    + " AND cba_wo_term.po_no = '" + pono + "')";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                bean.setPO_NO(pono);
                bean.setTERM_ID(rs.getString(1));
                bean.setTERM_DESC(rs.getString(2));
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
        return bean;
    }

    @Override
    public ArrayList<WorkBean> getWObySid(String sup_id, Boolean isApproved
    ) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<WorkBean> o = new ArrayList<WorkBean>();
        try {
            if (isApproved) {
                sql = "SELECT cba_wo_mst.po_no, TO_CHAR (cba_wo_mst.cr_date, 'DD-MON-YYYY'),"
                        + "       TO_CHAR (cba_wo_mst.exp_date, 'DD-MON-YYYY'),TO_CHAR (cba_wo_mst.fr_date, 'DD-MON-YYYY'), decode(cba_wo_mst.ou,'123','Nandesari'),"
                        + "       cba_user_mst.full_name, po_vendors.vendor_name, cba_wo_mst.site,"
                        + "       cba_wo_mst.cur, cba_wo_mst.status, cba_wo_mst.TYPE, cba_wo_mst.wo_desc,"
                        + "       cba_wo_mst.ret_amt, cba_wo_mst.val, cba_wo_mst.tax_val,"
                        + "       cba_wo_mst.total_val, cba_wo_mst.po_title,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE,cba_wo_mst.dep_amt"
                        + "  FROM cba_wo_mst, cba_user_mst, po_vendors,PO_VENDOR_SITES_ALL"
                        + " WHERE (    (cba_wo_mst.user_id = cba_user_mst.user_id)"
                        + "        AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "        AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE )"
                        + "        AND (cba_wo_mst.sup_id = '" + sup_id + "')"
                        + "        AND (cba_wo_mst.status = 'Approved')"
                        + "       ) ORDER BY cba_wo_mst.po_no";
            } else {
                sql = " SELECT DISTINCT cba_wo_mst.po_no, TO_CHAR (cba_wo_mst.cr_date, 'DD-MON-YYYY'),"
                        + "                TO_CHAR (cba_wo_mst.exp_date, 'DD-MON-YYYY'),TO_CHAR (cba_wo_mst.fr_date, 'DD-MON-YYYY'),"
                        + "                DECODE (cba_wo_mst.ou, '123', 'Nandesari'),"
                        + "                cba_user_mst.full_name, po_vendors.vendor_name,"
                        + "                cba_wo_mst.site, cba_wo_mst.cur, cba_wo_mst.status,"
                        + "                cba_wo_mst.TYPE, cba_wo_mst.wo_desc, cba_wo_mst.ret_amt,"
                        + "                cba_wo_mst.val, cba_wo_mst.tax_val, cba_wo_mst.total_val,"
                        + "                cba_wo_mst.po_title, po_vendor_sites_all.vendor_site_code,cba_wo_mst.dep_amt"
                        + "           FROM cba_wo_mst,"
                        + "                cba_user_mst,"
                        + "                po_vendors,"
                        + "                po_vendor_sites_all,"
                        + "                cba_tran_wo"
                        + "          WHERE (    (cba_wo_mst.user_id = cba_user_mst.user_id)"
                        + "                 AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "                 AND (cba_tran_wo.po_number = cba_wo_mst.po_no)"
                        + "                 AND (po_vendor_sites_all.vendor_site_id = cba_wo_mst.site)"
                        + "                 AND (cba_wo_mst.sup_id = '" + sup_id + "')"
                        + "                ) ORDER BY cba_wo_mst.po_no";
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean bean = new WorkBean();
                bean.setPO_NO(rs.getString(1));
                bean.setCR_DATE(rs.getString(2));
                bean.setEXP_DATE(rs.getString(3));
                bean.setFR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setSUP_NAME(rs.getString(7));
                bean.setSITE(rs.getString(8));
                bean.setCUR(rs.getString(9));
                bean.setSTATUS(rs.getString(10));
                bean.setTYPE(rs.getString(11));
                bean.setWO_DESC(rs.getString(12));
                bean.setRET_AMT(rs.getFloat(13));
                bean.setVAL(rs.getFloat(14));
                bean.setTOTAL_TAX(rs.getFloat(15));
                bean.setTOTAL_VAL(rs.getFloat(16));
                bean.setSITE_NAME(rs.getString(18));
                bean.setDEP_AMT(rs.getFloat(19));
                o.add(bean);
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
        return o;
    }

    @Override
    public ArrayList<WorkBean> getWO(Boolean isApproved
    ) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<WorkBean> o = new ArrayList<WorkBean>();
        try {
            if (isApproved) {
                sql = "SELECT cba_wo_mst.po_no, TO_CHAR (cba_wo_mst.cr_date, 'DD-MON-YYYY'),"
                        + "       TO_CHAR (cba_wo_mst.exp_date, 'DD-MON-YYYY'),"
                        + "       TO_CHAR (cba_wo_mst.fr_date, 'DD-MON-YYYY'), decode(cba_wo_mst.ou,'123','Nandesari'),"
                        + "       cba_user_mst.full_name, po_vendors.vendor_name, cba_wo_mst.site,"
                        + "       cba_wo_mst.cur, cba_wo_mst.status, cba_wo_mst.TYPE, cba_wo_mst.wo_desc,"
                        + "       cba_wo_mst.ret_amt, cba_wo_mst.val, cba_wo_mst.tax_val,"
                        + "       cba_wo_mst.total_val, cba_wo_mst.po_title,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE,cba_wo_mst.dep_amt"
                        + "  FROM cba_wo_mst, cba_user_mst, po_vendors,PO_VENDOR_SITES_ALL"
                        + " WHERE (    (cba_wo_mst.user_id = cba_user_mst.user_id)"
                        + "        AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "        AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE )"
                        + "        AND (cba_wo_mst.status = 'Approved')"
                        + "       ) ORDER BY cba_wo_mst.po_no,po_vendors.vendor_name";
            } else {
                sql = " SELECT DISTINCT cba_wo_mst.po_no, TO_CHAR (cba_wo_mst.cr_date, 'DD-MON-YYYY'),"
                        + "                TO_CHAR (cba_wo_mst.exp_date, 'DD-MON-YYYY'),TO_CHAR (cba_wo_mst.fr_date, 'DD-MON-YYYY'),"
                        + "                DECODE (cba_wo_mst.ou, '123', 'Nandesari'),"
                        + "                cba_user_mst.full_name, po_vendors.vendor_name,"
                        + "                cba_wo_mst.site, cba_wo_mst.cur, cba_wo_mst.status,"
                        + "                cba_wo_mst.TYPE, cba_wo_mst.wo_desc, cba_wo_mst.ret_amt,"
                        + "                cba_wo_mst.val, cba_wo_mst.tax_val, cba_wo_mst.total_val,"
                        + "                cba_wo_mst.po_title, po_vendor_sites_all.vendor_site_code,cba_wo_mst.dep_amt"
                        + "           FROM cba_wo_mst,"
                        + "                cba_user_mst,"
                        + "                po_vendors,"
                        + "                po_vendor_sites_all,"
                        + "                cba_tran_wo"
                        + "          WHERE (    (cba_wo_mst.user_id = cba_user_mst.user_id)"
                        + "                 AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "                 AND (cba_tran_wo.po_number = cba_wo_mst.po_no)"
                        + "                 AND (po_vendor_sites_all.vendor_site_id = cba_wo_mst.site)"
                        + "                ) ORDER BY cba_wo_mst.po_no,po_vendors.vendor_name";
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean bean = new WorkBean();
                bean.setPO_NO(rs.getString(1));
                bean.setCR_DATE(rs.getString(2));
                bean.setEXP_DATE(rs.getString(3));
                bean.setFR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setSUP_NAME(rs.getString(7));
                bean.setSITE(rs.getString(8));
                bean.setCUR(rs.getString(9));
                bean.setSTATUS(rs.getString(10));
                bean.setTYPE(rs.getString(11));
                bean.setWO_DESC(rs.getString(12));
                bean.setRET_AMT(rs.getFloat(13));
                bean.setVAL(rs.getFloat(14));
                bean.setTOTAL_TAX(rs.getFloat(15));
                bean.setTOTAL_VAL(rs.getFloat(16));
                bean.setSITE_NAME(rs.getString(18));
                bean.setDEP_AMT(rs.getFloat(19));
                o.add(bean);
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
        return o;
    }

    @Override
    public ArrayList<WorkBean> getWObyOU(String ou, Boolean isApproved
    ) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<WorkBean> o = new ArrayList<WorkBean>();
        try {
            if (isApproved) {
                sql = "SELECT cba_wo_mst.po_no, TO_CHAR (cba_wo_mst.cr_date, 'DD-MON-YYYY'),"
                        + "       TO_CHAR (cba_wo_mst.exp_date, 'DD-MON-YYYY'),TO_CHAR (cba_wo_mst.fr_date, 'DD-MON-YYYY'), decode(cba_wo_mst.ou,'123','Nandesari'),"
                        + "       cba_user_mst.full_name, po_vendors.vendor_name, cba_wo_mst.site,"
                        + "       cba_wo_mst.cur, cba_wo_mst.status, cba_wo_mst.TYPE, cba_wo_mst.wo_desc,"
                        + "       cba_wo_mst.ret_amt, cba_wo_mst.val, cba_wo_mst.tax_val,"
                        + "       cba_wo_mst.total_val, cba_wo_mst.po_title,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE, cba_wo_mst.dep_amt"
                        + "  FROM cba_wo_mst, cba_user_mst, po_vendors,PO_VENDOR_SITES_ALL"
                        + " WHERE (    (cba_wo_mst.user_id = cba_user_mst.user_id)"
                        + "        AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "        AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE )"
                        + "        AND (cba_wo_mst.ou = '" + ou + "')"
                        + "        AND (cba_wo_mst.status = 'Approved')"
                        + "       ) ORDER BY cba_wo_mst.po_no";
            } else {
                sql = " SELECT DISTINCT cba_wo_mst.po_no, TO_CHAR (cba_wo_mst.cr_date, 'DD-MON-YYYY'),"
                        + "                TO_CHAR (cba_wo_mst.exp_date, 'DD-MON-YYYY'),TO_CHAR (cba_wo_mst.fr_date, 'DD-MON-YYYY'),"
                        + "                DECODE (cba_wo_mst.ou, '123', 'Nandesari'),"
                        + "                cba_user_mst.full_name, po_vendors.vendor_name,"
                        + "                cba_wo_mst.site, cba_wo_mst.cur, cba_wo_mst.status,"
                        + "                cba_wo_mst.TYPE, cba_wo_mst.wo_desc, cba_wo_mst.ret_amt,"
                        + "                cba_wo_mst.val, cba_wo_mst.tax_val, cba_wo_mst.total_val,"
                        + "                cba_wo_mst.po_title, po_vendor_sites_all.vendor_site_code, cba_wo_mst.dep_amt"
                        + "           FROM cba_wo_mst,"
                        + "                cba_user_mst,"
                        + "                po_vendors,"
                        + "                po_vendor_sites_all,"
                        + "                cba_tran_wo"
                        + "          WHERE (    (cba_wo_mst.user_id = cba_user_mst.user_id)"
                        + "                 AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "                 AND (cba_tran_wo.po_number = cba_wo_mst.po_no)"
                        + "                 AND (po_vendor_sites_all.vendor_site_id = cba_wo_mst.site)"
                        + "                 AND (cba_wo_mst.ou = '" + ou + "')"
                        + "                ) ORDER BY cba_wo_mst.po_no";
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean bean = new WorkBean();
                bean.setPO_NO(rs.getString(1));
                bean.setCR_DATE(rs.getString(2));
                bean.setEXP_DATE(rs.getString(3));
                bean.setFR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setSUP_NAME(rs.getString(7));
                bean.setSITE(rs.getString(8));
                bean.setCUR(rs.getString(9));
                bean.setSTATUS(rs.getString(10));
                bean.setTYPE(rs.getString(11));
                bean.setWO_DESC(rs.getString(12));
                bean.setRET_AMT(rs.getFloat(13));
                bean.setVAL(rs.getFloat(14));
                bean.setTOTAL_TAX(rs.getFloat(15));
                bean.setTOTAL_VAL(rs.getFloat(16));
                bean.setSITE_NAME(rs.getString(18));
                bean.setDEP_AMT(rs.getFloat(19));
                o.add(bean);
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
        return o;
    }

    @Override
    public ArrayList<WorkBean> getWObyUid(String uid, Boolean isApproved
    ) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<WorkBean> o = new ArrayList<WorkBean>();
        try {
            if (isApproved) {
                sql = "SELECT cba_wo_mst.po_no, TO_CHAR (cba_wo_mst.cr_date, 'DD-MON-YYYY'),"
                        + "       TO_CHAR (cba_wo_mst.exp_date, 'DD-MON-YYYY'), TO_CHAR (cba_wo_mst.fr_date, 'DD-MON-YYYY'),decode(cba_wo_mst.ou,'123','Nandesari'),"
                        + "       cba_user_mst.full_name, po_vendors.vendor_name, cba_wo_mst.site,"
                        + "       cba_wo_mst.cur, cba_wo_mst.status, cba_wo_mst.TYPE, cba_wo_mst.wo_desc,"
                        + "       cba_wo_mst.ret_amt, cba_wo_mst.val, cba_wo_mst.tax_val,"
                        + "       cba_wo_mst.total_val, cba_wo_mst.po_title,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE,cba_wo_mst.dep_amt"
                        + "  FROM cba_wo_mst, cba_user_mst, po_vendors,PO_VENDOR_SITES_ALL"
                        + " WHERE (    (cba_wo_mst.user_id = cba_user_mst.user_id)"
                        + "        AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "        AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE )"
                        + "        AND (cba_wo_mst.user_id = '" + uid + "')"
                        + "        AND (cba_wo_mst.status = 'Approved')"
                        + "       ) ORDER BY cba_wo_mst.po_no ";
            } else {
                sql = " SELECT DISTINCT cba_wo_mst.po_no, TO_CHAR (cba_wo_mst.cr_date, 'DD-MON-YYYY'),"
                        + "                TO_CHAR (cba_wo_mst.exp_date, 'DD-MON-YYYY'),TO_CHAR (cba_wo_mst.fr_date, 'DD-MON-YYYY'),"
                        + "                DECODE (cba_wo_mst.ou, '123', 'Nandesari'),"
                        + "                cba_user_mst.full_name, po_vendors.vendor_name,"
                        + "                cba_wo_mst.site, cba_wo_mst.cur, cba_wo_mst.status,"
                        + "                cba_wo_mst.TYPE, cba_wo_mst.wo_desc, cba_wo_mst.ret_amt,"
                        + "                cba_wo_mst.val, cba_wo_mst.tax_val, cba_wo_mst.total_val,"
                        + "                cba_wo_mst.po_title, po_vendor_sites_all.vendor_site_code,cba_wo_mst.dep_amt"
                        + "           FROM cba_wo_mst,"
                        + "                cba_user_mst,"
                        + "                po_vendors,"
                        + "                po_vendor_sites_all,"
                        + "                cba_tran_wo"
                        + "          WHERE (    (cba_wo_mst.user_id = cba_user_mst.user_id)"
                        + "                 AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "                 AND (cba_tran_wo.po_number = cba_wo_mst.po_no)"
                        + "                 AND (po_vendor_sites_all.vendor_site_id = cba_wo_mst.site)"
                        + "                 AND (cba_tran_wo.user_id = '" + uid + "')"
                        + "                ) ORDER BY cba_wo_mst.po_no";
            }
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean bean = new WorkBean();
                bean.setPO_NO(rs.getString(1));
                bean.setCR_DATE(rs.getString(2));
                bean.setEXP_DATE(rs.getString(3));
                bean.setFR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setSUP_NAME(rs.getString(7));
                bean.setSITE(rs.getString(8));
                bean.setCUR(rs.getString(9));
                bean.setSTATUS(rs.getString(10));
                bean.setTYPE(rs.getString(11));
                bean.setWO_DESC(rs.getString(12));
                bean.setRET_AMT(rs.getFloat(13));
                bean.setVAL(rs.getFloat(14));
                bean.setTOTAL_TAX(rs.getFloat(15));
                bean.setTOTAL_VAL(rs.getFloat(16));
                bean.setSITE_NAME(rs.getString(18));
                bean.setDEP_AMT(rs.getFloat(19));
                o.add(bean);
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
        return o;
    }

    @Override
    public void UpdateWO(WorkBean wo, ArrayList<WorkItemBean> woit, ArrayList<WorkTaxBean> wotx, WorkTermBean woterm
    ) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "START UPDATEING WO : {0}", wo.getPO_NO());
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "DEP_SMT{0}", wo.getDEP_AMT());
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "TYPE OF WO{0}", wo.getTYPE());

            sql = "UPDATE cba_wo_mst"
                    + "   SET exp_date = to_date(?,'MM/DD/YYYY HH:MI:SS AM'),fr_date = to_date(?,'MM/DD/YYYY HH:MI:SS AM'),ou = ?,"
                    + "       sup_id = ?,site = ?,cur = ?,"
                    + "       status = ?,TYPE = ?,wo_desc = ?,ret_amt = ROUND(?, 2),val = ROUND(?, 2),"
                    + "       tax_val = ROUND(?, 2),total_val = ROUND(?, 2),po_title = ?,dep_amt = ROUND(?, 2)"
                    + " WHERE po_no = ?";
            ps = con.prepareStatement(sql);
            ps = con.prepareStatement(sql);
            ps.setString(1, wo.getEXP_DATE());
            ps.setString(2, wo.getFR_DATE());
            ps.setString(3, "123");
            ps.setString(4, wo.getSUP_ID());
            ps.setString(5, wo.getSITE());
            ps.setString(6, wo.getCUR());
            ps.setString(7, wo.getSTATUS());
            ps.setString(8, wo.getTYPE());
            ps.setString(9, wo.getWO_DESC());
            ps.setFloat(10, wo.getRET_AMT());
            ps.setFloat(11, wo.getVAL());
            ps.setFloat(12, wo.getTOTAL_TAX());
            ps.setFloat(13, wo.getTOTAL_VAL());
            ps.setString(14, wo.getPO_TITLE());
            ps.setFloat(15, wo.getDEP_AMT());
            ps.setString(16, wo.getPO_NO());
            ps.executeQuery();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "updated WO_MST WO_NO : {0}", wo.getPO_NO() + "and Status : " + wo.getTYPE());
            Statement st = con.createStatement();
            st.addBatch("delete from cba_wo_item where po_no = '" + wo.getPO_NO() + "'");
            st.addBatch("delete from cba_wo_re where po_no = '" + wo.getPO_NO() + "'");
            st.addBatch("delete from cba_wo_tax where po_no = '" + wo.getPO_NO() + "'");
            st.addBatch("delete from cba_wo_term where po_no = '" + wo.getPO_NO() + "'");
            st.executeBatch();
            st.close();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "cleared WO_OTHER");
            con.commit();
            InsWOItem(woit, con);
            InsWORemain(woit, con);
            InsWOTax(wotx, con);
            InsWOTerm(woterm, con);
        } catch (SQLException ex) {
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
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
    public void UpdateWOstatus(String ponum, String status
    ) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            sql = "update cba_wo_mst set status='" + status + "' where po_no = '" + ponum + "'";
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "updated  WO_STATUS : {0} WO_NO : {1}", new Object[]{status, ponum});
        } catch (SQLException ex) {
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
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
    public boolean isProjWO(String pono) {
        boolean ans = false;
        try {
            Connection con = Logic.DBmanager.GetConnection();
            sql = "select type from cba_wo_mst where po_no = '" + pono + "'";
            Logic.GetMethod g = new Logic.GetMethod();
            ResultSet rs = g.Get_rs(sql);

            while (rs.next()) {
                if (rs.getString(1).equalsIgnoreCase("Project")) {
                    ans = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }

}
