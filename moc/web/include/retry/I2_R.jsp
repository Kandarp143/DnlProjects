        <jsp:useBean id="objB" class="kp.beans.data.pojo.MocInitSta" scope="request"/>
<table  class="form_table" style="width:50%;float: left"  >
      <tr>
        <th colspan="4">
            1.Stationary Equipment
        </th>     
    </tr>
    <tr>
        <td>Task:
            <input type="checkbox" id="b1" name="b1" <%=gm.isASelected(objB.getB1())%>/>
        </td>
        <td >Vessel:
            <input type="checkbox" id="b2" name="b2" <%=gm.isASelected(objB.getB2())%>/>
        </td>
        <td>HeatExchanger:
            <input type="checkbox" id="b3" name="b3" <%=gm.isASelected(objB.getB3())%>/>
        </td>
    </tr>
    <tr>
        <td>Reactor:
            <input type="checkbox" id="b4" name="b4" <%=gm.isASelected(objB.getB4())%>/>
        </td>
        <td>Column:
            <input type="checkbox" id="b5" name="b5" <%=gm.isASelected(objB.getB5())%>/>
        </td>
        <td>Other:
            <input type="text" id="b6" name="b6"  value="<%=objB.getB6()%>"/>
        </td>
    </tr>
    <tr>
        <td>1.1</td>
        <td>New Equipment</td>
        <td><input type="checkbox" id="b7" name="b7" <%=gm.isASelected(objB.getB7())%>/></td>
    </tr>
    <tr>
        <td>1.2</td>
        <td >Material of Construction</td>
        <td><input type="checkbox" id="b8" name="b8" <%=gm.isASelected(objB.getB8())%>/></td>
    </tr>
    <tr>
        <td>1.3</td>
        <td>Jacket / Coil</td>
        <td><input type="checkbox" id="b9" name="b9" <%=gm.isASelected(objB.getB9())%>></td>
    </tr>
    <tr>
        <td>1.4</td>
        <td >Nozzle / Accessories</td>
        <td><input type="checkbox" id="b10" name="b10" <%=gm.isASelected(objB.getB10())%>/></td>
    </tr>
    <tr>
        <td>1.5</td>
        <td >Agitator / buffles</td>
        <td><input type="checkbox" id="b11" name="b11" <%=gm.isASelected(objB.getB11())%>/></td>
    </tr>
    <tr>
        <td>1.6</td>
        <td>Sealing elements ( gasket,seals,stuffing,etc.)</td>
        <td><input type="checkbox" id="b12" name="b12" <%=gm.isASelected(objB.getB12())%>/></td>
    </tr>
    <tr>
        <td>1.7</td>
        <td>Structural Project and Supports</td>
        <td><input type="checkbox" id="b13" name="b13" <%=gm.isASelected(objB.getB13())%>/></td>
    </tr>
    <tr>
        <td>1.8</td>
        <td >Safety device(Rupture Disk , PSV,PVRV,Relief Valve,breather valver,Vacuum,etc)</td>
        <td><input type="checkbox" id="b14" name="b14" <%=gm.isASelected(objB.getB14())%>/></td>
    </tr>
    <tr>
        <td>1.9</td>
        <td >Reinforcement specification</td>
        <td><input type="checkbox" id="b15" name="b15" <%=gm.isASelected(objB.getB15())%>/></td>
    </tr>
    <tr>
        <td>1.10</td>
        <td>Pressure vessel classification (er. NR-13)</td>
        <td><input type="checkbox" id="b16" name="b16" <%=gm.isASelected(objB.getB16())%>/></td>
    </tr>
    <tr>
        <td>1.11</td>
        <td>Other</td>
        <td><input type="text" id="b17" name="b17" value="<%=objB.getB17()%>"/></td>
    </tr>
</table>
