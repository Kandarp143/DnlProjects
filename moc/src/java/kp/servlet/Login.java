/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kp.beans.user.pojo.MocUserMst;
import kp.dao.user.LoginDao;
import kp.dao.user.UserDao;

/**
 *
 * @author 02948
 */
public class Login extends HttpServlet {

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

        String act = request.getParameter("act");
        LoginDao loginDao = new LoginDao();
        UserDao usrDao = new UserDao();

        if (act.equalsIgnoreCase("login")) {
            HttpSession session = request.getSession(true);
            String user_id = request.getParameter("user_id");
            String password = request.getParameter("password");
            Boolean isUserExsist = false;
            //check for user 
            isUserExsist = loginDao.authUser(user_id, password);
            if (isUserExsist) {
                int sid = loginDao.addloginHistory(user_id);
                MocUserMst usr = usrDao.fetchUser(user_id);
                session.setAttribute("seq", sid);
                session.setAttribute("usr", request.getParameter("user_id"));
                session.setAttribute("usrname", usr.getUserName());
                session.setAttribute("usrunit", usr.getUnit());
                session.setAttribute("accrole", usrDao.getUserAccRole(user_id));
                session.setAttribute("iscreator", usrDao.isUserCreator(user_id));
                response.sendRedirect("mywork.jsp");
            } else {
                request.setAttribute("errMessage", "Username/Password do not match. or User is inactive");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else if (act.equalsIgnoreCase("logout")) {
            HttpSession session = request.getSession(true);
            loginDao.updateloginHistory(Integer.parseInt(session.getAttribute("seq").toString()));
            //session.invalidate();
            session.invalidate();

            response.sendRedirect("index.jsp");

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
