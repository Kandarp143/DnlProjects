<table class="dtb">
    <%for (MocUserSelection bean : users) {%>
    <tr>
        <td><%=bean.getStgName()%></td>
        <td><%=bean.getUserName()%></td>
    </tr>
    <%}%>
</table>
