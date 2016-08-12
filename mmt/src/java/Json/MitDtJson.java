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

public class MitDtJson extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String mit = request.getParameter("mit");
        ArrayList<String> dtlist = new ArrayList<String>();
        String json = null;
        String sql = "";
        Logic.GetMethod g = new Logic.GetMethod();
        ResultSet rs;
        try {
            //ITEM LINE LEVEL DATA
            sql = "Select RECEIPT_NO,LOC_ID,LOT_NO,PRE_SHIP,UOM from mmt_tran_mst where mitno = '" + mit + "'";
            rs = g.Get_rs(sql);
            while (rs.next()) {
                dtlist.add(rs.getString(1));
                dtlist.add(rs.getString(2));
                dtlist.add(rs.getString(3));
                dtlist.add(rs.getString(4));
                dtlist.add(rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MitJson.class.getName()).log(Level.SEVERE, "Exception : " + ex);
        }

        //CONVERT TO JSON AND SEND
        json = new Gson().toJson(dtlist);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
