<table  class="form_table" style="width: 40%">
     <tr>
        <th colspan="4">
            2.Rotating Equipment
        </th>     
    </tr>
    <tr>
        <td>Pump: <%=gm.getViewCheckBox(moc.getMocInitRot().getC1())%>

        </td>
        <td>compressor:<%=gm.getViewCheckBox(moc.getMocInitRot().getC2())%>

        </td>
        <td>Blower:<%=gm.getViewCheckBox(moc.getMocInitRot().getC3())%>

        </td>
    </tr>
    <tr>
        <td>Agitator:<%=gm.getViewCheckBox(moc.getMocInitRot().getC4())%>

        </td>
        <td>Fan:<%=gm.getViewCheckBox(moc.getMocInitRot().getC5())%>

        </td>
        <td>centrifuge:<%=gm.getViewCheckBox(moc.getMocInitRot().getC6())%>

        </td>
    </tr>
    <tr>
        <td colspan=4> Other:<%=moc.getMocInitRot().getC7()%>

        </td>
    </tr>
    <tr>
        <td>2.1</td>
        <td>Material of construction</td>
        <td><%=gm.getViewCheckBox(moc.getMocInitRot().getC8())%>

        </td>
    </tr>
    <tr>
        <td>2.2</td>
        <td>Operation Data</td>
        <td><%=gm.getViewCheckBox(moc.getMocInitRot().getC9())%>

        </td>
    </tr>
    <tr>
        <td>2.3</td>
        <td>Sealing elements (gasket,sealls,stuffing,etc.)</td>
        <td><%=gm.getViewCheckBox(moc.getMocInitRot().getC10())%>

        </td>
    </tr>
    <tr>
        <td>2.4</td>
        <td>Safety device</td>
        <td><%=gm.getViewCheckBox(moc.getMocInitRot().getC11())%>

        </td>
    </tr>
    <tr>
        <td>2.5</td>
        <td>Others:</td>
        <td><%=moc.getMocInitRot().getC12()%>
        </td>
    </tr>
</table>