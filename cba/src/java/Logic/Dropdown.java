
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Bean.TaxBean;
import Bean.WorkItemBean;
import static Logic.Variables.billitem;
import static Logic.Variables.cur;
import static Logic.Variables.item;
import static Logic.Variables.itmplant;
import static Logic.Variables.payterm;
import static Logic.Variables.sup;
import static Logic.Variables.wo_type;
import static Logic.Variables.tax;
import static Logic.Variables.task;
import static Logic.Variables.project;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class Dropdown {

    static final Logger logger = Logger.getLogger(Dropdown.class.getName());

    public static void LoadDropdown() {
        wo_type.put("Project", "WO with Project Information");
        wo_type.put("Blanket", "WO with-out Project Information");
        //ITEM
        Connection con = DBmanager.GetConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            //ITEM
            String sql = "SELECT segment1, segment1 || '  :   ' || description"
                    + " FROM mtl_system_items"
                    + " WHERE organization_id = 0"
                    + " AND segment1 like '9%'"
                    + " ORDER BY segment1, description";
            item = new HashMap();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            item.clear();
            item.put("0", "Select Item");
            while (rs.next()) {
                if (rs.getString(2) == null || rs.getString(1) == null) {
                } else {
                    item.put(rs.getString(1), rs.getString(2));
                }
            }
            //TAX
            sql = "select tax_id,tax_name,tax_rate from JA_IN_TAX_CODES where (TAX_TYPE = 'VALUE ADDED TAX' "
                    + "OR TAX_TYPE = 'Service' OR TAX_TYPE ='SERVICE_EDUCATION_CESS') AND ORG_ID ='123'"
                    + "AND END_DATE is null order by tax_name";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            tax.clear();
            while (rs.next()) {
                if (rs.getString(2) == null || rs.getString(1) == null) {
                } else {
                    TaxBean tb = new TaxBean();
                    tb.setTAX_ID(Integer.parseInt(rs.getString(1)));
                    tb.setTAX_NAME(rs.getString(2));
                    tb.setTAX_RATE(rs.getString(3));
                    tax.add(tb);
                }
            }
            //TERM
            sql = "select term_id,name from ap_terms";
            payterm = new HashMap();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            payterm.clear();
            while (rs.next()) {
                if (rs.getString(2) == null || rs.getString(1) == null) {
                } else {
                    payterm.put(rs.getString(1), rs.getString(2));
                }
            }
            //PLANT
            sql = "select cc,cc || ' : ' ||plant from cba_plant_mst";
            itmplant = new HashMap();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            itmplant.clear();
            while (rs.next()) {
                if (rs.getString(2) == null || rs.getString(1) == null) {
                } else {
                    itmplant.put(rs.getString(1), rs.getString(2));
                }
            }
            //COST CENTRE
            sql = "select cc,cc || ' : ' ||plant from cba_cc_mst";
            Variables.itmcc = new HashMap();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Variables.itmcc.clear();
            while (rs.next()) {
                if (rs.getString(2) == null || rs.getString(1) == null) {
                } else {
                    Variables.itmcc.put(rs.getString(1), rs.getString(2));
                }
            }
            //SUPLIER
            sql = "select distinct VENDOR_NUMBER,VENDOR_NUMBER || ' : ' ||VENDOR_NAME from PO_VENDORS order by VENDOR_NUMBER";
            sup = new HashMap();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            sup.clear();
            while (rs.next()) {
                if (rs.getString(2) == null || rs.getString(1) == null) {
                } else {
                    sup.put(rs.getString(1), rs.getString(2));
                }
            }
            //CURRENCY
            sql = "select * from dnl_currency_mst";
            cur = new HashMap();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            cur.clear();
            while (rs.next()) {
                if (rs.getString(1) == null) {
                } else {
                    cur.put(rs.getString(1), rs.getString(1));
                }
            }
            con.commit();
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
    }

    public static void LaodBillDropdown(String pono) {
        Connection con = DBmanager.GetConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "";
            sql = "SELECT cba_wo_re.item_id,cba_wo_re.item_id|| '  :   ' || mtl_system_items.description"
                    + "  FROM cba_wo_re, mtl_system_items"
                    + " WHERE ((cba_wo_re.item_id = mtl_system_items.segment1)"
                    + " AND(cba_wo_re.po_no ='" + pono + "')"
                    + " AND(mtl_system_items.organization_id =0)"
                    + " AND(mtl_system_items.segment1 like '9%')"
                    + " AND(qty>0 or (qty = 0 and ischeck = 'N'))"
                    + " )";
            billitem = new HashMap();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            billitem.clear();
            billitem.put("0", "Select Item");
            while (rs.next()) {
                if (rs.getString(2) == null || rs.getString(1) == null) {
                } else {
                    billitem.put(rs.getString(1), rs.getString(2));
                }
            }
            con.commit();
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

    }

    public static void LoadTaxDropdown(String unit) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "";
        String orgId = "";
        try {

            if (unit.equalsIgnoreCase("NDS") || unit.equalsIgnoreCase("CRP")) {
                orgId = "123";
            }
            con = DBmanager.getInvcon();
            sql = "select tax_id,tax_name,tax_rate from JA_IN_TAX_CODES where (TAX_TYPE = 'VALUE ADDED TAX' "
                    + "OR TAX_TYPE = 'Service' OR TAX_TYPE ='SERVICE_EDUCATION_CESS') AND ORG_ID ='" + orgId + "'"
                    + "AND END_DATE is null order by tax_name";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            tax.clear();
            while (rs.next()) {
                if (rs.getString(2) == null || rs.getString(1) == null) {
                } else {
                    TaxBean tb = new TaxBean();
                    tb.setTAX_ID(Integer.parseInt(rs.getString(1)));
                    tb.setTAX_NAME(rs.getString(2));
                    tb.setTAX_RATE(rs.getString(3));
                    tax.add(tb);
                }
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void LoadProjectDropdown(String unit) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "";
        String orgId = "";
        if (unit.equalsIgnoreCase("NDS") || unit.equalsIgnoreCase("CRP")) {
            orgId = "123";
        }
        try {
            con = DBmanager.getInvcon();
            sql = "SELECT   segment1 || ' : ' || NAME project_name, segment1 project_code\n"
                    + "    FROM pa_projects_all ppa\n"
                    + "   WHERE ppa.project_status_code = 'APPROVED' AND org_id = '" + orgId + "'\n"
                    + "ORDER BY segment1";
            project = new HashMap();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            project.clear();
            project.put("0", "Select Project");
            while (rs.next()) {
                if (rs.getString(2) == null || rs.getString(1) == null) {
                } else {
                    project.put(rs.getString(2), rs.getString(1));
                }
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void LoadTaskDropdown(String projCode) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "";
        try {
            con = DBmanager.getInvcon();
            sql = "SELECT   task_number || ' :' || task_name, task_number\n"
                    + "    FROM apps.pa_tasks a, pa_projects_all b\n"
                    + "   WHERE a.project_id = b.project_id AND b.segment1 = '" + projCode + "' \n"
                    + "ORDER BY task_number";
            task = new LinkedHashMap();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            task.clear();
            task.put("-", "Select Task");
            while (rs.next()) {
                if (rs.getString(2) == null || rs.getString(1) == null) {
                } else {
                    task.put(rs.getString(2), rs.getString(1));
                }
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
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

    public static ArrayList<WorkItemBean> LoadProjTaskMst(String orgid) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<WorkItemBean> ans = new ArrayList<WorkItemBean>();
        String sql = "";
        try {
            con = DBmanager.getInvcon();
            sql = "SELECT   b.NAME project_name, b.segment1 project_code, a.task_name,\n"
                    + "         a.task_number\n"
                    + "    FROM pa_projects_all b, apps.pa_tasks a\n"
                    + "   WHERE a.project_id = b.project_id AND b.org_id = '" + orgid + "'\n"
                    + "ORDER BY b.segment1, a.task_number";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkItemBean b = new WorkItemBean();
                b.setPROJ(rs.getString(2));
                b.setTASK(rs.getString(4));
                b.setPROJ_NAME(rs.getString(1));
                b.setTASK_NAME(rs.getString(3));
                ans.add(b);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);
            }
        }
        Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, "PROJ TASK MST " + ans.size());
        return ans;
    }

    public static Map<String, String> LoadPlantMst() {
        Map<String, String> plant = new HashMap<String, String>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "";
        try {
            con = DBmanager.GetConnection();
            sql = "select cc,plant from cba_plant_mst";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                plant.put(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);
            }
        }
        return plant;
    }

}
