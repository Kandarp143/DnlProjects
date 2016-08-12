<table  class="form_table" style="width:40%">
      <tr>
        <th colspan="4">
            5.Production area :
        </th>     
    </tr>
    <tr>
        <td>5.1</td>
        <td>Ventilation</td>
        <td>
            <input type="checkbox" id="f1" name="f1" <%=gm.isASelected(objF.getF1())%>/>
        </td>
    </tr>
    <tr>
        <td>5.2</td>
        <td>Lighting</td>
        <td>
            <input type="checkbox" id="f2" name="f2" <%=gm.isASelected(objF.getF2())%>/>
        </td>
    </tr>
    <tr>
        <td>5.3</td>
        <td>Area classfication</td>
        <td>
            <input type="checkbox" id="f3" name="f3" <%=gm.isASelected(objF.getF3())%>/>
        </td>
    </tr>
    <tr>
        <td>5.4</td>
        <td>Noise Level</td>
        <td>
            <input type="checkbox" id="f4" name="f4" <%=gm.isASelected(objF.getF4())%>/>
        </td>
    </tr>
    <tr>
        <td>5.5</td>
        <td>Floor and Platforms structural analysis</td>
        <td>
            <input type="checkbox" id="f5" name="f5" <%=gm.isASelected(objF.getF5())%>/>
        </td>
    </tr>
    <tr>
        <td>5.6</td>
        <td>Others :</td>
        <td>
            <input type="text" id="f6" name="f6" value="<%=objF.getF6()%>"/>
        </td>
    </tr>
</table>