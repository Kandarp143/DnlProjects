<%@page import="kp.dao.wf.TranDao"%>
<%@page import="kp.beans.wf.pojo.MocWfTran"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<MocWfTran> workflow = new ArrayList<>();
    TranDao trandao = new TranDao();
    workflow = trandao.getTransactions(Integer.parseInt(request.getParameter("cid")));
%>
<table class="form_table" style="table-layout: fixed">
    <tr><th colspan="8">Case Overview</th></tr>
    <tr>
        <th>From Stage</th>
        <th>To Stage</th>
        <th>User</th>
        <th>Action</th>
        <th>Action Date</th>
        <th>Comment</th>
        <th>Next User</th>
        <th>Is Completed</th>    
    </tr>
    <%for (MocWfTran workflowb : workflow) {%>
    <tr>
        <td><%=workflowb.getStgCname()%></td>
        <td><%=workflowb.getStgNname()%></td>
        <td><%=workflowb.getUserCname()%></td>
        <td><%=workflowb.getActname()%></td>
        <td><%=workflowb.getActDateString()%></td>
        <td><%=workflowb.getCmt()%></td>
        <td><%=workflowb.getUserNname()%></td>
        <%if (workflowb.getIsCompletedStatus().equalsIgnoreCase("Completed")) {%>
        <td style="color: green"><%=workflowb.getIsCompletedStatus()%></td>
        <%} else if (workflowb.getIsCompletedStatus().equalsIgnoreCase("Pending")) {%>
        <td style="color: red"><%=workflowb.getIsCompletedStatus()%></td>
        <%} else {%>
        <td style="color: #660000"><%=workflowb.getIsCompletedStatus()%></td>
        <%}%>
    </tr>
    <%}%>

</table>