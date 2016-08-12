<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>

<%@page import="Dao.BillDao"%>
<%@page import="Bean.WorkBean"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLCBA</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.searchabledropdown-1.0.8.min.js"></script>
        <script src="js/dynamicrow.js" type="text/javascript"></script>
        <script src="js/Datepicker.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
        <link href="css/datasource_gui.css" type="text/css" rel="stylesheet"/>
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
                    <h4 class="page_title">Bill List</h4>
                </div>
                <%
                    String uid = session.getAttribute("uid").toString();
                    ArrayList<WorkBean> bill = new ArrayList<WorkBean>();
                    BillDao bdao = new BillDao();
                    bill = bdao.getBillbyUid(uid, false);
                    if (bill.size() == 0) {
                %>
                <p class="dtb"  >No Bills are created till now</p>
                <%} else {%>
                <table class="dtb" id="tab1w">
                    <thead>
                        <tr>
                            <th>Bill ID</th>
                            <th>Bill Number</th>
                            <th>WO Number</th>
                            <th>Bill Date</th>
                            <th>Created By</th>
                            <th>Supplier</th>
                            <th>Site</th>
                            <th>Operating Unit</th>
                            <th>Total Value</th>
                            <th>Status</th>
                            <th>Detail</th>
                        </tr>    
                    </thead>
                    <tbody>
                        <%  int index2 = 1;
                            for (WorkBean wo1 : bill) {%>
                        <tr>
                            <td><%=wo1.getBILL_NO()%></td>
                            <td><%=wo1.getBILL_ID()%></td>
                            <td><%=wo1.getPO_NO()%></td>
                            <td><%=wo1.getBILL_DATE()%></td>
                            <td><%=wo1.getUSER_NAME()%></td>
                            <td><%=wo1.getSUP_NAME()%></td>
                            <td><%=wo1.getSITE_NAME()%></td>
                            <td><%=wo1.getOU()%></td>
                            <td align="right"><%=wo1.getTOTAL_VAL()%></td>
                            <td><b><%=wo1.getSTATUS()%></b></td>
                            <td><a href="billdetail.jsp?bill=<%=wo1.getBILL_NO()%>&sid=0&pono=<%=wo1.getPO_NO()%>">Click Here </a></td>
                        </tr>
                        <% }
                            }%>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
