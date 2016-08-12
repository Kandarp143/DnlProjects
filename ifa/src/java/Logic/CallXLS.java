/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.File;
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
public class CallXLS {

    static final Logger logger = Logger.getLogger(CallXLS.class.getName());

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        CallXLS s = new CallXLS();
        s.GenXLS("C:\\Users\\02948\\Documents\\NetBeansProjects\\ifa\\web\\");
    }

    /**
     *
     * @param selected
     * @param relpath
     * @throws SQLException
     * @throws Exception
     */
    public void GenXLS(String selected,String relpath) throws SQLException, Exception {
        Connection con = DbConnect.GetPAYConnection();;
        String db_path = "xls\\";

        relpath = relpath + db_path + "DEL_ITEM_LIST.xls";
        logger.log(Level.INFO, "PATH" + relpath);
        String sql = " select distinct a.INVENTORY_ITEM_ID,b.segment1,a.description,"
                + " b.MATCH_ITEM_ID,b.GROUP_ID,to_char(b.act_date,'DD-MON-YYYY') "
                + " from XX_SYS_ITM_DT b ,XX_SYS_ITM_MST a"
                + " where a.segment1 = b.segment1 and a.organization_id = 541";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        ResultSetToExcel resultSetToExcel = new ResultSetToExcel(resultSet,
                new ResultSetToExcel.FormatType[]{
                    ResultSetToExcel.FormatType.TEXT,
                    ResultSetToExcel.FormatType.TEXT,
                    ResultSetToExcel.FormatType.TEXT,
                    ResultSetToExcel.FormatType.TEXT,
                    ResultSetToExcel.FormatType.TEXT,
                    ResultSetToExcel.FormatType.TEXT
                }, "Inactive Item");
        resultSetToExcel.generate(new File(relpath));
        System.out.print(relpath);
    }

    /**
     *
     * @param relpath
     * @throws SQLException
     * @throws Exception
     */
    public void GenXLS(String relpath) throws SQLException, Exception {
        Connection con = DbConnect.GetPAYConnection();
        String db_path = "xls\\";

        relpath = relpath + db_path + "DEL_ITEM_LIST.xls";
        logger.log(Level.INFO, "PATH" + relpath);
        String sql = " select distinct a.INVENTORY_ITEM_ID,b.segment1,a.description,"
                + " b.MATCH_ITEM_ID,b.GROUP_ID,to_char(b.act_date,'DD-MON-YYYY') "
                + " from XX_SYS_ITM_DT b ,XX_SYS_ITM_MST a"
                + " where a.segment1 = b.segment1 and a.organization_id = 541";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        ResultSetToExcel resultSetToExcel = new ResultSetToExcel(resultSet,
                new ResultSetToExcel.FormatType[]{
                    ResultSetToExcel.FormatType.TEXT,
                    ResultSetToExcel.FormatType.TEXT,
                    ResultSetToExcel.FormatType.TEXT,
                    ResultSetToExcel.FormatType.TEXT,
                    ResultSetToExcel.FormatType.TEXT,
                    ResultSetToExcel.FormatType.TEXT
                }, "Inactive Item");
        resultSetToExcel.generate(new File(relpath));
        System.out.print(relpath);
    }
}
