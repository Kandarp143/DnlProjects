<%@page import="kp.logic.GeneralMethods"%>
<%@page import="kp.beans.data.pojo.MocInitTskDt"%>
<%@page import="kp.dao.data.MocDao"%>
<%@page import="kp.beans.user.pojo.MocUserMst"%>
<%@page import="kp.beans.data.pojo.MocInitTskMst"%>
<%@page import="kp.dao.user.UserDao"%>
<%@page import="kp.logic.DropDown"%>
<%@page import="java.util.ArrayList"%>
<%
    MocDao mocdao = new MocDao();
    ArrayList<MocInitTskDt> uptasks = mocdao.fetchTaskList(Integer.parseInt(request.getParameter("cid")));
    GeneralMethods gm = new GeneralMethods();
%>
<table class="form_table" >
    <tr>
        <th colspan="2">Documentation to be updated</th>
        <th>isUpdate</th>
        <th>Responsible Party</th>
        <th>Target Date</th>
        <th>Completed Date</th>
    </tr>
    <%for (MocInitTskDt bean : uptasks) {
            if (bean.getUp() == "true") {%>
    <tr>
        <td><%=bean.getDocId()%></td>
        <td><%=bean.getDocName()%></td>
        <td><%=gm.getViewCheckBox(bean.getUp())%></td>
        <td><%=bean.getResPartyName()%></td>
        <td><%=bean.getTarDateString()%></td>
        <td>
            <input type="text" name="s<%=bean.getDocId()%>" id="s<%=bean.getDocId()%>"/>
        </td>
    </tr>
    <%}
        }%>
</table>
