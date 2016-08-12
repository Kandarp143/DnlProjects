<%GeneralMethods gm = new GeneralMethods();%>
<%@page import="kp.logic.GeneralMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/global.css" type="text/css" rel="stylesheet"/>
          <link href="../css/dtb.css" type="text/css" rel="stylesheet"/>
        <link href="../css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="../css/container.css" type="text/css" rel="stylesheet"/>
        <!--http://www.dynamicdrive.com/dynamicindex16/formwizard.htm-->
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../include/header_R.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="../include/navbar_R.jsp" %>
            </div>
            <div id="Right">
                <div>
                    <h4 id="page_header">Select User</h4>
                </div>
                <div>
                    <form action="processSel.jsp?cid=<%=request.getParameter("cid")%>" method="post" onsubmit="submit.disabled = true;
                            submit.value = 'Processing ..';
                            return true;">
                        <%@ include file="include/errorlist.jsp" %>
                        <%@ include file="include/retry/SEL_R.jsp" %>
                        <%@ include file="include/retry/CMT_R.jsp" %>
                        <input type="submit" value="submit" id="submit" name="submit" class="btn"/>
                    </form>
                </div>

            </div>
        </div>
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
