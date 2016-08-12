
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Bean.DDBean;
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
public class Dropdown {

    static final Logger logger = Logger.getLogger(Dropdown.class.getName());
    public ArrayList<DDBean> DDloc = new ArrayList<DDBean>();
    public ArrayList<DDBean> DDloc_exclude = new ArrayList<DDBean>();
    public ArrayList<DDBean> DDpur = new ArrayList<DDBean>();
    public ArrayList<DDBean> DDitm = new ArrayList<DDBean>();
    public ArrayList<DDBean> DDitm_exclude = new ArrayList<DDBean>();
    public ArrayList<DDBean> DDuom = new ArrayList<DDBean>();
    public ArrayList<DDBean> DDitmmst = new ArrayList<DDBean>();

    public void LoadDropdown(String uid) {
        Connection con = DBmanager.GetConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            //LOCATION DROPDOWN
            DDBean loc = new DDBean();
            loc.setLoc_Id("all");
            loc.setLoc_name("All Location");
            DDloc.add(loc);
            String sql = "SELECT DISTINCT a.loc_id"
                    + "           FROM mmt_onhand_mst a"
                    + "          WHERE a.loc_id IN ("
                    + "                             SELECT b.loc_name"
                    + "                               FROM mmt_user_vs_loc a, mmt_loc_mst b"
                    + "                              WHERE a.loc_id = b.loc_id"
                    + "                                    AND a.user_id = '" + uid + "')";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                rs.close();
                sql = "select distinct loc_id from mmt_onhand_mst where status <> 'closed' order by loc_id";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    DDBean locbean = new DDBean();
                    locbean.setLoc_Id(rs.getString(1));
                    locbean.setLoc_name(rs.getString(1));
                    DDloc.add(locbean);
                }
            } else {
                while (rs.next()) {
                    DDBean locbean = new DDBean();
                    locbean.setLoc_Id(rs.getString(1));
                    locbean.setLoc_name(rs.getString(1));
                    DDloc.add(locbean);
                }

            }

            //PURPOSE DROPDOWN
            sql = "select pur_id,purpose from mmt_cun_purpose";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DDBean purbean = new DDBean();
                purbean.setPur_id(rs.getInt(1));
                purbean.setPurpose(rs.getString(2));
                DDpur.add(purbean);
            }
            con.commit();
            //ITEM DROPDOWN
            sql = "select distinct item_code,item_desc from mmt_onhand_mst";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DDBean purbean = new DDBean();
                purbean.setItem_code(rs.getString(1));
                purbean.setItem_desc(rs.getString(2));
                DDitm.add(purbean);
            }
            //UOM DROPDOWN
            sql = "select distinct uom from mmt_onhand_mst";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DDBean uombean = new DDBean();
                uombean.setUom(rs.getString(1));
                uombean.setUom(rs.getString(1));
                DDuom.add(uombean);
            }
            con.commit();
            //ITEM MSTER DROPDOWN
            sql = "SELECT DISTINCT a.segment1, a.description"
                    + "           FROM mtl_system_items a"
                    + "          WHERE ( SUBSTR (a.segment1, 0,2) = '02')"
                    + "                AND (a.organization_id = '4634'"
                    + "             OR a.organization_id = '6434')";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DDBean itmmst = new DDBean();
                itmmst.setItem_code(rs.getString(1));
                itmmst.setItem_desc(rs.getString(2));
                DDitmmst.add(itmmst);
            }
            con.commit();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : " + ex);

        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
    }

}
