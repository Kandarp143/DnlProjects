<jsp:useBean id="objD" class="kp.beans.data.pojo.MocInitPip" scope="request"/>
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
            <input type="checkbox" id="d1" name="d1" <%=gm.isASelected(objD.getD1())%>/>
        </td>
    </tr>
    <tr>
        <td>3.2</td>
        <td>Heating</td>
        <td>
            <input type="checkbox" id="d2" name="d2" <%=gm.isASelected(objD.getD2())%>/>
        </td>
    </tr>
    <tr>
        <td>3.3</td>
        <td>Cooling</td>
        <td>
            <input type="checkbox" id="d3" name="d3" <%=gm.isASelected(objD.getD3())%>/>
        </td>
    </tr>
    <tr>
        <td>3.4</td>
        <td>specification (Temperature , Pressure , Valves ,Diameter , Insulation, etc.)</td>
        <td>
            <input type="checkbox" id="d4" name="d4" <%=gm.isASelected(objD.getD4())%>/>
        </td>
    </tr>
    <tr>
        <td>3.5</td>
        <td>Access to the valves / Instruments</td>
        <td>
            <input type="checkbox" id="d5" name="d5" <%=gm.isASelected(objD.getD5())%> />
        </td>
    </tr>
    <tr>
        <td>3.6</td>
        <td>Structure and Supports</td>
        <td>
            <input type="checkbox" id="d6" name="d6" <%=gm.isASelected(objD.getD6())%>/>
        </td>
    </tr>
    <tr>
        <td>3.7</td>
        <td>Others :</td>
        <td>
            <input type="text" id="d7" name="d7" value="<%=objD.getD7()%>"/>
        </td>
    </tr>
</table>