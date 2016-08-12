<%@page import="Bean.TaxBean"%>
<%@page import="Logic.Variables"%>
<%@page import="java.util.Map"%>
<table style="border-right: solid #C4C4C4 thin;padding: 15px;float: left">
    <tr>
        <td>
            <table id="txtable" name="txtable"  class="dtb" style="width: 60%;float: left">
                <thead>
                    <tr>
                        <th colspan="5">
                <p style="font-size: small;color: red">Enter tax rate only if selected tax is ad hoc.</p>
                </th>
    </tr>
    <th colspan="2">
        <input type="button" id="txAdd" name="txAdd" value="Apply Tax" size="5" style="color: #A70303; font-weight: bold"/>
    </th>
    <th>
        <select id="T" name="T"  style="width: 350px">
            <option value="0">Apply Tax</option>
            <% for (TaxBean tax1 : Variables.tax) {%>
            <option  value="<%=tax1.getTAX_ID()%>" data-rate="<%=tax1.getTAX_RATE()%>" >
                <%=tax1.getTAX_NAME()%>
            </option>
            <%}%>
        </select>
    </th>
    <th><input type="text" id="R" name="R" size="5" value="0" size="5"> </th>
</tr>
<tr>
    <th>Remove</th>
    <th>SR NO</th>
    <th>Tax Name</th>
    <th>Rate</th>
</tr>
<%
    int trow = 0;
    if (request.getParameter("txrows") == null || "".equals(request.getParameter("txrows"))) {
        trow = 0;
    } else {
        trow = Integer.parseInt(request.getParameter("txrows"));
    }
    //TAX LEVEL WO
    if (trow > 0) {
        for (int i = 1; i <= trow; i++) {
            if (request.getParameter("T" + i) != null) {%>
<tr>
    <td><button type="button" class="removebutton2" title="Remove this row">X</button></td>
    <td>  <%=i%>  </td>
    <td><input type="text" id="<%="T" + i%>"    name= "<%="T" + i%>"  value= "<%=request.getParameter("T" + i)%>"   readonly style="width: 350px"/></td>
    <td><input type="text" id="<%="R" + i%>"    name="<%="R" + i%>"   value="<%=request.getParameter("R" + i)%>"    readonly size="5" /></td>
</tr>
<% }
        }
    }
%>
</thead>
<tbody></tbody>
</table>
</td>
</tr>
<tr>
    <td>
        <table class="dtb">
            <tr>
                <th style="width: 350px">Select Payment Term</th>
            </tr>
            <tr>
                <td >
                    <select id="PT" name="PT" style="width: 500px" >
                        <% if (request.getParameter("PT") == null || request.getParameter("PT") == "") {%>
                        <option value="0">Select Payment Term</option>
                        <% } else {%>
                        <option value="<%=request.getParameter("PT")%>"><%=request.getParameter("PT")%></option>
                        <%}%>
                        <%for (Map.Entry<String, String> entry : Variables.payterm.entrySet()) {%>
                        <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
                        <%  }%>
                    </select>
                </td>
            </tr>
        </table>
    </td>
</tr>
</table>
<table style="padding: 15px;float: left">
    <tr>
        <td>
            <p style="color: brown;font-size: 29;"><b> Value : </b></p>
        </td>
        <td>
            <input type="text" class="cur readon"  value="<%=(request.getParameter("val") == null) ? 0 : request.getParameter("val")%>" size="20" name="val"  id="val"  readonly/>
        </td>
    </tr>
    <tr>
        <td>
            <p style="color: brown;font-size: 29;"><b>  Applied Tax : </b></p>
        </td>
        <td>
            <input type="text" class="cur readon"  value="<%=(request.getParameter("apt") == null) ? 0 : request.getParameter("apt")%>" size="20" name="apt" id="apt"  readonly/>
        </td>
    </tr>
    <tr>
        <td>
            <p style="color: brown;font-size: 29;"><b>  Total Value : </b></p>
        </td>
        <td>
            <input type="text" class="cur readon" value="<%=(request.getParameter("toval") == null) ? 0 : request.getParameter("toval")%>" size="20" name="toval" id="toval" 
                   readonly/>
        </td>
    </tr>
    <tr>
        <td colspan="2" >
            <p style="color: brown;font-size: 29;"><b>  Additional Description<br/> </b></p>
            <textarea id="cmt" name="cmt" rows="4" cols="50" value="<%=(request.getParameter("cmt") == null) ? "" : request.getParameter("cmt")%>"></textarea>
        </td>
    </tr>
    <input type="hidden" id="txrows" name="txrows"/>
</table>
<input type="button" id="calculate" value="Calculate"/>
<script>
    var ctrr = 0;
</script>
<script src="js/custom/wofoot.js"></script>