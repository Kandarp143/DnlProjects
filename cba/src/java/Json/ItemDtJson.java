
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

public class ItemDtJson extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String item = request.getParameter("itemdt");
        String sup = request.getParameter("supid");
        List<String> list = new ArrayList<String>();
        String json = null;
        String sql = "";
        String sql2 = "";
        Logic.GetMethod g = new Logic.GetMethod();
        ResultSet rs;
        ResultSet rs2;
        try {
            //ITEM LINE LEVEL DATA
            sql = "SELECT DISTINCT itm.primary_unit_of_measure, '0', r.rate, '0', '-'"
                    + "           FROM cba_rate_sup r, mtl_system_items itm"
                    + "          WHERE r.item_id = itm.segment1"
                    + "            AND itm.organization_id = 0"
                    + "            AND r.item_id = '" + item + "'"
                    + "            AND r.sup_id = '" + sup + "'";
            rs = g.Get_rs(sql);
            if (!rs.isBeforeFirst()) {
                sql2 = "SELECT DISTINCT itm.primary_unit_of_measure, '0', '0', '0', '-'"
                        + "           FROM  mtl_system_items itm"
                        + "            WHERE itm.organization_id = 0"
                        + "            AND itm.segment1 = '" + item + "'";
                rs2 = g.Get_rs(sql2);
            } else {
                rs2 = rs;
            }

            while (rs2.next()) {
                list.add(rs2.getString(1));
                list.add(rs2.getString(2));
                list.add(rs2.getString(3));
                list.add(rs2.getString(4));
                list.add(rs2.getString(5));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemDtJson.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        }

        //CONVERT TO JSON AND SEND
        json = new Gson().toJson(list);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
    private static final Logger LOG = Logger.getLogger(ItemDtJson.class.getName());
}
