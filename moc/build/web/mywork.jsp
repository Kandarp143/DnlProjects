<%@page import="kp.beans.wf.pojo.MocWfTran"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.dao.wf.TranDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLMOC</title>
        <link href="css/global.css" type="text/css" rel="stylesheet"/>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>     
        <link href="css/datasource_gui.css" type="text/css" rel="stylesheet"/>
        <!--Datagrid call -->
        <script type="text/javascript">
            $(document).ready(function () {
                $("#dtb2").dataTable({
                    "sPaginationType": "full_numbers",
                    "iDisplayLength": 18,
                    "bJQueryUI": true,
                    "aaSorting": []});
            });
        </script>

    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <div>
                    <h4 id="page_header">My WorkSpace / Pending WorkList</h4>
                </div>
                <div>
                    <%
                        ArrayList<MocWfTran> CreatedList = new ArrayList<>();
                        ArrayList<MocWfTran> PendingList = new ArrayList<>();
                        TranDao tdao = new TranDao();
                        CreatedList = tdao.getMocList("Created", session.getAttribute("usr").toString());
                        PendingList = tdao.getMocList(session.getAttribute("usr").toString());
                    %>
                    <!--Created List -->
                    <%if (!CreatedList.isEmpty()) {%>
                    <table class="dtb" id="mwork" name="mwork">
                        <tr>
                            <th colspan="4">
                                Initiate Workflow
                            </th>
                        </tr>
                        <thead></thead>

                        <tr>
                            <th>Case ID</th>
                            <th>Moc Title</th>
                            <th>Creation Date</th>
                            <th>Owner Name</th>
                            <th>Unit/Plant</th>
                            <th>Detail</th>
                            <th>Initiate Workflow</th>
                        </tr>
                        <%for (MocWfTran bean : CreatedList) {%>
                        <tr>
                            <td class="cur"><%=bean.getCaseId()%></td>
                            <td><%=bean.getCaseName()%></td>
                            <td><%=bean.getCrDateString()%></td>
                            <td><%=bean.getCaseOwnerName()%></td>
                            <td><%=bean.getUnitId()%>/<%=bean.getPlantId()%></td>
                            <td><a href="viewMoc.jsp?cid=<%=bean.getCaseId()%>&sid=<%=bean.getStgN()%>">Click Here</a></td>
                            <td><a href="selUser.jsp?cid=<%=bean.getCaseId()%>">Select User</a></td>
                        </tr>
                        <%}%>
                    </table>
                    <%} else {%>
                    <table class="dtb">
                        <tr>
                            <td>NO Moc Pending for initiate !</td>
                        </tr>
                    </table>
                    <%}%>


                    <!--Pending List-->
                    <%if (!PendingList.isEmpty()) {%>
                    <table class="dtb" id="dtb2" name="dtb2"  >
                        <thead>
                            <tr>
                                <th colspan="4">
                                    Action Pending
                                </th>
                            </tr>
                            <tr>
                                <th>Case ID</th>
                                <th>Moc Title</th>
                                <th>Creation Date</th>
                                <th>Owner Name</th>
                                <th>Unit/Plant</th>
                                <th>Last Stage</th>
                                <th>Action Done</th>
                                <th>Action Done By</th>
                                <th>Action Date</th>
                                <th>Current Stage</th>
                                <th>Detail</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (MocWfTran bean : PendingList) {%>
                            <tr>
                                <td class="cur"><%=bean.getCaseId()%></td>
                                <td><%=bean.getCaseName()%></td>
                                <td><%=bean.getCrDateString()%></td>
                                <td><%=bean.getCaseOwnerName()%></td>
                                <td><%=bean.getUnitId()%>/<%=bean.getPlantId()%></td>
                                <td><%=bean.getStgCname()%></td>
                                <td><%=bean.getActname()%></td>
                                <td><%=bean.getUserCname()%></td>
                                <td><%=bean.getActDateString()%></td>
                                <td><%=bean.getStgNname()%></td>
                                <td><a href="viewMoc.jsp?cid=<%=bean.getCaseId()%>&sid=<%=bean.getStgN()%>&page=<%=bean.getNextPage()%>">Click Here</a></td>
                                <td><a href="<%=bean.getNextPage()%>.jsp?cid=<%=bean.getCaseId()%>&sid=<%=bean.getStgN()%>">Click Here</a></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <%} else {%>
                    <table class="dtb">
                        <tr>
                            <td>NO Moc Pending for Action !</td>
                        </tr>
                    </table>
                    <%}%>
                </div>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
