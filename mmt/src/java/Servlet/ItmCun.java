/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.MitBean;
import Dao.ItemDao;
import Dao.MitDao;
import Dao.TranDao;
import Logic.Email;
import Logic.GetMethod;
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
public class ItmCun extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        Logic.GetMethod g = new GetMethod();
        MitDao mdao = new MitDao();

        ArrayList<MitBean> mitlist = new ArrayList<MitBean>();
        int row = Integer.parseInt(request.getParameter("totrow"));
        String uid = session.getAttribute("uid").toString();
        int tranid = g.Get_Seq("TRAN_ID", "MMT_TRAN_MST");
        //1.get data and create mit list
        for (int i = 1; i <= row; i++) {
            if (!request.getParameter("j" + i).equals("0")) {
                if (Float.parseFloat(request.getParameter("j" + i)) <= Float.parseFloat(request.getParameter("h" + i))) {
                    out.print(request.getParameter("j" + i));
                    MitBean bean = new MitBean();
                    bean.setTRAN_ID(tranid);
                    bean.setMITNO(mdao.genMIT_NO(request.getParameter("c" + i)));
                    bean.setUSER_ID(uid);
                    bean.setUSER_NAME(g.Get_Perameter(uid, "FULL_NAME", "MMT_USER_MST"));
                    bean.setITEM_CODE(request.getParameter("a" + i));
                    bean.setITEM_DESC(request.getParameter("b" + i));
                    bean.setLOC_ID(request.getParameter("c" + i));
                    bean.setPRE_SHIP(request.getParameter("d" + i));
                    bean.setLOT_NO(request.getParameter("e" + i));
                    bean.setRECIEPT_NO(request.getParameter("f" + i));
                    bean.setGRN_DATE(request.getParameter("m" + i));
                    bean.setINV_NO(request.getParameter("g" + i));
                    bean.setQTY(Float.parseFloat(request.getParameter("h" + i)));
                    bean.setUOM(request.getParameter("i" + i));
                    bean.setCUN_QTY(Float.parseFloat(request.getParameter("j" + i)));
                    bean.setPUR_ID(Integer.parseInt(request.getParameter("k" + i)));
                    bean.setPUR_NAME(g.Get_Perameter("PURPOSE", "MMT_CUN_PURPOSE", "PUR_ID", request.getParameter("k" + i)));
                    bean.setCUS_PO(request.getParameter("l" + i));
                    //For update QTY
                    bean.setRE_QTY(bean.getQTY() - bean.getCUN_QTY());
                    if (bean.getRE_QTY() == 0) {
                        bean.setSTATUS("closed");
                    } else {
                        bean.setSTATUS("partial");
                    }
                    mitlist.add(bean);
                } else {
                    response.sendRedirect("itmcun.jsp?err");
                }
            }
        }
        Logger.getLogger(ItmCun.class.getName()).log(Level.SEVERE, "total items cunsumed " + mitlist.size());

        if (!mitlist.isEmpty()) {

            //insert tran_items
            TranDao trndao = new TranDao();
            trndao.insMIT_TRAN(mitlist);

            //update qty
            ItemDao itmdao = new ItemDao();
            itmdao.updateQty(mitlist);

            //Send Email
            Email e = new Email();
            try {
                e.Send_Alert(mitlist, g.Get_Perameter(uid, "REVIEW_ID", "MMT_USER_MST"));
            } catch (EmailException ex) {
                Logger.getLogger(ItmCun.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ItmCun.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("mitlist", mitlist);
            request.getRequestDispatcher("msg.jsp").forward(request, response);

        } else {
            Logger.getLogger(ItmCun.class.getName()).log(Level.SEVERE, "SIZE " + mitlist.isEmpty());
            Logger.getLogger(ItmCun.class.getName()).log(Level.SEVERE, "IN THE LOOP");
            response.sendRedirect("itmcun.jsp?err2");

        }// response.sendRedirect("msg.jsp?mit=" + mitlist);
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
