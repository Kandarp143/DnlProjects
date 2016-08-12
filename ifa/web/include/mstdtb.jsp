<%@page import="Dao.ItemDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.Item"%>
<%
    ItemDao idao1 = new ItemDao();
    ArrayList<Item> deldoneItems = idao1.getDelDoneItem();
    ArrayList<Item> itmbygrp = new ArrayList<Item>();
    itmbygrp = idao1.getItem(request.getParameter("grp"), deldoneItems);
%>
<FORM method="POST" ACTION="SelItm?grp=<%=request.getParameter("grp")%>">
    <table  id="tab2" class="dtb">
        <thead>
            <tr>
                <th colspan="2" style="color: darkred">
                    Group : <%=request.getParameter("grp")%>
                </th>
                <td></td>
                <td></td>
                <td>
                    <input type="submit" value="Search Related Item" id="submit1" 
                           name="submit1" onsubmit="this.disabled = true;
                                   this.value = 'Processing ..';
                                   return true;"  />
                </td>
                <td colspan="2">
                    <input type="submit" value="Inactive" id="submit2" 
                           name="submit2" onsubmit="this.disabled = true;
                                   this.value = 'Processing ..';
                                   return true;"  />
                </td>

                <td colspan="2"> 
                    <input type="submit" value="Searched" id="submit3" 
                           name="submit3" onsubmit="this.disabled = true;
                                   this.value = 'Processing ..';
                                   return true;"  />
                </td>
            </tr>
            <tr>
                <th>INACT</th>
                <th>SELECT</th>
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
            <% for (Item b : itmbygrp) {%>
            <tr>
                <td><input type="checkbox" name="revitm" value="<%=b.getItem_no()%>"></td>
                <td><input type="radio" name="selitm" value="<%=b.getItem_no()%>"></td>
                <td><%=b.getItem_no()%></td>
                <td><%=b.getItem_desc()%></td>
                <td><%=b.getMin_max_flag()%></td>
                <td><%=b.getOnhand_qty() == null ? "-" : b.getOnhand_qty()%></td>
                <td><%=b.getCnt()%></td>
                <td><%=b.getUom()%></td>
                <td><%=b.getLast_trx_date()%></td>
            </tr>
            <%}%>
        </tbody>
    </table>
</FORM>
