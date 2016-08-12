<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd"> 

<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>JSP display all parameters</title> 
    </head> 
    <body> 
        <%

            // you can get an enumeratable list 
            // of parameter keys by using request.getParameterNames() 
            Enumeration en = request.getParameterNames();

            // enumerate through the keys and extract the values 
            // from the keys! 
            while (en.hasMoreElements()) {
                String parameterName = (String) en.nextElement();
                String parameterValue = request.getParameter(parameterName);
                out.println(parameterName + ":" + parameterValue + "<br />");
            }

            // now call your jsp file (from a browser and add on some paramters) 
            // file.jsp?a=12341234&b=apple&c=1.21gigawatts 
            < % @include file = "include/footer.jsp" %>
    </body> 
</html> 