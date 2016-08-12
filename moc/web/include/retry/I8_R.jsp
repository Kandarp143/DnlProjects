<jsp:useBean id="objH" class="kp.beans.data.pojo.MocInitPseq" scope="request"/>
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
            <input type="checkbox" id="h1" name="h1" <%=gm.isASelected(objH.getH1())%>/>
        </td>
    </tr>
    <tr>
        <td>02</td>
        <td>Will there be changes in any process control parameter ?</td>
        <td>
            <input type="checkbox" id="h2" name="h2" <%=gm.isASelected(objH.getH2())%>/>
        </td>
    </tr>
    <tr>
        <td>03</td>
        <td>Will there be changes in the Quality Plan ?</td>
        <td>
            <input type="checkbox" id="h3" name="h3"<%=gm.isASelected(objH.getH3())%> />
        </td>
    </tr>
    <tr>
        <td>04</td>
        <td>Will there be changes in the formula or specification of any of the raw materials , intermediates and/or product ?</td>
        <td>
            <input type="checkbox" id="h4" name="h4" <%=gm.isASelected(objH.getH4())%>/>
        </td>
    </tr>
    <tr>
        <td>05</td>
        <td>Will there be a relevant change in the energy consumption ?</td>
        <td>
            <input type="checkbox" id="h5" name="h5" <%=gm.isASelected(objH.getH5())%>/>
        </td>
    </tr>
    <tr>
        <td>06</td>
        <td>Will there be a relevant change in the water or gas emission , wastes and particulates ?</td>
        <td>
            <input type="checkbox" id="h6" name="h6" <%=gm.isASelected(objH.getH6())%>/>
        </td>
    </tr>
    <tr>
        <td>07</td>
        <td>Will there be changes in the Industrial Hygiene aspects (Physical , Chemical and ergonomic aspects ) ?</td>
        <td>
            <input type="checkbox" id="h7" name="h7" <%=gm.isASelected(objH.getH7())%>/>
        </td>
    </tr>
    <tr>
        <td>08</td>
        <td>Will the list of instrumentation that impacts on the Product's quality be altered ?</td>
        <td>
            <input type="checkbox" id="h8" name="h8" <%=gm.isASelected(objH.getH8())%>/>
        </td>
    </tr>
    <tr>
        <td>09</td>
        <td>Will the list of Equipments that impact on the Product's quality be altered ?</td>
        <td>
            <input type="checkbox" id="h9" name="h9"<%=gm.isASelected(objH.getH9())%> />
        </td>
    </tr>
    <tr>
        <td>10</td>
        <td>Does this change impact the product's quality ?</td>
        <td>
            <input type="checkbox" id="h10" name="h10" <%=gm.isASelected(objH.getH10())%>/>
        </td>
    </tr>
    <tr>
        <td>11</td>
        <td>Will it be necessary to alter the product register at Regulatory agencies ?</td>
        <td>
            <input type="checkbox" id="h11" name="h11" <%=gm.isASelected(objH.getH11())%>/>
        </td>
    </tr>
    <tr>
        <td>12</td>
        <td>Is the modification based on any test or experiment made previously ?</td>
        <td>
            <input type="checkbox" id="h12" name="h12" <%=gm.isASelected(objH.getH12())%>/>
        </td>
    </tr>
    <tr>
        <td>13</td>
        <td>Is the modification based on a client's request ?</td>
        <td>
            <input type="checkbox" id="h13" name="h13"<%=gm.isASelected(objH.getH13())%> />
        </td>
    </tr>
    <tr>
        <td>14</td>
        <td colspan="2">Others:
            <input type="text" id="h14" name="h14" value="<%=objH.getH14()%>"/>
        </td>

    </tr>
</table>