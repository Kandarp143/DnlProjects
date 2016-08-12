<table style="padding: 10px" cellspacing="5">
    <tr>
        <td class="lbl">Bill ID:</td>
        <td><input type="text" name="bno" id="bno" size="20" class="readon" value="XXXX" readonly/></td>
        <td class="lbl">Bill Number:<span style="color: red;font-weight: bold">*</span></td>
        <td><input type="text" name="bid" id="bid" size="20"/></td>

        <td class="lbl">Bill Date:<span style="color: red;font-weight: bold">*</span></td>
        <td><input type="text" id="k" name="k" size="20" /> <p style="color: gray;font-size: small">(MM/DD/YYYY)</p></td>
        <td class="lbl">Bill Status:</td>
        <td><input type="text" name="b" id="b" size="20" class="readon"  value="XXXX" readonly/></td>
    </tr>
    <tr>
        <td class="lbl" >WO Title:</td>
        <td colspan="3"><input type="text" name="pot" size="57" value="<%=wo1.getPO_TITLE()%>" class="readon" readonly/></td>
        <td class="lbl">WO Number:</td>
        <td><input type="text" name="a" id="a"size="20" class="readon" value="<%=wo1.getPO_NO()%>" readonly/></td>
        <td class="lbl">WO Created By :</td>
        <td><input type="text" name="cr" size="20" class="readon" value="<%=wo1.getUSER_NAME()%>" readonly/></td>
    </tr>
    <tr>
        <td class="lbl" >Supplier:</td>
        <td colspan="3"> <input type="text"  size="57" class="readon" value="<%=wo1.getSUP_NAME()%>" readonly/>
            <input type="hidden" name="e"  value="<%=wo1.getSUP_ID()%>" /></td>
        <td class="lbl">Site:</td>
        <td><input type="text" name="f" size="20" class="readon" value="<%=wo1.getSITE_NAME()%>" readonly/></td>
        <td class="lbl">Operating Unit:</td>
        <td><input type="text"  size="20" class="readon" value="<%=wo1.getOU()%>" readonly/>
            <input type="hidden" name="g"  value="123" /></td>
    </tr>
    <tr>
        <td>WO Type</td>
        <td><input type="text"  id= "j" name="j "size="20" class="readon" value="<%=wo1.getTYPE()%>" readonly/>
        </td>
<!--        <td colspan="5">  <p style="font-size: small;color: red">
                <-- If WO Type is 'Project' Then only Budget amount will display and task,project will enable</p>
        </td>-->
    </tr>
</table>
