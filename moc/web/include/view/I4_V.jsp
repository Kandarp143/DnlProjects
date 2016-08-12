<table  class="form_table" style="width:50%;float: left">
    <tr>
        <th colspan="4">
            3.Piping and Accessories:
        </th>     
    </tr>
    <tr>
        <td>3.1</td>
        <td>Material of Construction</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitPip().getD1())%>
        </td>
    </tr>
    <tr>
        <td>3.2</td>
        <td>Heating</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitPip().getD2())%>
        </td>
    </tr>
    <tr>
        <td>3.3</td>
        <td>Cooling</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitPip().getD3())%>
        </td>
    </tr>
    <tr>
        <td>3.4</td>
        <td>specification (Temperature , Pressure , Valves ,Diameter , Insulation, etc.)</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitPip().getD4())%>
        </td>
    </tr>
    <tr>
        <td>3.5</td>
        <td>Access to the valves / Instruments</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitPip().getD5())%>
        </td>
    </tr>
    <tr>
        <td>3.6</td>
        <td>Structure and Supports</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitPip().getD6())%>
        </td>
    </tr>
    <tr>
        <td>3.7</td>
        <td>Others :</td>
        <td>
            <%=moc.getMocInitPip().getD7()%>
        </td>
    </tr>
</table>