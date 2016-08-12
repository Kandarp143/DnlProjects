<%-- 
    Document   : index
    Created on : Jun 22, 2015, 4:43:41 PM
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

        <!--Datagrid call -->
        <script type="text/javascript">
            $(document).ready(function () {
                $("#tab1").dataTable({
                    "sPaginationType": "full_numbers",
                    "iDisplayLength": 50,
                    "bJQueryUI": true,
                    "aaSorting": []});
                $("#tab2").dataTable({
                    "sPaginationType": "full_numbers",
                    "iDisplayLength": 50,
                    "bJQueryUI": true,
                    "aaSorting": []});
            });
        </script>
    </head>
    <body>
        <div id="wrapper"/>
        <%@include file="include/header.jsp" %>  
        <table class="dtb_sub" style="table-layout: fixed">
            <tr>
                <td>
                    <div id="loading">
                        <h3>
                            Loading... Please wait <img id="loading-image" src="image/loader.gif" alt="Loading..." />
                        </h3>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <%@include file="include/grp.jsp" %>
                </td>
            </tr>
            <tr>
                <td>
                    <h3 style="margin: 0;color:#666">
                        Master Table
                    </h3>
                    <%@include file="include/mstdtb.jsp" %>
                </td>
            </tr>
        </table>
    </div>
    <script language="javascript" type="text/javascript">
        $(window).load(function () {
            $('#loading').hide();
        });
    </script>
</body>
</html>
