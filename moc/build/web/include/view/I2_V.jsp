<table  class="form_table" style="width:50%;float: left"  >
    <tr>
        <th colspan="4">
            1.Stationary Equipment
        </th>     
    </tr>
    <tr>
        <td>Task:<%=gm.getViewCheckBox(moc.getMocInitSta().getB1())%> </td>
        <td >Vessel:   <%=gm.getViewCheckBox(moc.getMocInitSta().getB2())%> </td>
        <td>HeatExchanger:   <%=gm.getViewCheckBox(moc.getMocInitSta().getB3())%> </td>
    </tr>
    <tr>
        <td>Reactor:   <%=gm.getViewCheckBox(moc.getMocInitSta().getB4())%>  </td>
        <td>Column:   <%=gm.getViewCheckBox(moc.getMocInitSta().getB5())%> </td>
        <td>Other:   <%=moc.getMocInitSta().getB6()%> </td>
    </tr>
    <tr>
        <td>1.1</td>
        <td>New Equipment</td>
        <td>   <%=gm.getViewCheckBox(moc.getMocInitSta().getB7())%></td>
    </tr>
    <tr>
        <td>1.2</td>
        <td >Material of Construction</td>
        <td>   <%=gm.getViewCheckBox(moc.getMocInitSta().getB8())%> </td>
    </tr>
    <tr>
        <td>1.3</td>
        <td>Jacket / Coil</td>
        <td>   <%=gm.getViewCheckBox(moc.getMocInitSta().getB9())%> </td>
    </tr>
    <tr>
        <td>1.4</td>
        <td >Nozzle / Accessories</td>
        <td>   <%=gm.getViewCheckBox(moc.getMocInitSta().getB10())%></td>
    </tr>
    <tr>
        <td>1.5</td>
        <td >Agitator / buffles</td>
        <td>   <%=gm.getViewCheckBox(moc.getMocInitSta().getB11())%> </td>
    </tr>
    <tr>
        <td>1.6</td>
        <td>Sealing elements ( gasket,seals,stuffing,etc.)</td>
        <td>   <%=gm.getViewCheckBox(moc.getMocInitSta().getB12())%> </td>
    </tr>
    <tr>
        <td>1.7</td>
        <td>Structural Project and Supports</td>
        <td>   <%=gm.getViewCheckBox(moc.getMocInitSta().getB13())%> </td>
    </tr>
    <tr>
        <td>1.8</td>
        <td >Safety device(Rupture Disk , PSV,PVRV,Relief Valve,breather valver,Vacuum,etc)</td>
        <td>   <%=gm.getViewCheckBox(moc.getMocInitSta().getB14())%> </td>
    </tr>
    <tr>
        <td>1.9</td>
        <td >Reinforcement specification</td>
        <td>   <%=gm.getViewCheckBox(moc.getMocInitSta().getB15())%></td>
    </tr>
    <tr>
        <td>1.10</td>
        <td>Pressure vessel classification (er. NR-13)</td>
        <td>   <%=gm.getViewCheckBox(moc.getMocInitSta().getB16())%> </td>
    </tr>
    <tr>
        <td>1.11</td>
        <td>Other</td>
        <td>   <%=moc.getMocInitSta().getB17()%></td>
    </tr>
</table>
