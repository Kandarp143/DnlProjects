<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Logic.GetMethod"%>
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
                    <h4 class="page_title">Release Work Order / Generate Bill</h4>
                </div>
                <%
                    String uid = session.getAttribute("uid").toString();
                    ArrayList<WorkBean> wo = new ArrayList<WorkBean>();
                    WorkDao wdao = new WorkDao();
                    wo = wdao.getWObyUid(uid, true);
                    if (wo.size() == 0) {%>
                <p class="dtb">No Work Order are approved till now </p>
                <%   } else {
                %>
                <table class="dtb">
                    <tr>
                        <th>SR NO</th>
                        <th>WO Number</th>
                        <th>Type</th>
                        <th>Create Date</th>
                        <th>Created By</th>
                        <th>Supplier</th>
                        <th>Site</th>
                        <th>Operating Unit</th>
                        <th>Detail</th>
                        <th>Release</th>
                        <th colspan="2">Use Data Loader</th>
                    </tr>
                    <%  int index2 = 1;
                        for (WorkBean wo1 : wo) {%>
                    <tr>
                        <td><%=index2++%></td>
                        <td><%=wo1.getPO_NO()%></td>
                        <td><%=wo1.getTYPE()%></td>
                        <td><%=wo1.getCR_DATE()%></td>
                        <td><%=wo1.getUSER_NAME()%></td>
                        <td><%=wo1.getSUP_NAME()%></td>
                        <td><%=wo1.getSITE()%></td>
                        <td><%=wo1.getOU()%></td>
                        <td><a href="wodetail.jsp?pono=<%=wo1.getPO_NO()%>&sid=0">Click here </a></td>
                        <%
                            String resql = "select * from cba_wo_re where qty >0 or (qty=0 and ischeck='N') and po_no = '" + wo1.getPO_NO() + "'";
                            Logic.GetMethod ggg = new GetMethod();
                            ResultSet rers = ggg.Get_rs(resql);
                            if (!rers.isBeforeFirst()) {
                        %>
                        <td><span style="color: red">Released</span></td>
                        <%} else {%>
                        <td><a href="billcreate.jsp?pono=<%=wo1.getPO_NO()%>&sid=6&typ=<%=wo1.getTYPE()%>">Release WO</a></td>
                        <%}%>
                        <td>
                            <a  style="color: #8B0000;font-weight: bold"href="xls/<%=wo1.getPO_NO().replace("/", "-")%>.xlsx" >Download Excel</a>
                        </td>
                    </tr>
                    <%
                        }%>
                </table>
                <%
                    }%>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
