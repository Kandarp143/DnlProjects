/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kp.beans.wf.pojo.MocWfTran;
import kp.dao.wf.TranDao;
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
public class ExportRpt extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Logger.getLogger(ExportRpt.class.getName()).log(Level.SEVERE, "accRole :" + request.getParameter("accRole"));
        Logger.getLogger(ExportRpt.class.getName()).log(Level.SEVERE, "Unit :" + request.getParameter("unit"));
        ArrayList<MocWfTran> Mocstatus = new ArrayList<>();
        TranDao tdao = new TranDao();
        Mocstatus = tdao.getMocStatusList(request.getParameter("accRole"),
                request.getParameter("unit"),
                request.getParameter("user"));

        //Developing Metadata
        String rptName = "MOC Status Excel Report";
        ArrayList<String> colLabel = new ArrayList<>();
        colLabel.add("Case Id");
        colLabel.add("Moc NO");
        colLabel.add("Moc Title");
        colLabel.add("Moc Status");
        colLabel.add("Creation Date");
        colLabel.add("Owner's Name");
        colLabel.add("Unit");
        colLabel.add("Plant");
        colLabel.add("Current Stage");
        colLabel.add("Pending At");

        //Starting EXCEL Creating
        //XLS Variable
        XSSFSheet spreadsheet;
        XSSFWorkbook workbook;
        XSSFRow row;
        XSSFCell cell;
        XSSFFont xfont = null;
        XSSFCellStyle xstyle = null;

        //2.Create WorkBook and Sheet
        workbook = new XSSFWorkbook();
        spreadsheet = workbook.createSheet(rptName);

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
        cell.setCellValue(rptName);
        cell.setCellStyle(borderStyle);
        spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colLabel.size() - 1));

        //3.Get First Row and Set Headers
        row = spreadsheet.createRow(1);

        for (int i = 0; i < colLabel.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(colLabel.get(i));
            cell.setCellStyle(xstyle);
        }

        //Itrate or Database data and write
        int i = 2;
        for (MocWfTran bean : Mocstatus) {
            row = spreadsheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(bean.getCaseId());
            cell = row.createCell(1);
            cell.setCellValue(bean.getMocNo());
            cell = row.createCell(2);
            cell.setCellValue(bean.getCaseName());
            cell = row.createCell(3);
            cell.setCellValue(bean.getMocStatus());
            cell = row.createCell(4);
            cell.setCellValue(bean.getCrDateString());
            cell = row.createCell(5);
            cell.setCellValue(bean.getCaseOwnerName());
            cell = row.createCell(6);
            cell.setCellValue(bean.getUnitId());
            cell = row.createCell(7);
            cell.setCellValue(bean.getPlantId());
            cell = row.createCell(8);
            cell.setCellValue(bean.getStgNname());
            cell = row.createCell(9);
            cell.setCellValue(bean.getUserNname());
            i++;
        }

        //Export to Excel
        String file_name = "MocStatus";
        String path = getServletContext().getRealPath("/");
        String full_path = path + "/report/" + file_name + ".xlsx";
//        FileOutputStream out = new FileOutputStream(new File("D://" + file_name + ".xlsx"));
        FileOutputStream out = new FileOutputStream(new File(full_path));
        workbook.write(out);

        //Download code 
        // reads input file from an absolute path
        File downloadFile = new File(full_path);
        OutputStream outStream;
        // obtains ServletContext
        try (FileInputStream inStream = new FileInputStream(downloadFile)) {
            //obtains ServletContext
            ServletContext context = getServletContext();
            // gets MIME type of the file
            String mimeType = context.getMimeType(full_path);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }   // modifies response
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());
            // forces download
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            // obtains response's output stream
            outStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        }
        outStream.close();
//        response.sendRedirect("mocstatus.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
