/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.SumBean;
import static Dao.MitDao.logger;
import java.math.BigDecimal;
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
public class SumDao {

//    public static void main(String[] args) {
//        SumDao dao = new SumDao();
////        dao.getStock("op_st");
////        dao.getStock("tran_st");
////        dao.getStock("m_rev");
////        dao.getStock("m_cun");
////        dao.getStock("cun");
//        dao.getFinalSummary();
//    }
    String SQL_OP_STOCK = "SELECT   ROUND (SUM (a.qty), 3) open_stock, a.item_code, a.item_desc,\n"
            + "         a.loc_id\n"
            + "    FROM mmt_onhand_mst a\n"
            + "   WHERE TRUNC (grn_date, 'MM') < TRUNC (TO_DATE (SYSDATE), 'MM')\n"
            + "     AND a.status <> 'closed'\n"
            + "     AND a.loc_id <> 'in-transit'\n"
            + "GROUP BY a.item_code, a.item_desc, a.loc_id, a.in_transit_loc\n"
            + "ORDER BY item_code, loc_id";
    String SQL_CUN_STOCK = "SELECT   ROUND (SUM (a.qty), 3) open_s, a.item_code, a.item_desc, a.loc_id\n"
            + "    FROM mmt_tran_mst a\n"
            + "   WHERE TRUNC (a.tran_date, 'MM') < TRUNC (TO_DATE (SYSDATE), 'MM')\n"
            + "GROUP BY a.item_code, a.loc_id, a.item_desc\n"
            + "ORDER BY item_code, loc_id";
    String SQL_MON_REV = "SELECT   ROUND (SUM (c.qty), 3) total_recieved, c.item_code, c.item_desc,\n"
            + "         c.loc_id\n"
            + "    FROM mmt_onhand_mst c\n"
            + "   WHERE TRUNC (grn_date, 'MM') >= TRUNC (TO_DATE (SYSDATE), 'MM')\n"
            + "     AND c.status <> 'closed'\n"
            + "GROUP BY c.item_code, c.item_desc, c.loc_id\n"
            + "ORDER BY item_code, loc_id";
    String SQL_MON_CUN = "SELECT   ROUND (SUM (a.qty), 3) total_con, a.item_code, a.item_desc, a.loc_id\n"
            + "    FROM mmt_tran_mst a\n"
            + "   WHERE TRUNC (a.tran_date, 'MM') >= TRUNC (TO_DATE (SYSDATE), 'MM')\n"
            + "GROUP BY a.item_code, a.loc_id, a.item_desc\n"
            + "ORDER BY item_code, loc_id";
    String SQL_IN_TRANSIT = "SELECT   ROUND (SUM (a.qty), 3) open_stock, a.item_code, a.item_desc,\n"
            + "         a.in_transit_loc loc_id\n"
            + "    FROM mmt_onhand_mst a\n"
            + "   WHERE TRUNC (grn_date, 'MM') < TRUNC (TO_DATE (SYSDATE), 'MM')\n"
            + "     AND a.status <> 'closed'\n"
            + "     AND a.loc_id = 'in-transit'\n"
            + "GROUP BY a.item_code, a.item_desc, a.loc_id, a.in_transit_loc\n"
            + "ORDER BY item_code, in_transit_loc";

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public ArrayList<SumBean> getStock(String stockType) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SumBean> stocklist = new ArrayList<>();
        String sql = "";
        if (stockType.equalsIgnoreCase("op_st")) {
            sql = SQL_OP_STOCK;
        } else if (stockType.equalsIgnoreCase("tran_st")) {
            sql = SQL_IN_TRANSIT;
        } else if (stockType.equalsIgnoreCase("m_rev")) {
            sql = SQL_MON_REV;
        } else if (stockType.equalsIgnoreCase("m_cun")) {
            sql = SQL_MON_CUN;
        } else if (stockType.equalsIgnoreCase("cun")) {
            sql = SQL_CUN_STOCK;
        }

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SumBean bean = new SumBean();
                bean.setQty(rs.getFloat(1));
                bean.setItm_code(rs.getString(2));
                bean.setItm_name(rs.getString(3));
                bean.setItm_loc(rs.getString(4));
                stocklist.add(bean);
            }
            System.out.println("Stock :" + stockType + " Records :" + stocklist.size());
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
        return stocklist;
    }

    public ArrayList<SumBean> getMstBlankList() {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SumBean> stocklist = new ArrayList<>();
        String SQL_MST_STOCK_COMBINATION = "SELECT DISTINCT a.item_code, a.item_desc,\n"
                + "                DECODE (a.loc_id,\n"
                + "                        'in-transit', a.in_transit_loc,\n"
                + "                        a.loc_id\n"
                + "                       ) final_loc_id,\n"
                + "                a.loc_id\n"
                + "           FROM mmt_onhand_mst a\n"
                + "          WHERE a.status <> 'closed'\n"
                + "       ORDER BY a.item_code, a.loc_id";
        try {
            ps = con.prepareStatement(SQL_MST_STOCK_COMBINATION);
            rs = ps.executeQuery();
            while (rs.next()) {

                SumBean ans = new SumBean();
                ans.setItm_name(rs.getString(2));
                ans.setItm_code(rs.getString(1));
                ans.setItm_loc(rs.getString(3));
                ans.setIn_transit(0);
                ans.setOp_stock(0);
                ans.setM_rec(0);
                ans.setM_con(0);
                ans.setNet_av(0);
                stocklist.add(ans);
            }
            System.out.println("MstBlankList Size : " + stocklist.size());
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
        return stocklist;
    }

    public static void main(String[] args) {
        SumDao d = new SumDao();
        d.getFinalSummary();
    }

    public ArrayList<SumBean> getFinalSummary() {
        SumDao dao = new SumDao();
        ArrayList<SumBean> finalSummary = dao.getMstBlankList();
        ArrayList<SumBean> open_stock = dao.getStock("op_st");
        ArrayList<SumBean> inTransit_stock = dao.getStock("tran_st");
        ArrayList<SumBean> mon_cun = dao.getStock("m_cun");
        ArrayList<SumBean> mon_rev = dao.getStock("m_rev");
        ArrayList<SumBean> cun = dao.getStock("cun");

//after getting master data add and update accordangly        
        for (SumBean a : finalSummary) {

            //update open stock
            int i = 0;
            for (SumBean b : open_stock) {
//                System.err.println((i++) + "" + a.getItm_code().equals(b.getItm_code()) + ":" + a.getItm_loc().equals(b.getItm_loc()));
                if (a.getItm_code().equals(b.getItm_code()) && a.getItm_loc().equals(b.getItm_loc())) {
                    System.err.println(a.getItm_code());
                    a.setOp_stock(b.getQty());
                }
            }

            //minus cunsume
            for (SumBean b : cun) {
                if (a.getItm_code().equals(b.getItm_code()) && a.getItm_loc().equals(b.getItm_loc())) {
//                    System.out.println(a.getItm_code() + "=" + b.getItm_code());
//                    System.out.println(a.getOp_stock() + "-" + b.getQty() + "=" + (a.getOp_stock() - b.getQty()));
                    a.setOp_stock(a.getOp_stock() - b.getQty());
                }
            }

            //update monthly received
            for (SumBean b : mon_rev) {
                if (a.getItm_code().equals(b.getItm_code()) && a.getItm_loc().equals(b.getItm_loc())) {
//                    System.out.println(a.getItm_code() + "=" + b.getItm_code());
//                    System.out.println(a.getOp_stock() + "-" + b.getQty() + "=" + (a.getOp_stock() - b.getQty()));
                    a.setM_rec(b.getQty());
                }
            }

            //updarte monthly consume
            for (SumBean b : mon_cun) {
                if (a.getItm_code().equals(b.getItm_code()) && a.getItm_loc().equals(b.getItm_loc())) {
//                    System.out.println(a.getItm_code() + "=" + b.getItm_code());
//                    System.out.println(a.getOp_stock() + "-" + b.getQty() + "=" + (a.getOp_stock() - b.getQty()));
                    a.setM_con(b.getQty());
                }
            }

            //update in_transit            
            for (SumBean b : inTransit_stock) {
                if (a.getItm_code().equals(b.getItm_code()) && a.getItm_loc().equals(b.getItm_loc())) {
//                    System.out.println(a.getItm_code() + "=" + b.getItm_code());
//                    System.out.println(a.getOp_stock() + "-" + b.getQty() + "=" + (a.getOp_stock() - b.getQty()));
                    a.setIn_transit(b.getQty());
                }
            }

            //set net avaiable
            a.setNet_av(round((a.getOp_stock() + a.getM_rec()) - a.getM_con(), 3));

        };

        System.out.println("Code \t\t|| In Transit \t\t|| Op Stock \t\t|| M Received \t\t|| M Consume \t\t|| Net Avail \t\t|| Location");
        for (SumBean ans : finalSummary) {
//            Logger.getLogger(ItemDao.class.getName()).
//                    log(Level.SEVERE, ans.getItm_code()+ "||" + ans.getItm_loc() + "||" + ans.getIn_transit() + "||"
//                            + ans.getOp_stock() + " ||" + ans.getM_rec() + " ||" + ans.getM_con() + " ||" + ans.getNet_av());

            System.out.println(ans.getItm_code() + "\t\t||" + ans.getIn_transit() + "\t\t||"
                    + ans.getOp_stock() + "\t\t||" + ans.getM_rec() + "\t\t||" + ans.getM_con()
                    + "\t\t||" + ans.getNet_av() + "\t\t||" + ans.getItm_loc());
        }

        return finalSummary;
    }

}
