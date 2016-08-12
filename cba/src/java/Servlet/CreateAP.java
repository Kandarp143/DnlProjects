/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.InvBean;
import Bean.WorkBean;
import Dao.BillDao;
import Dao.PODao;
import Logic.Email;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author 02948
 */
public class CreateAP extends HttpServlet {

    static final Logger logger = Logger.getLogger(CreateAP.class.getName());

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
        //assign parameter to variable and initiate object
        String billno = request.getParameter("bill");
        String page = request.getParameter("page");
        String step = request.getParameter("step");
        PODao pd = new PODao();
        BillDao bd = new BillDao();
        InvBean invbean = new InvBean();
        WorkBean billbean = new WorkBean();
        billbean = bd.getBill(billno);
        //step 1 - create po
        if (step.equalsIgnoreCase("1")) {
            if (!pd.checkPOInterface(billno)) {
                pd.createPO(billno);
            }
            response.sendRedirect(page + ".jsp?bill=" + billno + "&step=2");
        }
        //step 2 - check po
        if (step.equalsIgnoreCase("2")) {
            if (pd.checkPOProd(billno)) {
                response.sendRedirect(page + ".jsp?bill=" + billno + "&step=3");
            } else {
                response.sendRedirect(page + ".jsp?bill=" + billno + "&step=2");
            }
        }
        //step 3 - add tax
        if (step.equalsIgnoreCase("3")) {
            if (!pd.checkTax(billno)) {
                pd.addPoTax(billno);
            }
            response.sendRedirect(page + ".jsp?bill=" + billno + "&step=4");
        }
        //step 4 - check tax and print tax
        if (step.equalsIgnoreCase("4")) {
            if (pd.checkTax(billno)) {
                response.sendRedirect(page + ".jsp?bill=" + billno + "&step=5");
            } else {
                response.sendRedirect(page + ".jsp?bill=" + billno + "&step=4");
            }
        }
        //step 5 - create inv
        int req = 0;
        if (step.equalsIgnoreCase("5")) {
            if (!pd.checkINVInterface(billno)) {
                req = pd.createINV(billno);
            }
            response.sendRedirect(page + ".jsp?bill=" + billno + "&step=6");
        }
        //step 6 - check inv
        if (step.equalsIgnoreCase("6")) {
            if (pd.checkINVProd(billno)) {
                invbean.setINVOICE_NUM("CBA" + billno);
                invbean.setINV_REQ_ID(req);
                bd.UpdateBillstatus(billno, "INV Created");
                pd.insInv_mst(invbean, billbean);
                //Send Mail 
                Logic.Email em = new Email();
                try {
                    em.Send_Alert_AP(billno, "00090", Integer.toString(invbean.getINV_REQ_ID()), invbean.getINVOICE_NUM());
                    em.Send_Alert_AP(billno, "00250", Integer.toString(invbean.getINV_REQ_ID()), invbean.getINVOICE_NUM());
                } catch (EmailException ex) {
                    Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CreateWO.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
                }
                response.sendRedirect("msg3.jsp?inv_num=CBA" + billno + "&inv_req=" + req);
            } else {
                response.sendRedirect(page + ".jsp?bill=" + billno + "&step=6");
            }
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
