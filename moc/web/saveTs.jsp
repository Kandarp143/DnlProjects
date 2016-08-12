<%@page import="kp.logic.GeneralMethods"%>
<%@page import="kp.beans.data.pojo.MocTsMst"%>
<%@page import="kp.dao.data.TsDao"%>
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
                    <h4 id="page_header">Save TS</h4>
                </div>
                <div>
                    <form action="process/processTs.jsp?cid=<%=request.getParameter("cid")%>&sid=<%=request.getParameter("sid")%>&act=1" method="post" onsubmit="submit.disabled = true;
                            submit.value = 'Processing ..';
                            return true;">
                        <%
                            GeneralMethods gm = new GeneralMethods();
                            TsDao tdao = new TsDao();
                            MocTsMst objT = new MocTsMst();
                            objT = tdao.fecthTs(Integer.parseInt(request.getParameter("cid")));
                            if (objT == null) {%>
                        <%@ include file="include/form/T1.jsp" %>
                        <%} else {%>
                        <%@ include file="include/update/T1_U.jsp" %>
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
