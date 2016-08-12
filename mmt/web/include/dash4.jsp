
<%@page import="Dao.DashDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.DashBean"%>
<%@page import="Dao.DashDao"%>
<%
    ArrayList<DashBean> vsAns = new ArrayList<>();
    vsAns = ddao2.Dash_EdnlVSmmt();
%>
<table class="dtb" id="tt6" >
    <tr>
        <th colspan="5">ERP Onhand Stock</th>

    </tr>
    <tr>
        <th>Item</th>
        <th>Location</th>
        <th>Lot NO</th>
        <th>MMT QTY</th>
        <th>ERP QTY</th>
    </tr>
    <%
        for (DashBean ans : vsAns) {
    %>
    <tr>
        <td><%=ans.getITEM_DESC()%></td>
        <td><%=ans.getLOC_ID()%></td>
        <td><%=ans.getLOT_NO()%></td>
        <%if (ans.getQTY() == ans.getRE_QTY()) {%>
        <td class="cur"><%=ans.getQTY()%></td>
        <td class="cur"><%=ans.getRE_QTY()%></td>
        <%} else {%>
        <td class="cur" style="font-weight: bolder;color:#A70303"><%=ans.getQTY()%></td>
        <td class="cur" style="font-weight: bolder;color:#A70303"><%=ans.getRE_QTY()%></td>
        <%}%>
    </tr>
    <%}%>
</table>
