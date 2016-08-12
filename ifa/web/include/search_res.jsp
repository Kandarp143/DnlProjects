<%@page import="Dao.ItemDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.Item"%>
<%
    ItemDao idao1 = new ItemDao();
    ArrayList<Item> searcheditm = idao1.getRelatedItem(request.getParameter("desc"));
%>
<table  id="tab" class="dtb">
    <thead>
        <tr>
            <th colspan="5">
    <h3>Searched Items</h3>
</th>
</tr>
<tr>
    <!--<th>Select</th>-->
    <th>SR.NO</th>
    <th>ITEM ID </th>
    <th>ITEM NO</th>
    <th>ITEM DESCRIPTION</th>
</tr>
</thead>
<tbody>
    <% if (searcheditm.size() == 0) {%>
    <tr>
        <td>No Items Inactive till now</td>
    </tr>
    <%} else {
                int i = 1;%>
    <% for (Item b : searcheditm) {%>
    <tr>
        <td><%=i++%></td>
        <td><%=b.getItem_id()%></td>
        <td><%=b.getItem_no()%></td>
        <td><%=b.getItem_desc()%></td>
    </tr>
    <%}
                }%>
</tbody>
</table>