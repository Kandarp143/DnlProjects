<%-- 
    Document   : index
    Created on : May 25, 2015, 6:38:43 PM
    Author     : 02948
--%>
<%@page import="Bean.GlobalVar"%>
<%@page import="Dao.ItemDao"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="shortcut icon" href="image/favicon.ico"/>
        <!-- CSS -->
        <link href="css/nav.css" rel="stylesheet" type="text/css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link href="css/datasource.css" rel="stylesheet" type="text/css" />
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
        <link href="css/datasource_gui.css" rel="stylesheet" type="text/css" />
        <link href="css/jquery.loader.css" rel="stylesheet" type="text/css" />
        <!-- JS -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script src="js/formwizard.js" type="text/javascript"></script>
        <script src="js/jquery-1.7.1.js" type="text/javascript"></script>
        <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="js/default.js" type="text/javascript"></script>
        <script src="js/Datepicker.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="js/tabs.js" type="text/javascript"></script>
        <script src="js/table_rpt.js" type="text/javascript"></script>
        <script src="js/jquery.loader.js" type="text/javascript"></script>
    <body>
        <div id="wrapper"/>
        <%@include file="include/header_1.jsp" %>
        <form method="POST" ACTION="Login">
            <table class="dtb" style="table-layout: fixed">
                <th>Please Enter Password </th>
                <td><input type="password" size="20" name="pwd" id="pwd"/>
                    <input type="submit" value="submit" id="submit" name="submit" onsubmit="this.disabled = true;
                            this.value = 'Processing ..';
                            return true;"  />
                </td>
                <%if (request.getParameterMap().containsKey("err")) {%>
                <td style="color: red">
                    Invalid Credentials  
                </td>
                <%} else if (request.getParameterMap().containsKey("logout")) {
                %>
                <td style="color: green">
                    Successfully logged out  
                </td>
                <%} else {
                    }%>
                   
            </table>
        </form>
    </div>
</body>
</html>
