
package Json;

import Logic.Variables;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaxJson extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String json2 = null;
        Logic.Dropdown.LoadDropdown();
        json2 = new Gson().toJson(Variables.item);
        //  System.out.println("json = " + json);
        response.setContentType("application/json");
        response.getWriter().write(json2);

    }
    private static final Logger LOG = Logger.getLogger(TaxJson.class.getName());
}
