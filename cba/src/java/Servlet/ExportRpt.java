/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Logic.RStoXL;
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

            //generate Report From Database
            Connection con = Logic.DBmanager.GetConnection();
            Connection appcon = Logic.DBmanager.GetConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String file_name = "CBA" + request.getParameter("name");
            //based on report parameter it will take sql
            if (request.getParameter("rpt").equalsIgnoreCase("1")) {
                Logger.getLogger(ExportRpt.class.getName()).log(Level.SEVERE, "From date:{0}", request.getParameter("fd"));
                Logger.getLogger(ExportRpt.class.getName()).log(Level.SEVERE, "To date:{0}", request.getParameter("td"));
                if (!"".equals(request.getParameter("fd")) && !"".equals(request.getParameter("td"))) {
                    ps = con.prepareStatement(Logic.RptSQL.SQL1_P);
                    ps.setString(1, request.getParameter("fd"));
                    ps.setString(2, request.getParameter("td"));
                    Logger.getLogger(ExportRpt.class.getName()).log(Level.SEVERE, Logic.RptSQL.SQL1_P);
                } else {
                    Logger.getLogger(ExportRpt.class.getName()).log(Level.SEVERE, "All");
                    ps = con.prepareStatement(Logic.RptSQL.SQL1);
                }
            } else if (request.getParameter("rpt").equalsIgnoreCase("2")) {
                ps = con.prepareStatement(Logic.RptSQL.SQL2);
            } else if (request.getParameter("rpt").equalsIgnoreCase("3")) {
                ps = con.prepareStatement(Logic.RptSQL.SQL3);
            } else if (request.getParameter("rpt").equalsIgnoreCase("4")) {
                ps = appcon.prepareStatement(Logic.RptSQL.SQL4);
            } else if (request.getParameter("rpt").equalsIgnoreCase("5")) {
                ps = appcon.prepareStatement(Logic.RptSQL.SQL5);
            } else if (request.getParameter("rpt").equalsIgnoreCase("6")) {
                ps = appcon.prepareStatement(Logic.RptSQL.SQL6);
            } else if (request.getParameter("rpt").equalsIgnoreCase("7")) {
                ps = appcon.prepareStatement(Logic.RptSQL.SQL7);
            } else if (request.getParameter("rpt").equalsIgnoreCase("8")) {
                ps = appcon.prepareStatement(Logic.RptSQL.SQL8);
            } else if (request.getParameter("rpt").equalsIgnoreCase("9")) {
                ps = appcon.prepareStatement(Logic.RptSQL.SQL9);
            }
            //execute sql store into resultset
            rs = ps.executeQuery();
            
            //Generate Excel From ResultSet
            RStoXL x = new RStoXL();
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
    private static final Logger LOG = Logger.getLogger(ExportRpt.class.getName());

}
