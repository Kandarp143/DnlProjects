<%@page import="Bean.SumBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dao.SumDao"%>
<%@page import="Dao.SumDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<% SimpleDateFormat formatter = new SimpleDateFormat("MMMyyyy");
    String today = formatter.format(new java.util.Date());
    SumDao dao = new SumDao();
    ArrayList<SumBean> stocklist = new ArrayList<>();
    stocklist = dao.getFinalSummary();
%>
<table class="dtb" id="tt2">
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
        <th>Monthly Consume</th>
        <th>Net Available</th>
        <th>Consumption Detail </th>
    </tr>
    <%for (SumBean bean : stocklist) {%>
    <tr>
        <td><a href="dash_detail_st.jsp?itm=<%=bean.getItm_code()%>&loc=<%=bean.getItm_loc()%>" style="color: #3b5998"><%=bean.getItm_name()%></a></td>
        <td><%=bean.getItm_loc()%></td>
        <td class="cur"><%=bean.getIn_transit()%></td>
        <td class="cur"><%=bean.getOp_stock()%></td>
        <td class="cur" ><%=bean.getM_rec()%></td>
        <% if (bean.getM_con() == 0) {%>
        <td class="cur"><%=bean.getM_con()%></td>
        <% } else {%>
        <td class="cur" style="color: red"><%=bean.getM_con()%></td>
        <%}%>
        <% if (bean.getIn_transit() == 0) {%>
        <td class="cur " style="color: green"><%=bean.getNet_av()%></td>
        <% } else {%>
        <td class="cur " style="color: green">-</td>
        <%}%>
        <td><a href="dash_detail.jsp?itm=<%=bean.getItm_code()%>&loc=<%=bean.getItm_loc()%>" style="color: #3b5998">click here</a></td>
    </tr>
    <%}%>
</table>

