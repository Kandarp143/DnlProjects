<jsp:useBean id="objJ" class="kp.beans.data.pojo.MocHseMst" scope="request"/>
<table class="form_table" >
    <tr>
        <th>HSE Department Verification</th>
    </tr>
    <tr>
        <td>Risk Assessment recommendation<br/><br/>
            <textarea name="j1" id="j1" cols="45" rows="5"><%=objJ.getJ1()%></textarea>
        </td>
    </tr>
    <tr>
        <td>Identification of Aspects and Impacts on HSE recommendation<br/><br/>
            <textarea name="j2" id="j2" cols="45" rows="5"><%=objJ.getJ2()%></textarea>
        </td>
    </tr>

</table>