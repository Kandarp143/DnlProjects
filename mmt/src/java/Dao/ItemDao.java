/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.ItemBean;
import Bean.MitBean;
import Interface.ItemInter;
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
public class ItemDao implements ItemInter {

    static final Logger logger = Logger.getLogger(ItemDao.class.getName());
    private String sql;

    public static void main(String[] args) {
        ItemDao d = new ItemDao();
        d.getALLitem();
        d.getALLitem("LOC_ID", "Greenville Colorants, SC 29611");
        d.getALLitem("LOC_ID", "Greenville Colorants, SC 29611", "02948");

        ItemBean bean = new ItemBean();
        bean.setRECIEPT_NO("111111");
        bean.setGRN_DATE("14-mar-2012");
        bean.setPO_NO("111");
        bean.setLOT_NO("111");
        bean.setINV_NO("111");
        bean.setSO_NO("11");
        bean.setLOC_ID("11");
        bean.setQTY(1);
        bean.setUOM("111");
        bean.setITEM_CODE("111");
        bean.setITEM_DESC("11");
        bean.setPOST_SHIP("111");
        bean.setPRE_SHIP("111");
        bean.setSTATUS("open");
        bean.setREC_STATUS("CUSTOM");
        bean.setRE_QTY(2);
//        d.insItem(bean);

    }

    @Override
    public ArrayList<ItemBean> getALLitem() {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ItemBean> itb = new ArrayList<ItemBean>();
        try {
            sql = "select RECEIPT_NO,GRN_DATE,PO_NO,LOT_NO,INV_NO,SO_NO,LOC_ID,QTY,UOM,ITEM_CODE,"
                    + "ITEM_DESC,POST_SHIP,PRE_SHIP,STATUS,REC_STATUS,RE_QTY,TO_CHAR(GRN_DATE,'DD-MON-YYYY'),container_no,in_transit_loc from mmt_onhand_mst where STATUS <> 'closed'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ItemBean bean = new ItemBean();
                bean.setRECIEPT_NO(rs.getString(1));
                bean.setGRN_DATE(rs.getString(2));
                bean.setPO_NO(rs.getString(3));
                bean.setLOT_NO(rs.getString(4));
                bean.setINV_NO(rs.getString(5));
                bean.setSO_NO(rs.getString(6));
                bean.setLOC_ID(rs.getString(7));
                bean.setQTY(rs.getFloat(8));
                bean.setUOM(rs.getString(9));
                bean.setITEM_CODE(rs.getString(10));
                bean.setITEM_DESC(rs.getString(11));
                bean.setPOST_SHIP(rs.getString(12));
                bean.setPRE_SHIP(rs.getString(13));
                bean.setSTATUS(rs.getString(14));
                bean.setREC_STATUS(rs.getString(15));
                bean.setRE_QTY(rs.getFloat(16));
                bean.setGRN_DATE(rs.getString(17));
                bean.setCONTAINER_NO(rs.getString(18));
                bean.setIN_TRANSIT_LOC(rs.getString(19));
                itb.add(bean);
            }
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "MMT_ONHAND_MST RECORDS: " + itb.size());
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

    @Override
    public ArrayList<ItemBean> getALLitem(String peramater, String value) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ItemBean> itb = new ArrayList<ItemBean>();
        try {
            sql = "select RECEIPT_NO,GRN_DATE,PO_NO,LOT_NO,INV_NO,SO_NO,LOC_ID,QTY,UOM,ITEM_CODE,"
                    + "ITEM_DESC,POST_SHIP,PRE_SHIP,STATUS,REC_STATUS,RE_QTY,TO_CHAR(GRN_DATE,'DD-MON-YYYY'),container_no,in_transit_loc"
                    + " from mmt_onhand_mst where STATUS <> 'closed'"
                    + " AND " + peramater + " = '" + value + "'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ItemBean bean = new ItemBean();
                bean.setRECIEPT_NO(rs.getString(1));
                bean.setGRN_DATE(rs.getString(2));
                bean.setPO_NO(rs.getString(3));
                bean.setLOT_NO(rs.getString(4));
                bean.setINV_NO(rs.getString(5));
                bean.setSO_NO(rs.getString(6));
                bean.setLOC_ID(rs.getString(7));
                bean.setQTY(rs.getFloat(8));
                bean.setUOM(rs.getString(9));
                bean.setITEM_CODE(rs.getString(10));
                bean.setITEM_DESC(rs.getString(11));
                bean.setPOST_SHIP(rs.getString(12));
                bean.setPRE_SHIP(rs.getString(13));
                bean.setSTATUS(rs.getString(14));
                bean.setREC_STATUS(rs.getString(15));
                bean.setRE_QTY(rs.getFloat(16));
                bean.setGRN_DATE(rs.getString(17));
                bean.setCONTAINER_NO(rs.getString(18));
                bean.setIN_TRANSIT_LOC(rs.getString(19));
                itb.add(bean);
            }
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "MMT_ONHAND_MST RECORD : " + itb.size() + "," + peramater + "," + value);
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

    @Override
    public ArrayList<ItemBean> getALLitem(String peramater, String value, String uid) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ItemBean> itb = new ArrayList<ItemBean>();
        try {
            sql = "SELECT receipt_no, grn_date, po_no, lot_no, inv_no, so_no, loc_id, round(qty,2), uom,"
                    + "       item_code, item_desc, post_ship, pre_ship, status, rec_status, round(re_qty,2),"
                    + "       TO_CHAR (grn_date, 'DD-MON-YYYY'),container_no,in_transit_loc"
                    + "  FROM mmt_onhand_mst"
                    + " WHERE status <> 'closed'"
                    + "   AND " + peramater + " = '" + value + "'"
                    + "   AND item_code IN (SELECT prod_id"
                    + "                       FROM mmt_user_vs_prod"
                    + "                      WHERE user_id = '" + uid + "')"
                    + "                      ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                rs.close();
                sql = "select RECEIPT_NO,GRN_DATE,PO_NO,LOT_NO,INV_NO,SO_NO,LOC_ID,QTY,UOM,ITEM_CODE,"
                        + "ITEM_DESC,POST_SHIP,PRE_SHIP,STATUS,REC_STATUS,RE_QTY,TO_CHAR(GRN_DATE,'DD-MON-YYYY'),container_no,in_transit_loc from mmt_onhand_mst where STATUS <> 'closed'"
                        + " AND " + peramater + " = '" + value + "'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                ItemBean bean = new ItemBean();
                bean.setRECIEPT_NO(rs.getString(1));
                bean.setGRN_DATE(rs.getString(2));
                bean.setPO_NO(rs.getString(3));
                bean.setLOT_NO(rs.getString(4));
                bean.setINV_NO(rs.getString(5));
                bean.setSO_NO(rs.getString(6));
                bean.setLOC_ID(rs.getString(7));
                bean.setQTY(rs.getFloat(8));
                bean.setUOM(rs.getString(9));
                bean.setITEM_CODE(rs.getString(10));
                bean.setITEM_DESC(rs.getString(11));
                bean.setPOST_SHIP(rs.getString(12));
                bean.setPRE_SHIP(rs.getString(13));
                bean.setSTATUS(rs.getString(14));
                bean.setREC_STATUS(rs.getString(15));
                bean.setRE_QTY(rs.getFloat(16));
                bean.setGRN_DATE(rs.getString(17));
                bean.setCONTAINER_NO(rs.getString(18));
                bean.setIN_TRANSIT_LOC(rs.getString(19));
                itb.add(bean);

            }
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "MMT_ONHAND_MST RECORD : " + itb.size() + "," + peramater + "," + value);
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

    @Override
    public void updateQty(ArrayList<MitBean> itm) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "update mmt_onhand_mst set re_qty = ?,status = ? where receipt_no = ? and lot_no = ? and pre_ship = ?";
            ps = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (ItemBean bean : itm) {
                ps.setFloat(1, bean.getRE_QTY());
                ps.setString(2, bean.getSTATUS());
                ps.setString(3, bean.getRECIEPT_NO());
                ps.setString(4, bean.getLOT_NO());
                ps.setString(5, bean.getPRE_SHIP());
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
            Logger.getLogger(TranDao.class.getName()).log(Level.SEVERE, "MMT_ONHAND_MST UPDATE QTY");
        } catch (SQLException ex) {
            Logger.getLogger(TranDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);
            }
        }
    }

//    @Override
//    public void insItem(ArrayList<ItemBean> itm) {
//        Connection con = Logic.DBmanager.GetConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            sql = "insert into mmt_onhand_mst "
//                    + "(RECEIPT_NO,GRN_DATE,PO_NO,LOT_NO,INV_NO,SO_NO,LOC_ID,QTY,UOM,"
//                    + "ITEM_CODE,ITEM_DESC,POST_SHIP,PRE_SHIP,STATUS,REC_STATUS,RE_QTY)"
//                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            ps = con.prepareStatement(sql);
//            con.setAutoCommit(false);
//            for (ItemBean bean : itm) {
//                ps.setString(1, bean.getRECIEPT_NO());
//                ps.setString(2, bean.getGRN_DATE());
//                ps.setString(3, bean.getPO_NO());
//                ps.setString(4, bean.getLOT_NO());
//                ps.setString(5, bean.getINV_NO());
//                ps.setString(6, bean.getSO_NO());
//                ps.setString(7, bean.getLOC_ID());
//                ps.setFloat(8, bean.getQTY());
//                ps.setString(9, bean.getUOM());
//                ps.setString(10, bean.getITEM_CODE());
//                ps.setString(11, bean.getITEM_DESC());
//                ps.setString(12, bean.getPOST_SHIP());
//                ps.setString(13, bean.getPRE_SHIP());
//                ps.setString(14, bean.getSTATUS());
//                ps.setString(15, bean.getREC_STATUS());
//                ps.setFloat(16, bean.getRE_QTY());
//                ps.addBatch();
//            }
//            ps.executeBatch();
//            Logger.getLogger(TranDao.class.getName()).log(Level.SEVERE, "MMT_ONHAND_MST INSERT ");
//        } catch (SQLException ex) {
//            Logger.getLogger(TranDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
//        } finally {
//            try {
//                ps.close();
//                con.close();
//            } catch (Exception ex) {
//                logger.log(Level.SEVERE, "Exception : " + ex);
//            }
//        }
//    }
//    @Override
//    public void insItem(ItemBean itm) {
//        Connection con = Logic.DBmanager.GetConnection();
//        PreparedStatement ps = null;
//        try {
//            sql = "insert into mmt_onhand_mst "
//                    + "(RECEIPT_NO,GRN_DATE,PO_NO,LOT_NO,INV_NO,SO_NO,LOC_ID,QTY,UOM,"
//                    + "ITEM_CODE,ITEM_DESC,POST_SHIP,PRE_SHIP,STATUS,REC_STATUS,RE_QTY)"
//                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            ps = con.prepareStatement(sql);
//            ps.setString(1, itm.getRECIEPT_NO());
//            ps.setString(2, itm.getGRN_DATE());
//            ps.setString(3, itm.getPO_NO());
//            ps.setString(4, itm.getLOT_NO());
//            ps.setString(5, itm.getINV_NO());
//            ps.setString(6, itm.getSO_NO());
//            ps.setString(7, itm.getLOC_ID());
//            ps.setFloat(8, itm.getQTY());
//            ps.setString(9, itm.getUOM());
//            ps.setString(10, itm.getITEM_CODE());
//            ps.setString(11, itm.getITEM_DESC());
//            ps.setString(12, itm.getPOST_SHIP());
//            ps.setString(13, itm.getPRE_SHIP());
//            ps.setString(14, itm.getSTATUS());
//            ps.setString(15, itm.getREC_STATUS());
//            ps.setFloat(16, itm.getRE_QTY());
//            ps.executeQuery();
//            Logger.getLogger(TranDao.class.getName()).log(Level.SEVERE, "MMT_ONHAND_MST INSERT ");
//        } catch (SQLException ex) {
//            Logger.getLogger(TranDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
//        } finally {
//            try {
//                ps.close();
//                con.close();
//            } catch (Exception ex) {
//                logger.log(Level.SEVERE, "Exception : " + ex);
//            }
//        }
//    }
}
