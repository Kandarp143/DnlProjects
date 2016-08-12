<%@page import="kp.beans.mst.pojo.MocUserSelection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.dao.wf.WfUserDao"%>
<%@page import="kp.beans.wf.pojo.MocWfUser"%>
<%@page import="kp.dao.wf.TranDao"%>
<table class="form_table">
    <tr>
        <th>Action</th>
        <td>
            <% if (request.getParameter("sid").equalsIgnoreCase("21")) {%>
            <input type="radio" name="act" value="6"> Verified
            <input type="radio" name="act" value="7"> Not Verified
            <input type="radio" name="act" value="5"> Reject
            <%} else {%>
            <input type="radio" name="act" value="3"> Promote
            <input type="radio" name="act" value="4"> Demote
            <input type="radio" name="act" value="5"> Reject
            <%}%>
        </td>
    </tr>
</table>
<div id='show-me' style='display:none'>
    <table class="form_table">
        <tr>
            <th colspan="2">
                Demote To:   
            </th>
        </tr>
        <%
            WfUserDao udao = new WfUserDao();
            ArrayList<MocUserSelection> UserList
                    = udao.getBackUser(Integer.parseInt(request.getParameter("cid")), Integer.parseInt(request.getParameter("sid")));
            for (MocUserSelection user : UserList) {
        %>
        <tr>
            <th><%=user.getStgName()%></th>
            <td><input type="radio" name="usr" value="<%=user.getUserId()%>/<%=user.getStgId()%>"/><%=user.getUserName()%></td>
        </tr>
        <%}%>
    </table>
</div>
