/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.WFBean;
import Bean.WorkBean;
import Bean.WorkItemBean;
import Bean.WorkTaxBean;
import Bean.WorkTermBean;
import Dao.BillDao;
import Dao.WFDao;
import Logic.Email;
import Logic.GetMethod;
import Logic.ReportCBA;
import java.io.IOException;
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
public class UpdateBill extends HttpServlet {

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
        //assign parameter to variable and initiate objects
        HttpSession session = request.getSession(true);
        float val, tax, totaltax;
        val = 0;
        tax = 0;
        totaltax = 0;
        WorkBean w = new WorkBean();
        ArrayList<WorkItemBean> witm = new ArrayList<WorkItemBean>();
        ArrayList<WorkTaxBean> wtax = new ArrayList<WorkTaxBean>();
        WorkTermBean wterm = new WorkTermBean();
        GetMethod g = new GetMethod();

        //initiate and set bill master data
        String uname = session.getAttribute("uid").toString();
        w.setBILL_NO(request.getParameter("bno"));
        w.setBILL_ID(request.getParameter("bid"));
        w.setPO_NO(request.getParameter("a"));
        w.setPO_TITLE(request.getParameter("pot"));
        w.setBILL_DATE(request.getParameter("k"));
        w.setUSER_ID(uname);
        w.setSTATUS("Updated");
        w.setWO_DESC(request.getParameter("cmt"));
        //check if request contains line of item and tax or not
        int irow, trow;
        if (request.getParameter("itemrows") == null || "".equals(request.getParameter("itemrows"))) {
            irow = 0;
        } else {
            irow = Integer.parseInt(request.getParameter("itemrows"));
        }
        if (request.getParameter("txrows") == null || "".equals(request.getParameter("txrows"))) {
            trow = 0;
        } else {
            trow = Integer.parseInt(request.getParameter("txrows"));
        }
        //set bill item level
        if (irow > 0) {
            for (int i = 1; i <= irow; i++) {
                if (request.getParameter("A" + i) != null) {
                    WorkItemBean b = new WorkItemBean();
                    b.setPO_NO(w.getPO_NO());
                    b.setLINE_NO(Integer.parseInt(g.Get_Perameter("LINE_NO", "CBA_WO_ITEM", "ITEM_ID", request.getParameter("A" + i))));
                    b.setBILL_NO(w.getBILL_NO());
                    b.setITEM_ID(request.getParameter("A" + i));
                    b.setUOM(request.getParameter("B" + i));
                    b.setQTY(Float.parseFloat(request.getParameter("C" + i)));
                    b.setRATE(Float.parseFloat(request.getParameter("D" + i)));
                    b.setCMT((request.getParameter("F" + i) != null) ? request.getParameter("F" + i) : "-");
                    b.setPLANT(request.getParameter("G" + i));
                    b.setCC(request.getParameter("H" + i));
                    b.setPROJ(request.getParameter("I" + i));
                    b.setTASK(request.getParameter("J" + i));
                    b.setVAL(b.getQTY() * b.getRATE());
                    witm.add(b);
                    val += b.getQTY() * b.getRATE();
                }
            }
        }
        //set tax in bill
        if (trow > 0) {
            for (int i = 1; i <= trow; i++) {
                if (request.getParameter("T" + i) != null) {
                    WorkTaxBean t = new WorkTaxBean();
                    t.setPO_NO(w.getPO_NO());
                    t.setBILL_NO(w.getBILL_NO());
                    t.setTAX_TYPE(request.getParameter("T" + i));
                    t.setTAX_VAL(Float.parseFloat(request.getParameter("R" + i)));
                    wtax.add(t);
                    tax += Float.parseFloat(request.getParameter("R" + i));
                }
            }
        }

        totaltax = (val * (tax / 100));
        w.setVAL(val);
        w.setTOTAL_TAX(totaltax);
        w.setTOTAL_VAL(val + totaltax);

        //add payment term in bill
        wterm.setPO_NO(w.getPO_NO());
        wterm.setBILL_NO(w.getBILL_NO());
        wterm.setTERM_ID(request.getParameter("PT"));
        wterm.setTERM_DESC(request.getParameter("PT"));

        BillDao wd = new BillDao();
        //add remain qty for updated qty
        ArrayList<WorkItemBean> witmain = new ArrayList<WorkItemBean>();
        witmain = wd.getBillItem(w.getBILL_NO());
        ArrayList<WorkItemBean> anss = new ArrayList<WorkItemBean>();
        for (WorkItemBean main : witmain) {
            for (WorkItemBean current : witm) {
                WorkItemBean ans = new WorkItemBean();
                ans.setBILL_NO(current.getBILL_NO());
                ans.setPO_NO(current.getPO_NO());
                ans.setITEM_ID(current.getITEM_ID());
                ans.setRATE(current.getRATE());
                ans.setCMT(current.getCMT());
                ans.setUOM(current.getUOM());
                if (main.getQTY() == current.getQTY()) {
                } else {
                    float q = main.getQTY() - current.getQTY();
                    ans.setQTY(q);
                    ans.setVAL(q * main.getRATE());
                }
                anss.add(ans);
            }
        }
        wd.UpdateWORemain(anss, Boolean.TRUE);
        //call update bill method
        wd.UpdateBill(w, witm, wtax, wterm);

        //initiate workflow
        WFBean wfb = new WFBean();
        wfb.setTRAN_ID(g.Get_Seq("TRAN_ID", "CBA_TRAN_BILL"));
        wfb.setUSER_ID(uname);
        wfb.setBILL_NO(w.getBILL_NO());
        wfb.setPO_NUMBER(w.getPO_NO());
        wfb.setACT_ID(4);
        wfb.setSTG_C(10);
        if (request.getParameter("ucmt") == null || "".equals(request.getParameter("ucmt"))) {
            wfb.setCMT("Bill updated.please approve");
        } else {
            wfb.setCMT(request.getParameter("ucmt"));
        }
        wfb.setSTG_N(g.Get_NextSTG(wfb.getACT_ID(), wfb.getSTG_C()));
        String s = request.getParameter("NUID");
        if (s.isEmpty()) {
            s = "02948";
        } else {

        }
        wfb.setNXT_UID(s);
        WFDao wfdo = new WFDao();
        wfdo.updateTranFlag(wfb, Boolean.FALSE);
        wfdo.addUserAct(wfb);
        wfdo.addTranaction(wfb, Boolean.FALSE);

        //Generate ReportCBA 
        Logic.ReportCBA r = new ReportCBA();
        r.CreateBillReport(w.getBILL_NO(), getServletContext().getRealPath("/"), Boolean.FALSE);

        //Send Mail 
        Logic.Email em = new Email();
        try {
            em.Send_Alert_BILL(wfb.getBILL_NO(), wfb.getNXT_UID());
        } catch (EmailException ex) {
            Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        }

        response.sendRedirect("msg2.jsp?pono=" + request.getParameter("a") + "&nsid=" + wfb.getSTG_N() + "&bill=" + wfb.getBILL_NO());
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
    private static final Logger LOG = Logger.getLogger(UpdateBill.class.getName());

}
