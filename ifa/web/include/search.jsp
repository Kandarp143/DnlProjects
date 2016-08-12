<form method="POST" ACTION="itmmst.jsp" onsubmit="submit.disabled = true;
                        submit.value = 'Processing ..';
                        return true;">
    <table class="dtb" style="table-layout: fixed">
        <th>Please Enter Description </th>
        <td><input type="text" size="60" name="desc" id="desc"/>
            <input type="submit" value="submit" id="submit" name="submit" onsubmit="this.disabled = true;
                    this.value = 'Processing ..';
                    return true;"  />
        </td>
        <%if (request.getParameterMap().containsKey("err")) {%>
        <td style="color: red">
            There is some error  
        </td>
        <%}%>
    </table>
</form>