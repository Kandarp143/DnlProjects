package Json;

import Dao.BudgetDao;
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

public class BillItemDtJson extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String item = request.getParameter("itemdt");
        String pono = request.getParameter("pono");
        List<String> list = new ArrayList<String>();
        String json = null;
        String sql = "";
        Logic.GetMethod g = new Logic.GetMethod();
        ResultSet rs;
        BudgetDao bd = new BudgetDao();
        try {
            //ITEM LINE LEVEL DATA
            sql = "select * from cba_wo_re where item_id = '" + item + "' and po_no = '" + pono + "'";
            rs = g.Get_rs(sql);
            while (rs.next()) {
                list.add(rs.getString("UOM"));
                list.add(Float.toString(rs.getFloat("QTY")));
                list.add(Float.toString(rs.getFloat("RATE")));
                list.add(Float.toString(rs.getFloat("VAL")));
                list.add(rs.getString("CMT"));
                list.add(rs.getString("PLANT"));
                list.add(rs.getString("PROJ"));
                list.add(rs.getString("TASK"));
                list.add(Float.toString(bd.getBudgetValue(rs.getString("PROJ"), rs.getString("TASK"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDtJson.class.getName()).log(Level.SEVERE, "Exception : {0}", ex);
        }

        //CONVERT TO JSON AND SEND
        json = new Gson().toJson(list);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
    private static final Logger LOG = Logger.getLogger(BillItemDtJson.class.getName());
}
