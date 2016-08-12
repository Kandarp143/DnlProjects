/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.FileBean;
import Interface.FileInter;
import Logic.DBmanager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class FileDao implements FileInter {

    static final Logger logger = Logger.getLogger(DBmanager.class.getName());
    private Connection con;
    private PreparedStatement ps;
    private String sql;

    public FileDao() {
        con = Logic.DBmanager.GetConnection();
    }

    @Override
    public void getFile(String pono) {
    }

    @Override
    public void addFileRecord(FileBean bean) {
        try {
            sql = "insert into CBA_ATT_MST (ATT_ID ,ATT_TYPE ,PO_NUMBER ,BILL_NO ,"
                    + " F_PATH ,F_NAME ,USER_ID ,ACT_DATE) "
                    + " values (?,?,?,?,?,?,?,sysdate)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, bean.getATT_ID());
            ps.setString(2, bean.getATT_TYPE());
            ps.setString(3, bean.getPO_NO());
            ps.setString(4, bean.getBILL_NO());
            ps.setString(5, bean.getF_PATH());
            ps.setString(6, bean.getF_NAME());
            ps.setString(7, bean.getUSER_ID());
            ps.executeQuery();
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE,
                    "File Insert PO_NUM :{0}ATT_ID :{1}F_PATH :{2}",
                    new Object[]{bean.getPO_NO(), bean.getATT_ID(), bean.getF_PATH()});
        } catch (SQLException ex) {
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } 
    }

}
