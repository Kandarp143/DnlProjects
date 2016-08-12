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
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>     
        <link href="css/datasource_gui.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
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
                    <h4 id="page_header">Moc Status</h4>
                </div>
                <div>
                    <%
                        ArrayList<MocWfTran> Mocstatus = new ArrayList<>();
                        TranDao tdao = new TranDao();
                        if (request.getParameterMap().containsKey("onlyact")) {
                            Mocstatus = tdao.getMocStatusList("USR",
                                    session.getAttribute("usrunit").toString(),
                                    session.getAttribute("usr").toString());
                        } else {
                            Mocstatus = tdao.getMocStatusList(session.getAttribute("accrole").toString(),
                                    session.getAttribute("usrunit").toString(),
                                    session.getAttribute("usr").toString());
                        }
                    %>
                    <!--Pending List-->
                    <%if (!Mocstatus.isEmpty()) {%>
                    <table class="dtb" id="dtb2" name="dtb2">
                        <thead>
                            <tr>
                                <td colspan="5">
                                    <span class="err">Click on column header for sort table according to that column!</span> 
                                </td>
                                <td>
                                    <%if (request.getParameterMap().containsKey("onlyact")) {%>
                                    <form method="post" action="ExportRpt?accRole=USR&unit=<%=session.getAttribute("usrunit").toString()%>&user=<%=session.getAttribute("usr").toString()%>" 
                                          onsubmit="submit.disabled = true;
                                                  submit.value = 'Processing ..';
                                                  return true;">
                                        <input type="submit" value="Export to Excel"/>
                                    </form> 

                                    <%} else {%>
                                    <form method="post" action="ExportRpt?accRole=<%=session.getAttribute("accrole").toString()%>&unit=<%=session.getAttribute("usrunit").toString()%>&user=<%=session.getAttribute("usr").toString()%>" 
                                          onsubmit="submit.disabled = true;
                                                  submit.value = 'Processing ..';
                                                  return true;">
                                        <input type="submit" value="Export to Excel"/>
                                    </form> 

                                    <%}%>


                                </td>
                            </tr>
                            <tr>
                                <th>Case ID</th>
                                <th>Moc NO</th>
                                <th>Moc Title</th>
                                <th>Moc Status</th>
                                <th>Creation Date</th>
                                <th>Owner Name</th>
                                <th>Unit/Plant</th>
                                    <%   if (!request.getParameterMap().containsKey("onlyact")) {%>
                                <th>Current Stage</th>
                                <th>Pending At</th>
                                    <%}%>
                                <th>Detail</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (MocWfTran bean : Mocstatus) {%>
                            <tr>
                                <td class="cur"><b><%=bean.getCaseId()%></b></td>
                                <td><span style="color: green"><%=bean.getMocNo()%></span></td>
                                <td><%=bean.getCaseName()%></td>
                                <td><%=bean.getMocStatus()%></td>
                                <td><%=bean.getCrDateString()%></td>
                                <td><%=bean.getCaseOwnerName()%></td>
                                <td><%=bean.getUnitId()%>/<%=bean.getPlantId()%></td>
                                <%   if (!request.getParameterMap().containsKey("onlyact")) {%>
                                <td style="color: #8C1F1F;"><%=bean.getStgNname()%></td>
                                <td style="color: #8C1F1F;"><%=bean.getUserNname()%></td>
                                <%}%>
                                <td><a href="viewMoc.jsp?cid=<%=bean.getCaseId()%>&sid=<%=bean.getStgN()%>">Click Here</a></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <%} else {%>
                    <table class="dtb">
                        <tr>
                            <td>NO moc related to you !</td>
                        </tr>
                    </table>
                    <%}%>
                </div>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
