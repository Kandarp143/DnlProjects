<%-- 
    Document   : workflow
    Created on : Apr 7, 2015, 10:47:11 AM
    Author     : 02948
--%>

<%@page import="Logic.GetMethod"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String wfsql = "SELECT   cba_stg_mst.stg_name, cba_stg_mst2.stg_name, cba_user_mst.full_name,"
            + "         cba_act_mst.act_name, TO_CHAR(cba_tran_bill.act_date, 'DD-MON-YYYY'), cba_tran_bill.cmt,"
            + "         cba_user_mst2.full_name, cba_tran_bill.is_comp"
            + "    FROM cba_act_mst,"
            + "         cba_tran_bill,"
            + "         cba_user_mst,"
            + "         cba_bill_mst,"
            + "         cba_stg_mst,"
            + "         cba_stg_mst cba_stg_mst2,"
            + "         cba_user_mst cba_user_mst2"
            + "   WHERE (    (cba_act_mst.act_id = cba_tran_bill.act_id)"
            + "          AND (cba_bill_mst.bill_no = cba_tran_bill.bill_no)"
            + "          AND (cba_user_mst.user_id = cba_tran_bill.user_id)"
            + "          AND (cba_user_mst2.user_id = cba_tran_bill.nxt_uid)"
            + "          AND (cba_stg_mst.stg_id = cba_tran_bill.stg_c)"
            + "          AND (cba_stg_mst2.stg_id = cba_tran_bill.stg_n)"
            + "          AND (cba_tran_bill.bill_no = '" + request.getParameter("bill") + "')"
            + "         )"
            + "ORDER BY cba_tran_bill.tran_id";
    Logic.GetMethod g = new GetMethod();
    ResultSet wfrs = g.Get_rs(wfsql);

    if (!wfrs.isBeforeFirst()) {
%>
<h2 id="data_header">Workflow is not initiated for <%=request.getParameter("pono")%></h2>
<%        } else {
%>
<table class="dtb">

    <tr>
        <th>From Stage</th>
        <th>From User</th>
        <th>Action</th>
        <th>Action Date</th>
        <th>Comment</th>
        <th>To Stage</th>
        <th>To User</th>
        <th>Status</th>
    </tr>
    <%
        while (wfrs.next()) {%>
    <tr>
        <td><%=wfrs.getString(1)%></td>
        <td><%=wfrs.getString(3)%></td>
        <td><%=wfrs.getString(4)%></td>
        <td><%=wfrs.getString(5)%></td>
        <td><%=wfrs.getString(6)%></td>
        <td><%=wfrs.getString(2)%></td>
        <td><%=wfrs.getString(7)%></td>
        <% if (wfrs.getString(8) == "" || wfrs.getString(8) == null) {%>
        <td style="font-weight: 700;color: red">Pending</td>
        <%} else if ("R".equals(wfrs.getString(8))) {%>
        <td style="color: red">Rejected</td>
        <%} else {%>
        <td style="color: green">Completed</td>
        <%}%>
    </tr>
    <%}
        }
        wfrs.close();
    %>
</table>
