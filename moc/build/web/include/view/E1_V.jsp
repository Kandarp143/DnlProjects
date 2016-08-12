<%@page import="java.math.BigDecimal"%>
<%@page import="kp.beans.data.pojo.MocEngMst"%>
<table class="form_table" >
    <tr>
        <th colspan="2">
            Engineering Department Cost Estimation
        </th>
    </tr>
    <%double sum = 0;
        for (MocEngMst eng : engs) {%>
    <tr>
        <th style="width: 40%">
            <%=eng.getDeptId()%>
        </th>
    </tr>
    <tr>
        <td>
            Estimated Cost<span class="err">*</span>
        </td>
        <td>
            <%= String.format("%.2f", eng.getL1())%>
            <%  sum = sum + eng.getL1();%>
        </td>
    </tr>
    <tr>
        <td>Cost Specification</td>
        <td>
            <%=eng.getL2()%>
        </td>
    </tr>
    <% }%>
    <tr>
        <th>Total Engineering Cost</th>
        <th><%=String.format("%.2f", sum)%></th>
    </tr>

</table>
