
<%@page import="Bean.GlobalVar"%>
<%@page import="Bean.Item"%>
<div style="float: left">
    <FORM method="POST" ACTION="mstitm.jsp">
        <select name="grp" id="grp">
            <% if (request.getParameterMap().containsKey("grp")) {%>
            <option value="<%=request.getParameter("grp")%>"><%=request.getParameter("grp")%></option>
            <%} else {%>
            <option value="0">Select Item Group </option>
            <%}%><% for (Item b : GlobalVar.itm_grp) {%>
            <option value="<%=b.getGrpid()%>"><%=b.getGrpid()%></option>
            <%}%>
        </select>
        <input type="submit" value="submit" id="submit" name="submit" onsubmit="this.disabled = true;
                this.value = 'Processing ..';
                return true;"  />
    </form>
</div>
