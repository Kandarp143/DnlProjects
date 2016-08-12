/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.WFBean;
import Dao.WFDao;
import Dao.WorkDao;
import Logic.Email;
import Logic.GetMethod;
import Logic.ReportCBA;

import Dao.XlsBillDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author 02948
 */
public class WOApproval extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        try {
            //assign parameter to variable
            Logic.GetMethod g = new GetMethod();
            String ponum = request.getParameter("pono");
            String uname = session.getAttribute("uid").toString();
            String status = "";

            //initiate workflow
            WFBean wfb = new WFBean();
            wfb.setTRAN_ID(g.Get_Seq("TRAN_ID", "CBA_TRAN_WO"));
            wfb.setUSER_ID(uname);
            wfb.setPO_NUMBER(ponum);
            wfb.setACT_ID(Integer.parseInt(request.getParameter("act")));
            wfb.setSTG_C(Integer.parseInt(request.getParameter("sid")));
            wfb.setCMT(request.getParameter("cmt"));
            wfb.setSTG_N(g.Get_NextSTG(wfb.getACT_ID(), wfb.getSTG_C()));
            wfb.setNXT_UID(g.Get_NextUID(wfb.getSTG_N(), ponum, uname));
            WFDao wfdo = new WFDao();
            wfdo.updateTranFlag(wfb, Boolean.TRUE);
            wfdo.addUserAct(wfb);
            wfdo.addTranaction(wfb, Boolean.TRUE);
            if (wfb.getSTG_N() == 13 || wfb.getSTG_N() == 5) {
                wfdo.updateTranFlag(wfb, Boolean.TRUE);
            }
            if (wfb.getSTG_N() == 4) {
                status = "Pending for Update";
            } else if (wfb.getSTG_N() == 5) {
                status = "Rejected";
            } else if (wfb.getSTG_N() == 13) {

                status = "Approved";
            } else {
                status = "Approval Pending";
            }
            Dao.WorkDao wod = new WorkDao();
            wod.UpdateWOstatus(ponum, status);

            //Generate ReportCBA 
            Logic.ReportCBA r = new ReportCBA();
            r.CreateReport(ponum, getServletContext().getRealPath("/"), Boolean.FALSE);

            //Send Mail 
            Logic.Email em = new Email();
            try {
                em.Send_Alert(wfb.getPO_NUMBER(), wfb.getNXT_UID());
            } catch (EmailException ex) {
                Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            } catch (SQLException ex) {
                Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            }

            //Generate XLS 
            if (wfb.getSTG_N() == 13) {
                Dao.XlsBillDao xls = new XlsBillDao();
                xls.GenXLS(request.getParameter("pono"), getServletContext().getRealPath("/"));
            }
            //page redirect
            response.sendRedirect("msg.jsp?pono=" + ponum + "&nsid=" + wfb.getSTG_N());

        } finally {
            out.close();
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
    private static final Logger LOG = Logger.getLogger(WOApproval.class.getName());

}
