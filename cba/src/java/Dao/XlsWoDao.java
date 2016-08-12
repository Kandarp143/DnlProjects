/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.WorkItemBean;
import static Dao.WorkDao.logger;
import Logic.Dropdown;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
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
public class XlsWoDao {

    public static void main(String[] args) {
        XlsWoDao x = new XlsWoDao();
//        x.ReadXLS(new File("D:\\WO-2015-2008.xlsx"));
        x.GenXLS("123", "D:\\");
    }

    public ArrayList<WorkItemBean> ReadXLS(File f) {
        Logger.getLogger(XlsWoDao.class.getName()).log(Level.SEVERE, "READ XLS CALLED");
        FileInputStream fis = null;
        ArrayList<WorkItemBean> itm = new ArrayList<WorkItemBean>();
        try {
            XSSFRow row = null;
            //  fis = new FileInputStream(new File("D:\\CreateWO_Tmp.xlsx"));
            fis = new FileInputStream(f);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet spreadsheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = spreadsheet.iterator();
            int i = 0;
            while (rowIterator.hasNext()) {
//                System.out.println("NEW ROW");
                i++;
                row = (XSSFRow) rowIterator.next();
                if (i == 1) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (cell.getColumnIndex() == 0) {
                        }
                    }
                }
                if (i > 3) {
                    WorkItemBean bean = new WorkItemBean();
                    // System.out.println("ROW" + i);
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
//                        System.out.println("NEW COLUMN");
                        Cell cell = cellIterator.next();
                        if (cell.getColumnIndex() >= 0) {
                            //   System.out.print("COLUMN");
                            if (cell.getColumnIndex() == 0) {
                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    int t = (int) cell.getNumericCellValue();
                                    bean.setITEM_ID(String.valueOf(t));
                                } else {
                                    bean.setITEM_ID(cell.getStringCellValue());
                                }

                            } else if (cell.getColumnIndex() == 1) {
                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    bean.setRATE((float) cell.getNumericCellValue());
                                } else {
                                    bean.setRATE(Float.parseFloat(cell.getStringCellValue()));
                                }
                            } else if (cell.getColumnIndex() == 2) {
                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    int t = (int) cell.getNumericCellValue();
                                    bean.setPLANT(String.valueOf(t));
                                } else {
                                    bean.setPLANT(cell.getStringCellValue());
                                }
                            } else if (cell.getColumnIndex() == 3) {
                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    int t = (int) cell.getNumericCellValue();
                                    bean.setPROJ(String.valueOf(t));
                                } else {
                                    bean.setPROJ(cell.getStringCellValue());
                                }

                            } else if (cell.getColumnIndex() == 4) {
                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    int t = (int) cell.getNumericCellValue();
                                    bean.setTASK(String.valueOf(t));
                                } else {
                                    bean.setTASK(cell.getStringCellValue());
                                }
                            } else if (cell.getColumnIndex() == 5) {
                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    int t = (int) cell.getNumericCellValue();
                                    bean.setCMT(String.valueOf(t));
                                } else {
                                    bean.setCMT(cell.getStringCellValue());
                                }
                            }
                        }
                    }
                    // if (bean.getITEM_ID() != null || !"".equals(bean.getITEM_ID())) {
                    itm.add(bean);
                    // }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XlsWoDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } catch (IOException ex) {
            Logger.getLogger(XlsWoDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(XlsWoDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            }
        }
        return itm;
    }

    public void GenXLS(String orgId, String relpath) {
        try {
            //2.Create WorkBook and Sheet
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet1 = workbook.createSheet("WorkOrder Detail");
            XSSFSheet spreadsheet2 = workbook.createSheet("Plant Master");
            XSSFSheet spreadsheet3 = workbook.createSheet("Project and Task Master");
            //style
            XSSFFont xfont = workbook.createFont();
            xfont.setFontHeight(11);
            xfont.setFontName("Calibri");
            xfont.setBold(true);
            //Set font into style
            CellStyle borderStyle = workbook.createCellStyle();
            borderStyle.setAlignment(CellStyle.ALIGN_CENTER);
            borderStyle.setFont(xfont);
            XSSFCellStyle xstyle = workbook.createCellStyle();
            xstyle.setFont(xfont);

            //SHEET 1 HEADER
            //1row
            XSSFRow row1 = spreadsheet1.createRow(0);
            XSSFCell cell1 = row1.createCell(0);
            cell1.setCellValue("Note : If you are not sure "
                    + "about plant,project,task please "
                    + "leave it blank."
                    + "It can be add when release bill");
            spreadsheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
            //2row
            row1 = spreadsheet1.createRow(1);
            cell1 = row1.createCell(0);
            cell1.setCellValue("Create Work Order Template");
            cell1.setCellStyle(borderStyle);
            spreadsheet1.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
            //3row
            row1 = spreadsheet1.createRow(2);
            cell1 = row1.createCell(0);
            cell1.setCellValue("ITEM_NO");
            cell1.setCellStyle(xstyle);
            cell1 = row1.createCell(1);
            cell1.setCellValue("RATE");
            cell1.setCellStyle(xstyle);
            cell1 = row1.createCell(2);
            cell1.setCellValue("PLANT");
            cell1.setCellStyle(xstyle);
            cell1 = row1.createCell(3);
            cell1.setCellValue("PROJECT");
            cell1.setCellStyle(xstyle);
            cell1 = row1.createCell(4);
            cell1.setCellValue("TASK");
            cell1.setCellStyle(xstyle);
            cell1 = row1.createCell(5);
            cell1.setCellValue("ADDITIONAL NOTE");
            cell1.setCellStyle(xstyle);

            //SHEET 2 HEADER
            //row1
            XSSFRow row2 = spreadsheet2.createRow(0);
            XSSFCell cell2 = row2.createCell(0);
            cell2.setCellValue("Note : Please copy plant code and paste into 1 sheet");
            spreadsheet2.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
            //row2  
            row2 = spreadsheet2.createRow(1);
            cell2 = row2.createCell(0);
            cell2.setCellValue("PLANT_CODE");
            cell2.setCellStyle(xstyle);
            cell2 = row2.createCell(1);
            cell2.setCellValue("PLANT NAME");
            cell2.setCellStyle(xstyle);
            //SHEET 2 DATA
            int i = 2;
            Map<String, String> plant = Dropdown.LoadPlantMst();
            for (Map.Entry<String, String> entry : plant.entrySet()) {
                row2 = spreadsheet2.createRow(i);
                cell2 = row2.createCell(0);
                cell2.setCellValue(entry.getKey());
                cell2 = row2.createCell(1);
                cell2.setCellValue(entry.getValue());
                i++;
            }

            //SHEET 3 HEADER
            //row1
            XSSFRow row3 = spreadsheet3.createRow(0);
            XSSFCell cell3 = row3.createCell(0);
            cell3.setCellValue("Note : Please copy project,task code and paste into 1 sheet");
            spreadsheet3.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
            //row2  
            row3 = spreadsheet3.createRow(1);
            cell3 = row3.createCell(0);
            cell3.setCellValue("PROJECT CODE");
            cell3.setCellStyle(xstyle);
            cell3 = row3.createCell(1);
            cell3.setCellValue("PROJECT NAME");
            cell3.setCellStyle(xstyle);
            cell3 = row3.createCell(2);
            cell3.setCellValue("TASK CODE");
            cell3.setCellStyle(xstyle);
            //SHEET 3 DATA
            int j = 2;
            ArrayList<WorkItemBean> wi1 = Dropdown.LoadProjTaskMst(orgId);
            for (WorkItemBean w : wi1) {
                row3 = spreadsheet3.createRow(j);
                cell3 = row3.createCell(0);
                cell3.setCellValue(w.getPROJ());
                cell3 = row3.createCell(1);
                cell3.setCellValue(w.getPROJ_NAME());
                cell3 = row3.createCell(2);
                cell3.setCellValue(w.getTASK());
                j++;
            }
            //Export to Excel
            // FileOutputStream out = new FileOutputStream(new File("D://" + pono.replace("/", "-") + "_Items" + ".xlsx"));
            //   FileOutputStream out = new FileOutputStream(new File(relpath + "uxls//" + "WO_Creation_Template" + ".xlsx"));
            FileOutputStream out = new FileOutputStream(new File(relpath));
            workbook.write(out);
            out.close();
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "DONE|!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } catch (IOException ex) {
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        }
    }

    public ArrayList<WorkItemBean> getValidatedXls(ArrayList<WorkItemBean> input) {
        logger.log(Level.SEVERE, "GET ITEM DETAIL CALLED ");
        ArrayList<WorkItemBean> op = new ArrayList<WorkItemBean>();
        ArrayList<WorkItemBean> rateop = new ArrayList<WorkItemBean>();
        Connection con = Logic.DBmanager.GetConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String s = "";
        String sql = "";
        for (WorkItemBean b : input) {
            s = s + "'" + b.getITEM_ID() + "',";
        }
        int len = s.length();
        try {
            sql = "SELECT DISTINCT itm.segment1 item_id, itm.description item_desc,"
                    + "                itm.primary_unit_of_measure uom, '0' qty, '0' rate, '0' val,"
                    + "                'Remark'"
                    + "           FROM mtl_system_items itm"
                    + "          WHERE itm.organization_id = 0"
                    + "            AND itm.segment1 IN (" + s.substring(0, (len - 1)) + ")";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkItemBean b = new WorkItemBean();
                b.setITEM_ID(rs.getString(1));
                b.setITEM_DESC(rs.getString(2));
                b.setUOM(rs.getString(3));
                b.setQTY(rs.getFloat(4));
                b.setRATE(rs.getFloat(5));
                b.setVAL(rs.getFloat(6));
                b.setCMT(rs.getString(7));
                op.add(b);
            }
            //plant mst
            Map<String, String> plant = Dropdown.LoadPlantMst();
            ArrayList<WorkItemBean> wi1 = Dropdown.LoadProjTaskMst("123");
            ArrayList<String> proj = new ArrayList<String>();
            ArrayList<String> task = new ArrayList<String>();

            for (WorkItemBean b : wi1) {
                proj.add(b.getPROJ());
                task.add(b.getTASK());
            }
            for (WorkItemBean inp : input) {
                WorkItemBean b = new WorkItemBean();
                for (WorkItemBean opp : op) {
                    if (inp.getITEM_ID().equals(opp.getITEM_ID())) {
                        b.setITEM_ID(opp.getITEM_ID());
                        b.setITEM_DESC(opp.getITEM_DESC());
                        b.setUOM(opp.getUOM());
                        b.setQTY(opp.getQTY());
                        b.setRATE(inp.getRATE());
                        b.setVAL(opp.getQTY() * inp.getRATE());
                        b.setCMT("-");
                        if (plant.containsKey(inp.getPLANT())) {
                            b.setPLANT(inp.getPLANT());
                        } else {
                            b.setPLANT("-");
                        }
                        if (proj.contains(inp.getPROJ())) {
                            b.setPROJ(inp.getPROJ());
                        } else {
                            b.setPROJ("-");
                        }
                        if (task.contains(inp.getTASK())) {
                            b.setTASK(inp.getTASK());
                        } else {
                            b.setTASK("-");
                        }
                        if (inp.getCMT() != null) {
                            b.setCMT(inp.getCMT());
                        } else {
                            b.setCMT("-");
                        }
                        rateop.add(b);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Exception : {0}", ex);

            }
        }
        Logger.getLogger(WorkDao.class.getName()).log(Level.SEVERE, "ITRATE OVER ANS");
        return rateop;
    }

}
