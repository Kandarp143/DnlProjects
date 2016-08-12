<table class="form_table" >
    <tr><th> TS Department Verification</th></tr>
    <tr>
        <td> <%=gm.getViewCheckBox(ts.getI1())%>  P &amp; ID Verification</td>
    </tr>
    <tr>
        <td>
            <%=gm.getViewCheckBox(ts.getI2())%>   Process Verification
        </td>
    </tr>
    <tr>
        <td>
            Any Specific Requirement :<br/><br/>
            <%=ts.getI3()%>
        </td>
    </tr>
    <tr>
        <td>
            <%=gm.getViewCheckBox(ts.getI4())%>   VP technology approval required ?
        </td>
    </tr>

</table>
