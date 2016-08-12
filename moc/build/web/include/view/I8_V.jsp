<table  class="form_table" >
     <tr>
        <th colspan="4">
            Product,Safety,Environment and Energy Quality Control Checklist:
        </th>     
    </tr>
    <tr>
        <th>#</th>
        <th>Item</th>
        <th>Yes/No</th>
        
    </tr>
    <tr>
        <td>01</td>
        <td>Will there be changes in the list of critical equipments that impact on Safety , Health anf the Environment</td>
        <td>
           <%=gm.getViewCheckBox(moc.getMocInitPseq().getH1())%>
        </td>
    </tr>
    <tr>
        <td>02</td>
        <td>Will there be changes in any process control parameter ?</td>
        <td>
         <%=gm.getViewCheckBox(moc.getMocInitPseq().getH2())%>
        </td>
    </tr>
    <tr>
        <td>03</td>
        <td>Will there be changes in the Quality Plan ?</td>
        <td>
         <%=gm.getViewCheckBox(moc.getMocInitPseq().getH3())%>
        </td>
    </tr>
    <tr>
        <td>04</td>
        <td>Will there be changes in the formula or specification of any of the raw materials , intermediates and/or product ?</td>
        <td>
           <%=gm.getViewCheckBox(moc.getMocInitPseq().getH4())%>
        </td>
    </tr>
    <tr>
        <td>05</td>
        <td>Will there be a relevant change in the energy consumption ?</td>
        <td>
             <%=gm.getViewCheckBox(moc.getMocInitPseq().getH5())%>
        </td>
    </tr>
    <tr>
        <td>06</td>
        <td>Will there be a relevant change in the water or gas emission , wastes and particulates ?</td>
        <td>
         <%=gm.getViewCheckBox(moc.getMocInitPseq().getH6())%>
        </td>
    </tr>
    <tr>
        <td>07</td>
        <td>Will there be changes in the Industrial Hygiene aspects (Physical , Chemical and ergonomic aspects ) ?</td>
        <td>
           <%=gm.getViewCheckBox(moc.getMocInitPseq().getH7())%>
        </td>
    </tr>
    <tr>
        <td>08</td>
        <td>Will the list of instrumentation that impacts on the Product's quality be altered ?</td>
        <td>
           <%=gm.getViewCheckBox(moc.getMocInitPseq().getH8())%>
        </td>
    </tr>
    <tr>
        <td>09</td>
        <td>Will the list of Equipments that impact on the Product's quality be altered ?</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitPseq().getH9())%>
        </td>
    </tr>
    <tr>
        <td>10</td>
        <td>Does this change impact the product's quality ?</td>
        <td>
            <%=gm.getViewCheckBox(moc.getMocInitPseq().getH10())%>
        </td>
    </tr>
    <tr>
        <td>11</td>
        <td>Will it be necessary to alter the product register at Regulatory agencies ?</td>
        <td>
          <%=gm.getViewCheckBox(moc.getMocInitPseq().getH11())%>
        </td>
    </tr>
    <tr>
        <td>12</td>
        <td>Is the modification based on any test or experiment made previously ?</td>
        <td>
          <%=gm.getViewCheckBox(moc.getMocInitPseq().getH12())%>
        </td>
    </tr>
    <tr>
        <td>13</td>
        <td>Is the modification based on a client's request ?</td>
        <td>
          <%=gm.getViewCheckBox(moc.getMocInitPseq().getH13())%>
        </td>
    </tr>
    <tr>
        <td>14</td>
        <td colspan="2">Others:
         <%=moc.getMocInitPseq().getH14()%>
        </td>

    </tr>
</table>