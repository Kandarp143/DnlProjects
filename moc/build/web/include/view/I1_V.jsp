<table class="form_table" style="table-layout: fixed">
    <tr>
        <th colspan="4">
            MOC Detail
        </th>     
    </tr>
    <tr>
        <td colspan="2">
            Unit or Facility :<%=moc.getMocInitReq().getA1()%>
        </td>
        <td colspan="2">
            Plant: <%=moc.getMocInitReq().getA2()%>
        </td>
    </tr>
    <tr>
        <td colspan="4">Related P &amp; ID No :<%=moc.getMocInitReq().getA3()%>
        </td>
    </tr>
    <tr>
        <td colspan="4">Title:<%=moc.getMocInitReq().getA4()%>
        </td>
    </tr>
    <tr>
        <td>

            Type of change : <%=moc.getMocInitReq().getA5()%>


        </td>
        <td>

            Emergency : <%=gm.getViewCheckBox(moc.getMocInitReq().getA7())%>
        </td>
        <td>

            Instrumentation : <%=gm.getViewCheckBox(moc.getMocInitReq().getA8())%>
        </td>
        <td>

            Equipment : <%=gm.getViewCheckBox(moc.getMocInitReq().getA9())%>
        </td>
    </tr>
    <tr>
        <td>Temporary until <%=moc.getMocInitReq().getA6()%>

        </td>
        <td colspan="3">Others<%=moc.getMocInitReq().getA10()%>

        </td>
    </tr>
    <tr>
        <td colspan="4">Justification</br>
            <%=moc.getMocInitReq().getA11()%>

        </td>
    </tr>
    <tr>
        <td colspan="4">
            Expenditure Type
            <%=moc.getMocInitReq().getA12()%>

        </td>
    </tr>
    <tr>
        <td colspan="4">Situation Description before this modification : 
            <%=moc.getMocInitReq().getA13()%>

        </td>
    </tr>
    <tr>
        <td colspan="4">Situation Description after this modification : 
            <%=moc.getMocInitReq().getA14()%>
        </td>
    </tr>
</table>