package Json;

import Bean.MitBean;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MitJson extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String itm = request.getParameter("itm");
        ArrayList<String> mitlist = new ArrayList<String>();
        String json = null;
        String sql = "";
        Logic.GetMethod g = new Logic.GetMethod();
        ResultSet rs;
        try {
            //ITEM LINE LEVEL DATA
            sql = "select MITNO from MMT_TRAN_MST where ITEM_CODE = '" + itm + "' AND PUR_ID = 2 ORDER BY MITNO";
            rs = g.Get_rs(sql);
            mitlist.add("Select MIT-NO");
            while (rs.next()) {
                mitlist.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MitJson.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        }

        //CONVERT TO JSON AND SEND
        json = new Gson().toJson(mitlist);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
