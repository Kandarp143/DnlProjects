/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Logic.Xls;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        try {
            response.setContentType("text/html;charset=UTF-8");

            //1.Generate Report From Database
            Connection con = Logic.DBmanager.GetConnection();
            Connection appcon = Logic.DBmanager.GetProdAppsConnection();

            PreparedStatement ps = null;
            ResultSet rs = null;
            String file_name = "Report";
            if (request.getParameter("rpt").equalsIgnoreCase("1")) {
                ps = con.prepareStatement(Logic.RptSQL.SQL1);
                file_name = "Report1";
            } else if (request.getParameter("rpt").equalsIgnoreCase("2")) {
                ps = con.prepareStatement(Logic.RptSQL.SQL2);
                file_name = "Report2";

            } else if (request.getParameter("rpt").equalsIgnoreCase("3")) {
                ps = con.prepareStatement(Logic.RptSQL.SQL3);
                file_name = "Report3";

            } else if (request.getParameter("rpt").equalsIgnoreCase("4")) {
                ps = appcon.prepareStatement(Logic.RptSQL.SQL4);
                file_name = "Report-OutStanding (Current Period)";
            } else if (request.getParameter("rpt").equalsIgnoreCase("4-1")) {
                ps = appcon.prepareStatement(Logic.RptSQL.SQL4_1);
                file_name = "Report-OutStanding ";
            } else if (request.getParameter("rpt").equalsIgnoreCase("5")) {
                Connection prod = Logic.DBmanager.GetProdAppsConnection();
                ps = prod.prepareStatement(Logic.RptSQL.SQL6);
                file_name = "Report-Collection Summary";
            } else if (request.getParameter("rpt").equalsIgnoreCase("6")) {
                Connection prod = Logic.DBmanager.GetProdAppsConnection();
                ps = prod.prepareStatement(Logic.RptSQL.SQL7);
                file_name = "Report-Collection (Inv vs Payment)";
            }
            rs = ps.executeQuery();

            //2.Generate Excel From ResultSet
            Xls x = new Xls();
            String path = getServletContext().getRealPath("/");
            String full_path = path + "/rpt/" + file_name + ".xlsx";
            x.genXLS(rs, file_name, full_path);

            // reads input file from an absolute path
            File downloadFile = new File(full_path);
            FileInputStream inStream = new FileInputStream(downloadFile);

            // obtains ServletContext
            ServletContext context = getServletContext();

            // gets MIME type of the file
            String mimeType = context.getMimeType(full_path);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);

            // modifies response
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());

            // forces download
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            // obtains response's output stream
            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
            response.sendRedirect("report.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(ExportRpt.class.getName()).log(Level.SEVERE, null, ex);
        }
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
