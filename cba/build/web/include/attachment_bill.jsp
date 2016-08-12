<%@page import="Logic.GetMethod"%>
<%@page import="java.sql.ResultSet"%>

<div style="float: right">  
    <input type="button" id="aa" name="aa" value="Add Attachment" onclick="aa()"/>
</div>
<script type="text/javascript">
    var popup;
    function aa() {
        popup = window.open("include/F_Upload.jsp?status=N&pono=<%=request.getParameter("pono")%>&bill=<%=request.getParameter("bill")%>", "att", "width=500,height=200");
        popup.focus();
        return false;
    }
</script>

<%
    String attsql = "SELECT cba_att_mst.f_name, cba_att_mst.f_path, cba_att_mst.po_number,"
            + "       cba_att_mst.bill_no, cba_user_mst.full_name, TO_CHAR(cba_att_mst.act_date, 'DD-MON-YYYY')"
            + "  FROM cba_att_mst, cba_user_mst"
            + " WHERE ((cba_user_mst.user_id = cba_att_mst.user_id)"
            + "       AND cba_att_mst.att_type='ATT' "
            + "        AND (cba_att_mst.bill_no = '" + request.getParameter("bill") + "')"
            + "       )";
    Logic.GetMethod attg = new GetMethod();
    ResultSet attrs = attg.Get_rs(attsql);

    if (!attrs.isBeforeFirst()) {
%>
<h2 id="data_header">No Attachment till Now</h2>
<%        } else {
%>
<table class="dtb"> 

    <tr>
        <th>Attachment List</th>
    </tr>
    <tr>
        <th>File Name</th>
        <th>WO Number</th>
        <th>Bill ID</th>
        <th>Attached By</th>
        <th>Date</th>
    </tr>

    <%
        while (attrs.next()) {%>
    <tr>
        <td><a href="<%=attrs.getString(2)%>" target="blank"><%=attrs.getString(1)%></a></td>
        <td><%=attrs.getString(3)%></td>
        <td><%=attrs.getString(4)%></td>
        <td><%=attrs.getString(5)%></td>
        <td><%=attrs.getString(6)%></td>
    </tr>
    <%
            }
        }
        attrs.close();
    %>
</table>
<%
    attsql = "SELECT f_name, f_path"
            + "  FROM cba_att_mst"
            + " WHERE att_id = (SELECT MAX (att_id)"
            + "                   FROM cba_att_mst"
            + "                  WHERE bill_no = '" + request.getParameter("bill") + "' AND att_type = 'RPT')";
    attrs = attg.Get_rs(attsql);

    if (!attrs.isBeforeFirst()) {
%>
<h2 id="data_header">No Report Generated ! Contact Administrator</h2>
<%        } else {
%>
<table class="dtb"> 
    <%
        while (attrs.next()) {%>
    <tr>
        <th>Report</th>
        <td><a href="<%=attrs.getString(2)%>" target="blank"><%=attrs.getString(1)%></a></td>
    </tr>
    <%
            }
        }
        attrs.close();
    %>
</table>
