/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.MitBean;
import Interface.TranInter;
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
public class TranDao implements TranInter {

    static final Logger logger = Logger.getLogger(TranDao.class.getName());
    private String sql = "";

    @Override
    public void insMIT_TRAN(ArrayList<MitBean> mitlist) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "insert into mmt_tran_mst "
                    + "(TRAN_ID,MITNO,TRAN_DATE,USER_ID,PUR_ID,CUS_PO,RECEIPT_NO,"
                    + "GRN_DATE,PO_NO,LOT_NO,INV_NO,SO_NO,LOC_ID,QTY,UOM,"
                    + "ITEM_CODE,ITEM_DESC,POST_SHIP,PRE_SHIP)"
                    + "VALUES(?,?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (MitBean bean : mitlist) {
                ps.setInt(1, bean.getTRAN_ID());
                ps.setString(2, bean.getMITNO());
                ps.setString(3, bean.getUSER_ID());
                ps.setInt(4, bean.getPUR_ID());
                ps.setString(5, bean.getCUS_PO());
                ps.setString(6, bean.getRECIEPT_NO());
                ps.setString(7, bean.getGRN_DATE());
                ps.setString(8, bean.getPO_NO());
                ps.setString(9, bean.getLOT_NO());
                ps.setString(10, bean.getINV_NO());
                ps.setString(11, bean.getSO_NO());
                ps.setString(12, bean.getLOC_ID());
                ps.setFloat(13, bean.getCUN_QTY());
                ps.setString(14, bean.getUOM());
                ps.setString(15, bean.getITEM_CODE());
                ps.setString(16, bean.getITEM_DESC());
                ps.setString(17, bean.getPOST_SHIP());
                ps.setString(18, bean.getPRE_SHIP());
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
            Logger.getLogger(TranDao.class.getName()).log(Level.SEVERE, "MMT CONSUME TRAN ADDED");
        } catch (SQLException ex) {
            try {
                con.rollback();
                Logger.getLogger(TranDao.class.getName()).log(Level.SEVERE, "Exception : " + ex);
            } catch (SQLException ex1) {
                Logger.getLogger(TranDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);

            }
        }
    }

    @Override
    public ArrayList<MitBean> getMIT_TRAN() {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<MitBean> itb = new ArrayList<MitBean>();
        try {
            sql = "SELECT distinct tran.item_code, tran.item_desc, tran.mitno, usr.full_name, cun.purpose,"
                    + "       TO_CHAR (tran.tran_date, 'DD-MON-YYYY'), tran.cus_po, tran.loc_id,"
                    + "       tran.receipt_no, tran.lot_no, tran.pre_ship,tran.qty"
                    + "  FROM mmt_onhand_mst mst,"
                    + "       mmt_tran_mst tran,"
                    + "       mmt_user_mst usr,"
                    + "       mmt_cun_purpose cun"
                    + " WHERE mst.receipt_no = tran.receipt_no"
                    + "   AND mst.item_code = tran.item_code"
                    + "   AND tran.user_id = usr.user_id"
                    + "   AND tran.pur_id = cun.pur_id order by tran.MITNO";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MitBean bean = new MitBean();
                bean.setITEM_CODE(rs.getString(1));
                bean.setITEM_DESC(rs.getString(2));
                bean.setMITNO(rs.getString(3));
                bean.setUSER_NAME(rs.getString(4));
                bean.setPUR_NAME(rs.getString(5));
                bean.setTRAN_DATE(rs.getString(6));
                bean.setCUS_PO(rs.getString(7));
                bean.setLOC_ID(rs.getString(8));
                bean.setRECIEPT_NO(rs.getString(9));
                bean.setLOT_NO(rs.getString(10));
                bean.setPRE_SHIP(rs.getString(11));
                bean.setCUN_QTY(rs.getFloat(12));
                itb.add(bean);
            }
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "MMT_TRAN_MST RECORDS: " + itb.size());
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
    public ArrayList<MitBean> getONHAND_TRAN(String item_code, String location) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<MitBean> itb = new ArrayList<MitBean>();
        try {
            sql = "SELECT receipt_no, to_char(grn_date,'DD-MON-YYYY'), po_no, lot_no, inv_no, so_no, loc_id, re_qty, uom, "
                    + "       item_code, item_desc, post_ship, pre_ship, status, in_transit_loc, "
                    + "       container_no,cro_no "
                    + "  FROM mmt_onhand_mst WHERE item_code='" + item_code + "' and status <>'closed' order by loc_id";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MitBean bean = new MitBean();
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
                bean.setIN_TRANSIT_LOC(rs.getString(15));
                bean.setCONTAINER_NO(rs.getString(16));
                bean.setCRO_NO(rs.getString(17));
                itb.add(bean);
            }
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "ONHAND RECORD RECORDS: " + itb.size() + " FOR ITEM" + item_code);
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
    public ArrayList<MitBean> getMIT_TRAN(String user_id) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<MitBean> itb = new ArrayList<MitBean>();
        try {
            sql = "SELECT distinct tran.item_code, tran.item_desc, tran.mitno, usr.full_name, cun.purpose,"
                    + "       TO_CHAR (tran.tran_date, 'DD-MON-YYYY'), tran.cus_po, tran.loc_id,"
                    + "       tran.receipt_no, tran.lot_no, tran.pre_ship,tran.qty"
                    + "  FROM mmt_onhand_mst mst,"
                    + "       mmt_tran_mst tran,"
                    + "       mmt_user_mst usr,"
                    + "       mmt_cun_purpose cun"
                    + " WHERE mst.receipt_no = tran.receipt_no"
                    + "   AND mst.item_code = tran.item_code"
                    + "   AND tran.user_id = usr.user_id"
                    + "   AND tran.pur_id = cun.pur_id AND tran.user_id ='" + user_id + "' order by tran.MITNO";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MitBean bean = new MitBean();
                bean.setITEM_CODE(rs.getString(1));
                bean.setITEM_DESC(rs.getString(2));
                bean.setMITNO(rs.getString(3));
                bean.setUSER_NAME(rs.getString(4));
                bean.setPUR_NAME(rs.getString(5));
                bean.setTRAN_DATE(rs.getString(6));
                bean.setCUS_PO(rs.getString(7));
                bean.setLOC_ID(rs.getString(8));
                bean.setRECIEPT_NO(rs.getString(9));
                bean.setLOT_NO(rs.getString(10));
                bean.setPRE_SHIP(rs.getString(11));
                bean.setCUN_QTY(rs.getFloat(12));
                itb.add(bean);
            }
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "MMT_TRAN_MST RECORDS: " + itb.size());
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
    public MitBean getMIT_TRAN2(String mit) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        MitBean bean = new MitBean();
        try {
            sql = "SELECT distinct tran.item_code, tran.item_desc, tran.mitno, usr.full_name, cun.purpose,"
                    + "       TO_CHAR (tran.tran_date, 'DD-MON-YYYY'), tran.cus_po, tran.loc_id,"
                    + "       tran.receipt_no, tran.lot_no, tran.pre_ship,tran.qty"
                    + "  FROM mmt_onhand_mst mst,"
                    + "       mmt_tran_mst tran,"
                    + "       mmt_user_mst usr,"
                    + "       mmt_cun_purpose cun"
                    + " WHERE mst.receipt_no = tran.receipt_no"
                    + "   AND mst.item_code = tran.item_code"
                    + "   AND tran.user_id = usr.user_id"
                    + "   AND tran.pur_id = cun.pur_id AND tran.mitno ='" + mit + "' order by tran.MITNO";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                bean.setITEM_CODE(rs.getString(1));
                bean.setITEM_DESC(rs.getString(2));
                bean.setMITNO(rs.getString(3));
                bean.setUSER_NAME(rs.getString(4));
                bean.setPUR_NAME(rs.getString(5));
                bean.setTRAN_DATE(rs.getString(6));
                bean.setCUS_PO(rs.getString(7));
                bean.setLOC_ID(rs.getString(8));
                bean.setRECIEPT_NO(rs.getString(9));
                bean.setLOT_NO(rs.getString(10));
                bean.setPRE_SHIP(rs.getString(11));
                bean.setCUN_QTY(rs.getFloat(12));
            }
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "MMT_TRAN_MST RECORDS: ");
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
        return bean;
    }

    @Override
    public ArrayList<MitBean> getMIT_TRAN(String item_code, String location) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<MitBean> itb = new ArrayList<MitBean>();
        try {
            sql = "SELECT distinct tran.item_code, tran.item_desc, tran.mitno, usr.full_name, cun.purpose,"
                    + "       TO_CHAR (tran.tran_date, 'DD-MON-YYYY'), tran.cus_po, tran.loc_id,"
                    + "       tran.receipt_no, tran.lot_no, tran.pre_ship,tran.qty"
                    + "  FROM mmt_onhand_mst mst,"
                    + "       mmt_tran_mst tran,"
                    + "       mmt_user_mst usr,"
                    + "       mmt_cun_purpose cun"
                    + " WHERE mst.receipt_no = tran.receipt_no"
                    + "   AND mst.item_code = tran.item_code"
                    + "   AND tran.user_id = usr.user_id"
                    + "   AND tran.pur_id = cun.pur_id AND tran.item_code ='" + item_code + "' "
                    + "   AND tran.loc_id = '" + location + "'"
                    + "  order by tran.MITNO";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MitBean bean = new MitBean();
                bean.setITEM_CODE(rs.getString(1));
                bean.setITEM_DESC(rs.getString(2));
                bean.setMITNO(rs.getString(3));
                bean.setUSER_NAME(rs.getString(4));
                bean.setPUR_NAME(rs.getString(5));
                bean.setTRAN_DATE(rs.getString(6));
                bean.setCUS_PO(rs.getString(7));
                bean.setLOC_ID(rs.getString(8));
                bean.setRECIEPT_NO(rs.getString(9));
                bean.setLOT_NO(rs.getString(10));
                bean.setPRE_SHIP(rs.getString(11));
                bean.setCUN_QTY(rs.getFloat(12));
                itb.add(bean);
            }
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "MMT_TRAN_MST RECORDS: ");
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

}
