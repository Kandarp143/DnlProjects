<jsp:useBean id="objT" class="kp.beans.data.pojo.MocTsMst" scope="request"/>
<table class="form_table" >
    <tr><th> TS Department Verification</th></tr>
    <tr>
        <td><input type="checkbox" name="i1" id="i1" <%=gm.isASelected(objT.getI1())%>/>P &amp; ID Verification</td>
    </tr>
    <tr>
        <td>
            <input type="checkbox" name="i2" id="i2" <%=gm.isASelected(objT.getI2())%>/>Process Verification
        </td>
    </tr>
    <tr>
        <td>
            Any Specific Requirement<br/><br/>
            <textarea name="i3" id="i3" cols="45" rows="5" ><%=objT.getI3()%></textarea>
        </td>
    </tr>
    <tr>
        <td>
            <input type="checkbox" name="i4" id="i4" <%=gm.isASelected(objT.getI4())%>/>VP technology approval required ?
        </td>
    </tr>

</table>
