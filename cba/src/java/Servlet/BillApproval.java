/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.WFBean;
import Bean.WorkItemBean;
import Dao.BillDao;
import Dao.WFDao;
import Logic.Email;
import Logic.GetMethod;
import Logic.ReportCBA;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class BillApproval extends HttpServlet {

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
            String bill = request.getParameter("bill");
            String uname = session.getAttribute("uid").toString();
            String pono = g.Get_Perameter("PO_NO", "CBA_BILL_MST", "BILL_NO", bill);
            String status = "";

            //initiate workflow
            WFBean wfb = new WFBean();
            wfb.setTRAN_ID(g.Get_Seq("TRAN_ID", "CBA_TRAN_BILL"));
            wfb.setBILL_NO(bill);
            wfb.setPO_NUMBER(pono);
            wfb.setUSER_ID(uname);
            wfb.setACT_ID(Integer.parseInt(request.getParameter("act")));
            wfb.setSTG_C(Integer.parseInt(request.getParameter("sid")));
            wfb.setCMT(request.getParameter("cmt"));
            wfb.setSTG_N(g.Get_NextSTG(wfb.getACT_ID(), wfb.getSTG_C()));
            if (wfb.getSTG_N() == 17) {
                if (request.getParameterMap().containsKey("NUID")) {
                    String s = request.getParameter("NUID");
                    if (s.isEmpty()) {
                        s = "02948";
                    }
                    wfb.setNXT_UID(s);
                }
            } else {
                wfb.setNXT_UID(g.Get_BillNextUID(wfb.getSTG_N(), bill, uname));
            }
            if (wfb.getNXT_UID().isEmpty()) {
                wfb.setNXT_UID("02948");
            }
            WFDao wfdo = new WFDao();
            wfdo.updateTranFlag(wfb, Boolean.FALSE);
            wfdo.addUserAct(wfb);
            wfdo.addTranaction(wfb, Boolean.FALSE);
            if (wfb.getSTG_N() == 12 || wfb.getSTG_N() == 11) {
                wfdo.updateTranFlag(wfb, Boolean.FALSE);
            }
            if (wfb.getSTG_N() == 10) {
                status = "Pending for Update";
            } else if (wfb.getSTG_N() == 11) {
                status = "Rejected";
            } else if (wfb.getSTG_N() == 12) {
                status = "Approved";
            } else {
                status = "Approval Pending";
            }
            Dao.BillDao wod = new BillDao();
            wod.UpdateBillstatus(bill, status);

            //Add quantity if bill rejected
            if (wfb.getACT_ID() == 3) {
                Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "BILL REJECTED QTY UPDATING");
                ArrayList<WorkItemBean> witm = new ArrayList<WorkItemBean>();
                witm = wod.getBillItem(bill);
                for (WorkItemBean woit1 : witm) {
                    woit1.setPO_NO(pono);
                }
                wod.UpdateWORemain(witm, Boolean.TRUE);
            }

            //Generate ReportCBA 
            Logic.ReportCBA r = new ReportCBA();
            r.CreateBillReport(bill, getServletContext().getRealPath("/"), Boolean.FALSE);

            //Send Mail 
            Logic.Email em = new Email();
            try {
                em.Send_Alert_BILL(bill, wfb.getNXT_UID());
            } catch (EmailException ex) {
                Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            } catch (SQLException ex) {
                Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
            }
            
            //page redirect
            response.sendRedirect("msg2.jsp?bill=" + bill + "&nsid=" + wfb.getSTG_N() + "&pono=" + wfb.getPO_NUMBER());
            
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
    private static final Logger LOG = Logger.getLogger(BillApproval.class.getName());
    
}
