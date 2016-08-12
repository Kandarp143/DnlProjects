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
        <td class="lbl">Bill ID:</td>
        <td><input type="text" name="bno" size="15" class="readon" value="<%=wo1.getBILL_NO()%>" readonly/></td>
        <td class="lbl">Bill Number:</td>
        <td><input type="text" name="bid" size="15" value="<%=wo1.getBILL_ID()%>" /></td>
        <td class="lbl">WO Number:</td>
        <td><input type="text" name="a" size="15" class="readon" value="<%=wo1.getPO_NO()%>" readonly/></td>
        <td class="lbl" >WO Title:</td>
        <td colspan="3" ><input type="text" name="pot" size="48" value="<%=wo1.getPO_TITLE()%>" class="readon" readonly/></td>
      
    </tr>
    <tr>
        <td class="lbl" >Supplier:</td>
        <td colspan="3"> <input type="text" name="e" size="45" class="readon" value="<%=wo1.getSUP_NAME()%>" readonly/></td>
        <td class="lbl">Site:</td>
        <td><input type="text" name="f" size="15" class="readon" value="<%=wo1.getSITE()%>" readonly/></td>
        <td class="lbl">Operating Unit:</td>
        <td><input type="text" name="g" size="15" class="readon" value="<%=wo1.getOU()%>" readonly/></td>
          <td class="lbl">Bill Status:</td>
        <td><input type="text" name="b" size="15" class="readon"  value="<%=wo1.getSTATUS()%>" readonly/></td>
    </tr>
    <tr>
        <td class="lbl">Created By :</td>
        <td colspan="3"><input type="text" name="cr" size="15" class="readon" value="<%=wo1.getUSER_NAME()%>" readonly/></td>
        <td class="lbl">Bill Date:</td>
        <td><input type="text" id="k" name="k" size="15" /> <p style="color: gray;font-size: small">(MM/DD/YYYY)</p></td>
    </tr>
</table>
