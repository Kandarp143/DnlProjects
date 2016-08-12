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
import Interface.BillInter;
import Logic.LineNOCompatator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class BillDao implements BillInter {

    static final Logger logger = Logger.getLogger(BillDao.class.getName());

    public static void main(String[] args) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String s = "";
        String s2 = "";
        String s3 = "";
        String d1 = "20151017";
        String d2 = "20151217";
        try {
            s = "SELECT   ven.vendor_name, SUM (mst.total_val) expanse,mst.cr_date\n"
                    + "    FROM cba_bill_mst mst, cba_wo_mst wo, po_vendors ven\n"
                    + "   WHERE mst.po_no = wo.po_no\n"
                    + "     AND mst.status <> 'Rejected'\n"
                    + "     AND ven.vendor_number = wo.sup_id\n"
                    + "     AND ltrim(rtrim(to_char(trunc(mst.cr_date),'MMDDYYYY')))='12042015'"
                    + "     AND upper(ven.vendor_name) = upper('Hi-Tech Controls')\n"
                    + "GROUP BY ven.vendor_name,mst.cr_date\n"
                    + "ORDER BY ven.vendor_name";
            s2 = "SELECT   *\n"
                    + "    FROM cba_bill_mst mst, cba_wo_mst wo, po_vendors ven\n"
                    + "   WHERE mst.po_no = wo.po_no\n"
                    + "     AND mst.status <> 'Rejected'\n"
                    + "     AND ven.vendor_number = wo.sup_id\n"
                    + "     AND ven.vendor_name = 'Hi-Tech Controls'\n"
                    + "     AND mst.bill_no = '719'\n"
                    + "ORDER BY ven.vendor_name";
            s3 = "select count(*) from cba_bill_mst ";

            ps = con.prepareStatement(s3);
//            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, s);
            rs = ps.executeQuery();
            while (rs.next()) {
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "{0}", rs.getString(1));
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
    }
    private String sql;

    @Override
    public void CreateBill(WorkBean wo, ArrayList< WorkItemBean> woit, ArrayList< WorkTaxBean> wotx, WorkTermBean woterm) {
        InsBill(wo);
        InsBillItem(woit);
        UpdateWORemain(woit, Boolean.FALSE);
        InsBillTax(wotx);
        InsBillTerm(woterm);
    }

    @Override
    public void InsBill(WorkBean bean) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            con.setAutoCommit(false);
            sql = "insert into CBA_BILL_MST (BILL_NO, PO_NO, BILL_DATE, "
                    + "CR_DATE, OU, USER_ID, STATUS, VAL, TAX_VAL, TOTAL_VAL, BILL_DESC,BILL_USR_NO )"
                    + "values(?,?,to_date(?,'MM/DD/YYYY HH:MI:SS AM'),sysdate,?,?,?,ROUND(?,2),ROUND(?,2),ROUND(?,2),?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getBILL_NO());
            ps.setString(2, bean.getPO_NO());
            ps.setString(3, bean.getBILL_DATE());
            ps.setString(4, bean.getOU());
            ps.setString(5, bean.getUSER_ID());
            ps.setString(6, bean.getSTATUS());
            ps.setFloat(7, bean.getVAL());
            ps.setFloat(8, bean.getTOTAL_TAX());
            ps.setFloat(9, bean.getTOTAL_VAL());
            ps.setString(10, bean.getWO_DESC());
            ps.setString(11, bean.getBILL_ID());
            ps.executeUpdate();
            con.commit();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "BILL_MST INS :{0}", bean.getBILL_NO());
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BillDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
    public void InsBillItem(ArrayList< WorkItemBean> woit) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            con.setAutoCommit(false);
            sql = "insert into CBA_BILL_ITEM (BILL_NO,LINE_NO,ITEM_ID,ITEM_DESC,UOM,QTY,RATE,VAL,CMT,PLANT,CC,PROJ,TASK)"
                    + " values(?,?,?,?,?,ROUND(?,2),ROUND(?,2),ROUND(?,2),?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (WorkItemBean bean : woit) {
                ps.setString(1, bean.getBILL_NO());
                ps.setInt(2, bean.getLINE_NO());
                ps.setString(3, bean.getITEM_ID());
                ps.setString(4, bean.getITEM_DESC());
                ps.setString(5, bean.getUOM());
                ps.setFloat(6, bean.getQTY());
                ps.setFloat(7, bean.getRATE());
                ps.setFloat(8, bean.getVAL());
                ps.setString(9, bean.getCMT());
                ps.setString(10, bean.getPLANT());
                ps.setString(11, bean.getCC());
                ps.setString(12, bean.getPROJ());
                ps.setString(13, bean.getTASK());
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "BILL_ITEM INS");
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BillDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
    public void InsBillTax(ArrayList<WorkTaxBean> wotx) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            con.setAutoCommit(false);
            sql = "insert into CBA_BILL_TAX (BILL_NO,TAX_TYPE ,TAX_VAL )"
                    + "values(?,?,ROUND(?,2))";
            ps = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (WorkTaxBean bean : wotx) {
                ps.setString(1, bean.getBILL_NO());
                ps.setString(2, bean.getTAX_TYPE());
                ps.setFloat(3, bean.getTAX_VAL());
                ps.addBatch();
                Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "BILL_TAX INS : {0} {1}{2}", new Object[]{bean.getBILL_NO(), bean.getTAX_TYPE(), bean.getTAX_VAL()});
            }
            ps.executeBatch();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BillDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
    public void InsBillTerm(WorkTermBean bean) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            con.setAutoCommit(false);
            sql = "insert into CBA_BILL_TERM (BILL_NO ,TERM_ID ,TERM_DESC )"
                    + "values(?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, bean.getBILL_NO());
            ps.setString(2, bean.getTERM_ID());
            ps.setString(3, bean.getTERM_DESC());
            ps.executeUpdate();
            con.commit();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "BILL_TERMS INS");
        } catch (SQLException ex) {
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BillDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
    public void UpdateBill(WorkBean wo, ArrayList< WorkItemBean> woit, ArrayList< WorkTaxBean> wotx, WorkTermBean woterm) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            con.setAutoCommit(false);
            sql = "UPDATE cba_bill_mst"
                    + "   SET bill_date = to_date(?,'MM/DD/YYYY HH:MI:SS AM'),"
                    + "       status = ?,bill_desc = ?,val = ROUND(?,2),"
                    + "       tax_val = ROUND(?,2),total_val = ROUND(?,2),bill_usr_no =?"
                    + " WHERE bill_no = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, wo.getBILL_DATE());
            ps.setString(2, wo.getSTATUS());
            ps.setString(3, wo.getWO_DESC());
            ps.setFloat(4, wo.getVAL());
            ps.setFloat(5, wo.getTOTAL_TAX());
            ps.setFloat(6, wo.getTOTAL_VAL());
            ps.setString(7, wo.getBILL_ID());
            ps.setString(8, wo.getBILL_NO());
            ps.executeUpdate();
            Statement st = con.createStatement();
            st.addBatch("delete from cba_bill_item where bill_no = " + wo.getBILL_NO() + "");
            st.addBatch("delete from cba_bill_tax where bill_no = " + wo.getBILL_NO() + "");
            st.addBatch("delete from cba_bill_term where bill_no = " + wo.getBILL_NO() + "");
            st.executeBatch();
            st.close();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "cleared BILL_OTHER");
            InsBillItem(woit);
            InsBillTax(wotx);
            InsBillTerm(woterm);
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
    public void UpdateBillstatus(String billno, String status) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            sql = "update cba_bill_mst set status='" + status + "' where bill_no = '" + billno + "'";
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            con.commit();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "BILL_STATUS : {0} BILL_NO : {1} UPDATED", new Object[]{status, billno});
        } catch (SQLException ex) {
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        }
    }

    @Override
    public void UpdateWORemain(ArrayList<WorkItemBean> problem, Boolean isReject) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<WorkItemBean> quebean = new ArrayList<WorkItemBean>();
        ArrayList<WorkItemBean> ansbean = new ArrayList<WorkItemBean>();
        try {
            StringBuilder inClause = new StringBuilder();
            for (int i = 0; problem.size() > i; i++) {
                inClause.append('?');
                if (i != problem.size() - 1) {
                    inClause.append(',');
                }
            }
            ps = con.prepareStatement("select * from CBA_WO_RE where (item_id in (" + inClause.toString() + ") and po_no = ?)");
            for (int i = 0; problem.size() > i; i++) {
                ps.setString(i + 1, problem.get(i).getITEM_ID());
            }
            ps.setString(problem.size() + 1, problem.get(0).getPO_NO());
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkItemBean quebeann = new WorkItemBean();
                quebeann.setPO_NO(rs.getString(1));
                quebeann.setLINE_NO(rs.getInt(2));
                quebeann.setITEM_ID(rs.getString(3));
                quebeann.setITEM_DESC(rs.getString(4));
                quebeann.setUOM(rs.getString(5));
                quebeann.setQTY(rs.getFloat(6));
                quebeann.setRATE(rs.getFloat(7));
                quebeann.setVAL(rs.getFloat(8));
                quebeann.setCMT(rs.getString(9));
                quebeann.setPLANT(rs.getString(10));
                quebeann.setISCHECK(rs.getString(11));
                quebean.add(quebeann);
            }
            for (int i = 0; i < quebean.size(); i++) {
                WorkItemBean anss = new WorkItemBean();
                anss.setPO_NO(quebean.get(i).getPO_NO());
                anss.setITEM_ID(quebean.get(i).getITEM_ID());
                anss.setUOM(quebean.get(i).getUOM());
                anss.setRATE(quebean.get(i).getRATE());
                anss.setISCHECK(quebean.get(i).getISCHECK());
                if (quebean.get(i).getISCHECK().equals("Y")) {
                    if (isReject) {
                        anss.setQTY((quebean.get(i).getQTY()) + (problem.get(i).getQTY()));
                        anss.setVAL((quebean.get(i).getRATE()) * ((quebean.get(i).getQTY()) + (problem.get(i).getQTY())));
                    } else {
                        anss.setQTY((quebean.get(i).getQTY()) - (problem.get(i).getQTY()));
                        anss.setVAL((quebean.get(i).getRATE()) * ((quebean.get(i).getQTY()) - (problem.get(i).getQTY())));
                    }
                } else {
                    anss.setQTY(0);
                    anss.setVAL(0);
                }
                anss.setCMT(quebean.get(i).getCMT());
                anss.setPLANT(quebean.get(i).getPLANT());
                ansbean.add(anss);
            }
            sql = "update cba_wo_re set qty = ROUND(?,2) ,val= ROUND(?,2) where item_id = ? and po_no = ?";
            ps = con.prepareStatement(sql);
            for (WorkItemBean bean : ansbean) {
                logger.log(Level.SEVERE, "UPDATED");
                ps.setFloat(1, bean.getQTY());
                ps.setFloat(2, bean.getVAL());
                ps.setString(3, bean.getITEM_ID());
                ps.setString(4, bean.getPO_NO());
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
            logger.log(Level.SEVERE, "QTY REMAIN UPDATED");
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BillDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
    }

    @Override
    public ArrayList<WorkBean> getBillbyPONO(String pono) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList< WorkBean> o = new ArrayList< WorkBean>();
        try {
            sql = "SELECT cba_bill_mst.bill_no, cba_bill_mst.po_no, to_char(cba_bill_mst.bill_date,'DD-MON-YYYY'),"
                    + "       to_char(cba_bill_mst.cr_date,'DD-MON-YYYY'), cba_bill_mst.ou, cba_user_mst.full_name,"
                    + "       cba_bill_mst.status, cba_bill_mst.val, cba_bill_mst.tax_val,"
                    + "       cba_bill_mst.total_val, cba_bill_mst.bill_desc, po_vendors.vendor_name,"
                    + "       cba_wo_mst.site, cba_wo_mst.cur,cba_bill_mst.bill_usr_no"
                    + "  FROM cba_bill_mst, cba_user_mst, cba_wo_mst, po_vendors"
                    + " WHERE (    (cba_bill_mst.user_id = cba_user_mst.user_id)"
                    + "        AND (cba_bill_mst.po_no = cba_wo_mst.po_no)"
                    + "        AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                    + "        AND (cba_bill_mst.po_no = '" + pono + "')" + "       ) ORDER BY cba_bill_mst.bill_no ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean bean = new WorkBean();
                bean.setBILL_NO(rs.getString(1));
                bean.setPO_NO(rs.getString(2));
                bean.setBILL_DATE(rs.getString(3));
                bean.setCR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setSTATUS(rs.getString(7));
                bean.setVAL(rs.getFloat(8));
                bean.setTOTAL_TAX(rs.getFloat(9));
                bean.setTOTAL_VAL(rs.getFloat(10));
                bean.setWO_DESC(rs.getString(11));
                bean.setSUP_NAME(rs.getString(12));
                bean.setSITE(rs.getString(13));
                bean.setCUR(rs.getString(14));
                bean.setBILL_ID(rs.getString(15));
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
    public ArrayList<WorkBean> getBill_usr_no(String po) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<WorkBean> bean = new ArrayList<WorkBean>();
        try {
            sql = "SELECT REGEXP_REPLACE(initcap(a.bill_usr_no),'[^[:alnum:]'' '']', NULL),a.BILL_USR_NO"
                    + "  FROM cba_bill_mst a, cba_wo_mst b "
                    + " WHERE a.po_no = b.po_no AND a.po_no = '" + po + "'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean b = new WorkBean();
                b.setBILL_ID(rs.getString(1));
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
    public ArrayList<WorkBean> getBillbyStatus(String uid, String status) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<WorkBean> o = new ArrayList< WorkBean>();
        try {
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "VALLED : {0}", status);
            String sql = " SELECT cba_bill_mst.bill_no, cba_bill_mst.po_no, to_char(cba_bill_mst.bill_date,'DD-MON-YYYY'),"
                    + "                            to_char(cba_bill_mst.cr_date,'DD-MON-YYYY'), cba_bill_mst.ou, cba_user_mst.full_name,"
                    + "                            cba_bill_mst.status, cba_bill_mst.val, cba_bill_mst.tax_val,"
                    + "                            cba_bill_mst.total_val, cba_bill_mst.bill_desc, po_vendors.vendor_name,"
                    + "                            cba_wo_mst.site, cba_wo_mst.cur,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE,cba_bill_mst.bill_usr_no,"
                    + "                            cba_inv_mst.inv_req,cba_inv_mst.inv_id,cba_inv_mst.inv_no"
                    + "                       FROM cba_bill_mst, cba_user_mst, cba_wo_mst, po_vendors,PO_VENDOR_SITES_ALL,cba_inv_mst"
                    + "                      WHERE (    (cba_bill_mst.user_id = cba_user_mst.user_id)"
                    + "                             AND (cba_bill_mst.po_no = cba_wo_mst.po_no)"
                    + "                             AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                    + "                             AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE )"
                    + "                             AND (cba_bill_mst.bill_no = cba_inv_mst.bill_no)"
                    + "                             AND (cba_bill_mst.user_id = '" + uid + "')"
                    + "                             AND (cba_bill_mst.status = '" + status + "')   ) ORDER BY cba_bill_mst.bill_no ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean bean = new WorkBean();
                bean.setBILL_NO(rs.getString(1));
                bean.setPO_NO(rs.getString(2));
                bean.setBILL_DATE(rs.getString(3));
                bean.setCR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setSTATUS(rs.getString(7));
                bean.setVAL(rs.getFloat(8));
                bean.setTOTAL_TAX(rs.getFloat(9));
                bean.setTOTAL_VAL(rs.getFloat(10));
                bean.setWO_DESC(rs.getString(11));
                bean.setSUP_NAME(rs.getString(12));
                bean.setSITE(rs.getString(13));
                bean.setCUR(rs.getString(14));
                bean.setSITE_NAME(rs.getString(15));
                bean.setBILL_ID(rs.getString(16));
                bean.setINV_REQ_ID(rs.getString(17));
                bean.setINV_ID(rs.getInt(18));
                bean.setINV_NUM(rs.getString(19));
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

    public ArrayList<WorkBean> getBillbyStatus(String status) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<WorkBean> o = new ArrayList<WorkBean>();
        try {
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "VALLED : {0}", status);
            sql = "SELECT cba_bill_mst.bill_no, cba_bill_mst.po_no, to_char(cba_bill_mst.bill_date,'DD-MON-YYYY'),"
                    + "       to_char(cba_bill_mst.cr_date,'DD-MON-YYYY'), cba_bill_mst.ou, cba_user_mst.full_name,"
                    + "       cba_bill_mst.status, cba_bill_mst.val, cba_bill_mst.tax_val,"
                    + "       cba_bill_mst.total_val, cba_bill_mst.bill_desc, po_vendors.vendor_name,"
                    + "       cba_wo_mst.site, cba_wo_mst.cur,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE,cba_bill_mst.bill_usr_no,"
                    + "       cba_inv_mst.inv_req,cba_inv_mst.inv_id,cba_inv_mst.inv_no"
                    + "  FROM cba_bill_mst, cba_user_mst, cba_wo_mst, po_vendors,PO_VENDOR_SITES_ALL,cba_inv_mst"
                    + " WHERE (    (cba_bill_mst.user_id = cba_user_mst.user_id)"
                    + "        AND (cba_bill_mst.po_no = cba_wo_mst.po_no)"
                    + "        AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                    + "        AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE )"
                    + "        AND (cba_bill_mst.bill_no = cba_inv_mst.bill_no)"
                    + "        AND (cba_bill_mst.status = '" + status + "')" + " ) ORDER BY cba_bill_mst.bill_no ";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean bean = new WorkBean();
                bean.setBILL_NO(rs.getString(1));
                bean.setPO_NO(rs.getString(2));
                bean.setBILL_DATE(rs.getString(3));
                bean.setCR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setSTATUS(rs.getString(7));
                bean.setVAL(rs.getFloat(8));
                bean.setTOTAL_TAX(rs.getFloat(9));
                bean.setTOTAL_VAL(rs.getFloat(10));
                bean.setWO_DESC(rs.getString(11));
                bean.setSUP_NAME(rs.getString(12));
                bean.setSITE(rs.getString(13));
                bean.setCUR(rs.getString(14));
                bean.setSITE_NAME(rs.getString(15));
                bean.setBILL_ID(rs.getString(16));
                bean.setINV_REQ_ID(rs.getString(17));
                bean.setINV_ID(rs.getInt(18));
                bean.setINV_NUM(rs.getString(19));
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
    public WorkBean getBill(String billno) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        WorkBean bean = new WorkBean();
        try {
            sql = "SELECT   cba_bill_mst.bill_no, cba_bill_mst.po_no,"
                    + "         TO_CHAR (cba_bill_mst.bill_date, 'DD-MON-YYYY') bill_date,"
                    + "         TO_CHAR (cba_bill_mst.cr_date, 'DD-MON-YYYY') cr_date,"
                    + "         cba_bill_mst.ou, cba_user_mst.full_name, cba_bill_mst.status,"
                    + "         cba_bill_mst.val, cba_bill_mst.tax_val, cba_bill_mst.total_val,"
                    + "         cba_bill_mst.bill_desc, po_vendors.vendor_number,"
                    + "         po_vendors.vendor_name, cba_wo_mst.site, cba_wo_mst.cur,"
                    + "         cba_wo_mst.po_title, po_vendor_sites_all.vendor_site_code,"
                    + "         cba_bill_mst.bill_usr_no, cba_inv_mst.inv_id, cba_inv_mst.inv_no,"
                    + "         cba_inv_mst.inv_req"
                    + "    FROM cba_bill_mst,"
                    + "         cba_user_mst,"
                    + "         cba_wo_mst,"
                    + "         po_vendors,"
                    + "         po_vendor_sites_all,"
                    + "         cba_inv_mst"
                    + "   WHERE (    (cba_bill_mst.user_id = cba_user_mst.user_id)"
                    + "          AND (cba_bill_mst.po_no = cba_wo_mst.po_no)"
                    + "          AND (po_vendor_sites_all.vendor_site_id = cba_wo_mst.site)"
                    + "          AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                    + "          AND (cba_inv_mst.bill_no(+) = cba_bill_mst.bill_no)"
                    + "          AND (cba_bill_mst.bill_no = '" + billno + "')"
                    + "         )"
                    + "ORDER BY cba_bill_mst.bill_no";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                bean.setBILL_NO(rs.getString(1));
                bean.setPO_NO(rs.getString(2));
                bean.setBILL_DATE(rs.getString(3));
                bean.setCR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setSTATUS(rs.getString(7));
                bean.setVAL(rs.getFloat(8));
                bean.setTOTAL_TAX(rs.getFloat(9));
                bean.setTOTAL_VAL(rs.getFloat(10));
                bean.setWO_DESC(rs.getString(11));
                bean.setSUP_ID(rs.getString(12));
                bean.setSUP_NAME(rs.getString(13));
                bean.setSITE(rs.getString(14));
                bean.setCUR(rs.getString(15));
                bean.setPO_TITLE(rs.getString(16));
                bean.setSITE_NAME(rs.getString(17));
                bean.setBILL_ID(rs.getString(18));
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
    public ArrayList<WorkTaxBean> getBillTax(String billno) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList< WorkTaxBean> bean = new ArrayList< WorkTaxBean>();
        try {
            sql = "SELECT cba_bill_tax.bill_no,ja_in_tax_codes.tax_id, ja_in_tax_codes.tax_name,"
                    + "       ja_in_tax_codes.tax_descr, cba_bill_tax.tax_val"
                    + "  FROM cba_bill_tax, ja_in_tax_codes"
                    + " WHERE ((cba_bill_tax.tax_type = ja_in_tax_codes.tax_id)"
                    + " AND (cba_bill_tax.bill_no = '" + billno + "'))";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkTaxBean b = new WorkTaxBean();
                b.setBILL_NO(rs.getString(1));
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
    public ArrayList<WorkItemBean> getBillItem(String billno) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<WorkItemBean> bean = new ArrayList< WorkItemBean>();
        try {
            sql = "SELECT cba_bill_item.bill_no,cba_bill_item.line_no,cba_bill_item.item_id, mtl_system_items.description, cba_bill_item.uom,"
                    + "       cba_bill_item.qty, cba_bill_item.rate, cba_bill_item.val, cba_bill_item.cmt,cba_bill_item.plant,"
                    + "        mtl_system_items.INVENTORY_ITEM_ID,cba_bill_item.cc,cba_bill_item.proj,cba_bill_item.task"
                    + "  FROM cba_bill_item, mtl_system_items"
                    + " WHERE ((cba_bill_item.item_id = mtl_system_items.segment1)"
                    + " AND (mtl_system_items.organization_id = 0)"
                    + " AND (cba_bill_item.bill_no = " + billno + ")" + " ) order by cba_bill_item.line_no";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkItemBean b = new WorkItemBean();
                b.setBILL_NO(rs.getString(1));
                b.setLINE_NO(rs.getInt(2));
                b.setITEM_ID(rs.getString(3));
                b.setITEM_DESC(rs.getString(4));
                b.setUOM(rs.getString(5));
                b.setQTY(rs.getFloat(6));
                b.setRATE(rs.getFloat(7));
                b.setVAL(rs.getFloat(8));
                b.setCMT(rs.getString(9));
                b.setPLANT(rs.getString(10));
                b.setINV_ITEM_ID(rs.getString(11));
                b.setCC(rs.getString(12));
                b.setPROJ(rs.getString(13));
                b.setTASK(rs.getString(14));
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
        Collections.sort(bean, new LineNOCompatator());
        return bean;
    }

    @Override
    public WorkTermBean getBillTerm(String billno) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        WorkTermBean bean = new WorkTermBean();
        try {
            sql = "SELECT cba_bill_term.term_id, ap_terms.description"
                    + "  FROM ap_terms, cba_bill_term" + " WHERE (    (cba_bill_term.term_id = ap_terms.term_id)"
                    + "        AND (cba_bill_term.bill_no = '" + billno + "')" + "       )";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
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
    public ArrayList<WorkBean> getBillbyUid(String uid, Boolean isApproved) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList< WorkBean> o = new ArrayList< WorkBean>();
        try {
            if (isApproved) {
                sql = "SELECT cba_bill_mst.bill_no, cba_bill_mst.po_no, to_char(cba_bill_mst.bill_date,'DD-MON-YYYY'),"
                        + "       to_char(cba_bill_mst.cr_date,'DD-MON-YYYY'), cba_bill_mst.ou, cba_user_mst.full_name,"
                        + "       cba_bill_mst.status, cba_bill_mst.val, cba_bill_mst.tax_val,"
                        + "       cba_bill_mst.total_val, cba_bill_mst.bill_desc, po_vendors.vendor_name,"
                        + "       cba_wo_mst.site, cba_wo_mst.cur,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE,cba_bill_mst.bill_usr_no"
                        + "  FROM cba_bill_mst, cba_user_mst, cba_wo_mst, po_vendors,PO_VENDOR_SITES_ALL"
                        + " WHERE (    (cba_bill_mst.user_id = cba_user_mst.user_id)"
                        + "        AND (cba_bill_mst.po_no = cba_wo_mst.po_no)"
                        + "        AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "        AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE )"
                        + "        AND (cba_bill_mst.user_id = '" + uid + "')"
                        + "        AND (cba_bill_mst.status = 'Approved' OR cba_bill_mst.status = 'INV Created')" + "       )"
                        + "  ORDER BY cba_bill_mst.status,to_number(trim(cba_bill_mst.bill_no))";

            } else {
                sql = "SELECT cba_bill_mst.bill_no, cba_bill_mst.po_no, to_char(cba_bill_mst.bill_date,'DD-MON-YYYY'),"
                        + "       to_char(cba_bill_mst.cr_date,'DD-MON-YYYY'), cba_bill_mst.ou, cba_user_mst.full_name,"
                        + "       cba_bill_mst.status, cba_bill_mst.val, cba_bill_mst.tax_val,"
                        + "       cba_bill_mst.total_val, cba_bill_mst.bill_desc, po_vendors.vendor_name,"
                        + "       cba_wo_mst.site, cba_wo_mst.cur,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE,cba_bill_mst.bill_usr_no"
                        + "  FROM cba_bill_mst, cba_user_mst, cba_wo_mst,  po_vendors,PO_VENDOR_SITES_ALL"
                        + " WHERE (    (cba_bill_mst.user_id = cba_user_mst.user_id)"
                        + "        AND (cba_bill_mst.po_no = cba_wo_mst.po_no)"
                        + "        AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE )"
                        + "        AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "        AND (cba_bill_mst.user_id = '" + uid + "')" + "       ) "
                        + " ORDER BY cba_bill_mst.status,to_number(trim(cba_bill_mst.bill_no))";

            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean bean = new WorkBean();
                bean.setBILL_NO(rs.getString(1));
                bean.setPO_NO(rs.getString(2));
                bean.setBILL_DATE(rs.getString(3));
                bean.setCR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setSTATUS(rs.getString(7));
                bean.setVAL(rs.getFloat(8));
                bean.setTOTAL_TAX(rs.getFloat(9));
                bean.setTOTAL_VAL(rs.getFloat(10));
                bean.setWO_DESC(rs.getString(11));
                bean.setSUP_NAME(rs.getString(12));
                bean.setSITE(rs.getString(13));
                bean.setCUR(rs.getString(14));
                bean.setSITE_NAME(rs.getString(15));
                bean.setBILL_ID(rs.getString(16));
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
    public ArrayList<WorkBean> getRelatedBillbyUid(String uid) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList< WorkBean> o = new ArrayList< WorkBean>();
        try {
            sql = "SELECT   cba_bill_mst.bill_no, cba_bill_mst.po_no,\n"
                    + "         TO_CHAR (cba_bill_mst.bill_date, 'DD-MON-YYYY'),\n"
                    + "         TO_CHAR (cba_bill_mst.cr_date, 'DD-MON-YYYY'), cba_bill_mst.ou,\n"
                    + "         cba_user_mst.full_name, cba_bill_mst.status, cba_bill_mst.val,\n"
                    + "         cba_bill_mst.tax_val, cba_bill_mst.total_val, cba_bill_mst.bill_desc,\n"
                    + "         po_vendors.vendor_name, cba_wo_mst.site, cba_wo_mst.cur,\n"
                    + "         po_vendor_sites_all.vendor_site_code, cba_bill_mst.bill_usr_no,\n"
                    + "         (SELECT b.full_name\n"
                    + "            FROM cba_tran_bill a, cba_user_mst b\n"
                    + "           WHERE a.nxt_uid = b.user_id\n"
                    + "             AND a.bill_no = cba_bill_mst.bill_no\n"
                    + "             AND a.is_comp IS NULL)\n"
                    + "    FROM cba_bill_mst,\n"
                    + "         cba_user_mst,\n"
                    + "         cba_wo_mst,\n"
                    + "         po_vendors,\n"
                    + "         cba_tran_bill,\n"
                    + "         po_vendor_sites_all\n"
                    + "   WHERE (    (cba_bill_mst.user_id = cba_user_mst.user_id)\n"
                    + "          AND (cba_bill_mst.po_no = cba_wo_mst.po_no)\n"
                    + "          AND (cba_wo_mst.sup_id = po_vendors.vendor_number)\n"
                    + "          AND (po_vendor_sites_all.vendor_site_id = cba_wo_mst.site)\n"
                    + "          AND (cba_bill_mst.bill_no = cba_tran_bill.bill_no)\n"
                    + "          AND (cba_tran_bill.user_id = '" + uid + "')\n"
                    + "         )\n"
                    + "ORDER BY cba_tran_bill.act_date DESC";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean bean = new WorkBean();
                bean.setBILL_NO(rs.getString(1));
                bean.setPO_NO(rs.getString(2));
                bean.setBILL_DATE(rs.getString(3));
                bean.setCR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setSTATUS(rs.getString(7));
                bean.setVAL(rs.getFloat(8));
                bean.setTOTAL_TAX(rs.getFloat(9));
                bean.setTOTAL_VAL(rs.getFloat(10));
                bean.setWO_DESC(rs.getString(11));
                bean.setSUP_NAME(rs.getString(12));
                bean.setSITE(rs.getString(13));
                bean.setCUR(rs.getString(14));
                bean.setSITE_NAME(rs.getString(15));
                bean.setBILL_ID(rs.getString(16));
                bean.setP_USR_NAME(rs.getString(17));
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
    public ArrayList< WorkBean> getBillbySid(String sup_id, Boolean isApproved) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList< WorkBean> o = new ArrayList< WorkBean>();
        try {
            if (isApproved) {
                sql = "SELECT cba_bill_mst.bill_no, cba_bill_mst.po_no, to_char(cba_bill_mst.bill_date,'DD-MON-YYYY'),"
                        + "       to_char(cba_bill_mst.cr_date,'DD-MON-YYYY'), cba_bill_mst.ou, cba_user_mst.full_name,"
                        + "       cba_bill_mst.status, cba_bill_mst.val, cba_bill_mst.tax_val,"
                        + "       cba_bill_mst.total_val, cba_bill_mst.bill_desc, po_vendors.vendor_name,"
                        + "       cba_wo_mst.site, cba_wo_mst.cur,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE,cba_bill_mst.bill_usr_no"
                        + "  FROM cba_bill_mst, cba_user_mst, cba_wo_mst, po_vendors,PO_VENDOR_SITES_ALL"
                        + " WHERE (    (cba_bill_mst.user_id = cba_user_mst.user_id)"
                        + "        AND (cba_bill_mst.po_no = cba_wo_mst.po_no)"
                        + "        AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "        AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE )"
                        + "        AND (cba_wo_mst.sup_id = '" + sup_id + "')"
                        + "        AND (cba_bill_mst.status = 'Approved')" + "       ) ORDER BY cba_bill_mst.bill_no ";

            } else {
                sql = "SELECT cba_bill_mst.bill_no, cba_bill_mst.po_no, to_char(cba_bill_mst.bill_date,'DD-MON-YYYY'),"
                        + "       to_char(cba_bill_mst.cr_date,'DD-MON-YYYY'), cba_bill_mst.ou, cba_user_mst.full_name,"
                        + "       cba_bill_mst.status, cba_bill_mst.val, cba_bill_mst.tax_val,"
                        + "       cba_bill_mst.total_val, cba_bill_mst.bill_desc, po_vendors.vendor_name,"
                        + "       cba_wo_mst.site, cba_wo_mst.cur,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE,cba_bill_mst.bill_usr_no"
                        + "  FROM cba_bill_mst, cba_user_mst, cba_wo_mst,  po_vendors,PO_VENDOR_SITES_ALL"
                        + " WHERE (    (cba_bill_mst.user_id = cba_user_mst.user_id)"
                        + "        AND (cba_bill_mst.po_no = cba_wo_mst.po_no)"
                        + "        AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE )"
                        + "        AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "        AND (cba_wo_mst.sup_id = '" + sup_id + "')" + "       ) ORDER BY cba_bill_mst.bill_no ";

            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean bean = new WorkBean();
                bean.setBILL_NO(rs.getString(1));
                bean.setPO_NO(rs.getString(2));
                bean.setBILL_DATE(rs.getString(3));
                bean.setCR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setSTATUS(rs.getString(7));
                bean.setVAL(rs.getFloat(8));
                bean.setTOTAL_TAX(rs.getFloat(9));
                bean.setTOTAL_VAL(rs.getFloat(10));
                bean.setWO_DESC(rs.getString(11));
                bean.setSUP_NAME(rs.getString(12));
                bean.setSITE(rs.getString(13));
                bean.setCUR(rs.getString(14));
                bean.setSITE_NAME(rs.getString(15));
                bean.setBILL_ID(rs.getString(16));
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
    public ArrayList<WorkBean> getBill(Boolean isApproved) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<WorkBean> o = new ArrayList< WorkBean>();
        try {
            if (isApproved) {
                sql = "SELECT   mstb.bill_no, mstb.bill_usr_no, mstw.po_no,"
                        + "         TO_CHAR (mstb.bill_date, 'DD-MON-YYYY') bill_date,"
                        + "         TO_CHAR (mstb.cr_date, 'DD-MON-YYYY') cr_date, mstu2.full_name,"
                        + "         mstw.cur, ven.vendor_name, mstw.site, vensit.vendor_site_code,"
                        + "         mstb.ou, mstb.val, mstb.tax_val, mstb.total_val, mstb.bill_desc,"
                        + "         mstb.status, TO_CHAR (trb.act_date, 'DD-MON-YYYY') last_up_date,"
                        + "         mstu.full_name, msts.stg_name,mstw.type"
                        + "    FROM cba_tran_bill trb,"
                        + "         cba_wo_mst mstw,"
                        + "         cba_bill_mst mstb,"
                        + "         cba_stg_mst msts,"
                        + "         cba_user_mst mstu,"
                        + "         cba_user_mst mstu2,"
                        + "         po_vendors ven,"
                        + "         po_vendor_sites_all vensit"
                        + "   WHERE tran_id IN (SELECT   MAX (tran_id)"
                        + "                         FROM cba_tran_bill"
                        + "                     GROUP BY bill_no)"
                        + "     AND mstb.po_no = mstw.po_no"
                        + "     AND mstw.sup_id = ven.vendor_number"
                        + "     AND trb.bill_no = mstb.bill_no"
                        + "     AND trb.nxt_uid = mstu.user_id"
                        + "     AND trb.stg_n = msts.stg_id"
                        + "     AND mstu2.user_id = mstb.user_id"
                        + "     AND mstw.site = vensit.vendor_site_id"
                        + "     AND mstb.STATUS = 'Approved'"
                        + " ORDER BY TO_NUMBER (TRIM (trb.bill_no)) ";

            } else {
                sql = "SELECT   mstb.bill_no, mstb.bill_usr_no, mstw.po_no,"
                        + "         TO_CHAR (mstb.bill_date, 'DD-MON-YYYY') bill_date,"
                        + "         TO_CHAR (mstb.cr_date, 'DD-MON-YYYY') cr_date, mstu2.full_name,"
                        + "         mstw.cur, ven.vendor_name, mstw.site, vensit.vendor_site_code,"
                        + "         mstb.ou, mstb.val, mstb.tax_val, mstb.total_val, mstb.bill_desc,"
                        + "         mstb.status, TO_CHAR (trb.act_date, 'DD-MON-YYYY') last_up_date,"
                        + "         mstu.full_name, msts.stg_name,mstw.type"
                        + "    FROM cba_tran_bill trb,"
                        + "         cba_wo_mst mstw,"
                        + "         cba_bill_mst mstb,"
                        + "         cba_stg_mst msts,"
                        + "         cba_user_mst mstu,"
                        + "         cba_user_mst mstu2,"
                        + "         po_vendors ven,"
                        + "         po_vendor_sites_all vensit"
                        + "   WHERE tran_id IN (SELECT   MAX (tran_id)"
                        + "                         FROM cba_tran_bill"
                        + "                     GROUP BY bill_no)"
                        + "     AND mstb.po_no = mstw.po_no"
                        + "     AND mstw.sup_id = ven.vendor_number"
                        + "     AND trb.bill_no = mstb.bill_no"
                        + "     AND trb.nxt_uid = mstu.user_id"
                        + "     AND trb.stg_n = msts.stg_id"
                        + "     AND mstu2.user_id = mstb.user_id"
                        + "     AND mstw.site = vensit.vendor_site_id"
                        + " ORDER BY TO_NUMBER (TRIM (trb.bill_no))";

            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean bean = new WorkBean();
                bean.setBILL_NO(rs.getString(1));
                bean.setBILL_ID(rs.getString(2));
                bean.setPO_NO(rs.getString(3));
                bean.setBILL_DATE(rs.getString(4));
                bean.setCR_DATE(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setCUR(rs.getString(7));
                bean.setSUP_NAME(rs.getString(8));
                bean.setSITE(rs.getString(9));
                bean.setSITE_NAME(rs.getString(10));
                bean.setOU(rs.getString(11));
                bean.setVAL(rs.getFloat(12));
                bean.setTOTAL_TAX(rs.getFloat(13));
                bean.setTOTAL_VAL(rs.getFloat(14));
                bean.setWO_DESC(rs.getString(15));
                bean.setSTATUS(rs.getString(16));
                bean.setLAST_UP_DATE(rs.getString(17));
                bean.setP_USR_NAME(rs.getString(18));
                bean.setP_STG_NAME(rs.getString(19));
                bean.setTYPE(rs.getString(20));
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
    public ArrayList<WorkBean> getBillbyOU(String ou, Boolean isApproved) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList< WorkBean> o = new ArrayList< WorkBean>();
        try {
            if (isApproved) {
                sql = "SELECT cba_bill_mst.bill_no, cba_bill_mst.po_no, to_char(cba_bill_mst.bill_date,'DD-MON-YYYY'),"
                        + "       to_char(cba_bill_mst.cr_date,'DD-MON-YYYY'), cba_bill_mst.ou, cba_user_mst.full_name,"
                        + "       cba_bill_mst.status, cba_bill_mst.val, cba_bill_mst.tax_val,"
                        + "       cba_bill_mst.total_val, cba_bill_mst.bill_desc, po_vendors.vendor_name,"
                        + "       cba_wo_mst.site, cba_wo_mst.cur,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE,cba_bill_mst.bill_usr_no"
                        + "  FROM cba_bill_mst, cba_user_mst, cba_wo_mst, po_vendors,PO_VENDOR_SITES_ALL"
                        + " WHERE (    (cba_bill_mst.user_id = cba_user_mst.user_id)"
                        + "        AND (cba_bill_mst.po_no = cba_wo_mst.po_no)"
                        + "        AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "        AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE )"
                        + "        AND (cba_bill_mst.ou = '" + ou + "')"
                        + "        AND (cba_bill_mst.status = 'Approved')" + "       ) ORDER BY cba_bill_mst.bill_no ";

            } else {
                sql = "SELECT cba_bill_mst.bill_no, cba_bill_mst.po_no, to_char(cba_bill_mst.bill_date,'DD-MON-YYYY'),"
                        + "       to_char(cba_bill_mst.cr_date,'DD-MON-YYYY'), cba_bill_mst.ou, cba_user_mst.full_name,"
                        + "       cba_bill_mst.status, cba_bill_mst.val, cba_bill_mst.tax_val,"
                        + "       cba_bill_mst.total_val, cba_bill_mst.bill_desc, po_vendors.vendor_name,"
                        + "       cba_wo_mst.site, cba_wo_mst.cur,PO_VENDOR_SITES_ALL.VENDOR_SITE_CODE,cba_bill_mst.bill_usr_no"
                        + "  FROM cba_bill_mst, cba_user_mst, cba_wo_mst,  po_vendors,PO_VENDOR_SITES_ALL"
                        + " WHERE (    (cba_bill_mst.user_id = cba_user_mst.user_id)"
                        + "        AND (cba_bill_mst.po_no = cba_wo_mst.po_no)"
                        + "        AND (PO_VENDOR_SITES_ALL.VENDOR_SITE_ID  = CBA_WO_MST.SITE )"
                        + "        AND (cba_wo_mst.sup_id = po_vendors.vendor_number)"
                        + "        AND (cba_bill_mst.ou = '" + ou + "')" + "       ) ORDER BY cba_bill_mst.bill_no ";

            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkBean bean = new WorkBean();
                bean.setBILL_NO(rs.getString(1));
                bean.setPO_NO(rs.getString(2));
                bean.setBILL_DATE(rs.getString(3));
                bean.setCR_DATE(rs.getString(4));
                bean.setOU(rs.getString(5));
                bean.setUSER_NAME(rs.getString(6));
                bean.setSTATUS(rs.getString(7));
                bean.setVAL(rs.getFloat(8));
                bean.setTOTAL_TAX(rs.getFloat(9));
                bean.setTOTAL_VAL(rs.getFloat(10));
                bean.setWO_DESC(rs.getString(11));
                bean.setSUP_NAME(rs.getString(12));
                bean.setSITE(rs.getString(13));
                bean.setCUR(rs.getString(14));
                bean.setSITE_NAME(rs.getString(15));
                bean.setBILL_ID(rs.getString(16));
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

}
