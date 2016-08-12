/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.WorkItemBean;
import Logic.DBmanager;
import Logic.Dropdown;
import Logic.ItemIDCompatator;
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
import java.util.Collections;
import java.util.Iterator;
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
public class XlsBillDao {

    public static void main(String[] args) {
        XlsBillDao x = new XlsBillDao();
//        x.GenFullXLS("WO/2015/2001", "D:\\");
        x.GenXLS("WO/2015/2001", "D:\\");
        // x.ReadXLS(new File("D:\\WO-2015-2012.xlsx"));
    }

    public void GenFullXLS(String pono, String relpath) {
        try {
            //0.Declare Variables for Sheet
            //DB Variable
            //            pono = "WO/2015/2005";
            //XLS Variable
            XSSFSheet spreadsheet;
            XSSFWorkbook workbook;
            XSSFRow row;
            XSSFCell cell;
            XSSFFont xfont = null;
            XSSFCellStyle xstyle = null;

            //1.Get Connection and Fetch Data
            ArrayList< WorkItemBean> wi1 = new ArrayList< WorkItemBean>();
            WorkDao wdao1 = new WorkDao();
            wi1 = wdao1.getWOItem(pono);

            //2.Create WorkBook and Sheet
            workbook = new XSSFWorkbook();
            spreadsheet = workbook.createSheet("WorkOrder Detail");

            //   spreadsheet.protectSheet("kandarpCBA");
            //        spreadsheet.setColumnWidth(0, 255);
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
            cell.setCellValue("WORK ORDER NO : " + pono);
            cell.setCellStyle(borderStyle);
            spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

            //3.Get First Row and Set Headers
            row = spreadsheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellValue("LINE_NO");
            cell.setCellStyle(xstyle);
            cell = row.createCell(1);
            cell.setCellValue("ITEM_ID");
            cell.setCellStyle(xstyle);
            cell = row.createCell(2);
            cell.setCellValue("DESCRIPTION");
            cell.setCellStyle(xstyle);
            cell = row.createCell(3);
            cell.setCellValue("UOM");
            cell.setCellStyle(xstyle);
            cell = row.createCell(4);
            cell.setCellValue("QTY");
            cell.setCellStyle(xstyle);
            cell = row.createCell(5);
            cell.setCellValue("RATE");
            cell.setCellStyle(xstyle);
            cell = row.createCell(6);
            cell.setCellValue("NOTE");
            cell.setCellStyle(xstyle);

            int i = 2;
            for (WorkItemBean w : wi1) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(w.getLINE_NO());
                cell = row.createCell(1);
                cell.setCellValue(w.getITEM_ID());
                cell = row.createCell(2);
                cell.setCellValue(w.getITEM_DESC());
                cell = row.createCell(3);
                cell.setCellValue(w.getUOM());
                cell = row.createCell(4);
                cell.setCellValue(w.getQTY());
                cell = row.createCell(5);
                cell.setCellValue(w.getRATE());
                cell = row.createCell(6);
                cell.setCellValue(w.getCMT());
                i++;
            }

            //Export to Excel
//            FileOutputStream out = new FileOutputStream(new File("D://" + pono.replace("/", "-") + "_Items" + ".xlsx"));
            FileOutputStream out = new FileOutputStream(new File(relpath + "uxls//" + pono.replace("/", "-") + "_Items" + ".xlsx"));
            workbook.write(out);
            out.close();
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "DONE|!");
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "{0}uxls//{1}" + "_Items" + ".xlsx", new Object[]{relpath, pono.replace("/", "-")});
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } catch (IOException ex) {
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        }
    }

    public void GenXLS(String pono, String relpath) {
        try {
            //0.Declare Variables for Sheet
            //DB Variable
            //            pono = "WO/2015/2005";
            String sql;
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            //XLS Variable
            XSSFSheet spreadsheet;
            XSSFWorkbook workbook;
            XSSFRow row;
            XSSFCell cell;
            XSSFFont xfont = null;
            XSSFCellStyle xstyle = null;

            //1.Get Connection and Fetch Data
            con = DBmanager.GetConnection();
            sql = "SELECT DISTINCT cba_wo_item.line_no, cba_wo_item.item_id,\n"
                    + "                mtl_system_items.description, cba_wo_item.uom,\n"
                    + "                cba_wo_item.qty, cba_wo_item.rate, cba_wo_item.cmt,\n"
                    + "                cba_wo_item.plant, cba_wo_item.proj, cba_wo_item.task,"
                    + "                cba_wo_item.po_no\n"
                    + "           FROM cba_wo_item, mtl_system_items\n"
                    + "          WHERE (    (cba_wo_item.item_id = mtl_system_items.segment1)\n"
                    + "                 AND (mtl_system_items.organization_id = 0)\n"
                    + "                 AND (cba_wo_item.po_no = '" + pono + "')\n"
                    + "                )\n"
                    + "       ORDER BY cba_wo_item.line_no";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            //2.Create WorkBook and Sheet
            workbook = new XSSFWorkbook();
            spreadsheet = workbook.createSheet("WorkOrder Detail");

            //spreadsheet.protectSheet("kandarpCBA");
            //spreadsheet.setColumnWidth(0, 255);
            //set header style
            xfont = workbook.createFont();
            xfont.setFontHeight(11);
            xfont.setFontName("Calibri");
            xfont.setBold(true);
            //Set font into style
            CellStyle borderStyle = workbook.createCellStyle();
            borderStyle.setAlignment(CellStyle.ALIGN_CENTER);
            borderStyle.setFont(xfont);
            //        borderStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
            //        borderStyle.setFillPattern(CellStyle.ALIGN_FILL);

            xstyle = workbook.createCellStyle();
            xstyle.setFont(xfont);

            //header
            row = spreadsheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("WORK ORDER NO : " + pono
                    + " Note : If WO is with project information,each bill item should have project and task");
            cell.setCellStyle(borderStyle);
            spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

            //3.Get First Row and Set Headers
            row = spreadsheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellValue("LINE_NO");
            cell.setCellStyle(xstyle);
            cell = row.createCell(1);
            cell.setCellValue("ITEM_ID");
            cell.setCellStyle(xstyle);
            cell = row.createCell(2);
            cell.setCellValue("DESCRIPTION");
            cell.setCellStyle(xstyle);
            cell = row.createCell(3);
            cell.setCellValue("UOM");
            cell.setCellStyle(xstyle);
            cell = row.createCell(4);
            cell.setCellValue("QTY");
            cell.setCellStyle(xstyle);
            cell = row.createCell(5);
            cell.setCellValue("RATE");
            cell.setCellStyle(xstyle);
            cell = row.createCell(6);
            cell.setCellValue("WO NOTE");
            cell.setCellStyle(xstyle);
            cell = row.createCell(7);
            cell.setCellValue("PLANT");
            cell.setCellStyle(xstyle);
            cell = row.createCell(8);
            cell.setCellValue("COST CENTER");
            cell.setCellStyle(xstyle);
            cell = row.createCell(9);
            cell.setCellValue("PROJECT");
            cell.setCellStyle(xstyle);
            cell = row.createCell(10);
            cell.setCellValue("TASK");
            cell.setCellStyle(xstyle);
            cell = row.createCell(11);
            cell.setCellValue("HERE ADD NOTE");
            cell.setCellStyle(xstyle);

            //Itrate or Database data and write
            int i = 2;
            while (rs.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(rs.getString(1));
                cell = row.createCell(1);
                cell.setCellValue(rs.getString(2));
                cell = row.createCell(2);
                cell.setCellValue(rs.getString(3));
                cell = row.createCell(3);
                cell.setCellValue(rs.getString(4));
                cell = row.createCell(4);
                cell.setCellValue(rs.getString(6));
                cell = row.createCell(5);
                cell.setCellValue(rs.getString(5));
                cell = row.createCell(6);
                cell.setCellValue("");
                cell = row.createCell(7);
                cell.setCellValue(rs.getString(7));
                cell = row.createCell(8);
                cell.setCellValue(rs.getString(8));
                cell = row.createCell(9);
                cell.setCellValue(rs.getString(9));
                cell = row.createCell(10);
                cell.setCellValue(rs.getString(10));
                cell = row.createCell(11);
                cell.setCellValue("");
                i++;
            }

            //SECOND WORKSHEET FOR COST CENTER AND PLANT DETAIL
            XSSFRow row2;
            XSSFCell cell2;
            XSSFSheet ccsheet = workbook.createSheet("Cost Center");
            row2 = ccsheet.createRow(0);
            cell2 = row2.createCell(0);
            cell2.setCellValue("Cost Center name and code. Please enter only code in excel");
            cell2.setCellStyle(borderStyle);
            ccsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

            row2 = ccsheet.createRow(1);
            cell2 = row2.createCell(0);
            cell2.setCellValue("CODE");
            cell2.setCellStyle(xstyle);

            cell2 = row2.createCell(1);
            cell2.setCellValue("NAME");
            cell2.setCellStyle(xstyle);

            con = DBmanager.GetConnection();
            sql = "select cc,plant from cba_cc_mst";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            int i2 = 2;
            while (rs.next()) {
                row2 = ccsheet.createRow(i2);
                cell2 = row2.createCell(0);
                cell2.setCellValue(rs.getString(1));
                cell2 = row2.createCell(1);
                cell2.setCellValue(rs.getString(2));
                i2++;
            }

            //THIRD SHEET
            //SECOND WORKSHEET FOR COST CENTER AND PLANT DETAIL
            XSSFRow row3;
            XSSFCell cell3;
            XSSFSheet plantsheet = workbook.createSheet("Plant Center");
            row3 = plantsheet.createRow(0);
            cell3 = row3.createCell(0);
            cell3.setCellValue("Plant Center name and code. Please enter only code in excel");
            cell3.setCellStyle(borderStyle);
            plantsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

            row3 = plantsheet.createRow(1);
            cell3 = row3.createCell(0);
            cell3.setCellValue("CODE");
            cell3.setCellStyle(xstyle);

            cell3 = row3.createCell(1);
            cell3.setCellValue("NAME");
            cell3.setCellStyle(xstyle);

            con = DBmanager.GetConnection();
            sql = "select cc,plant from cba_plant_mst";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            int i3 = 2;
            while (rs.next()) {
                row3 = plantsheet.createRow(i3);
                cell3 = row3.createCell(0);
                cell3.setCellValue(rs.getString(1));
                cell3 = row3.createCell(1);
                cell3.setCellValue(rs.getString(2));
                i3++;
            }
            //SHEET 3 HEADER
            //row1
            XSSFSheet spreadsheet4 = workbook.createSheet("Project And Task");
            XSSFRow row4 = spreadsheet4.createRow(0);
            XSSFCell cell4 = row4.createCell(0);
            cell4.setCellValue("Note : Please copy project,task code and paste into 1 sheet");
            spreadsheet4.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
            //row2  
            row4 = spreadsheet4.createRow(1);
            cell4 = row4.createCell(0);
            cell4.setCellValue("PROJECT CODE");
            cell4.setCellStyle(xstyle);
            cell4 = row4.createCell(1);
            cell4.setCellValue("PROJECT NAME");
            cell4.setCellStyle(xstyle);
            cell4 = row4.createCell(2);
            cell4.setCellValue("TASK CODE");
            cell4.setCellStyle(xstyle);
            //SHEET 3 DATA
            int j = 2;
            ArrayList<WorkItemBean> wi1 = Dropdown.LoadProjTaskMst("123");
            for (WorkItemBean w : wi1) {
                row4 = spreadsheet4.createRow(j);
                cell4 = row4.createCell(0);
                cell4.setCellValue(w.getPROJ());
                cell4 = row4.createCell(1);
                cell4.setCellValue(w.getPROJ_NAME());
                cell4 = row4.createCell(2);
                cell4.setCellValue(w.getTASK());
                j++;
            }

            //Export to Excel
//            FileOutputStream out = new FileOutputStream(new File("D://" + pono.replace("/", "-") + ".xlsx"));
            FileOutputStream out = new FileOutputStream(new File(relpath + "xls//" + pono.replace("/", "-") + ".xlsx"));
            workbook.write(out);
            out.close();

            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "DONE|!");
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "{0}xls//{1}.xlsx", new Object[]{relpath, pono.replace("/", "-")});
        } catch (SQLException ex) {
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } catch (IOException ex) {
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        }
    }

    public ArrayList<WorkItemBean> ReadXLS(File f) {
        WorkDao wdao = new WorkDao();
        FileInputStream fis = null;
        ArrayList< WorkItemBean> itm = new ArrayList< WorkItemBean>();
        try {
            String pono = null;
            XSSFRow row = null;
            //fis = new FileInputStream(new File("D:\\WO-2015-2008.xlsx"));
            fis = new FileInputStream(f);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet spreadsheet = workbook.getSheetAt(0);
            Iterator< Row> rowIterator = spreadsheet.iterator();
            int i = 0;
            while (rowIterator.hasNext()) {
                i++;
                row = (XSSFRow) rowIterator.next();
                if (i == 1) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (cell.getColumnIndex() == 0) {
                            pono = cell.getStringCellValue();
                            pono = pono.substring((pono.indexOf(":") + 1));
                        }
                    }
                }
                if (i > 2) {
                    WorkItemBean bean = new WorkItemBean();
                    //                    System.out.println("ROW" + i);
                    Iterator< Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();
                        if (cell.getColumnIndex() >= 0) {
                            //System.out.print("COLUMN");
                            if (cell.getColumnIndex() == 1) {
                                //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + " \t ");
                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    bean.setITEM_ID(String.valueOf(cell.getNumericCellValue()));
                                } else {
                                    bean.setITEM_ID(cell.getStringCellValue());
                                }
                            } else if (cell.getColumnIndex() == 2) {
                                //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + " \t ");
                                bean.setITEM_DESC(cell.getStringCellValue());
                            } else if (cell.getColumnIndex() == 3) {
                                //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + " \t ");
                                bean.setUOM(cell.getStringCellValue());
                            } else if (cell.getColumnIndex() == 4) {
                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    bean.setQTY((float) cell.getNumericCellValue());
                                    //System.out.print(cell.getColumnIndex() + ":" + cell.getNumericCellValue() + " \t ");
                                } else {
                                    bean.setQTY(Float.parseFloat(cell.getStringCellValue()));
                                    //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + " \t ");
                                }
                            } else if (cell.getColumnIndex() == 5) {
                                //System.out.print(cell.getColumnIndex() + ":" + cell.getNumericCellValue() + " \t ");
                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    bean.setRATE((float) cell.getNumericCellValue());
                                } else {
                                    bean.setRATE(Float.parseFloat(cell.getStringCellValue()));
                                }

                            } else if (cell.getColumnIndex() == 7) {

                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    bean.setPLANT(Integer.toString((int) cell.getNumericCellValue()));
                                    //System.out.print(cell.getColumnIndex() + ":" + cell.getNumericCellValue() + " \t ");
                                } else {
                                    bean.setPLANT(cell.getStringCellValue());
                                    //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + " \t ");
                                }
                            } else if (cell.getColumnIndex() == 8) {

                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    bean.setCC(Integer.toString((int) cell.getNumericCellValue()));
                                    //System.out.print(cell.getColumnIndex() + ":" + cell.getNumericCellValue() + " \t ");
                                } else {
                                    bean.setCC(cell.getStringCellValue());
                                    //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + " \t ");
                                }
                            } else if (cell.getColumnIndex() == 9) {
                                if (wdao.isProjWO(pono)) {
                                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                        bean.setPROJ(Integer.toString((int) cell.getNumericCellValue()));
                                        //System.out.print(cell.getColumnIndex() + ":" + cell.getNumericCellValue() + " \t ");
                                    } else {
                                        bean.setPROJ(cell.getStringCellValue());
                                        //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + " \t ");
                                    }
                                } else {
                                    bean.setPROJ("-");

                                }
                            } else if (cell.getColumnIndex() == 10) {
                                if (wdao.isProjWO(pono)) {
                                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                        bean.setTASK(Integer.toString((int) cell.getNumericCellValue()));
                                        //System.out.print(cell.getColumnIndex() + ":" + cell.getNumericCellValue() + " \t ");
                                    } else {
                                        bean.setTASK(cell.getStringCellValue());
                                        //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + " \t ");
                                    }
                                } else {
                                    bean.setTASK("-");

                                }
                            } else if (cell.getColumnIndex() == 11) {

                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    bean.setCMT(Integer.toString((int) cell.getNumericCellValue()));
                                    //System.out.print(cell.getColumnIndex() + ":" + cell.getNumericCellValue() + " \t ");
                                } else {
                                    bean.setCMT(cell.getStringCellValue());
                                    //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + " \t ");
                                }
                            } else {

                            }
                        }
                    }
                    //System.out.println();
                    itm.add(bean);
                }
            }
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "READ DONE !!");
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } catch (IOException ex) {
            Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            }

        }
        for (WorkItemBean i : itm) {
            if (i.getQTY() != 0) {
                Logger.getLogger(XlsBillDao.class.getName()).log(Level.INFO, "ITEM_ID : {0} QTY:{1} PLANT:{2} CC:{3}", new Object[]{i.getITEM_ID(), i.getQTY(), i.getPLANT(), i.getCC()});
            }
        }
        return itm;
    }

    public boolean ValidateXLS(ArrayList<WorkItemBean> org, ArrayList<WorkItemBean> xls) {
        boolean ans = true;
        Collections.sort(org, new ItemIDCompatator());
        Collections.sort(xls, new ItemIDCompatator());
        if (org.size() == xls.size()) {
            for (int i = 0; i < org.size(); i++) {
                if (!org.get(i).getITEM_ID().equals(xls.get(i).getITEM_ID())) {
                    Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "ITEM_ID Altered : ORG {0} XLS :{1}", new Object[]{org.get(i).getITEM_ID(), xls.get(i).getITEM_ID()});
                    ans = false;
                }
                if (!org.get(i).getUOM().equals(xls.get(i).getUOM())) {
                    Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "UOM Altered");
                    ans = false;
                }
                if (org.get(i).getRATE() != xls.get(i).getRATE()) {
                    Logger.getLogger(XlsBillDao.class.getName()).log(Level.SEVERE, "RATE Altered");
                    ans = false;
                }
            }
        } else {
            ans = false;
        }

        return ans;
    }

}
