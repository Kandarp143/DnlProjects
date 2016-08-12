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
                    <h4 class="page_title">Create ERP Invoice</h4>
                </div>
                <%
                    String uid = session.getAttribute("uid").toString();
                    ArrayList<WorkBean> bill = new ArrayList<WorkBean>();
                    BillDao bdao = new BillDao();
                    bill = bdao.getBillbyUid(uid, true);
                    if (bill.size() == 0) {
                %>
                <p class="dtb"  >No Bills are approved till now</p>
                <%} else {%>
                <table class="dtb">
                    <tr>
                        <th>SR NO</th>
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
                        <th>Create Invoice</th>
                    </tr>
                    <%  int index2 = 1;
                        for (WorkBean wo1 : bill) {%>
                    <tr>
                        <td><%=index2++%></td> 
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
                        <% if (!request.getParameterMap().containsKey("inv")) {%>
                        <td>
                            <% if (wo1.getSTATUS().equals("Approved")) {%>
                            <%if (!request.getParameterMap().containsKey("step")) {%>

                            <form  id="testForm" action="CreateAP?bill=<%=wo1.getBILL_NO()%>&page=inv&step=1"
                                   method="post" onsubmit="
                                           submit.disabled = true;
                                           submit.value = 'Processing ..';
                                           return true;">
                                <input type="submit" value="Create PO"  
                                       onclick="alert('Purchase Order Creation process Start');"
                                       name="submit" /></form>   


                            <% } else {
                                String step = request.getParameter("step");
                                String b = request.getParameter("bill");

                                if (step.equalsIgnoreCase("2") && wo1.getBILL_NO().equalsIgnoreCase(b)) {%>


                            <form  id="testForm" action="CreateAP?bill=<%=wo1.getBILL_NO()%>&page=inv&step=2"
                                   method="post" onsubmit="submit.disabled = true;
                                           submit.value = 'Processing ..';
                                           return true;">
                                <input type="submit" value="Refresh" 
                                       onclick="alert('Checking PO is created or not');"
                                       name="submit"/></form>


                            <% } else if (step.equalsIgnoreCase("3") && wo1.getBILL_NO().equalsIgnoreCase(b)) {%>


                            <form  id="testForm" action="CreateAP?bill=<%=wo1.getBILL_NO()%>&page=inv&step=3"
                                   method="post" onsubmit="submit.disabled = true;
                                           submit.value = 'Processing ..';
                                           return true;">
                                <input type="submit" value="Add PO Tax" 
                                       onclick="alert('Adding tax into PO');"
                                       name="submit"/></form>


                            <% } else if (step.equalsIgnoreCase("4") && wo1.getBILL_NO().equalsIgnoreCase(b)) {%>


                            <form  id="testForm" action="CreateAP?bill=<%=wo1.getBILL_NO()%>&page=inv&step=4"
                                   method="post" onsubmit="submit.disabled = true;
                                           submit.value = 'Processing ..';
                                           return true;">
                                <input type="submit" value="Refresh" 
                                       onclick="alert('Checking Tax has been added to PO or not');"
                                       name="submit"/></form>  


                            <% } else if (step.equalsIgnoreCase("5") && wo1.getBILL_NO().equalsIgnoreCase(b)) {%>


                            <form  id="testForm" action="CreateAP?bill=<%=wo1.getBILL_NO()%>&page=inv&step=5"
                                   method="post" onsubmit="submit.disabled = true;
                                           submit.value = 'Processing ..';
                                           return true;">
                                <input type="submit" value="Create Invoice" 
                                       onclick="alert('Creating Ap Invoice');"
                                       name="submit"/></form>  


                            <% } else if (step.equalsIgnoreCase("6") && wo1.getBILL_NO().equalsIgnoreCase(b)) {%>


                            <form  id="testForm" action="CreateAP?bill=<%=wo1.getBILL_NO()%>&page=inv&step=6"
                                   method="post" onsubmit="submit.disabled = true;
                                           submit.value = 'Processing ..';
                                           return true;">
                                <input type="submit" value="Refresh" 
                                       onclick="alert('Checking Invoice has been created or not !');"
                                       name="submit"/></form>
                                <%     }
                                    }
                                %>

                        </td>
                        <%}
                            }%>
                    </tr>








                    <%}
                        }%>
                </table>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
