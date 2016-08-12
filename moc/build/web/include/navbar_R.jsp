<div class="content">
    <div class="arrowlistmenu">
        <!--        <h3 class="headerbar">Test Cases</h3>
                <ul>
                    <li><a href="../createMoc.jsp">Create Moc</a></li>
                    <li><a href="../saveTs.jsp?cid=1">Save TS</a></li>
                    <li><a href="../saveHse.jsp?cid=1">Save HSE</a></li>
                    <li><a href="../saveEng.jsp?cid=1">Save ENG</a></li>
                    <li><a href="../saveQc.jsp?cid=1">Save QC</a></li>
                    <li><a href="../selUser.jsp?cid=1">Select User</a></li>
                </ul>-->

        <h3 class="headerbar">WorkSpace</h3>
        <%String accRoleforNav = session.getAttribute("accrole").toString();%>
        <ul>
            <li><a href="../mywork.jsp">My Workspace</a></li>
        </ul>
        <h3 class="headerbar">MOC</h3>
        <ul>
            <%if (session.getAttribute("iscreator").toString().equalsIgnoreCase("true")) {%>
            <li><a href="../createMoc.jsp">Create MOC</a></li>
                <%}%>
            <li><a href="../mocstatus.jsp">MOC Status (All)</a></li>
            <li><a href="../mocstatus.jsp?onlyact">MOC Status (Action Taken)</a></li>
        </ul>
        <h3 class="headerbar">User Details</h3>
        <ul>
            <li><a href="#">Change Password</a></li>
            <li><a href="#">User Details</a></li>
            <li><a href="#">User Manuals</a></li>
        </ul>
        <%  if (accRoleforNav.equalsIgnoreCase("SYS")) {%>
        <h3 class="headerbar">Admin Task</h3>
        <ul>
            <li><a href="#">User Management</a></li>
            <li><a href="#">MOC Tasks</a></li>
            <li><a href="#">Online User</a></li>
        </ul>
        <%}%>
    </div>
</div>
<!-- end content -->