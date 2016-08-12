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
public class CreateBill extends HttpServlet {

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
        String bill = Integer.toString(g.Get_Seq("BILL_NO", "CBA_BILL_MST"));
        String ponum = request.getParameter("a");
        String uname = session.getAttribute("uid").toString();
        w.setBILL_ID(request.getParameter("bid"));
        w.setBILL_NO(bill);
        w.setPO_NO(ponum);
        w.setPO_TITLE(request.getParameter("pot"));
        w.setBILL_DATE(request.getParameter("k"));
        w.setOU(request.getParameter("g"));
        w.setUSER_ID(uname);
        w.setSUP_ID(request.getParameter("e"));
        w.setSTATUS("Created");
        w.setSITE(request.getParameter("f"));
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
                    String lino = g.Get_Perameter("LINE_NO", "CBA_WO_ITEM", "ITEM_ID", request.getParameter("A" + i));
                    if (lino.isEmpty()) {
                        lino = "0";
                    }
                    b.setLINE_NO(Integer.parseInt(lino));
                    b.setBILL_NO(w.getBILL_NO());
                    b.setITEM_ID(request.getParameter("A" + i));
                    b.setUOM(request.getParameter("B" + i));
                    b.setQTY(Float.parseFloat(request.getParameter("C" + i)));
                    b.setRATE(Float.parseFloat(request.getParameter("D" + i)));
                    b.setVAL(Float.parseFloat(request.getParameter("E" + i)));
                    b.setCMT((request.getParameter("F" + i) != null) ? request.getParameter("F" + i) : "-");
                    b.setPLANT(request.getParameter("G" + i));
                    b.setCC(request.getParameter("H" + i));
                    b.setPROJ(request.getParameter("I" + i));
                    b.setTASK(request.getParameter("J" + i));
                    witm.add(b);
                    val += Float.parseFloat(request.getParameter("E" + i));
                }
            }
        }

        //set tax in bill
        if (trow > 0) {
            for (int i = 1; i <= trow; i++) {
//                Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "TAX ROW : " + i);
//                Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "T : " + request.getParameter("T" + i));
                if (request.getParameter("T" + i) != null) {
                    WorkTaxBean t = new WorkTaxBean();
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
        wterm.setBILL_NO(w.getBILL_NO());
        wterm.setTERM_ID(request.getParameter("PT"));
        wterm.setTERM_DESC(request.getParameter("PT"));

        //create bill method call
        BillDao wd = new BillDao();
        wd.CreateBill(w, witm, wtax, wterm);

        //initiate workflow
        WFBean wfb = new WFBean();
        wfb.setPO_NUMBER(ponum);
        wfb.setTRAN_ID(g.Get_Seq("TRAN_ID", "CBA_TRAN_BILL"));
        wfb.setUSER_ID(uname);
        wfb.setBILL_NO(bill);
        wfb.setACT_ID(0);
        wfb.setSTG_C(6);
        wfb.setCMT("Please Approve");
        wfb.setSTG_N(g.Get_NextSTG(0, 6));
        String s = request.getParameter("NUID");
        if (s.isEmpty()) {
            s = "02948";
        }
        wfb.setNXT_UID(s);
        WFDao wfdo = new WFDao();
        wfdo.addUserAct(wfb);
        wfdo.addTranaction(wfb, Boolean.FALSE);

        //Generate ReportCBA 
        Logic.ReportCBA r = new ReportCBA();
        r.CreateBillReport(bill, getServletContext().getRealPath("/"), Boolean.TRUE);

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
    private static final Logger LOG = Logger.getLogger(CreateBill.class.getName());

}
