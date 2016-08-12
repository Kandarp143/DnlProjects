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
                    "iDisplayLength": 100,
                    "aaSorting": []});
                $("#tab2").dataTable({
                    "sPaginationType": "full_numbers",
                    "bJQueryUI": true,
                    "iDisplayLength": 100,
                    "aaSorting": []});

            });
        </script>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <%@page import="Dao.ItemDao"%>
        <%@page import="java.util.ArrayList"%>
        <%@page import="Bean.Item"%>
        <%
            ItemDao idao1 = new ItemDao();
            ArrayList<Item> delItems = idao1.getDoneItem();
        %>
        <%if (request.getParameterMap().containsKey("done")) {%>
        <h2 style="color: green">Selected Item Reverted Successfully</h2>
        <%}%>
        <FORM method="POST" ACTION="Revert">
            <table  id="tab2" class="dtb" width = "95%">
                <thead>
                    <tr>
                        <th colspan="5">
                <h3>Searched Items (Total Item :<%=delItems.size()%> )</h3>
                </th>
                <th>
                    <input type="submit" value="Revert" id="submit" 
                           name="submit" onsubmit="this.disabled = true;
                                   this.value = 'Processing ..';
                                   return true;"  />
                </th>
                </tr>
                <tr>
                    <!--<th>Select</th>-->
                    <th>SELECT</th>
                    <th>SR.NO</th>
                    <th>ITEM ID </th>
                    <th>ITEM NO</th>
                    <th>ITEM DESCRIPTION</th>
                    <th>ACT DATE</th>
                </tr>
                </thead>
                <tbody>
                    <% if (delItems.size() == 0) {%>
                    <tr>
                        <td>No Items Inactive till now</td>
                    </tr>
                    <%} else {
                        int i = 1;%>
                    <% for (Item b : delItems) {%>
                    <tr>
                        <td><input type="checkbox" name="revitm" value="<%=b.getItem_no()%>"></td>
                        <td><%=i++%></td>
                        <td><%=b.getItem_id()%></td>
                        <td><%=b.getItem_no()%></td>
                        <td><%=b.getItem_desc()%></td>
                        <td><%=b.getAct_date()%></td>
                    </tr>
                    <%}
                        }%>
                </tbody>
            </table>
        </form>
    </body>
</html>
