<jsp:useBean id="objE" class="kp.beans.data.pojo.MocEngMst" scope="request"/>
<table class="form_table" >
    <tr>
        <th colspan="2">
            Engineering Department Cost Estimation
        </th>
    </tr>
    <tr>
        <td>
            Estimated Cost<span class="err">*</span>
        </td>
        <td>
            <input type="text" name="l1" id="l1" value = "<%=objE.getL1()%>"/>
        </td>
    </tr>
    <tr>
        <td>Cost Specification</td>
        <td>
            <textarea name="l2" id="l2" cols="45" rows="5"><%=objE.getL2()%></textarea>
        </td>
    </tr>
</table>
