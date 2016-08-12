/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.Item;
import Dao.ItemDao;
import Logic.Call;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 02948
 */
public class SelItm extends HttpServlet {

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
        String grp = request.getParameter("grp");
        ItemDao d = new ItemDao();
        if (request.getParameterMap().containsKey("submit1")) {
            //SEARCH RELATED
            response.sendRedirect("dtitm.jsp?grp=" + grp + "&selitm="
                    + request.getParameter("selitm"));
        } else if (request.getParameterMap().containsKey("submit2")) {
            //INACTIVE
            String[] doneitm = request.getParameterValues("revitm");
            System.out.println("Request for inactive : " + doneitm.length);
            ArrayList<Item> inactlist = new ArrayList<Item>();
            if (doneitm != null) {
                for (String doneitm1 : doneitm) {
                    Item itm = new Item();
                    itm.setItem_no(doneitm1);
                    itm.setItem_id(d.getItemPera(doneitm1, "INVENTORY_ITEM_ID"));
                    itm.setItem_desc(d.getItemPera(doneitm1, "DESCRIPTION"));
                    itm.setMatch_item_no(doneitm1);
                    inactlist.add(itm);
                }
                d.insDelItem(inactlist);
                d.insDoneItem(inactlist);
//                for (Item i : inactlist) {
//                    System.out.println(i.getItem_no());
//                }
                response.sendRedirect("mstitm.jsp?grp=" + grp);
            }
        } else {
            //SEARCHED
            String[] doneitm = request.getParameterValues("revitm");
            System.out.println("Request for searched : " + doneitm.length);
            ArrayList<Item> inactlist = new ArrayList<Item>();
            if (doneitm != null) {
                for (String doneitm1 : doneitm) {
                    Item itm = new Item();
                    itm.setItem_no(doneitm1);
                    itm.setMatch_item_no(doneitm1);
                    itm.setItem_id(d.getItemPera(doneitm1, "INVENTORY_ITEM_ID"));
                    itm.setItem_desc(d.getItemPera(doneitm1, "DESCRIPTION"));
                    inactlist.add(itm);
                }
                d.insDoneItem(inactlist);
                response.sendRedirect("mstitm.jsp?grp=" + grp);
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
