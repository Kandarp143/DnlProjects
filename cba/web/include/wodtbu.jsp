<%@page import="Bean.WorkItemBean"%>
<%@page import="Logic.Variables"%>
<%@page import="java.util.Map"%>
<table id="myTable" class="dtb">
    <thead>
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
            for (WorkItemBean wi2 : wi1) {
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