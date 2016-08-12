/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Bean.FileBean;
import Dao.FileDao;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author 02948
 */
public class ReportCBA {

    static final Logger logger = Logger.getLogger(ReportCBA.class.getName());
    public static ByteArrayOutputStream outStream;
    public static JasperReport jasperReport;
    public static JasperPrint jasperPrint;

    public void CreateReport(String pono, String rel_path, Boolean InsReacord) {
        Connection con = DBmanager.GetConnection();
        try {
            String db_path = "report";
            String abslute_path = rel_path + db_path;
            String fileName = "";
            outStream = new ByteArrayOutputStream();
            Map parameters = new HashMap();
            parameters.put("pono", pono);
            JasperDesign jasperDesign = JRXmlLoader.load(abslute_path + "\\Jrxml\\WoReport.jrxml");
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
            fileName = "Report-" + pono.substring(0, 2) + "-" + pono.substring(3, 7) + "-" + pono.substring(8);
            abslute_path = abslute_path + "\\" + fileName + ".pdf";
            logger.log(Level.SEVERE, "Report Created :{0}", abslute_path);
            JasperExportManager.exportReportToPdfFile(jasperPrint, abslute_path);
            db_path = "report\\" + fileName + ".pdf";
            if (!InsReacord) {
                String sql = "select * from cba_att_mst where PO_NUMBER='" + pono + "'";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    logger.log(Level.SEVERE, "Report Record not found");
                    //DATABASE TRANACTION
                    Logic.GetMethod g = new GetMethod();
                    FileBean fb = new FileBean();
                    fb.setATT_ID(g.Get_Seq("ATT_ID", "CBA_ATT_MST"));
                    fb.setATT_TYPE("RPT");
                    fb.setPO_NO(pono);
                    fb.setBILL_NO("0");
                    fb.setF_PATH(db_path);
                    fb.setF_NAME(fileName);
                    fb.setUSER_ID("02948");
                    FileDao fdo = new FileDao();
                    fdo.addFileRecord(fb);
                }

            } else {
                //DATABASE TRANACTION
                Logic.GetMethod g = new GetMethod();
                FileBean fb = new FileBean();
                fb.setATT_ID(g.Get_Seq("ATT_ID", "CBA_ATT_MST"));
                fb.setATT_TYPE("RPT");
                fb.setPO_NO(pono);
                fb.setBILL_NO("0");
                fb.setF_PATH(db_path);
                fb.setF_NAME(fileName);
                fb.setUSER_ID("02948");
                FileDao fdo = new FileDao();
                fdo.addFileRecord(fb);
            }
        } catch (JRException ex) {
            logger.log(Level.SEVERE, "Exception : {0}", ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportCBA.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);
            }

        }
    }

    public void CreateBillReport(String bill, String rel_path, Boolean InsReacord) {
        Connection con = DBmanager.GetConnection();
        try {
            String db_path = "report";
            String abslute_path = rel_path + db_path;
            String fileName = "";
            outStream = new ByteArrayOutputStream();
            Map parameters = new HashMap();
            parameters.put("bill", bill);
            JasperDesign jasperDesign = JRXmlLoader.load(abslute_path + "\\Jrxml\\BillReport.jrxml");
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
            fileName = "CBA_BILL_Report_" + bill;
            abslute_path = abslute_path + "\\" + fileName + ".pdf";
            logger.log(Level.SEVERE, "Report Created :{0}", abslute_path);
            JasperExportManager.exportReportToPdfFile(jasperPrint, abslute_path);
            db_path = "report\\" + fileName + ".pdf";
            if (InsReacord) {
                //DATABASE TRANACTION
                Logic.GetMethod g = new GetMethod();
                FileBean fb = new FileBean();
                fb.setATT_ID(g.Get_Seq("ATT_ID", "CBA_ATT_MST"));
                fb.setATT_TYPE("RPT");
                fb.setPO_NO("0");
                fb.setBILL_NO(bill);
                fb.setF_PATH(db_path);
                fb.setF_NAME(fileName);
//            fb.setUSER_ID("02948");
                FileDao fdo = new FileDao();
                fdo.addFileRecord(fb);
            }
        } catch (JRException ex) {
            logger.log(Level.SEVERE, "Exception : {0}", ex);
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);
            }

        }

    }
}
