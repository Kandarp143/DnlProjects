/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.InvBean;
import Bean.InvItemBean;
import Bean.WorkBean;
import Bean.WorkItemBean;
import Bean.WorkTaxBean;
import Bean.WorkTermBean;
import static Dao.WorkDao.logger;
import Logic.GetMethod;
import Logic.IpAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author 02948
 */
public class ProjINVDao implements Interface.ProjINVInter {

    //INVOICE LOGIC
    //if supplier 9787 1272 = civil work
    // CIVIL WORK  CC = 85 CODE = 42702
    // ELSE CC = user selected CODE = 42723
    // IF CIVIL TOTAL TAX AS ITEM
    // ELSE TAX LINE - IF SERVICE 25553 ELSE 16979
    public static void main(String[] args) {
        ProjINVDao d = new ProjINVDao();
    }

    @Override
    public int getACCpayID(String sup, String site) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        int x = 0;
        try {
            con = Logic.DBmanager.getInvcon();
            String sql = "select distinct a.ACCTS_PAY_CODE_COMBINATION_ID "
                    + " comb_id from APPS.PO_VENDOR_SITES_ALL a,APPS.PO_VENDORS b "
                    + " where (a.VENDOR_ID = b.VENDOR_ID) AND b.segment1 = '" + sup + "' AND a.VENDOR_SITE_ID = '" + site + "'";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                x = Integer.parseInt(rs.getString(1));
            }
            logger.log(Level.SEVERE, "VENDOR ACC_PAY_ID :" + x);

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : " + ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public int getGLcodeID(String seg1, String seg2, String seg3, String seg4, String seg5, String seg6) {
        //SEG1 = OU
        //SEG2 = COST CENTER
        //SEG3 = PLANT CENTRE 
        //SEG4 = NATURAL ACCOUNT
        //SEG5 =  DUMMY
        //SEG6 =  DUMMY
        PreparedStatement ps = null;
        ResultSet rs = null;
        int x = 0;
        Connection con = null;
        try {
            con = Logic.DBmanager.getInvcon();
            String sql = "select distinct CODE_COMBINATION_ID from APPS.gl_code_combinations "
                    + " where SEGMENT1=LPAD(?,2,0)   AND SEGMENT2=LPAD(?,3,0) AND SEGMENT3=LPAD(?,3,0) "
                    + " AND SEGMENT4=LPAD(?,5,0) AND SEGMENT5=LPAD(?,4,0) AND SEGMENT6=LPAD(?,3,0)";

            ps = con.prepareStatement(sql);
            ps.setString(1, seg1);
            ps.setString(2, seg2);
            ps.setString(3, seg3);
            ps.setString(4, seg4);
            ps.setString(5, seg5);
            ps.setString(6, seg6);
            rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt(1);
            }

            String gl_comb = seg1 + "-" + StringUtils.leftPad(seg2, 3, "0") + "-" + StringUtils.leftPad(seg3, 3, "0") + "-" + seg4 + "-" + seg5 + "-" + seg6;

            if (x == 0) {
                logger.log(Level.INFO, "CALLING FUNCATION");
                String call = "{ ? = call apps.xx_gl_comb_creation(?) }";
                CallableStatement cstmt = con.prepareCall(call);
                cstmt.setQueryTimeout(1800);
                cstmt.registerOutParameter(1, Types.NUMERIC);
                cstmt.setString(2, gl_comb);
                cstmt.executeUpdate();
                x = cstmt.getInt(1);
                cstmt.close();
            }
            logger.log(Level.INFO, "GL_COMBINATION :" + gl_comb);
            logger.log(Level.INFO, "GL_CODE_COMB_ID :" + x);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : " + ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ProjINVDao.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public InvBean getProjInv(WorkBean billbean, ArrayList<WorkItemBean> billitem, ArrayList<WorkTaxBean> billtax, WorkTermBean billterm) {
        InvBean invbean = new InvBean();
        ProjINVDao pinvDao = new ProjINVDao();
        GetMethod gm = new GetMethod();
        //Set inv
        invbean.setINVOICE_NUM("CBA" + billbean.getBILL_NO());
        invbean.setINVOICE_TYPE_LOOKUP_CODE("STANDARD");
        invbean.setINVOICE_DATE(billbean.getBILL_DATE());
        invbean.setVENDOR_ID(Integer.parseInt(gm.Get_Perameter("VENDOR_ID", "PO_VENDORS", "VENDOR_NUMBER", billbean.getSUP_ID())));
        invbean.setVENDOR_NAME(billbean.getSUP_NAME());
        invbean.setVENDOR_NUM(billbean.getSUP_ID());
        invbean.setVENDOR_SITE_CODE(gm.Get_Perameter("VENDOR_SITE_CODE", "PO_VENDOR_SITES_ALL", "VENDOR_SITE_ID", billbean.getSITE()));
        invbean.setVENDOR_SITE_ID(Integer.parseInt(billbean.getSITE()));
        invbean.setINVOICE_AMOUNT(billbean.getTOTAL_VAL());
        invbean.setINVOICE_CURRENCY_CODE(billbean.getCUR());
        invbean.setTERMS_ID(Integer.parseInt(billterm.getTERM_ID()));
        invbean.setACCpayID(pinvDao.getACCpayID(billbean.getSUP_ID(), billbean.getSITE()));
        String desc = "";
        desc = "CBA BILL_ID :" + billbean.getBILL_NO();
        invbean.setDESCRIPTION(desc);
        invbean.setGROUP_ID("CBA" + billbean.getBILL_NO());
        invbean.setSOURCE("LEGACY");
        return invbean;

    }

    @Override
    public ArrayList<InvItemBean> getProjInvItem(WorkBean billbean, ArrayList<WorkItemBean> billitem, ArrayList<WorkTaxBean> billtax, WorkTermBean billterm) {
        //set itm summary for invoice
        ArrayList<InvItemBean> summarize_itm = new ArrayList<InvItemBean>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String dblink = "";
        String schema = "";
        int i = 1;
        try {
            IpAddress ip = new IpAddress();
            if (ip.isProdEnv()) {
                dblink = "@dnlclone_to_payclone_moc";
                schema = "dnlmoc";
            } else {
//                dblink = "@dnlcba.dnlpune.com";
//                schema = "dnlcba";
                dblink = "@dnlclone_to_payclone_moc";
                schema = "dnlmoc";
            }
            String sql = "SELECT   cba_bill_item.bill_no,cba_bill_mst.ou, SUM (cba_bill_item.qty) qty,\n"
                    + "         ROUND (SUM (cba_bill_item.val)) val,\n"
                    + "         ROUND (SUM (cba_bill_item.val) / SUM (cba_bill_item.qty)) rate,\n"
                    + "            LPAD (21, 2, 0)\n"
                    + "         || '-'\n"
                    + "         || LPAD (DECODE (cba_wo_mst.sup_id,\n"
                    + "                          '1272', 85,\n"
                    + "                          '9787', 85,\n"
                    + "                          cba_bill_item.cc\n"
                    + "                         ),\n"
                    + "                  3,\n"
                    + "                  0\n"
                    + "                 )\n"
                    + "         || '-'\n"
                    + "         || LPAD (cba_bill_item.plant, 3, 0)\n"
                    + "         || '-'\n"
                    + "         || LPAD (DECODE (cba_wo_mst.sup_id,\n"
                    + "                          '1272', 42702,\n"
                    + "                          '9787', 42702,\n"
                    + "                          42723\n"
                    + "                         ),\n"
                    + "                  5,\n"
                    + "                  0\n"
                    + "                 )\n"
                    + "         || '-'\n"
                    + "         || LPAD (9999, 4, 0)\n"
                    + "         || '-'\n"
                    + "         || LPAD (999, 3, 0) gl_code,\n"
                    + "         dnlcust.get_glid (   LPAD (21, 2, 0)\n"
                    + "                           || '-'\n"
                    + "                           || LPAD (DECODE (cba_wo_mst.sup_id,\n"
                    + "                                            '1272', 85,\n"
                    + "                                            '9787', 85,\n"
                    + "                                            cba_bill_item.cc\n"
                    + "                                           ),\n"
                    + "                                    3,\n"
                    + "                                    0\n"
                    + "                                   )\n"
                    + "                           || '-'\n"
                    + "                           || LPAD (cba_bill_item.plant, 3, 0)\n"
                    + "                           || '-'\n"
                    + "                           || LPAD (DECODE (cba_wo_mst.sup_id,\n"
                    + "                                            '1272', 42702,\n"
                    + "                                            '9787', 42702,\n"
                    + "                                            42723\n"
                    + "                                           ),\n"
                    + "                                    5,\n"
                    + "                                    0\n"
                    + "                                   )\n"
                    + "                           || '-'\n"
                    + "                           || LPAD (9999, 4, 0)\n"
                    + "                           || '-'\n"
                    + "                           || LPAD (999, 3, 0)\n"
                    + "                          ) comb_id,\n"
                    + "         cba_bill_item.proj, cba_bill_item.task\n"
                    + "    FROM " + schema + ".cba_bill_item" + dblink + ",\n"
                    + "         " + schema + ".cba_wo_mst" + dblink + ",\n"
                    + "         " + schema + ".cba_bill_mst" + dblink + ",\n"
                    + "         apps.mtl_system_items\n"
                    + "   WHERE (    (cba_bill_item.item_id = mtl_system_items.segment1)\n"
                    + "          AND (mtl_system_items.organization_id = 0)\n"
                    + "          AND (cba_bill_item.bill_no = '" + billbean.getBILL_NO() + "')\n"
                    + "          AND cba_bill_mst.bill_no = cba_bill_item.bill_no\n"
                    + "          AND cba_bill_mst.po_no = cba_wo_mst.po_no\n"
                    + "         )\n"
                    + " GROUP BY cba_bill_item.bill_no,\n"
                    + "         cba_bill_item.cc,\n"
                    + "         cba_bill_item.plant,\n"
                    + "         cba_wo_mst.sup_id,\n"
                    + "         cba_bill_item.proj,\n"
                    + "         cba_bill_item.task,\n"
                    + "         cba_bill_mst.ou\n"
                    + "ORDER BY cba_bill_item.bill_no";
            con = Logic.DBmanager.getInvcon();
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "SQL :" + sql);
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                InvItemBean bean = new InvItemBean();
                bean.setLINE_NUMBER(i++);
                bean.setLINE_TYPE_LOOKUP_CODE("ITEM");
                bean.setORG_ID(Integer.parseInt(rs.getString(2)));
                bean.setGL_CODE(rs.getString(6));
                bean.setPROJ(rs.getString(8));
                bean.setTASK(rs.getString(9));
                bean.setAMOUNT(rs.getFloat(4));
                bean.setDESCRIPTION("CBA BILL ID :" + rs.getString(1));
                summarize_itm.add(bean);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.NullPointerException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        }
        //TAX LINE
        InvItemBean bean = new InvItemBean();
        bean.setLINE_NUMBER(i++);
        bean.setLINE_TYPE_LOOKUP_CODE("ITEM");
        bean.setORG_ID(Integer.parseInt(billbean.getOU()));
        bean.setGL_CODE("if ->recoverable then service tax GL \n"
                + "if-> non recoverable then item charge account");
        bean.setPROJ("-");
        bean.setTASK("-");
        bean.setAMOUNT(billbean.getTOTAL_TAX());
        bean.setDESCRIPTION("CBA TAX LINE ");
        summarize_itm.add(bean);
        return summarize_itm;
    }

}
