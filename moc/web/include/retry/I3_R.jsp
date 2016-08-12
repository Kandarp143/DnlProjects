 <jsp:useBean id="objC" class="kp.beans.data.pojo.MocInitRot" scope="request"/>
<table  class="form_table" style="width: 40%">
      <tr>
        <th colspan="4">
            2.Rotating Equipment
        </th>     
    </tr>
    <tr>
        <td>Pump:
            <input type="checkbox" id="c1" name="c1" <%=gm.isASelected(objC.getC1())%>/>
        </td>
        <td>Compressor:
            <input type="checkbox" id="c2" name="c2" <%=gm.isASelected(objC.getC2())%>/>
        </td>
        <td>Blower:
            <input type="checkbox" id="c3" name="c3" <%=gm.isASelected(objC.getC3())%>/>
        </td>
    </tr>
    <tr>
        <td>Agitator:
            <input type="checkbox" id="c4" name="c4" <%=gm.isASelected(objC.getC4())%>/>
        </td>
        <td>Fan:
            <input type="checkbox" id="c5" name="c5" <%=gm.isASelected(objC.getC5())%>/>
        </td>
        <td>Centrifuge:
            <input type="checkbox" id="c6" name="c6" <%=gm.isASelected(objC.getC6())%>/>
        </td>
    </tr>
    <tr>
        <td Colspan=4> Other:
            <input type="text" id="c7" name="c7" value="<%=objC.getC7()%>" />
        </td>
    </tr>
    <tr>
        <td>2.1</td>
        <td>Material of Construction</td>
        <td>
            <input type="checkbox" id="c8" name="c8" <%=gm.isASelected(objC.getC8())%>/>
        </td>
    </tr>
    <tr>
        <td>2.2</td>
        <td>Operation Data</td>
        <td>
            <input type="checkbox" id="c9" name="c9" <%=gm.isASelected(objC.getC9())%>/>
        </td>
    </tr>
    <tr>
        <td>2.3</td>
        <td>Sealing elements (gasket,sealls,stuffing,etc.)</td>
        <td>
            <input type="checkbox" id="c10" name="c10" <%=gm.isASelected(objC.getC10())%>/>
        </td>
    </tr>
    <tr>
        <td>2.4</td>
        <td>Safety device</td>
        <td>
            <input type="checkbox" id="c11" name="c11" <%=gm.isASelected(objC.getC11())%>/>
        </td>
    </tr>
    <tr>
        <td>2.5</td>
        <td>Others:</td>
        <td>
            <input type="text" id="c12" name="c12" value="<%=objC.getC12()%>"/>
        </td>
    </tr>
</table>