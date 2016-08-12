/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author 02948
 */
public class Xls {

    public void genXLS(ResultSet rs, String Rpt_name, String path) {
        try {
            //RS METE DATA
            ResultSetMetaData rsmd = rs.getMetaData();
            int col_count = rsmd.getColumnCount();
            ArrayList<String> col_name = new ArrayList<>();
            for (int i = 1; i <= col_count; i++) {
                col_name.add(rsmd.getColumnLabel(i));
            }

            //XLS Variable
            XSSFSheet spreadsheet;
            XSSFWorkbook workbook;
            XSSFRow row;
            XSSFCell cell;
            XSSFFont xfont = null;
            XSSFCellStyle xstyle = null;

            //2.Create WorkBook and Sheet
            workbook = new XSSFWorkbook();
            spreadsheet = workbook.createSheet(Rpt_name);

            //set header style
            xfont = workbook.createFont();
            xfont.setFontHeight(11);
            xfont.setFontName("Calibri");
            xfont.setBold(true);

            //Set font into style
            CellStyle borderStyle = workbook.createCellStyle();
            borderStyle.setAlignment(CellStyle.ALIGN_CENTER);
            borderStyle.setFont(xfont);
            xstyle = workbook.createCellStyle();
            xstyle.setFont(xfont);

            //header
            row = spreadsheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue(Rpt_name);
            cell.setCellStyle(borderStyle);
            spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, col_count - 1));

            //3.Get First Row and Set Headers
            row = spreadsheet.createRow(1);

            for (int i = 0; i < col_count; i++) {
                cell = row.createCell(i);
                cell.setCellValue(col_name.get(i));
                cell.setCellStyle(xstyle);
            }

            //Itrate or Database data and write
            int i = 2;
            while (rs.next()) {
                row = spreadsheet.createRow(i);
                for (int j = 1; j <= col_count; j++) {
                    cell = row.createCell(j - 1);
                    cell.setCellValue(rs.getString(j));
                }
                i++;
            }

            //Export to Excel
            // FileOutputStream out = new FileOutputStream(new File("D://" + Rpt_name + ".xlsx"));
            FileOutputStream out = new FileOutputStream(new File(path));
            workbook.write(out);

            Logger.getLogger(Xls.class.getName()).log(Level.SEVERE, "DONE|!");
            Logger.getLogger(Xls.class.getName()).log(Level.SEVERE, "");
        } catch (Exception ex) {
            Logger.getLogger(Xls.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        }
    }

}
