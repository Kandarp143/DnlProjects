/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.MitInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class MitDao implements MitInter {

    static final Logger logger = Logger.getLogger(ItemDao.class.getName());
    private String sql;

    @Override
    public String genMIT_NO(String loc) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String loc_id = "";
        String mitno = "";
        int seq = 0;
        try {
            sql = "SELECT   loc_id, cur_seq + 1, CONCAT (CONCAT(loc_id,'-'), LPAD (MAX (cur_seq) + 1, 5, 0))"
                    + "    FROM mmt_loc_mst"
                    + "   WHERE loc_name = '" + loc + "'"
                    + "GROUP BY loc_id, cur_seq";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                loc_id = rs.getString(1);
                seq = rs.getInt(2);
                mitno = rs.getString(3);
            }
            new MitDao().updateMIT_SEQ(seq, loc_id);
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "MIT_NO :" + mitno);
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
        return mitno;

    }

    @Override
    public void updateMIT_SEQ(int seq, String loc_id) {
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        try {
            sql = "UPDATE MMT_LOC_MST SET CUR_SEQ = ? WHERE LOC_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, seq);
            ps.setString(2, loc_id);
            ps.executeUpdate();
            con.commit();
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, "SEQ UPDATED ");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Exception : " + ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : " + ex);

            }
        }

    }

}
