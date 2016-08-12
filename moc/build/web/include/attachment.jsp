<%@page import="kp.dao.data.AttDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.beans.wf.pojo.MocWfAtt"%>
<table class="dtb">
    <tr>
        <th>Print Moc Report</th>
        <td><a href="viewMocP.jsp?cid=<%=request.getParameter("cid")%>">Click Here</a></td>

    </tr>
    <tr>
        <td colspan="2"> <span class="err">After open new page , click on print page button on the top</span></td>
    </tr>
</table>
<table class="dtb">
    <td style="float: right;color: rosybrown">
        Click Here for attach file-->
    </td>
    <td>
        <input type="button" id="aa" name="aa" value="Add Attachment" onclick="aa()"/>

    </td>
</table>

<script type="text/javascript">
    var popup;
    function aa() {
        popup = window.open("include/fileupload.jsp?status=N&cid=<%=request.getParameter("cid")%>", "att", "width=500,height=200");
        popup.focus();
        return false;
    }
</script>
<%
    ArrayList<MocWfAtt> att = new ArrayList<>();
    AttDao attdao = new AttDao();
    att = attdao.getMocAtt(Integer.parseInt(request.getParameter("cid")));
    for (MocWfAtt ab : att) {
        if (ab.getAttType().equalsIgnoreCase("RPT")) {

%>
<table class="dtb">
    <tr>
        <th>MOC Report</th>
        <td><a href="<%=ab.getFPath()%>" style="color: #006699" target="_blank"><%=ab.getFName()%></a> </td>
    </tr>
</table>
<%}
    }%>

<table class="dtb">

    <tr>
        <th>Attachment</th>
        <th>Action Date</th>
        <th>Attached By </th>
    </tr>

    <%
        for (MocWfAtt bean2 : att) {
            if (!bean2.getAttType().equalsIgnoreCase("RPT")) {
    %>
    <tr>
        <td><a href="<%=bean2.getFPath()%>" style="color: #006699" target="_blank"><%=bean2.getFName()%></a> </td>
        <td><%=bean2.getActDateString()%> </td>
        <td><%=bean2.getUserName()%></td>
    </tr>
    <%}
        }%>     

</table>





