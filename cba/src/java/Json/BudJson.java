/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

import Dao.BudgetDao;
import Logic.Variables;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 02948
 */
public class BudJson extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String proj = request.getParameter("proj");
        String tsk = request.getParameter("tsk");
        List<String> list = new ArrayList<String>();
        BudgetDao bd = new BudgetDao();
        list.add(Float.toString(bd.getBudgetValue(proj, tsk)));
        String json = null;
        //CONVERT TO JSON AND SEND
        json = new Gson().toJson(list);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
    private static final Logger LOG = Logger.getLogger(ItemDtJson.class.getName());
}
