<%@page import="java.util.Collections"%>
<%@page import="Dao.ItemDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.Item"%>
<%
    ItemDao idao2 = new ItemDao();
    ArrayList<Item> relItem = new ArrayList<Item>();
    String selitmno = request.getParameter("selitm");
    Item selected = new Item();
    selected.setItem_no(selitmno);
    selected.setItem_id(idao2.getItemPera(selitmno, "INVENTORY_ITEM_ID"));
    selected.setItem_desc(idao2.getItemPera(selitmno, "description"));
    relItem = idao2.getRelatedItem(selected);
    idao2.insDoneItem(selected);
%>
<FORM method="POST" ACTION="Selection?selitm=<%=request.getParameter("selitm")%>">
    <table id="tab1" class="dtb">
        <thead>
            <tr>
                <th colspan="3">
                    <input type="submit" value="Inactive" id="submit" 
                           name="submit" onsubmit="this.disabled = true;
                                   this.value = 'Processing ..';
                                   return true;"  />
                </th>
                <th colspan="7" style="color: darkred">
                    Selected Item no : <%=selected.getItem_no()%><br/>
                    Selected Item Desc : <%=selected.getItem_desc()%>
                </th>
            </tr>
            <tr>
                <th>Select</th>
                <th>ITEM NO</th>
                <th>ITEM DESCRIPTION</th>     
                <th>MIN_MAX_F</th>
                <th>ON HAND QTY</th>
                <th>CNT</th>
                <th>U.O.M</th>
                <th>LAST TRAX DATE</th>
            </tr>
        </thead>
        <tbody>
            <% for (Item b : relItem) {%>
            <tr>
                <td><input type="checkbox" name="delitm" value="<%=b.getItem_no()%>"></td>
                <td><%=b.getItem_no()%></td>
                <td><%=b.getItem_desc()%></td>
                <td><%=b.getMin_max_flag()%></td>
                <td><%=b.getOnhand_qty()%></td>
                <td><%=b.getCnt()%></td>
                <td><%=b.getUom()%></td>
                <td><%=b.getLast_trx_date()%></td>
            </tr>
            <%}%>
        </tbody>
    </table>
</FORM>

