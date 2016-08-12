<%@page import="Logic.Variables"%>
<%@page import="java.util.Map"%>
<table id="myTable" class="dtb">
    <thead>
        <tr>
            <th colspan="2">
                <a href="billcreate_data.jsp?pono=<%=request.getParameter("pono")%>&sid=6">Load Excel
                </a>
            </th>
            <td colspan="4"></td>
            <th colspan="4">Budgeted Amount</th>
            <th colspan="2"><input type="text" class="readon,cur" name="bud" id="bud" readonly/></th>
        </tr>
        <tr>
            <th colspan="2"><input type="button" id="btnAdd" name="btnAdd" value="Add Item" style="color: #A70303; font-weight: bold"/></th>
            <th><select id="A" name="A" style="width: 300px"> </select></th>
            <th><input type="text" id="B" name="B"  size="2"  class="readon" readonly> </th>
            <th><input type="text" id="C" name="C"  value="0" size="3" class="cur" > </th>
            <th><input type="text" id="D" name="D"  value="0" size="3"  class="readon cur" readonly > </th>
            <th><input type="text" id="E" name="E" value="0" size="3"class="readon cur" readonly > </th>
            <th><select id="G" name="G" style="width: 70px">  <option value="0">Plant</option>
                    <%for (Map.Entry<String, String> entry : Variables.itmplant.entrySet()) {%>
                    <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                    <%  }%></select></th>
            <th><select id="H" name="H" style="width: 70px">  <option value="0">Cost Center</option>
                    <%for (Map.Entry<String, String> entry : Variables.itmcc.entrySet()) {%>
                    <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                    <%  }%></select></th>
            <th><select id="I" name="I" style="width: 200px">  <option value="-">Project</option>
                    <%for (Map.Entry<String, String> e : Variables.project.entrySet()) {%>
                    <option value="<%=e.getKey()%>"><%=e.getValue()%></option>
                    <%  }%></select></th>
            <th><select id="J" name="J" style="width: 100px">  
                    <option value="-">Task</option>
                </select></th>
            <th><input type="text" id="F" name="F" size="5" value="-"> </th>
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
            <th>Cost Center</th>
            <th>Project</th>
            <th>Task</th>
            <th>Note</th>
        </tr>
        <%  int index2v = 1;
            int ind2 = 0;
            for (WorkItemBean wi2 : wi21) {
                ind2 = index2v++;
        %>
        <tr>
            <td><button type="button" class="removebutton" title="Remove this row">X</button></td>
            <td><%=ind2%></td>
            <td><input type="hidden" id="A<%=ind2%>"  name="A<%=ind2%>" value="<%=wi2.getITEM_ID()%>" />
                <input type="text" value="<%=wi2.getITEM_DESC()%>" readonly style="width: 300px"/></td>
            <td><input type="text" id="B<%=ind2%>"  name="B<%=ind2%>" value="<%=wi2.getUOM()%>"   class="readon"   readonly size="2"   /></td>
            <td><input type="text" id="C<%=ind2%>"  name="C<%=ind2%>" value="<%=wi2.getQTY()%>"  class="cur"     size="3"   /></td>
            <td><input type="text" id="D<%=ind2%>"  name="D<%=ind2%>" value="<%=wi2.getRATE()%>" class="cur readon"   readonly size="3"   /></td>
            <td><input type="text" id="E<%=ind2%>"  name="E<%=ind2%>" value="<%=wi2.getVAL()%>"  class="cur readon"   readonly size="3"   /></td>
            <td><select id="G<%=ind2%>" name="G<%=ind2%>" style="width: 70px">  <option value="<%=wi2.getPLANT()%>"><%=wi2.getPLANT()%></option>
                    <%for (Map.Entry<String, String> entry : Variables.itmplant.entrySet()) {%>
                    <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                    <%  }%></select></td>
            <td><select id="H<%=ind2%>" name="H<%=ind2%>" style="width: 70px">  <option value="<%=wi2.getCC()%>"><%=wi2.getCC()%></option>
                    <%for (Map.Entry<String, String> entry : Variables.itmcc.entrySet()) {%>
                    <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                    <%  }%></select></td>
            <td><select id="I<%=ind2%>" name="I<%=ind2%>" style="width: 200px">  <option value="<%=wi2.getPROJ()%>"><%=wi2.getPROJ()%></option>
                    <%for (Map.Entry<String, String> entry : Variables.project.entrySet()) {%>
                    <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                    <%  }%></select></td>
            <td><select id="J<%=ind2%>" name="J<%=ind2%>" style="width: 100px">  <option value="<%=wi2.getTASK()%>"><%=wi2.getTASK()%></option>
                    <%for (Map.Entry<String, String> entry : Variables.task.entrySet()) {%>
                    <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                    <%  }%></select></td>
            <td><input type="text" id="F<%=ind2%>"  name="F<%=ind2%>" value="<%=wi2.getCMT()%>"  size="4"/></td>

        </tr>
        <% }%>
    <input type="hidden" id="itemrows" name="itemrows" value="<%=ind2%>" />
</thead>
</table>
<script>
    var ctr = <%=ind2%>;
</script>
<script src="js/custom/billdt.js"></script>