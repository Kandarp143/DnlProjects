<%@page import="Logic.Variables"%>
<%@page import="java.util.Map"%>
<style>
    .lbl{
        float: right;

    }
    .readon{
        background: #C0C0C0;
    }
</style>
<table class="dtb_sub">
    <tr>
        <td class="lbl">WO Number:</td>
        <td><input type="text" name="a" size="15" class="readon" value="<%=wo1.getPO_NO()%>" readonly/></td>
        <td class="lbl">Status:</td>
        <td><input type="text" name="b" size="15" class="readon" value="<%=wo1.getSTATUS()%>" readonly/></td>
        <td class="lbl">Created By:</td>
        <td><input type="text" name="c" size="15" value="<%=wo1.getUSER_NAME()%>" class="readon" readonly/></td>
        <td class="lbl">Creation Date:</td>
        <td><input type="text" name="d" size="15" value="<%=wo1.getCR_DATE()%>" class="readon" readonly/></td>
    </tr>
    <tr>
        <td class="lbl">WO Title:</td>
        <td colspan="5"><input type="text" name="pot" size="110" value="<%=wo1.getPO_TITLE()%>" /></td>
        <td class="lbl">Type:</td>
        <td>
            <select id="j" name="j"  style="width:100px">
                <option value="<%=wo1.getTYPE()%>"><%=wo1.getTYPE()%></option>
                <%for (Map.Entry<String, String> entry : Variables.wo_type.entrySet()) {%>
                <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                <% }%>
            </select>
        </td>
    </tr>
    <tr>
        <td class="lbl">Supplier:</td>
        <td><select id="e" name="e"  style="width:300px">
                <option value="<%=wo1.getSUP_ID()%>"><%=wo1.getSUP_NAME()%></option>
                <%for (Map.Entry<String, String> entry : Variables.sup.entrySet()) {%>
                <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                <%  }%>
            </select></td>
        <td class="lbl">Site:</td>
        <td><select id="f" name="f"  style="width:120px">
                <option value="<%=wo1.getSITE()%>"><%=wo1.getSITE()%></option>
            </select></td>
        <td class="lbl">Currency:</td>
        <td><select id="h" name="h"  style="width:150px">
                <option value="<%=wo1.getCUR()%>"><%=wo1.getCUR()%></option>
                <%for (Map.Entry<String, String> entry : Variables.cur.entrySet()) {%>
                <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                <% }%>
            </select></td>
        <td class="lbl">Operating Unit:</td>
        <td><input type="text" name="g" size="15"  class="readon"   value="<%=wo1.getOU()%>"   readonly/></td>
    </tr>
    <tr>
        <td class="lbl" >Retention Amount:</td>
        <td><input type="text" name="i" size="15"  class="cur" value="<%=wo1.getRET_AMT()%>"/></td>
        <td class="lbl" >Deposit Amount:</td>
        <td><input type="text" name="i2" size="15"  class="cur"  value="<%=wo1.getDEP_AMT()%>"/></td>
        <td class="lbl">Valid From:</td>
        <td><input type="text" id="fk" name="fk" size="15" value="" />
         <p style="color: gray;font-size: small">(MM/DD/YYYY)</p></td>
        <td class="lbl">Valid To:</td>
        <td><input type="text" id="k" name="k" size="15" value="" />
        <p style="color: gray;font-size: small">(MM/DD/YYYY)</p></td>
    </tr>
   
</table>
