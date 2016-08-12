<%@page import="Bean.DashBean"%>
<%@page import="Bean.DashBean"%>
<%@page import="Dao.DashDao"%>
<%@page import="java.util.ArrayList"%>

<table class="dtb" id="tt2" >
    <tr>
        <td style="font-weight: bolder;color:#A70303">Item Consumption By Location</td>
    </tr>
    <tr>
        <th>Item</th>
        <th>Location</th>
        <th>Total QTY (MT)</th>
    </tr>
    <%
        ArrayList<DashBean> list2_1 = new ArrayList<DashBean>();
        list2_1 = ddao2.Dash2_1();
        for (DashBean bean : list2_1) {
    %>
    <tr>
        <td><%=bean.getITEM_DESC()%></td>
        <td><%=bean.getLOC_ID()%></td>
        <td class="cur"><%=bean.getQTY()%></td>
    </tr>
    <%}%>
</table>    

<table class="dtb" id="tt3">
    <tr>
        <td style="font-weight: bolder;color:#A70303">Item Consumption By Purpose of Consume</td>
    </tr>
    <tr>
        <th>Item</th>
        <th>Purpose of Consume</th>
        <th>Total QTY (MT)</th>
    </tr>
    <%
        list2_1.clear();
        list2_1 = new ArrayList<DashBean>();
        ddao2 = new DashDao();
        list2_1 = ddao2.Dash2_2();
        for (DashBean bean : list2_1) {
    %>
    <tr>
        <td><%=bean.getITEM_DESC()%></td>
        <td ><%=bean.getLOC_ID()%></td>
        <td class="cur"><%=bean.getQTY()%></td>
    </tr>
    <%}%>
</table> 
<table class="dtb" id="tt4">
    <tr>
        <td style="font-weight: bolder;color:#A70303">Item Consumption By User</td>
    </tr>
    <tr>
        <th>Item</th>
        <th>Consume By</th>
        <th>Total QTY (MT)</th>
    </tr>
    <%
        list2_1.clear();
        list2_1 = new ArrayList<DashBean>();
        ddao2 = new DashDao();
        list2_1 = ddao2.Dash2_3();
        for (DashBean bean : list2_1) {
    %>
    <tr>
        <td><%=bean.getITEM_DESC()%></td>
        <td ><%=bean.getLOC_ID()%></td>
        <td class="cur"><%=bean.getQTY()%></td>
    </tr>
    <%}%>
</table>
