/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Item;
import Inter.ItemInter;
import Logic.Call;
import Logic.DbConnect;
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

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<Item> deleted = new ArrayList<Item>();
        Item i = new Item();
        i.setItem_id("010000001");
        i.setItem_no("010000001");
        deleted.add(i);
        Item i1 = new Item();
        i1.setItem_id("010000005");
        i1.setItem_no("010000005");
        deleted.add(i1);
        ItemDao d = new ItemDao();
//        d.getItem();
//        d.getItem("08");
//        d.getItem("01", deleted);
//        d.getItem("21", d.getDelItem());
//        d.getDelItem();
//        d.getItemGrp();
//        i.setItem_desc(d.getItemPera("010000001", "description"));
//        d.getRelatedItem(i);
        // d.getDoneItem();
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Item> getItem() {
        ArrayList<Item> itm = new ArrayList<Item>();
        Connection con = DbConnect.GetPAYConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Bean.Sql.get_Item_all);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item b = new Item();
                b.setItem_id(rs.getString(1));
                b.setItem_no(rs.getString(2));
                b.setItem_desc(rs.getString(3));
                b.setUom(rs.getString(4));
                b.setMin_max_flag(rs.getString(5));
                b.setOnhand_qty(rs.getString(6));
                b.setCnt(rs.getString(7));
                b.setLast_trx_date(rs.getString(8));
                itm.add(b);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        logger.log(Level.SEVERE, "ALL ITEM : " + itm.size());
//        for (Item i : itm) {
//            logger.log(Level.SEVERE, i.getItem_no());
//        }
        return itm;
    }

    /**
     *
     * @param grp
     * @return
     */
    @Override
    public ArrayList<Item> getItem(String grp) {
        ArrayList<Item> itm = new ArrayList<Item>();
        Connection con = DbConnect.GetPAYConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Bean.Sql.get_Item_By_Grp);
            ps.setString(1, grp);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item b = new Item();
                b.setItem_id(rs.getString(1));
                b.setItem_no(rs.getString(2));
                b.setItem_desc(rs.getString(3));
                b.setUom(rs.getString(4));
                b.setMin_max_flag(rs.getString(5));
                b.setOnhand_qty(rs.getString(6));
                b.setCnt(rs.getString(7));
                b.setLast_trx_date(rs.getString(8));
                itm.add(b);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        logger.log(Level.SEVERE, "ITEM BY GROUP : " + itm.size());
//        for (Item i : itm) {
//            logger.log(Level.SEVERE, i.getItem_no());
//        }
        return itm;
    }

    /**
     *
     * @param grp
     * @param deldone
     * @return
     */
    @Override
    public ArrayList<Item> getItem(String grp, ArrayList<Item> deldone) {
        ArrayList<Item> itm = new ArrayList<Item>();
        ArrayList<Item> final_itm = new ArrayList<Item>();
        Connection con = DbConnect.GetPAYConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Bean.Sql.get_Item_By_Grp);
            ps.setString(1, grp);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item b = new Item();
                b.setItem_id(rs.getString(1));
                b.setItem_no(rs.getString(2));
                b.setItem_desc(rs.getString(3));
                b.setUom(rs.getString(4));
                b.setMin_max_flag(rs.getString(5));
                b.setOnhand_qty(rs.getString(6));
                b.setCnt(rs.getString(7));
                b.setLast_trx_date(rs.getString(8));
                itm.add(b);
            }
            for (Item a : itm) {
                for (Item d : deldone) {
                    Item b = new Item();
                    if (a.getItem_id().equals(d.getItem_id())) {

                        final_itm.add(a);
                    }
                }
            }
            itm.removeAll(final_itm);
            logger.log(Level.SEVERE, "ITEM BY GROUP - DELDONE :" + itm.size());
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }

//        for (Item i : itm) {
//            logger.log(Level.SEVERE, i.getItem_no());
//        }
        return itm;
    }

    /**
     *
     * @param deldone
     * @return
     */
    @Override
    public ArrayList<Item> getItem(ArrayList<Item> deldone) {
        ArrayList<Item> itm = new ArrayList<Item>();
        ArrayList<Item> final_itm = new ArrayList<Item>();
        Connection con = DbConnect.GetPAYConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Bean.Sql.get_Item_all);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item b = new Item();
                b.setItem_id(rs.getString(1));
                b.setItem_no(rs.getString(2));
                b.setItem_desc(rs.getString(3));
                b.setUom(rs.getString(4));
                b.setMin_max_flag(rs.getString(5));
                b.setOnhand_qty(rs.getString(6));
                b.setCnt(rs.getString(7));
                b.setLast_trx_date(rs.getString(8));
                itm.add(b);
            }
            for (Item a : itm) {
                for (Item d : deldone) {
                    Item b = new Item();
                    if (a.getItem_id().equals(d.getItem_id())) {
                        final_itm.add(a);
                    }
                }
            }
            itm.removeAll(final_itm);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);

            }
        }
        logger.log(Level.SEVERE, "ALL ITEM - DELDONE :" + itm.size());
//        for (Item i : itm) {
//            logger.log(Level.SEVERE, i.getItem_no());
//        }
        return itm;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Item> getDelDoneItem() {
        ArrayList<Item> itm = new ArrayList<Item>();
        Connection con = DbConnect.GetPAYConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Bean.Sql.get_deldone_item);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item b = new Item();
                b.setItem_id(rs.getString(1));
                b.setItem_no(rs.getString(2));
                b.setItem_desc(rs.getString(3));
                b.setAct_date(rs.getString(4));
                itm.add(b);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);

            }
        }
        logger.log(Level.SEVERE, "DELDONE ITEM :" + itm.size());
//        for (Item i : itm) {
//            logger.log(Level.SEVERE, i.getItem_no());
//        }
        return itm;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Item> getOnlyDelItem() {
        ArrayList<Item> itm = new ArrayList<Item>();
        Connection con = DbConnect.GetPAYConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Bean.Sql.get_only_del_item);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item b = new Item();
                b.setItem_id(rs.getString(1));
                b.setItem_no(rs.getString(2));
                b.setItem_desc(rs.getString(3));
                b.setMatch_item_no(rs.getString(4));
                b.setGrpname(rs.getString(5));
                b.setAct_date(rs.getString(6));
                itm.add(b);
            }
            //  itm.addAll(getDoneItem());
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);

            }
        }
        logger.log(Level.SEVERE, "DEL ITEM :" + itm.size());
//        for (Item i : itm) {
//            logger.log(Level.SEVERE, i.getItem_no());
//        }
        return itm;
    }

    /**
     *
     * @param selected
     * @return
     */
    @Override
    public ArrayList<Item> getRelatedItem(Item selected) {
        //Declare Variable
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Item> que = new ArrayList<Item>();
        ArrayList<Item> deleted = new ArrayList<Item>();
        ArrayList<Item> ans = new ArrayList<Item>();

        //Get select distincted Item Desc        
        String desc = selected.getItem_desc();
        // logger.log(Level.SEVERE, "INPUT ITEM DESC :" + selected.getItem_desc());
        //Desc convert to words
        Call c = new Call();
        words = c.toWords(desc);
        //find max word count
        int maxword = ((words.size() * 80) / 100);
        // logger.log(Level.SEVERE, "MAX WORD AS PER 80% :" + maxword);
        // logger.log(Level.SEVERE, "GROUP TO BE PASS :" + selected.getItem_no().substring(0, 2));
        //get master Item
        ItemDao idao = new ItemDao();
        deleted = idao.getDelDoneItem();
        que = idao.getItem(selected.getItem_no().substring(0, 2), deleted);

        //for loop for check score
        for (Item q : que) {
            //      logger.log(Level.SEVERE, "In String loop");
            int score = 0;
            for (String w : words) {
                //  logger.log(Level.SEVERE, "In word loop");
                if (c.checkWord(w, q.getItem_desc())) {
                    score++;
                }
            }
            if (score >= maxword) {
                Item a = new Item();
                a.setItem_id(q.getItem_id());
                a.setItem_no(q.getItem_no());
                a.setItem_desc(q.getItem_desc());
                a.setItem_socre(score);
                a.setMin_max_flag(q.getMin_max_flag());
                a.setOnhand_qty(q.getOnhand_qty());
                a.setUom(q.getUom());
                a.setCnt(q.getCnt());
                a.setLast_trx_date(q.getLast_trx_date());
                ans.add(a);
            }
        }
        logger.log(Level.SEVERE, "RELEATED ITEM : " + ans.size());
//        for (Item i : ans) {
//            logger.log(Level.SEVERE, "NO: " + i.getItem_no() + "SCORE: " + i.getItem_socre() + "DESC: " + i.getItem_desc());
//        }
        return ans;
    }

    /**
     *
     * @param desc
     * @return
     */
    @Override
    public ArrayList<Item> getRelatedItem(String desc) {
        //Declare Variable
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Item> que = new ArrayList<Item>();
        ArrayList<Item> deleted = new ArrayList<Item>();
        ArrayList<Item> ans = new ArrayList<Item>();

        //Desc convert to words
        Call c = new Call();
        words = c.toWords(desc);
        //find max word count
        int maxword = ((words.size() * 80) / 100);

        //get master Item
        ItemDao idao = new ItemDao();
        deleted = idao.getDelDoneItem();
        que = idao.getItem();

        //for loop for check score
        for (Item q : que) {
            //      logger.log(Level.SEVERE, "In String loop");
            int score = 0;
            for (String w : words) {
                //  logger.log(Level.SEVERE, "In word loop");
                if (c.checkWord(w, q.getItem_desc())) {
                    score++;
                }
            }
            if (score >= maxword) {
                Item a = new Item();
                a.setItem_id(q.getItem_id());
                a.setItem_no(q.getItem_no());
                a.setItem_desc(q.getItem_desc());
                a.setItem_socre(score);
                a.setMin_max_flag(q.getMin_max_flag());
                a.setOnhand_qty(q.getOnhand_qty());
                a.setUom(q.getUom());
                a.setCnt(q.getCnt());
                a.setLast_trx_date(q.getLast_trx_date());
                ans.add(a);
            }
        }
        logger.log(Level.SEVERE, "RELEATED ITEM : " + ans.size());
//        for (Item i : ans) {
//            logger.log(Level.SEVERE, "NO: " + i.getItem_no() + "SCORE: " + i.getItem_socre() + "DESC: " + i.getItem_desc());
//        }
        return ans;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Item> getItemGrp() {
        ArrayList<Item> grp = new ArrayList<Item>();
        Connection con = DbConnect.GetPAYConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Bean.Sql.get_item_grp);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item b = new Item();
                b.setGrpid(rs.getString(1));
                grp.add(b);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        logger.log(Level.SEVERE, "ITEM GRP : " + grp.size());
//        for (Item i : grp) {
//            logger.log(Level.SEVERE, i.getGrpid());
//        }
        return grp;
    }

    /**
     *
     * @param item_no
     * @param Perameter
     * @return
     */
    @Override
    public String getItemPera(String item_no, String Perameter) {
        String ans = "";
        Connection con = DbConnect.GetPAYConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select distinct " + Perameter
                    + " from XX_SYS_ITM_MST where organization_id = 541"
                    + " and segment1 = ? ";
            ps = con.prepareStatement(sql);
            //  ps.setString(1, Perameter);
            ps.setString(1, item_no);
            rs = ps.executeQuery();
            while (rs.next()) {
                ans = rs.getString(1);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);

            }
        }
     //   logger.log(Level.SEVERE, "ITEM PERAMETER :" + item_no + "," + Perameter + ":" + ans);
        return ans;
    }

    /**
     *
     * @param deleted
     */
    @Override
    public void insDelItem(ArrayList<Item> deleted) {
        Connection con = DbConnect.GetPAYConnection();
        Call c = new Call();
        PreparedStatement ps = null;
        try {
            String sql = "insert into xx_sys_itm_dt (INVENTORY_ITEM_ID,segment1,"
                    + "  description,MATCH_ITEM_ID,GROUP_ID,ACT_DATE) values (?,?,?,?,?,sysdate)";
            ps = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (Item d : deleted) {
                ps.setString(1, d.getItem_id());
                ps.setString(2, d.getItem_no());
                ps.setString(3, d.getItem_desc());
                ps.setString(4, d.getMatch_item_no());
                ps.setInt(5, c.Get_Seq("GROUP_ID", "XX_SYS_ITM_DT"));
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        logger.log(Level.SEVERE, "INSERT DELETED ITEM");
    }

    /**
     *
     * @param inactlist
     */
    public void insDoneItem(ArrayList<Item> inactlist) {
        Connection con = DbConnect.GetPAYConnection();
        Call c = new Call();

        PreparedStatement ps = null;
        try {
            String sql = "insert into xx_sys_itm_done (INVENTORY_ITEM_ID,segment1,"
                    + "  description,MATCH_ITEM_ID,ACT_DATE) values (?,?,?,?,sysdate)";
            ps = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (Item d : inactlist) {
                ps.setString(1, d.getItem_id());
                ps.setString(2, d.getItem_no());
                ps.setString(3, d.getItem_desc());
                ps.setString(4, d.getMatch_item_no());
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        logger.log(Level.SEVERE, "INSERT DONE ITEM : " + inactlist.size());
    }

    /**
     *
     * @param searched
     */
    @Override
    public void insDoneItem(Item searched) {
        Connection con = DbConnect.GetPAYConnection();
        Call c = new Call();

        PreparedStatement ps = null;
        try {
            String sql = "insert into xx_sys_itm_done (INVENTORY_ITEM_ID,segment1,"
                    + "  description,MATCH_ITEM_ID,ACT_DATE) values (?,?,?,?,sysdate)";
            ps = con.prepareStatement(sql);
            con.setAutoCommit(false);
            ps.setString(1, searched.getItem_id());
            ps.setString(2, searched.getItem_no());
            ps.setString(3, searched.getItem_desc());
            ps.setString(4, searched.getMatch_item_no());
            ps.executeQuery();
            con.commit();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        logger.log(Level.SEVERE, "INSERT DONE ITEM :" + searched.getItem_no());
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Item> getDoneItem() {
        ArrayList<Item> itm = new ArrayList<Item>();
        Connection con = DbConnect.GetPAYConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Bean.Sql.get_done_item);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item b = new Item();
                b.setItem_id(rs.getString(1));
                b.setItem_no(rs.getString(2));
                b.setItem_desc(rs.getString(3));
                b.setAct_date(rs.getString(4));
                itm.add(b);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);

            }
        }
        logger.log(Level.SEVERE, "DONE ITEM : " + itm.size());
//        for (Item i : itm) {
//            logger.log(Level.SEVERE, i.getItem_no());
//        }
        return itm;
    }

    /**
     *
     * @param reverted
     */
    @Override
    public void delDoneItem(ArrayList<Item> reverted) {
        Connection con = DbConnect.GetPAYConnection();
        Call c = new Call();
        PreparedStatement ps = null;
        try {
            String sql = "delete from xx_sys_itm_done where segment1 = ?";
            ps = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (Item d : reverted) {
                ps.setString(1, d.getItem_no());
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        logger.log(Level.SEVERE, "REVERT ITEM :" + reverted.size());
    }

}
