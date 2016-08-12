<%@page import="Logic.GetMethod"%>
<div class="content">
    <div class="arrowlistmenu">
        <h3 class="headerbar">WorkSpace</h3>
        <ul>
            <li><a href="mywork.jsp">My Workspace</a></li>
            <li><a href="reports.jsp">CBA Reports</a></li>
        </ul>
        <h3 class="headerbar">Work Order</h3>
        <%
            Logic.GetMethod gg = new GetMethod();
            String typ = gg.Get_Perameter(session.getAttribute("uid").toString(), "ACC_ROLE", "CBA_USER_MST");
            String urole = gg.Get_Perameter(session.getAttribute("uid").toString(), "ROLE_ID", "CBA_USER_ROLE");

            if (typ.equals("SA")) {%>
        <ul>
            <li><a href="wocreate.jsp">Create WO (ARC)</a></li>
            <li><a href="wocreate_data.jsp">Create WO (ARC)<br/> [WIth Excel Data Loader]</a></li>
            <li><a href="a_wolist.jsp">Work Order List</a></li>
        </ul>

        <% } else if (typ.equals("CN")) {%>
        <ul>
            <li><a href="sup_wo_list.jsp">Work Order List</a></li>
        </ul>
        <% } else if (urole.equals("FIN") || urole.equals("DHA") || urole.equals("UHA")) {%>
        <ul>
            <li><a href="a_wolist.jsp">Work Order List</a></li>
        </ul>
        <% } else {%>
        <ul>
            <li><a href="wocreate.jsp">Create WO (ARC)</a></li>
            <li><a href="wocreate_data.jsp">Create WO (ARC)<br/><br/> [WIth Excel Data Loader]</a></li>
            <li><a href="wolist.jsp">Work Order List</a></li>
        </ul>
        <%}%>

        <h3 class="headerbar">Bill</h3> 
        <%
            if (typ.equals("SA")) {%>
        <ul>
            <li><a href="worelease.jsp">Create Bill</a></li>
            <li><a href="a_billlist.jsp">Bill List (All)</a></li>
            <li><a href="billlistr.jsp">Bill List (Action Taken)</a></li>
        </ul>
        <% } else if (typ.equals("CN")) {%>
        <ul>
            <li><a href="sup_bill_create.jsp">Create Bill</a></li>
            <li><a href="sup_bill_list.jsp">Bill List</a></li>
            <li><a href="billlistr.jsp">Bill List (Action Taken)</a></li>
        </ul>
        <% } else if (urole.equals("FIN") || urole.equals("DHA") || urole.equals("UHA")) {%>
        <ul>
            <li><a href="a_billlist.jsp">Bill List (All)</a></li>
            <li><a href="billlistr.jsp">Bill List (Action Taken)</a></li>
        </ul>
        <% } else {%>
        <ul>
            <li><a href="worelease.jsp">Create Bill</a></li>
            <li><a href="billlist.jsp">Bill List (Created)</a></li>
            <li><a href="billlistr.jsp">Bill List (Action Taken)</a></li>
        </ul>
        <%}%>        
        <h3 class="headerbar">Invoice</h3>
        <%
            if (typ.equals("SA")) {%>
        <ul>
            <li><a href="a_inv.jsp">Create Invoice</a></li>
            <li><a href="a_invlist.jsp">Invoice List</a></li>
        </ul>
        <% } else if (typ.equals("CN")) {%>
        <ul>
            <li><a href="inv.jsp">Create Invoice</a></li>
            <li><a href="invlist.jsp">Invoice List</a></li>
        </ul>
        <% } else if (urole.equals("FIN") || urole.equals("DHA") || urole.equals("UHA")) {%>
        <ul>
            <li><a href="a_invlist.jsp">Invoice List</a></li>
        </ul>
        <% } else {%>
        <ul>
            <li><a href="inv.jsp">Create Invoice</a></li>
            <li><a href="invlist.jsp">Invoice List</a></li>
        </ul>
        <%}%>
        <h3 class="headerbar">User Details</h3>
        <ul>
            <li><a href="ucpass.jsp">Change Password</a></li>
        </ul>
    </div>
</div>
<!-- end content -->