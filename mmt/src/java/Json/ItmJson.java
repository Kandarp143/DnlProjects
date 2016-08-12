package Json;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItmJson extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String itm = request.getParameter("itm");
        ArrayList<String> itmlist = new ArrayList<String>();
        String json = null;
        String sql = "";
        Logic.GetMethod g = new Logic.GetMethod();
        ResultSet rs;
        try {
            //ITEM LINE LEVEL DATA
            sql = "SELECT DISTINCT a.segment1, a.description"
                    + "           FROM mtl_system_items a"
                    + "          WHERE SUBSTR (a.segment1, 0, 2) = '02'"
                    + "                AND a.organization_id = '4634'"
                    + "             OR a.organization_id = '6434'";
            rs = g.Get_rs(sql);
            itmlist.add("Select Item");
            while (rs.next()) {
                itmlist.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MitJson.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        }

        //CONVERT TO JSON AND SEND
        json = new Gson().toJson(itmlist);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
