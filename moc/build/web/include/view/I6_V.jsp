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
             <%=gm.getViewCheckBox(moc.getMocInitProd().getF1())%>
        </td>
    </tr>
    <tr>
        <td>5.2</td>
        <td>Lighting</td>
        <td>
           <%=gm.getViewCheckBox(moc.getMocInitProd().getF2())%>
        </td>
    </tr>
    <tr>
        <td>5.3</td>
        <td>Area classfication</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitProd().getF3())%>
        </td>
    </tr>
    <tr>
        <td>5.4</td>
        <td>Noise Level</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitProd().getF4())%>
        </td>
    </tr>
    <tr>
        <td>5.5</td>
        <td>Floor and Platforms structural analysis</td>
        <td>
             <%=gm.getViewCheckBox(moc.getMocInitProd().getF5())%>
        </td>
    </tr>
    <tr>
        <td>5.6</td>
        <td>Others :</td>
        <td>
            <%=moc.getMocInitProd().getF6()%>
        </td>
    </tr>
</table>