<%@page import="Bean.TaxBean"%>
<%@page import="Logic.Variables"%>
<%@page import="java.util.Map"%>

<table style="border-right: solid #C4C4C4 thin;padding: 15px;float: left">
    <tr>
        <td>
            <table id="txtable" name="txtable" class="dtb" style="width: 60%;float: left">
                <thead>
                    <tr>
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
                    <%!int ind;%>
                    <%  int indexv = 1;
                        for (WorkTaxBean wt2 : wt1) {

                            ind = indexv++;
                    %>
                    <tr>
                        <td><button type="button" class="removebutton2" title="Remove this row">X</button></td>
                        <td><%=ind%></td>
                        <td><input  class="readon" type="text"  value="<%=wt2.getTAX_NAME()%>"  style="width: 350px"  readonly/>
                            <input   type="hidden" id="T<%=ind%>" name="T<%=ind%>" value="<%=wt2.getTAX_TYPE()%>" />
                        </td>
                        <td><input class="readon" type="text" id="R<%=ind%>" name="R<%=ind%>" value="<%=wt2.getTAX_VAL()%>"  size="5"  readonly/></td>
                    </tr>
                    <% }%>
                </thead>
                <tbody>
                </tbody>
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

                    <td style="width: 350px">

                        <input type="text" value="<%=wp1.getTERM_DESC()%>" size="50" class="readon" readonly/>
                        <input type="hidden" value="<%=wp1.getTERM_ID()%>" name="PT" id="PT" class="readon" readonly/>
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
            <input type="text" class="cur readon" value="<%=(request.getParameter("toval") == null) ? 0 : request.getParameter("toval")%>" size="20" name="toval" id="toval"  readonly/>
        </td>

    </tr>
    <tr>
        <td colspan="2" >
            <p style="color: brown;font-size: 29;"><b>  Additional Description<br/> </b></p>  
            <textarea id="cmt" name="cmt" rows="4" cols="50" value="<%=(request.getParameter("cmt") == null) ? "" : request.getParameter("cmt")%>"></textarea>
        </td>
    </tr>
    <input type="hidden" id="txrows" name="txrows" value="<%=ind%>" />
</table>
<input type="button" id="calculate" value="Calculate"/>
<script>
    var ctrr = <%=ind%>;
</script>
<script src="js/custom/billfoot.js" type="text/javascript"></script>