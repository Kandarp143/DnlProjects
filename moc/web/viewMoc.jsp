<%@page import="java.util.Set"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="kp.beans.data.pojo.MocInitTskDt"%>
<%@page import="kp.beans.mst.pojo.MocUserSelection"%>
<%@page import="kp.dao.wf.WfUserDao"%>
<%@page import="kp.beans.data.pojo.MocQcMst"%>
<%@page import="kp.dao.data.QcDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.beans.data.pojo.MocEngMst"%>
<%@page import="kp.dao.data.EngDao"%>
<%@page import="kp.beans.data.pojo.MocHseMst"%>
<%@page import="kp.dao.data.HseDao"%>
<%@page import="kp.beans.data.pojo.MocTsMst"%>
<%@page import="kp.dao.data.TsDao"%>
<%@page import="kp.logic.GeneralMethods"%>
<%@page import="kp.dao.data.MocDao"%>
<%@page import="kp.beans.data.pojo.MocInitMst"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLMOC</title>
        <link href="css/global.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link rel="stylesheet" href="css/jquery_tab_view_css.css"/>
        <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="js/jquery_tab_view.js"></script>
        <script src="js/table_rpt.js" type="text/javascript"></script>
        <script>
            $(function () {
                $("#tabs").tabs();
            });
        </script>
    </head>
    <%
        GeneralMethods gm = new GeneralMethods();
        int cid = Integer.parseInt(request.getParameter("cid"));
        %>
    <body>
        <%@include file="include/header.jsp" %>

        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <div>
                    <h4 id="page_header">MOC Case ID : <%=cid%>
                        <%if (request.getParameterMap().containsKey("page")) {
                                String url = request.getParameter("page") + ".jsp"
                                        + "?cid=" + request.getParameter("cid")
                                        + "&sid=" + request.getParameter("sid");
                        %>
                        <span style="float: right">   
                            <a href="<%=url%>">Take Action</a></span>
                        <%}%></h4>
                </div>
                <div id="tabs">
                    <ul>
                        <li><a href="#tabs-1">Moc Request Detail</a></li>
                        <li><a href="#tabs-2">TS Department</a></li>
                        <li><a href="#tabs-3">HSE Department</a></li>
                        <li><a href="#tabs-4">ENG Department</a></li>
                        <li><a href="#tabs-5">QC Department</a></li>
                        <li><a href="#tabs-6">Workflow</a></li>
                        <li><a href="#tabs-7">Report and Attachments</a></li>
                    </ul>
                    <div id="tabs-1">
                        <%
                            MocDao mocdao = new MocDao();
                            MocInitMst moc = mocdao.fetchMoc(Integer.parseInt(request.getParameter("cid")));
                            ArrayList<MocInitTskDt> uptasks = mocdao.fetchTaskList(Integer.parseInt(request.getParameter("cid")));
                            %>
                        <%@ include file="include/view/I1_V.jsp" %>
                        <%@ include file="include/view/I2_V.jsp" %>
                        <%@ include file="include/view/I3_V.jsp" %>
                        <%@ include file="include/view/I4_V.jsp" %>
                        <%@ include file="include/view/I5_V.jsp" %>
                        <%@ include file="include/view/I6_V.jsp" %>
                        <%@ include file="include/view/I7_V.jsp" %>
                        <%@ include file="include/view/I8_V.jsp" %>
                        <%@ include file="include/view/I9_V.jsp" %>
                    </div>
                    <div id="tabs-2">
                        <%TsDao tsd = new TsDao();
                            MocTsMst ts = tsd.fecthTs(cid);
                            if (ts != null) {
                        %>
                        <%@ include file="include/view/T1_V.jsp" %>
                        <%} else {%>
                        <h2>No Data / Moc Not visited</h2><%}%>
                    </div>
                    <div id="tabs-3">
                        <%
                            HseDao hsd = new HseDao();
                            MocHseMst hse = hsd.fecthHse(cid);
                            if (hse != null) {
                        %>
                        <%@ include file="include/view/H1_V.jsp" %>
                        <%} else {%>
                        <h2>No Data / Moc Not visited</h2><%}%>
                    </div>
                    <div id="tabs-4">
                        <%
                            EngDao end = new EngDao();
                            ArrayList<MocEngMst> engs = end.fecthEngList(cid);
                            if (engs != null) {
                        %>
                        <%@ include file="include/view/E1_V.jsp" %>
                        <%} else {%>
                        <h2>No Data / Moc Not visited</h2><%}%>
                    </div>
                    <div id="tabs-5">
                        <%
                            QcDao qcd = new QcDao();
                            MocQcMst qc = qcd.fecthQc(cid);
                            if (qc != null) {
                        %>
                        <%@ include file="include/view/Q1_V.jsp" %>
                        <%} else {%>
                        <h2>No Data / Moc Not visited</h2><%}%>
                    </div>
                    <div id="tabs-6">
                        <%@include file="include/canvas.jsp" %>
                        <%@include file="include/workflow.jsp" %>
                    </div>
                    <div id="tabs-7">
                        <%@include file="include/attachment.jsp" %>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
