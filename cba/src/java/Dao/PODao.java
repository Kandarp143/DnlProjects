/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.InvBean;
import Bean.WorkBean;
import static Dao.BillDao.logger;
import Logic.DBmanager;
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

/**
 *
 * @author 02948
 */
public class PODao {

    public static void main(String[] args) {
        PODao pd = new PODao();
        String bill = "972";
//        pd.checkPO(bill);
//        pd.addPoTax(bill);
//        pd.createPO(bill);
//        pd.checkTax(bill);
//        pd.createINV(bill);
//        pd.checkINV(bill);
        pd.checkINVInterface(bill);
    }

    public int createPO(String billno) {
        Connection con = null;
        CallableStatement callstmt = null;
        int req_id = 0;
        if (!checkPOInterface(billno)) {
            try {
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "Calling APPS.XX_CBA_PO_CREATION FOR BILL :" + billno);
                con = Logic.DBmanager.getInvcon();
                String sql = "{call APPS.XX_CBA_PO_CREATION(?,?,?,?,?,?,?)}";
                callstmt = con.prepareCall(sql);
                callstmt.setString(1, billno);
                callstmt.setString(2, "7737");
                callstmt.registerOutParameter(3, Types.NUMERIC);
                callstmt.registerOutParameter(4, Types.VARCHAR);
                callstmt.registerOutParameter(5, Types.VARCHAR);
                callstmt.registerOutParameter(6, Types.VARCHAR);
                callstmt.registerOutParameter(7, Types.VARCHAR);
                callstmt.executeUpdate();
                req_id = callstmt.getInt(3);
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "--FROM XX_CBA_PO_CREATION--");
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "REQ_ID :" + callstmt.getInt(3));
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "MSG :" + callstmt.getString(4));
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "PO_HEADER :" + callstmt.getString(5));
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "PO_LINE :" + callstmt.getString(6));
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "PO_IMPORT :" + callstmt.getString(7));
            } catch (SQLException ex) {
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return req_id;
    }

    public boolean checkPOInterface(String billno) {
        Boolean ans = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        try {
            //check in interface table
            sql = "SELECT *\n"
                    + "  FROM apps.po_headers_interface\n"
                    + " WHERE attribute15 = ? AND UPPER (process_code) <> 'REJECTED'";
            con = Logic.DBmanager.getInvcon();
            ps = con.prepareStatement(sql);
            ps.setString(1, billno);
            rs = ps.executeQuery();
            while (rs.next()) {
                ans = true;
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "Interface record found");
            }
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "{0}", ans);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.NullPointerException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }

    public boolean checkPOProd(String billno) {
        Boolean ans = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        try {

            sql = "SELECT pha.po_header_id, pha.segment1, pla.po_line_id, plla.line_location_id,\n"
                    + "       pha.vendor_id, pla.unit_price * pla.quantity amt\n"
                    + "  FROM apps.po_headers_all pha,\n"
                    + "       apps.po_lines_all pla,\n"
                    + "       apps.po_line_locations_all plla,\n"
                    + "       apps.po_distributions_all pda\n"
                    + " WHERE pha.attribute15 = ?"
                    + "   AND pla.po_header_id = pha.po_header_id\n"
                    + "   AND plla.po_line_id = pla.po_line_id\n"
                    + "   AND pha.po_header_id = pda.po_header_id\n"
                    + "   AND pla.po_line_id = pda.po_line_id\n"
                    + "   AND plla.po_header_id = pla.po_header_id\n"
                    + "   AND pha.document_creation_method = 'PDOI'";
            con = Logic.DBmanager.getInvcon();
            ps = con.prepareStatement(sql);
            ps.setString(1, billno);
            rs = ps.executeQuery();
            while (rs.next()) {
                ans = true;
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "production record found");
            }
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "{0}", ans);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.NullPointerException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }

    public boolean checkCBAtax(String billno) {
        Boolean ans = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from cba_bill_tax where bill_no = ?";
            con = Logic.DBmanager.GetConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, billno);
            rs = ps.executeQuery();
            while (rs.next()) {
                ans = true;
            }
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "{0}", ans);
        } catch (SQLException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }

    public String addPoTax(String billno) {
        Connection con = null;
        CallableStatement callstmt = null;
        String po_num = "";
        PODao d = new PODao();
        try {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "ADD PO TAX CALLED");
            if (!d.checkTax(billno)) {
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "Calling APPS.XX_CBA_PO_TAX_ADD FOR BILL :" + billno);
                con = Logic.DBmanager.getInvcon();
                String sql = "{call APPS.XX_CBA_PO_TAX_ADD_NEW(?,?,?,?,?)}";
                callstmt = con.prepareCall(sql);
                callstmt.setString(1, billno);
                callstmt.setString(2, "7737");
                callstmt.registerOutParameter(3, Types.VARCHAR);
                callstmt.registerOutParameter(4, Types.VARCHAR);
                callstmt.registerOutParameter(5, Types.VARCHAR);
                callstmt.executeUpdate();
                po_num = callstmt.getString(3);
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "--FROM XX_CBA_TAX_ADD--");
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "PO NUM :" + callstmt.getString(3));
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "MSG :" + callstmt.getString(4));
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "PO_TAX :" + callstmt.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return po_num;
    }

    public boolean checkTax(String billno) {
        PODao pd = new PODao();
        Boolean ans = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT pha.segment1, potax.tax_id, potax.tax_amount tax_amt_tax,\n"
                    + "       polocs.tax_amount tax_amt_loc, polocs.total_amount\n"
                    + "  FROM apps.ja_in_po_line_location_taxes potax,\n"
                    + "       apps.ja_in_po_line_locations polocs,\n"
                    + "       apps.po_headers_all pha\n"
                    + " WHERE potax.line_location_id = polocs.line_location_id\n"
                    + "   AND pha.po_header_id = potax.po_header_id\n"
                    + "   AND pha.attribute15 = ?";
            con = Logic.DBmanager.getInvcon();
            ps = con.prepareStatement(sql);
            ps.setString(1, billno);
            rs = ps.executeQuery();
            while (rs.next()) {
                ans = true;
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "TOTAL_TAX : {0}", rs.getString("tax_amt_tax"));
            }
            if (!ans) {
                if (!checkCBAtax(billno)) {
                    ans = true;
                }

            }
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "{0}", ans);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;

    }

    public int createINV(String billno) {
        Connection con = null;
        CallableStatement callstmt = null;
        int req_id = 0;
        PODao d = new PODao();
        try {
            if (d.checkTax(billno) && !d.checkINVInterface(billno)) {
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "Calling APPS.XX_CBA_INV_CREATION FOR BILL :" + billno);
                con = Logic.DBmanager.getInvcon();
                String sql = "{call APPS.XX_CBA_INV_CREATION(?,?,?,?,?,?)}";
                callstmt = con.prepareCall(sql);
                callstmt.setString(1, billno);
                callstmt.setString(2, "9043");
                callstmt.registerOutParameter(3, Types.NUMERIC);
                callstmt.registerOutParameter(4, Types.VARCHAR);
                callstmt.registerOutParameter(5, Types.VARCHAR);
                callstmt.registerOutParameter(6, Types.VARCHAR);
                callstmt.executeUpdate();
                req_id = callstmt.getInt(3);
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "--FROM XX_CBA_INV_CREATION--");
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "REQ_D :" + callstmt.getString(3));
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "MSG :" + callstmt.getString(4));
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "INV :" + callstmt.getString(5));
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "INV_IMPORT :" + callstmt.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return req_id;

    }

    public boolean checkINVProd(String billno) {
        Boolean ans = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT DISTINCT inv.invoice_id, inv.invoice_num, ven.vendor_name,\n"
                    + "                site.vendor_site_code, line.po_distribution_id,\n"
                    + "                TRIM (REGEXP_SUBSTR (bat.batch_name, '[^M|T|W|F|S]+', 1, 1)\n"
                    + "                     ) cba_bill_id,\n"
                    + "                inv.invoice_amount, inv.amount_paid,\n"
                    + "                TO_CHAR (inv.invoice_date, 'DD-MON-YYYY') inv_date,\n"
                    + "                TO_CHAR (inv.creation_date, 'DD-MON-YYYY') creation_date\n"
                    + "           FROM apps.ap_invoices_all inv,\n"
                    + "                apps.ap_batches_all bat,\n"
                    + "                apps.po_vendors ven,\n"
                    + "                apps.po_vendor_sites_all site,\n"
                    + "                apps.ap_invoice_distributions_all line\n"
                    + "          WHERE inv.batch_id = bat.batch_id\n"
                    + "            AND ven.vendor_id = inv.vendor_id\n"
                    + "            AND ven.vendor_id = site.vendor_id\n"
                    + "            AND inv.vendor_site_id = site.vendor_site_id\n"
                    + "            AND bat.batch_name LIKE ?"
                    + "             AND inv.invoice_amount <> 0"
                    + "            AND (inv.created_by = 9043 OR inv.created_by = 3096)\n"
                    + "            AND inv.invoice_id = line.invoice_id\n"
                    + "       ORDER BY 3, 4";
            con = Logic.DBmanager.getInvcon();
            ps = con.prepareStatement(sql);
            ps.setString(1, "CBA" + billno + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ans = true;
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "INVOICE_ID : {0}", rs.getString("invoice_id"));
            }
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "{0}", ans);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }

    public boolean checkINVInterface(String billno) {
        Boolean ans = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String inv = "CBA" + billno;
        try {
            String sql = "select * from ap_invoices_interface a where a.INVOICE_NUM ='" + inv + "'";
            con = Logic.DBmanager.getInvcon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ans = true;
                Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "Interface Record found");
            }
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "{0}", ans);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }

    public ArrayList<InvBean> getInvList(String uid) {
        ArrayList<InvBean> ans = new ArrayList<InvBean>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        try {
            if (uid.startsWith("C")) {
                uid = uid.substring(1);
                sql = "SELECT DISTINCT inv.invoice_id, inv.invoice_num,\n"
                        + "                SUBSTR (TRIM (REGEXP_SUBSTR (bat.batch_name,\n"
                        + "                                             '[^M|T|W|F|S]+',\n"
                        + "                                             1,\n"
                        + "                                             1\n"
                        + "                                            )\n"
                        + "                             ),\n"
                        + "                        4\n"
                        + "                       ) cba_bill_id,\n"
                        + "                DECODE (pha.segment1, NULL, '-', pha.segment1) po_num,\n"
                        + "                ven.vendor_name, site.vendor_site_code, inv.invoice_amount,\n"
                        + "                inv.amount_paid,\n"
                        + "                TO_CHAR (inv.invoice_date, 'DD-MON-YYYY') inv_date,\n"
                        + "                TO_CHAR (inv.creation_date, 'DD-MON-YYYY') creation_date\n"
                        + "           FROM apps.ap_invoices_all inv,\n"
                        + "                apps.ap_batches_all bat,\n"
                        + "                apps.po_vendors ven,\n"
                        + "                apps.po_vendor_sites_all site,\n"
                        + "                apps.ap_invoice_distributions_all line,\n"
                        + "                apps.po_headers_all pha,\n"
                        + "                apps.po_distributions_all pda\n"
                        + "          WHERE inv.batch_id = bat.batch_id\n"
                        + "            AND ven.vendor_id = inv.vendor_id\n"
                        + "            AND ven.vendor_id = site.vendor_id\n"
                        + "            AND inv.vendor_site_id = site.vendor_site_id\n"
                        + "            AND bat.batch_name LIKE 'CBA%'\n"
                        + "            AND (inv.created_by = 9043 OR inv.created_by = 3096)\n"
                        + "            AND inv.invoice_id = line.invoice_id\n"
                        + "            AND pda.po_header_id = pha.po_header_id(+)\n"
                        + "            AND line.po_distribution_id = pda.po_distribution_id(+)\n"
                        + "            AND ven.SEGMENT1 = '" + uid + "'\n"
                        + "       ORDER BY 5,10";
            } else {
                sql = "SELECT DISTINCT inv.invoice_id, inv.invoice_num,\n"
                        + "                SUBSTR (TRIM (REGEXP_SUBSTR (bat.batch_name,\n"
                        + "                                             '[^M|T|W|F|S]+',\n"
                        + "                                             1,\n"
                        + "                                             1\n"
                        + "                                            )\n"
                        + "                             ),\n"
                        + "                        4\n"
                        + "                       ) cba_bill_id,\n"
                        + "                DECODE (pha.segment1, NULL, '-', pha.segment1) po_num,\n"
                        + "                ven.vendor_name, site.vendor_site_code, inv.invoice_amount,\n"
                        + "                inv.amount_paid,\n"
                        + "                TO_CHAR (inv.invoice_date, 'DD-MON-YYYY') inv_date,\n"
                        + "                TO_CHAR (inv.creation_date, 'DD-MON-YYYY') creation_date\n"
                        + "           FROM apps.ap_invoices_all inv,\n"
                        + "                apps.ap_batches_all bat,\n"
                        + "                apps.po_vendors ven,\n"
                        + "                apps.po_vendor_sites_all site,\n"
                        + "                apps.ap_invoice_distributions_all line,\n"
                        + "                apps.po_headers_all pha,\n"
                        + "                apps.po_distributions_all pda\n"
                        + "          WHERE inv.batch_id = bat.batch_id\n"
                        + "            AND ven.vendor_id = inv.vendor_id\n"
                        + "            AND ven.vendor_id = site.vendor_id\n"
                        + "            AND inv.vendor_site_id = site.vendor_site_id\n"
                        + "            AND bat.batch_name LIKE 'CBA%'\n"
                        + "            AND (inv.created_by = 9043 OR inv.created_by = 3096)\n"
                        + "            AND inv.invoice_id = line.invoice_id\n"
                        + "            AND pda.po_header_id = pha.po_header_id(+)\n"
                        + "            AND line.po_distribution_id = pda.po_distribution_id(+)\n"
                        + "       ORDER BY 5,10";
            }
            con = Logic.DBmanager.getInvcon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                InvBean b = new InvBean();
                b.setINVOICE_ID(rs.getInt(1));
                b.setINVOICE_NUM(rs.getString(2));
                b.setSOURCE(rs.getString(3));
                b.setPO_NUM(rs.getString(4));
                b.setVENDOR_NAME(rs.getString(5));
                b.setVENDOR_SITE_CODE(rs.getString(6));
                b.setINVOICE_AMOUNT(rs.getFloat(7));
                b.setAMOUNT_PAID(rs.getString(8));
                b.setINVOICE_DATE(rs.getString(9));
                ans.add(b);
            }
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, "{0}", ans);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }

    public void insInv_mst(InvBean mst, WorkBean wb) {
        PreparedStatement ps = null;
        Connection dbcon = null;
        try {
            String sql = "insert into cba_inv_mst (inv_req,inv_no,inv_id,bill_no,bill_user_no,cr_date,erp_po_no)"
                    + " values(?,?,?,?,?,sysdate,?)";
            dbcon = DBmanager.GetConnection();
            ps = dbcon.prepareStatement(sql);
            ps.setInt(1, mst.getINV_REQ_ID());
            ps.setString(2, mst.getINVOICE_NUM());
            ps.setInt(3, mst.getINVOICE_ID());
            ps.setString(4, wb.getBILL_NO());
            ps.setString(5, wb.getBILL_ID());
            ps.setInt(6, mst.getERP_PO_NO());
            ps.executeQuery();
            dbcon.commit();
            logger.log(Level.SEVERE, "INV_MST INSERTED");
        } catch (SQLException ex) {
            Logger.getLogger(PODao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                dbcon.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);

            }
        }
    }

}
