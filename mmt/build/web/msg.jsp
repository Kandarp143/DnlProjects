<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.MitBean"%>
<%
    ArrayList<MitBean> mitlist = new ArrayList<MitBean>();
    mitlist = (ArrayList<MitBean>) request.getAttribute("mitlist");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLMMT</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <%@include file="include/navbar.jsp" %>
            <div class="page_title">
                <h3>Message</h3>  
            </div>
            <table class="dtb" width="50%">
                <tr>
                    <td  colspan="2"style="color: #A70303;font-weight: bold">Item successfully consumed,Please review details</td>
                </tr>
                <tr>
                    <th>Item Code</th>
                    <th>Description</th>
                    <th>Location</th>
                    <th>Pre-Shipment No.</th>
                    <th>Lot No</th>
                    <th>Receipt No.</th>
                    <th>Consume QTY</th>
                    <th style="color: #A70303">Generated MIT_NO</th>
                </tr>

                <%for (MitBean bean : mitlist) {%>
                <tr>
                    <td><%=bean.getITEM_CODE()%></td>
                    <td><%=bean.getITEM_DESC()%></td>
                    <td><%=bean.getLOC_ID()%></td>
                    <td><%=bean.getPRE_SHIP()%></td>
                    <td><%=bean.getLOT_NO()%></td>
                    <td><%=bean.getRECIEPT_NO()%></td>
                    <td class="cur"><%=bean.getCUN_QTY()%></td>
                    <td class="cur" style="color: #A70303"><b><%=bean.getMITNO()%></b></td>
                </tr>
                <%}%>

            </table>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
