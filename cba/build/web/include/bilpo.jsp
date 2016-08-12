<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
<%@page import="Dao.BillDao"%>
<%@page import="Bean.WorkBean"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<WorkBean> billpo = new ArrayList<WorkBean>();
    BillDao billdao = new BillDao();
    billpo = billdao.getBillbyPONO(request.getParameter("pono"));
%>
<% if (billpo.size() == 0) {%>
<p class="dtb"  >No Bill are generated against this WO</p>
<%} else {%>
<table class="dtb">
    <tr>
        <th>SR NO</th>
        <th>Bill ID</th>
        <th>WO Number</th>
        <th>Bill Date</th>
        <th>Created By</th>
        <th>Supplier</th>
        <th>Site</th>
        <th>Operating Unit</th>
        <th>Total Value</th>
        <th>Detail</th>
    </tr>
    <%  int index4 = 1;
        for (WorkBean wobill : billpo) {%>
    <tr>
        <td><%=index4++%></td>
        <td><%=wobill.getBILL_NO()%></td>
        <td><%=wobill.getPO_NO()%></td>
        <td><%=wobill.getBILL_DATE()%></td>
        <td><%=wobill.getUSER_NAME()%></td>
        <td><%=wobill.getSUP_NAME()%></td>
        <td><%=wobill.getSITE()%></td>
        <td><%=wobill.getOU()%></td>
        <td><%=wobill.getTOTAL_VAL()%></td>
        <td><a href="billdetail.jsp?bill=<%=wobill.getBILL_NO()%>&sid=0&pono=<%=wobill.getPO_NO()%>">Click Here </a></td>
    </tr>
    <% }
        }%>
</table>