<%-- 
    Document   : errorlist
    Created on : Nov 20, 2015, 10:53:28 AM
    Author     : 02948
--%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <table class="form_table">
            <tr>
                <td>
                    <p class="err"><b>Validation Error :</b></p>
                    <ul>
                        <%
                            ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");
                            for (String error : errors) {%>
                        <li class="err-list"><%=error%></li>
                            <%}%>     
                    </ul>
                </td>
            </tr>
        </table>
    </body>
</html>

