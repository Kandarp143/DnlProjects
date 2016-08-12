<%@page import="Logic.Variables"%>
<%@page import="java.util.Map"%>
<table id="myTable" class="dtb">
    <thead>
        <tr>
            <th colspan="2"><input type="button" id="btnAdd" name="btnAdd" value="Add Item" style="color: #A70303; font-weight: bold"/></th>
            <th><select id="A" name="A" style="width: 300px"></select></th>
            <th><input type="text" id="B" name="B" size="2"  class="readon" readonly> </th>
            <th><input type="text" id="C" name="C" size="3" value="0"  class="cur"> </th>
            <th><input type="text" id="D" name="D" size="3" value="0" class="cur"> </th>
            <th><input type="text" id="E" name="E" size="3" value="0"  class="readon cur" readonly> </th>
            <th><select id="G" name="G" style="width: 100px">  <option value="-">Plant</option>
                    <%for (Map.Entry<String, String> entry : Variables.itmplant.entrySet()) {%>
                    <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                    <%  }%></select></th>
            <th><select id="I" name="I" style="width: 200px">  <option value="-">Project</option>
                    <%for (Map.Entry<String, String> e : Variables.project.entrySet()) {%>
                    <option value="<%=e.getKey()%>"><%=e.getValue()%></option>
                    <%  }%></select></th>
            <th><select id="J" name="J" style="width: 100px">  
                    <option value="-">Task</option>
                </select></th>
            <th><input type="text" id="F" name="F" size="10"  value="-"> </th>
        </tr>
        <tr>
            <th>Remove</th>
            <th>SR NO</th>
            <th>Item & Description </th>
            <th>U.O.M</th>
            <th>Quantity</th>
            <th>Rate</th>
            <th>Value</th>
            <th>Plant</th>
            <th>Project</th>
            <th>Task</th>
            <th>Note</th>
        </tr>
        <%  int irow = 0;
            for (WorkItemBean wi2 : wi1) {
                irow = irow + 1;
        %>
        <tr>
            <td><button type="button" class="removebutton" title="Remove this row">X</button></td>
            <td><%=irow%></td>
            <td><input type="hidden" id="A<%=irow%>"  name="A<%=irow%>" value="<%=wi2.getITEM_ID()%>" />
                <input type="text"   value="<%=wi2.getITEM_DESC()%>"    style="width:300px"  readonly /></td>
            <td><input type="text"   id="B<%=irow%>"  name="B<%=irow%>" value="<%=wi2.getUOM()%>"  size="2"  readonly /></td>
            <td><input type="text"   id="C<%=irow%>"  name="C<%=irow%>" value="<%=wi2.getQTY()%>"  class="cur" size="3" readonly /></td>
            <td><input type="text"   id="D<%=irow%>"  name="D<%=irow%>" value="<%=wi2.getRATE()%>" class="cur" size="3" readonly /></td>
            <td><input type="text"   id="E<%=irow%>"  name="E<%=irow%>" value="<%=wi2.getVAL()%>"  class="cur" size="3" readonly /></td>
            <td><input type="hidden" id="G<%=irow%>"  name="G<%=irow%>" value="<%=wi2.getPLANT()%>" />
                <input type="text"   value="<%=wi2.getPLANT()%>"        style="width: 100px"  readonly /></td>
            <td><input type="hidden" id="I<%=irow%>"  name="I<%=irow%>" value="<%=wi2.getPROJ()%>" />
                <input type="text"   value="<%=wi2.getPROJ()%>"         style="width: 200px" readonly/></td>
            <td><input type="hidden" id="J<%=irow%>"  name="J<%=irow%>" value="<%=wi2.getTASK()%>" />
                <input type="text"   value="<%=wi2.getTASK()%>"         style="width: 100px" readonly /></td>
            <td><input type="text"   id="F<%=irow%>"  name="F<%=irow%>" value="<%=wi2.getCMT()%>"  size="10" readonly /></td>
        </tr>
        <% }%>
    <input type="hidden" id="itemrows" name="itemrows" value="<%=irow%>"/>
</thead>
</table>
<script>
    var ctr = "<%=irow%>";
    var itms = [];
</script>
<%for (WorkItemBean wi2 : wi1) {%>
<script>
    //already item duplicate check
    itms.push("<%=wi2.getITEM_ID()%>");
</script>
<%}%>
<script src="js/custom/wodt.js"></script>