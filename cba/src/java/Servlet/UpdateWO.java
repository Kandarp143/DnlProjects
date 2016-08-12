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
import Dao.WFDao;
import Dao.WorkDao;
import Logic.DBmanager;
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
public class UpdateWO extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static final Logger logger = Logger.getLogger(DBmanager.class.getName());

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

        //Master Work Order
        String uname = session.getAttribute("uid").toString();
        w.setPO_NO(request.getParameter("a"));
        w.setPO_TITLE(request.getParameter("pot"));
        w.setEXP_DATE(request.getParameter("k"));
        w.setFR_DATE(request.getParameter("fk"));
        w.setOU(request.getParameter("g"));
        w.setUSER_ID(uname);
        w.setSUP_ID(request.getParameter("e"));
        w.setSTATUS("Updated");
        w.setCUR(request.getParameter("h"));
        w.setSITE(request.getParameter("f"));
        w.setWO_DESC(request.getParameter("cmt"));
        w.setRET_AMT(Float.parseFloat(request.getParameter("i")));
        w.setDEP_AMT(Float.parseFloat(request.getParameter("i2")));
        w.setTYPE(request.getParameter("j"));
        logger.log(Level.SEVERE, "TYPE OF WO :" + request.getParameter("j"));
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

        //set work order item level
        if (irow > 0) {
            int count = 1;
            for (int i = 1; i <= irow; i++) {
                if (request.getParameter("A" + i) != null) {
                    WorkItemBean b = new WorkItemBean();
                    b.setPO_NO(w.getPO_NO());
                    b.setLINE_NO(count++);
                    b.setITEM_ID(request.getParameter("A" + i));
                    b.setUOM(request.getParameter("B" + i));
                    b.setQTY(Float.parseFloat(request.getParameter("C" + i)));
                    b.setRATE(Float.parseFloat(request.getParameter("D" + i)));
                    b.setVAL(Float.parseFloat(request.getParameter("E" + i)));
                    b.setPLANT(request.getParameter("G" + i));
                    if (w.getTYPE().equalsIgnoreCase("Project")) {
                        b.setPROJ(request.getParameter("I" + i));
                        b.setTASK(request.getParameter("J" + i));
                    } else {
                        b.setPROJ("-");
                        b.setTASK("-");
                    }

                    b.setCMT(request.getParameter("F" + i));
                    witm.add(b);
                    val += Float.parseFloat(request.getParameter("E" + i));
                }
            }
        }

        //set tax in work order
        if (trow > 0) {
            for (int i = 1; i <= trow; i++) {
                if (request.getParameter("T" + i) != null) {
                    WorkTaxBean t = new WorkTaxBean();
                    t.setPO_NO(w.getPO_NO());
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

        //add payment term in work order
        wterm.setPO_NO(w.getPO_NO());
        wterm.setTERM_ID(request.getParameter("PT"));
        wterm.setTERM_DESC(request.getParameter("PT"));

        //call update work order method
        WorkDao wd = new WorkDao();
        wd.UpdateWO(w, witm, wtax, wterm);

        //initiate workflow
        WFBean wfb = new WFBean();
        wfb.setTRAN_ID(g.Get_Seq("TRAN_ID", "CBA_TRAN_WO"));
        wfb.setUSER_ID(uname);
        wfb.setPO_NUMBER(request.getParameter("a"));
        wfb.setACT_ID(4);
        wfb.setSTG_C(4);
        if (request.getParameter("ucmt") == null || "".equals(request.getParameter("ucmt"))) {
            wfb.setCMT("WO updated Please Approve");
        } else {
            wfb.setCMT(request.getParameter("ucmt"));
        }
        wfb.setSTG_N(g.Get_NextSTG(4, 4));
        wfb.setNXT_UID(g.Get_NextUID(wfb.getSTG_N(), w.getPO_NO(), uname));
        WFDao wfdo = new WFDao();
        wfdo.updateTranFlag(wfb, Boolean.TRUE);
        wfdo.addUserAct(wfb);
        wfdo.addTranaction(wfb, Boolean.TRUE);

        //Generate ReportCBA 
        Logic.ReportCBA r = new ReportCBA();
        r.CreateReport(w.getPO_NO(), getServletContext().getRealPath("/"), Boolean.FALSE);

        //Send Mail 
        Logic.Email em = new Email();
        try {
            em.Send_Alert(wfb.getPO_NUMBER(), wfb.getNXT_UID());
        } catch (EmailException ex) {
            Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        }
        //page redirect
        response.sendRedirect("msg.jsp?pono=" + request.getParameter("a") + "&nsid=" + wfb.getSTG_N());
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
    private static final Logger LOG = Logger.getLogger(UpdateWO.class.getName());

}
