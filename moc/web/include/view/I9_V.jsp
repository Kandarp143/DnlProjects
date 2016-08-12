<table class="form_table"  id="myT" name="myT" >
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
        <%if (bean.getComDateString() == null) {%>
        <td>-</td>
        <%} else {%>
        <td><%=bean.getComDateString()%></td>
        <%}%>
    </tr>
    <%} else {%>
    <tr>
        <td><%=bean.getDocId()%></td>
        <td><%=bean.getDocName()%></td>
        <td><%=gm.getViewCheckBox(bean.getUp())%></td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <%}
        }%>
</table>