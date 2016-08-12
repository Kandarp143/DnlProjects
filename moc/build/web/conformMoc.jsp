<%-- 
    Document   : createMOC
    Created on : Oct 7, 2015, 5:30:44 PM
    Author     : 02948
--%>
<%@page import="kp.beans.mst.pojo.MocUnitMst"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.logic.DropDown"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/global.css" type="text/css" rel="stylesheet"/>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/Datepicker.js" ></script>
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
        <script>
            $(document).ready(function () {
                //Date Picker for update task
                for (var i = 1; i < 18; i++) {
                    $("#s" + i).datepicker({
                        showOn: 'button',
                        buttonText: 'Show Date',
                        buttonImageOnly: true,
                        buttonImage: 'image/calendar.gif',
                        dateFormat: 'dd/mm/yy',
                        constrainInput: true
                    });
                }
            });
        </script>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <div>
                    <h4 id="page_header">Conform MOC</h4>
                </div>
                <div>
                    <form action="process/processConform.jsp?cid=<%=request.getParameter("cid")%>&sid=<%=request.getParameter("sid")%>" method="post" onsubmit="submit.disabled = true;
                            submit.value = 'Processing ..';
                            return true;">
                        <%@ include file="include/form/I10.jsp" %>
                        <%@ include file="include/form/CMT.jsp" %>
                        <input type="submit" value="submit" id="submit" name="submit" class="btn"/>
                    </form>
                </div>

            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
