<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>

<%@page import="Dao.PODao"%>
<%@page import="Dao.PODao"%>
<%@page import="Bean.InvBean"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLCBA</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <link href="css/datasource_gui.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.searchabledropdown-1.0.8.min.js"></script>
        <script src="js/dynamicrow.js" type="text/javascript"></script>
        <script src="js/Datepicker.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
        <!--Datagrid call -->
        <script type="text/javascript">
            $(document).ready(function () {
                $("#tab1w").dataTable({
                    "sPaginationType": "full_numbers",
                    "iDisplayLength": 18,
                    "bJQueryUI": true,
                    "aaSorting": []});
            });
        </script>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <div>
                    <h4 class="page_title">
                        ERP Invoice List
                    </h4>
                </div>
                <%
                    String uid = session.getAttribute("uid").toString();
                    ArrayList<InvBean> inv = new ArrayList<InvBean>();
                    PODao pdao = new PODao();
                    inv = pdao.getInvList(uid);
                    if (inv.size() == 0) {
                %>
                <p class="dtb"  >No invoice till now</p>
                <%} else {%>
                <table class="dtb" id="tab1w" >
                    <thead>
                        <tr>
                            <th>SR NO</th>
                            <th>ERP Invoice Number</th>
                            <th>CBA Bill ID</th>
                            <th>ERP PO Number</th>
                            <th>Supplier</th>
                            <th>Site</th>
                            <th>Total Value</th>
                            <th>Amount Paid</th>
                            <th>Invoice Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int index2 = 1;
                            for (InvBean iv1 : inv) {%>
                        <tr>
                            <td><%=index2++%></td> 
                            <td><%=iv1.getINVOICE_NUM()%></td>
                            <td><%=iv1.getSOURCE()%></td>
                            <td><%=iv1.getPO_NUM()%></td>
                            <td><%=iv1.getVENDOR_NAME()%></td>
                            <td><%=iv1.getVENDOR_SITE_CODE()%></td>
                            <td align="right"><%=iv1.getINVOICE_AMOUNT()%></td>
                            <td align="right"><%=iv1.getAMOUNT_PAID()%></td>
                            <td><%=iv1.getINVOICE_DATE()%></td>
                        </tr>
                        <%}
                            }%>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
