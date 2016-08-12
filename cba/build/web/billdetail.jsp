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
        <!--[if lte IE 7]>
        <style type="text/css">
           body {word-wrap:break-word;}
           #outer2 {float:left; width:59.8%; background:#c0c0c0;}
           /* for IE6 */
           * html #wrapper {display:inline-block;}
        </style>
        <![endif]-->
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
                            Bill ID:<%=request.getParameter("bill")%>

                        </td>
                        <td class="current"><a href="#tab-1">Bill Summary</a></td>
                        <td><a href="#tab-2">Attachment & Report</a></td>
                        <td><a href="#tab-3">Workflow</a></td>
                        <td><a href="#tab-4">Projected Invoice</a></td>
                        <% if (sid != 0) {%>
                        <% if (sid != 10) {%>
                        <td><a href="#tab-5">Action</a></td>
                        <%}
                            }%>
                    </tr>
                </table>
                <div id="tab-1" class="tab-content" style="width: 100%">
                    <%@ include file="include/viewbill.jsp" %></div>
                <div id="tab-2" class="tab-content"> 
                    <%@ include file="include/attachment_bill.jsp" %>
                </div>
                <div id="tab-3" class="tab-content">
                    <%@ include file="include/workflow_bill.jsp" %>
                </div>
                <% if (sid != 0 && sid != 10) {
                %>
                <div id="tab-5" class="tab-content">
                    <%@ include file="include/action_bill.jsp" %>
                </div>
                <% }%>
                <div id="tab-4" class="tab-content">
                    <%@ include file="include/viewinv.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
