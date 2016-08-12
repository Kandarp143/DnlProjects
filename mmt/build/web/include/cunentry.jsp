<%@page import="Bean.ItemBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dao.ItemDao"%>
<table class="dtb">
    <tr>
        <th>ITEM_CODE</th>
        <th>ITEM_DESCRIPTION</th>
        <th>LOCATION</th>
        <th>PRE-SHIPMENT NO</th>
        <th>LOT_NO</th>
        <th>GRN_NO</th>
         <th>INV_NO</th>
        <th>QTY</th>
        <th>UOM</th>
        <th>REQUIRED_QTY</th>
        <th>PURPOSE OF CONSUME</th>
        <th>CUSTOMER PO_NO</th>
    </tr>
    <%
        ItemDao d = new ItemDao();
        ArrayList<ItemBean> itmlist = new ArrayList<>();
        itmlist = d.getALLitem();
              for (ItemBean bean : itmlist) {%>
    <tr>
        <td><%=bean.getITEM_CODE()%></td>	
        <td><%=bean.getITEM_DESC()%></td>	
        <td><%=bean.getLOC_ID()%></td>
        <td><%=bean.getPRE_SHIP()%></td>
        <td><%=bean.getLOT_NO()%></td>
        <td><%=bean.getRECIEPT_NO()%></td>
        <td><%=bean.getINV_NO()%></td>
        <td><%=bean.getQTY()%></td>
        <td><%=bean.getUOM()%></td>
        <td><input type="text" size="15" id="a" name="a"/></td>
        <td><input type="text" size="15" id="b" name="b"/></td>
        <td><input type="text" size="15" id="c" name="c"/></td>
    </tr>   
    <%}%>
</table>    