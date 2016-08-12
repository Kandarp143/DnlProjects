<%@page import="Logic.Variables"%>
<%@page import="java.util.Map"%>
<table class="dtb_sub">
    <tr>
        <td class="lbl" >WO Title:<span style="color: red;font-weight: bold">*</span></td>
        <td colspan="3" ><input type="text" name="pot" id="pot" size="75"  
                                value = "<%=(request.getParameter("pot") == null) ? "" : request.getParameter("pot")%>"  />
        </td>
        <td class="lbl">WO Number:</td>
        <td><input type="text" name="a" size="15" class="readon" value="XXXXXX" readonly/></td>
        <td class="lbl">Status:</td>
        <td><input type="text" name="b" size="15" class="readon" value="XXXXXX" readonly/></td>
    </tr>
    <tr>
        <td class="lbl">Supplier:<span style="color: red;font-weight: bold">*</span></td>
        <td>
            <select id="e" name="e"  style="width:320px">
                <% if (request.getParameter("e") == null || request.getParameter("e") == "") {%>
                <%} else {%>
                <option value="<%=request.getParameter("e")%>"><%=request.getParameter("e")%></option>
                <%}%>
                <option value="">Select Supplier</option>
                <%for (Map.Entry<String, String> entry : Variables.sup.entrySet()) {%>
                <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                <%  }%>
            </select>
        </td>
        <td class="lbl">Site:<span style="color: red;font-weight: bold">*</span></td>
        <td><select id="f" name="f"  style="width:140px"></select></td>
        <td class="lbl">Currency:<span style="color: red;font-weight: bold">*</span></td>
        <td>
            <select id="h" name="h"  style="width:150px">
                <% if (request.getParameter("h") == null || request.getParameter("h") == "") {%>
                <option value="INR">INR</option>
                <%} else {%>
                <option><%=request.getParameter("h")%></option>
                <%}%>
                <%for (Map.Entry<String, String> entry : Variables.cur.entrySet()) {%>
                <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                <% }%>
            </select>
        </td>
        <td class="lbl">Operating Unit:</td>
        <td><input  name="g"  type="hidden"  value="123" />
            <input    type="text" size="15" class="readon" value="Nandesari" readonly/>
        </td>
    </tr>
    <tr>
        <td class="lbl" colspan="2">Retention Amount:</td>
        <td><input type="text" name="i"id="i" size="15" class="cur"  value = "<%=(request.getParameter("i") == null) ? 0 : request.getParameter("i")%>"/></td>
        <td class="lbl" >Deposit Amount:</td>
        <td><input type="text" name="i2"id="i2" size="15" class="cur"  value = "<%=(request.getParameter("i2") == null) ? 0 : request.getParameter("i2")%>"/></td>
        <td class="lbl">Valid From:<span style="color: red;font-weight: bold">*</span></td>
        <td>
            <input type="text" id="fk" name="fk" size="15" value = "<%=(request.getParameter("fk") == null) ? "" : request.getParameter("fk")%>"/>
        </td>
        <td class="lbl">Valid To:<span style="color: red;font-weight: bold">*</span></td>
        <td>
            <input type="text" id="k" name="k" size="15" value = "<%=(request.getParameter("k") == null) ? "" : request.getParameter("k")%>"/>
        </td></tr>
    <tr>
        <!--        <td class="lbl" >WO Type:<span style="color: red;font-weight: bold">*</span>
                <td colspan="4">
                    <select id="j" name="j"  style="width:320px">
        <%for (Map.Entry<String, String> entry : Variables.wo_type.entrySet()) {%>
        <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
        <% }%>
    </select>
</td>
<td>
    <p style="color: gray;font-size: small">(MM/DD/YYYY)</p>
    </p>
</td>-->
        <td class="lbl" >WO Type:<span style="color: red;font-weight: bold">*</span>
        <td colspan="4"><input type="text" value="Blanket"  name="j" id="j" readonly class="readon"/></td>
        <td>
            <p style="color: gray;font-size: small">(MM/DD/YYYY)</p>
            </p>
        </td>
        <td></td>
        <td>
            <p style="color: gray;font-size: small">(MM/DD/YYYY)</p>
            </p>
        </td>
    </tr>

</table>