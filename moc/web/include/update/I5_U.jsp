<table  class="form_table" style="width:40%">
     <tr>
        <th colspan="4">
            4.Electrical and instrumentation :
        </th>     
    </tr>
    <tr>
        <td>4.1</td>
        <td>Material of Construction</td>
        <td>
            <input type="checkbox" id="e1" name="e1" <%=gm.isASelected(objE.getE1())%>/>
        </td>
    </tr>
    <tr>
        <td>4.2</td>
        <td>Equipment</td>
        <td>
            <input type="checkbox" id="e2" name="e2" <%=gm.isASelected(objE.getE2())%>/>
        </td>
    </tr>
    <tr>
        <td>4.3</td>
        <td>Grounding / Bonding</td>
        <td>
            <input type="checkbox" id="e3" name="e3" <%=gm.isASelected(objE.getE3())%>/>
        </td>
    </tr>
    <tr>
        <td>4.4</td>
        <td>Process interlock</td>
        <td>
            <input type="checkbox" id="e4" name="e4" <%=gm.isASelected(objE.getE4())%>/>
        </td>
    </tr>
    <tr>
        <td>4.5</td>
        <td>Control Program / DCS ,PLC ,Hardwired</td>
        <td>
            <input type="checkbox" id="e5" name="e5" <%=gm.isASelected(objE.getE5())%>/>
        </td>
    </tr>
    <tr>
        <td>4.6</td>
        <td>Rotating System (Fix/ Variable)</td>
        <td>
            <input type="checkbox" id="e6" name="e6" <%=gm.isASelected(objE.getE6())%>/>
        </td>
    </tr>
    <tr>
        <td>4.7</td>
        <td>Others :</td>
        <td>
            <input type="text" id="e7" name="e7" value="<%=objE.getE7()%>"/>
        </td>
    </tr>
</table>