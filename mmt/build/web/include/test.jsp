<%@page import="Bean.ItemBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dao.DashDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<% SimpleDateFormat formatter = new SimpleDateFormat("MMMyyyy");
    String today = formatter.format(new java.util.Date());
    ArrayList<ItemBean> summarylist = new ArrayList<ItemBean>();
    DashDao ddao1 = new DashDao();
    summarylist = ddao1.getStockSummary();
%>
<table class="dtb" id="tt">
    <tr>
        <td  style="font-weight: bolder;color:#A70303">Stocked QTY Summary in (MT)</td>  
        <td  style="font-weight: bolder;color:#A70303">Opening Period :  <%=today%></td> 
    </tr>
    <tr>
        <th>Description</th>
        <th>Location</th>
        <th>In Transit</th>
        <th>Opening Stock</th>
        <th>Monthly Received</th>
        <th>Total Received</th>
        <th>Monthly Consume</th>
        <th>Net Available</th>
        <th>Detail </th>
    </tr>
</table>