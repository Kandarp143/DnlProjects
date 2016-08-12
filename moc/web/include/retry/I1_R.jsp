<jsp:useBean id="objA" class="kp.beans.data.pojo.MocInitReq" scope="request"/>
<table class="form_table" >
    <tr>
        <th colspan="4">
            MOC Detail
        </th>     
    </tr>
    <tr>
        <td colspan="2">
            Unit or Facility <span class="req">*</span>
            <select name="a1" id="a1">
                <option value="">Select Unit</option>
                <option value="<%=session.getAttribute("usrunit").toString()%>"><%=session.getAttribute("usrunit").toString()%></option>
            </select>
        </td>
        <td colspan="2">
            Plant<span class="req">*</span> :
            <select name="a2" id="a2" >
                <option value="<%=objA.getA2()%>"><%=objA.getA2()%></option>
            </select>
        </td>
    </tr>
    <tr>
        <td colspan="4">Related P &amp; ID No :
            <input type="text" name="a3" id="a3" size="86" value="<%=objA.getA3()%>" />

        </td>
    </tr>
    <tr>
        <td colspan="4">Title:<span class="req">*</span>
            <input type="text" name="a4" id="a4" size="100" placeholder="Only 100 Character allowed" value="<%=objA.getA4()%>"/>
        </td>
    </tr>
    <tr>
        <td>
            Type of change
            <select name="a5" id="a5">
                <option value="<%=objA.getA5()%>"><%=objA.getA5()%></option>
                <option>Temporary</option>
                <option>Permanent</option>
            </select>

        </td>
        <td>
            <input type="checkbox" name="a7" id="a7" <%=gm.isASelected(objA.getA7())%>/>
            Emergency
        </td>
        <td>
            <input type="checkbox" name="a8" id="a8" <%=gm.isASelected(objA.getA8())%>/>
            Instrumentation
        </td>
        <td>
            <input type="checkbox" name="a9" id="a9" <%=gm.isASelected(objA.getA9())%>/>
            Equipment
        </td>
    </tr>
    <tr>
        <td>Temporary until
            <input type="text" name="a6"  id="a6" value="<%=objA.getA6()%>"/>
        </td>
        <td colspan="3">Others
            <input type="text" name="a10" id="a10" value="<%=objA.getA10()%>"/>
        </td>
    </tr>
    <tr>
        <td colspan="4">Justification</br>
            <textarea name="a11" id="a11" cols="90" rows="5"><%=objA.getA11()%></textarea>
        </td>
    </tr>
    <tr>
        <td colspan="4">
            Expenditure Type
            <select name="a12" id="a12">
                <option value="<%=objA.getA12()%>"><%=objA.getA12()%></option>
                <option>Capital</option>
                <option>Revenue</option>
            </select>
        </td>
    </tr>
    <tr>
        <td colspan="4">Situation Description before this modification : 
            <br/>
            <textarea name="a13" id="a13" cols="90" rows="5" ><%=objA.getA13()%></textarea>
        </td>
    </tr>
    <tr>
        <td colspan="4">Situation Description after this modification : 
            <br />
            <textarea name="a14" id="a14" cols="90" rows="5"><%=objA.getA14()%></textarea>
        </td>
    </tr>
</table>
