<%-- 
    Document   : index
    Created on : May 25, 2015, 6:38:43 PM
    Author     : 02948
--%>

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

        <!--Datagrid call -->
        <script type="text/javascript">
            $(document).ready(function () {
                $("#tab1").dataTable({
                    "sPaginationType": "full_numbers",
                    "bJQueryUI": true,
                    "aaSorting": []});
                $("#tab2").dataTable({
                    "sPaginationType": "full_numbers",
                    "bJQueryUI": true,
                    "aaSorting": []});

            });
        </script>
    </head>
    <body>
        <div id="loading">
            <h3>
                Loading... Please wait <img id="loading-image" src="image/loader.gif" alt="Loading..." />
            </h3>
        </div>
        <%@include file="include/header.jsp" %>
        <%if (request.getParameterMap().containsKey("desc")) {%>
        <%@include file="include/search_res.jsp" %>
        <%} else {%>
        <%@include file="include/search.jsp" %>
        <%}%>
        <script language="javascript" type="text/javascript">
            $(window).load(function () {
                $('#loading').hide();
            });
        </script>
    </body>
</html>
