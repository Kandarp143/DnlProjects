<%@page import="Bean.DDBean"%>
<%@page import="Bean.DashBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dao.DashDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
    ArrayList<DDBean> cus_list = new ArrayList<DDBean>();
    DashDao ddao3 = new DashDao();
    cus_list = ddao3.Dash3_CusList();
    ArrayList<DashBean> DList3 = new ArrayList<DashBean>();
    DashDao ddao3_1 = new DashDao();
    DList3 = ddao3_1.Dash3_1();
    if (request.getParameterMap().containsKey("cus")) {
%>
<form action="dashboard.jsp?cus" method="post"  onsubmit="submit.disabled = true;
        submit.value = 'Processing ..';
        return true;">
    <select name="cusno" id="cusno">
        <option value="<%=request.getParameter("cusno")%>"></option>
        <%for (DDBean d : cus_list) {%>
        <option value="<%=d.getCus_name()%>"><%=d.getCus_name()%></option>
        <%}%>
    </select>
    <input type="submit">
</form>
<%DList3 = ddao3_1.Dash3_Customer_Collection(request.getParameter("cusno"));%>
<table class="dtb" id="tt5">
    <tr>
        <td  style="font-weight: bolder;color:#A70303">Total Collection Overview Report</td>  
    </tr>
    <tr>
        <th>Customer Name</th>
        <th>Receipt No</th>
        <th>Invoice No</th>
        <th>Collection</th>
        <th>GL Date</th>
        <th>Currency Code</th>
    </tr>
    <%for (DashBean bean : DList3) {%>
    <tr>
        <td><%=bean.getCUS_NAME()%></td>
        <td><%=bean.getRECIEPT_NO()%></td>
        <td><%=bean.getINV_NO()%></td>
        <td><%=bean.getTOTAL_RECV()%></td>
        <td><%=bean.getGLDATE()%></td>
        <td><%=bean.getUOM()%></td>

    </tr>
    <%}%>
</table>
<%} else {%>
<form action="dashboard.jsp?cus" method="post"  onsubmit="submit.disabled = true;
        submit.value = 'Processing ..';
        return true;">
    <select name="cusno" id="cusno">
        <%for (DDBean d : cus_list) {%>
        <option value="<%=d.getCus_no()%>"><%=d.getCus_name()%></option>
        <%}%>
    </select>
    <input type="submit">
</form>
<%}%>


