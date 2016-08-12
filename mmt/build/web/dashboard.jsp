<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLMMT</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <link rel="stylesheet" href="css/jquery_tab_view_css.css"/>
        <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="js/jquery_tab_view.js"></script>
        <script src="js/table_rpt.js" type="text/javascript"></script>
        <script>
            $(function () {
                $("#tabs").tabs();
            });
        </script>
        <%
            DashDao ddao2 = new DashDao();
        %>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <%@include file="include/navbar.jsp" %>
            <div class="page_title">
                <h3>DashBoard</h3>  
            </div>
            <div id="tabs">
                <ul>
                    <li><a href="#tabs-1">Stocked Item Summary (MT)</a></li>
                    <li><a href="#tabs-2">Consume Item Summary</a></li>
                    <li><a href="#tabs-3">MMT v/s ERP Onhand Qty</a></li>
                </ul>
                <div id="tabs-1">
                    <%@include file="include/dash1.jsp" %>
                </div>
                <div id="tabs-2">
                    <%@include file="include/dash2.jsp" %>
                </div>
                <div id="tabs-3">
                    <%@include file="include/dash4.jsp" %>
                </div>
            </div>
            <%@include file="include/footer.jsp" %>
        </div>
    </body>
</html>
