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
        <%
            int irow = 0;
            if (request.getParameter("itemrows") == null || "".equals(request.getParameter("itemrows"))) {
                irow = 0;
            } else {
                irow = Integer.parseInt(request.getParameter("itemrows"));
            }
            //ITEM LEVEL WO
            if (irow > 0) {
                for (int i = 1; i <= irow; i++) {
                    if (request.getParameter("A" + i) != null) {%>
        <tr>
            <td><button type="button" class="removebutton" title="Remove this row">X</button></td>
            <td> <%=i%> </td>
            <td><input type="text" id="<%="A" + i%>"    name="<%="A" + i%>"    value="<%=request.getParameter("A" + i)%>"    readonly style="width: 300px"/></td>
            <td><input type="text" id= "<%="B" + i%>"   name="<%="B" + i%>"    value="<%=request.getParameter("B" + i)%>"    readonly size="2" /></td>
            <td><input type="text" id= "<%="C" + i%>"   name="<%="C" + i%>"    value="<%=request.getParameter("C" + i)%>"   readonly size="3" class="cur"/></td>
            <td><input type="text" id= "<%="D" + i%>"   name="<%="D" + i%>"    value="<%=request.getParameter("D" + i)%>"   readonly size="3" class="cur"/></td>
            <td><input type="text" id= "<%="E" + i%>"   name="<%="E" + i%>"    value= "<%=request.getParameter("E" + i)%>"   readonly size="3" class="cur"/></td>
            <td><input type="text" id="<%="G" + i%>"    name="<%="G" + i%>"    value="<%=request.getParameter("G" + i)%>"    readonly style="width: 100px"/></td>
            <td><input type="text" id="<%="I" + i%>"    name="<%="I" + i%>"    value="<%=request.getParameter("I" + i)%>"    readonly style="width: 200px"/></td>
            <td><input type="text" id="<%="J" + i%>"    name="<%="J" + i%>"    value="<%=request.getParameter("J" + i)%>"    readonly style="width: 100px"/></td>
            <td><input type="text" id="<%="F" + i%>"    name="<%="F" + i%>"    value="<%=request.getParameter("F" + i)%>"    readonly size="10"/></td>
        </tr>
        <%     }
                }
            }
        %>
    <input type="hidden" id="itemrows" name="itemrows" />
</thead>
</table>
<script>
    var ctr = "<%=irow%>";
    var itms = [];
</script>
<script src="js/custom/wodt.js"></script>