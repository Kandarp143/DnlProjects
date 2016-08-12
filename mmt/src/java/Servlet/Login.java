/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.LoginBean;
import Dao.LoginDao;
import Logic.GetMethod;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 02948
 */
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        LoginBean loginBean = new LoginBean();
        LoginDao loginDao = new LoginDao();
        loginBean.setUSER_ID(request.getParameter("login_id"));
        loginBean.setPASS(request.getParameter("pass"));
        String userValidate = loginDao.authenticateUser(loginBean);
        if (userValidate.equals("SUCCESS")) {
            HttpSession session = request.getSession(true);
            GetMethod g = new GetMethod();
            int seq = g.Get_Seq("SEQ", "MMT_LOGIN_HISTORY");
            loginBean.setSEQ(seq);
            loginBean.setUSER_ID(request.getParameter("login_id"));
            loginDao.addLoginHistory(loginBean);
            session.setAttribute("seq", seq);
            session.setAttribute("uid", request.getParameter("login_id"));
            session.setAttribute("uname", loginDao.getUserName(loginBean));
            response.sendRedirect("dashboard.jsp");
        } else if (userValidate.equals("Expired")) {
            request.setAttribute("errMessage", "User account expired ! Please contact administrator");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("errMessage", userValidate);
            request.getRequestDispatcher("index.jsp").forward(request, response);//forwarding the request
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
