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
    <input type="hidden" id="itemrows" name="itemrows" />
</thead>
</table>
<script>
    var ctr = 0;
</script>
<script src="js/custom/billdt.js"></script>