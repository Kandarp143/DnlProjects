
<%@page import="Logic.GetMethod"%>
<%@page import="java.sql.ResultSet"%>
<%
    String wosql = "SELECT cba_tran_wo.po_number, TO_CHAR(cba_wo_mst.cr_date, 'DD-MON-YYYY'), cba_user_mst2.full_name,"
            + "       cba_stg_mst.stg_name, cba_act_mst.act_name, cba_user_mst.full_name,"
            + "       cba_stg_mst2.stg_name,cba_tran_wo.stg_n,po_vendors.vendor_name"
            + "  FROM cba_act_mst,"
            + "       cba_tran_wo,"
            + "       cba_user_mst,"
            + "       cba_wo_mst,"
            + "       cba_stg_mst,"
            + "       cba_stg_mst cba_stg_mst2,"
            + "       cba_user_mst cba_user_mst2,"
            + "       po_vendors"
            + " WHERE (    (cba_act_mst.act_id = cba_tran_wo.act_id)"
            + "        AND (cba_wo_mst.po_no = cba_tran_wo.po_number)"
            + "        AND (cba_user_mst.user_id = cba_tran_wo.user_id)"
            + "        AND (cba_stg_mst.stg_id = cba_tran_wo.stg_c)"
            + "        AND (cba_stg_mst2.stg_id = cba_tran_wo.stg_n)"
            + "        AND (cba_user_mst2.user_id = cba_wo_mst.user_id)"
            + "        AND (cba_tran_wo.nxt_uid = '" + session.getAttribute("uid").toString() + "')"
            + "        AND (cba_tran_wo.is_comp is NULL)"
            + "        AND(cba_wo_mst.sup_id = po_vendors.vendor_number)"
            + "       )ORDER BY cba_tran_wo.po_number,cba_tran_wo.tran_id";
    Logic.GetMethod g = new GetMethod();
    ResultSet wors = g.Get_rs(wosql);

    if (!wors.isBeforeFirst()) {
%>
<p class="dtb"  >No WOs are pending in your inbox</p>
<%        } else {
%>
<table class="dtb"> 

    <tr>
        <th>Pending WO</th>
    </tr>
    <tr>
        <th>WO Number</th>
        <th>Supplier</th>
        <th>Create Date</th>
        <th>Creator</th>
        <th>From Activity</th>
        <th>Action done</th>
        <th>Action done by</th>
        <th>To Activity</th>
        <th>Action</th>
    </tr>

    <%
        while (wors.next()) {%>
    <tr>
        <td><%=wors.getString(1)%></td>
        <td><%=wors.getString(9)%></td>
        <td><%=wors.getString(2)%></td>
        <td><%=wors.getString(3)%></td>
        <td><%=wors.getString(4)%></td>
        <td><%=wors.getString(5)%></td>
        <td><%=wors.getString(6)%></td>
        <td><%=wors.getString(7)%></td>
        <td><a href="wodetail.jsp?pono=<%=wors.getString(1)%>&sid=<%=wors.getString(8)%>">Click Here</a></td>
    </tr>
    <%
            }
        }
        wors.close();
    %>
</table>
<%
    wosql = "SELECT cba_tran_bill.bill_no,cba_bill_mst.po_no, TO_CHAR(cba_bill_mst.cr_date, 'DD-MON-YYYY'), cba_user_mst2.full_name,"
            + "       cba_stg_mst.stg_name, cba_act_mst.act_name, cba_user_mst.full_name,"
            + "       cba_stg_mst2.stg_name,cba_tran_bill.stg_n"
            + "  FROM cba_act_mst,"
            + "       cba_tran_bill,"
            + "       cba_user_mst,"
            + "       cba_bill_mst,"
            + "       cba_stg_mst,"
            + "       cba_stg_mst cba_stg_mst2,"
            + "       cba_user_mst cba_user_mst2"
            + " WHERE (    (cba_act_mst.act_id = cba_tran_bill.act_id)"
            + "        AND (cba_bill_mst.bill_no = cba_tran_bill.bill_no)"
            + "        AND (cba_user_mst.user_id = cba_tran_bill.user_id)"
            + "        AND (cba_stg_mst.stg_id = cba_tran_bill.stg_c)"
            + "        AND (cba_stg_mst2.stg_id = cba_tran_bill.stg_n)"
            + "        AND (cba_user_mst2.user_id = cba_bill_mst.user_id)"
            + "        AND (cba_tran_bill.nxt_uid = '" + session.getAttribute("uid").toString() + "')"
            + "        AND (cba_tran_bill.is_comp is NULL)"
            + "       )ORDER BY cba_tran_bill.bill_no";
    g = new GetMethod();
    wors = g.Get_rs(wosql);

    if (!wors.isBeforeFirst()) {
%>
<p class="dtb"  >No Bills are pending in your inbox</p>
<%        } else {
%>
<table class="dtb"> 

    <tr>
        <th colspan="2">Pending Bill</th>
    </tr>
    <tr>
        <th>Bill ID</th>
        <th>WO Number</th>
        <th>Create Date</th>
        <th>Creator</th>
        <th>From Activity</th>
        <th>Action done</th>
        <th>Action done by</th>
        <th>To Activity</th>
        <th>Action</th>
    </tr>

    <%
        while (wors.next()) {%>
    <tr>
        <td><%=wors.getString(1)%></td>
        <td><%=wors.getString(2)%></td>
        <td><%=wors.getString(3)%></td>
        <td><%=wors.getString(4)%></td>
        <td><%=wors.getString(5)%></td>
        <td><%=wors.getString(6)%></td>
        <td><%=wors.getString(7)%></td>
        <td><%=wors.getString(8)%></td>
        <td><a href="billdetail.jsp?bill=<%=wors.getString(1)%>&sid=<%=wors.getString(9)%>&pono=<%=wors.getString(2)%>">Click Here</a></td>
    </tr>
    <%
            }
        }
        wors.close();
    %>
</table>
