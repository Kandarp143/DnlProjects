<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.WorkBean"%>
<%@page import="Dao.WorkDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <div>
                     <h4 class="page_title">Work Order List</h4>
                </div>
                <!-- CONTENTS ARE GOES HERE OF REAL PAGE ----->
                <%
                    String uid = session.getAttribute("uid").toString();
                    ArrayList<WorkBean> wo = new ArrayList<WorkBean>();
                    WorkDao wdao = new WorkDao();
                    wo = wdao.getWObyUid(uid, false);
                    if (wo.size() == 0) {
                %>
                <p class="dtb"  >No WOs are created till now</p>
                <%} else {%>
                <table class="dtb">
                    <tr>
                        <th>SR NO</th>
                        <th>WO Number</th>
                        <th>Create Date</th>
                        <th>Created By</th>
                        <th>Supplier</th>
                        <th>Site</th>
                        <th>Operating Unit</th>
                        <th>Total Value</th>
                        <th>Status</th>
                        <th>Detail</th>
                    </tr>
                    <%  int index2 = 1;
                        for (WorkBean wo1 : wo) {%>
                    <tr>
                        <td><%=index2++%></td>
                        <td><%=wo1.getPO_NO()%></td>
                        <td><%=wo1.getCR_DATE()%></td>
                        <td><%=wo1.getUSER_NAME()%></td>
                        <td><%=wo1.getSUP_NAME()%></td>
                        <td><%=wo1.getSITE_NAME()%></td>
                        <td><%=wo1.getOU()%></td>
                        <td align="right"><%=wo1.getTOTAL_VAL()%></td>
                        <td><b><%=wo1.getSTATUS()%></b></td>
                        <td><a href="wodetail.jsp?pono=<%=wo1.getPO_NO()%>&sid=0">Click Here </a></td>
                    </tr>
                    <% }
                        }%>

                </table>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>