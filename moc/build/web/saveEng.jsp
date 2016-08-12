<%@page import="kp.beans.data.pojo.MocEngMst"%>
<%@page import="kp.dao.data.EngDao"%>
<%@page import="kp.logic.GeneralMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/global.css" type="text/css" rel="stylesheet"/>
          <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <!--http://www.dynamicdrive.com/dynamicindex16/formwizard.htm-->
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
                    <h4 id="page_header">Save ENG</h4>
                </div>
                <div>
                    <form action="process/processEng.jsp?cid=<%=request.getParameter("cid")%>&sid=<%=request.getParameter("sid")%>&act=1" method="post" onsubmit="submit.disabled = true;
                            submit.value = 'Processing ..';
                            return true;">
                        <%
                            GeneralMethods gm = new GeneralMethods();
                            EngDao edao = new EngDao();
                            MocEngMst objE = new MocEngMst();
                            objE = edao.fecthEng(Integer.parseInt(request.getParameter("cid")), session.getAttribute("usr").toString());
                            if (objE == null) {%>
                        <%@ include file="include/form/E1.jsp" %>
                        <%} else {%>
                        <%@ include file="include/update/E1_U.jsp" %>
                        <%}%>
                        <%@ include file="include/form/CMT.jsp" %>
                        <input type="submit" value="submit" id="submit" name="submit" class="btn"/>
                    </form>
                </div>

            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
