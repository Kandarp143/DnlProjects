package Json;

import com.google.gson.Gson;
import java.io.IOException;
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

public class SupDtJson extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String sup = request.getParameter("sup");
        List<String> list = new ArrayList<String>();
        String json = null;
        String sql = "";
        Logic.GetMethod g = new Logic.GetMethod();
        ResultSet rs;
        try {
            //ITEM LINE LEVEL DATA
            list.clear();
            sql = "select distinct a.vendor_site_id,a.vendor_site_code||'-'||a.vendor_site_id from PO_VENDOR_SITES_ALL a,PO_VENDORS b  where "
                    + "(a.vendor_id = b.vendor_id) and"
                    + "(b.vendor_number = '" + sup + "') AND a.ORG_ID ='123'";
            rs = g.Get_rs(sql);
            while (rs.next()) {
                list.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDtJson.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        }

        //CONVERT TO JSON AND SEND
        json = new Gson().toJson(list);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
    private static final Logger LOG = Logger.getLogger(SupDtJson.class.getName());
}
