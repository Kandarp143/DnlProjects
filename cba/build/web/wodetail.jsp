<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
<%!int sid;%>
<%
    if (request.getParameter("sid") != null || request.getParameter("sid") != "") {
        sid = Integer.parseInt(request.getParameter("sid"));
    } else {
        sid = 0;
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLCBA</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.searchabledropdown-1.0.8.min.js"></script>
        <script src="js/dynamicrow.js" type="text/javascript"></script>
        <script src="js/Datepicker.js" type="text/javascript"></script>
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
        <script src="js/jquery-1.7.1.js" type="text/javascript"></script>
        <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
        <link href="css/tabs2.css" rel="stylesheet" type="text/css" />
        <script src="js/tabs.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <table class="tabs-menu" >
                    <tr>
                        <td style="width:40%">
                            WO Number: <%=request.getParameter("pono")%>
                        </td>
                        <td class="current"><a href="#tab-1">WO Summary</a></td>
                        <td><a href="#tab-2">Attachment & Report</a></td>
                        <td><a href="#tab-3">Workflow</a></td>
                        <% if (sid == 0) {%>
                        <td><a href="#tab-4">Bill Against WO</a></td>
                        <%}%>
                        <% if (sid != 4 && sid != 0) {%>
                        <td><a href="#tab-5">Action</a></td>
                        <% }%>
                    </tr>
                </table>
                <div id="tab-1" class="tab-content" style="width: 100%">
                    <%@ include file="include/viewwo.jsp" %>
                </div>
                <div id="tab-2" class="tab-content"> 
                    <%@ include file="include/attachment.jsp" %>
                </div>
                <div id="tab-3" class="tab-content">
                    <%@ include file="include/workflow.jsp" %>
                </div>
                <% if (sid == 0) {
                %>
                <div id="tab-4" class="tab-content">
                    <%@ include file="include/bilpo.jsp" %>
                </div>
                <% }%>
                <% if (sid != 0) {
                %>
                <div id="tab-5" class="tab-content">
                    <%@ include file="include/action.jsp" %>
                </div>
                <% }%>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>