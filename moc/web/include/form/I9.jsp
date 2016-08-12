<%@page import="kp.beans.user.pojo.MocUserMst"%>
<%@page import="kp.beans.data.pojo.MocInitTskMst"%>
<%@page import="kp.dao.user.UserDao"%>
<%@page import="kp.logic.DropDown"%>
<%@page import="java.util.ArrayList"%>
<%
    DropDown Tskdd = new DropDown();
    UserDao Tskud = new UserDao();
    ArrayList<MocInitTskMst> tsklist = Tskdd.getTskDD();
    ArrayList<MocUserMst> userlist = Tskud.getUserByUnit(session.getAttribute("usrunit").toString());
%>
<table class="form_table" >
    <tr>
        <th colspan="2">Documentation to be updated</th>
        <th>Responsible Party</th>
        <th>Target Date</th>
    </tr>
    <%for (MocInitTskMst bean : tsklist) {%>
    <tr>
        <td><%=bean.getDocId()%></td>
        <td><%=bean.getDocTitle()%></td>
        <td> <select name="q<%=bean.getDocId()%>" id="q<%=bean.getDocId()%>">
                <option value="0">---Select User---</option>
                <%for (MocUserMst usr : userlist) {%>
                <option value="<%=usr.getUserId()%>"><%=usr.getUserName()%></option>
                <%}%>
            </select>
        </td>
        <td>
            <input type="text" name="r<%=bean.getDocId()%>" id="r<%=bean.getDocId()%>"/>
        </td>
    </tr>
    <%}%>
</table>