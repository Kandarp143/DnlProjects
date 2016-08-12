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
            <%=gm.getViewCheckBox(moc.getMocInitEle().getE1())%>
        </td>
    </tr>
    <tr>
        <td>4.2</td>
        <td>Equipment</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitEle().getE2())%>
        </td>
    </tr>
    <tr>
        <td>4.3</td>
        <td>Grounding / Bonding</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitEle().getE3())%>
        </td>
    </tr>
    <tr>
        <td>4.4</td>
        <td>Process interlock</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitEle().getE4())%>
        </td>
    </tr>
    <tr>
        <td>4.5</td>
        <td>Control Program / DCS ,PLC ,Hardwired</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitEle().getE5())%>
        </td>
    </tr>
    <tr>
        <td>4.6</td>
        <td>Rotating System (Fix/ Variable)</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitEle().getE6())%>
        </td>
    </tr>
    <tr>
        <td>4.7</td>
        <td>Others :</td>
        <td>
            <%=moc.getMocInitEle().getE7()%>
        </td>
    </tr>
</table>