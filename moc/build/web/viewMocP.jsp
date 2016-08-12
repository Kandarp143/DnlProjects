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
        <style>
            .form_table {
                border: 1px solid #C0C0C0;
                border-collapse: collapse;
                padding: 3px;
                width: 95%;
                margin-left:1%;
                margin-right:5%;
                margin-bottom:1%;
                font-size: small;

            }
            .form_table th {
                border: 1px solid #999999;
                padding: 1px;
                color: #000;
                text-align: center;
                font-weight: bold;
            }
            .form_table td {
                border: 1px solid #C0C0C0;
                text-align: left;
                padding: 3px;
                word-wrap: break-word;

            }
            body {
                width: 100%;
                height: 100%;
                margin: 0;
                padding: 0;
                font: 12pt "Tahoma";
            }
            * {
                box-sizing: border-box;
                -moz-box-sizing: border-box;
            }
            .page {
                width: 300mm;
                min-height: 297mm;
                padding: 10mm;
                margin: 10mm auto;
                border: 1px #D3D3D3 solid;
                border-radius: 5px;
                background: white;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            }
            .subpage {
                padding: 1cm;
                border: 5px red solid;
                height: 257mm;
                outline: 2cm #FFEAEA solid;
            }
            @page {
                size: A4;
                margin: 0;
            }
            @media print {
                html, body {
                    width: 210mm;
                    height: 297mm;        
                }
                .page {
                    margin: 0;
                    border: initial;
                    border-radius: initial;
                    width: initial;
                    min-height: initial;
                    box-shadow: initial;
                    background: initial;
                    page-break-after: always;
                }
                .footer {page-break-after: always;}
            }
        </style>
    </head>
    <%
        GeneralMethods gm = new GeneralMethods();
        int cid = Integer.parseInt(request.getParameter("cid"));
    %>
    <body>
        <div class="book">
            <form>
                <input type="button" value="Print this page" onClick="window.print()" class="form_table">
            </form>
            <%
                MocDao mocdao = new MocDao();
                MocInitMst moc = mocdao.fetchMoc(Integer.parseInt(request.getParameter("cid")));
                ArrayList<MocInitTskDt> uptasks = mocdao.fetchTaskList(Integer.parseInt(request.getParameter("cid")));
                TsDao tsd = new TsDao();
                MocTsMst ts = tsd.fecthTs(cid);
                HseDao hsd = new HseDao();
                MocHseMst hse = hsd.fecthHse(cid);
                EngDao end = new EngDao();
                ArrayList<MocEngMst> engs = end.fecthEngList(cid);
                QcDao qcd = new QcDao();
                MocQcMst qc = qcd.fecthQc(cid);
            %>
            <div class="page">
                <table style="margin-left: 5px;margin-bottom: 5px;width: 90%    ">
                    <tr><td rowspan="2">
                            <img src="image/logo.png"/>
                        </td>
                        <td><b><span style="font-size: larger">Deepak Nitrite Ltd</span><br/>
                                Management of Change (MOC) Application</b></td>
                        <td colspan="10"> </td>
                        <td style="float: right">
                            Case ID : <%=moc.getCaseId()%> <br/>
                            Status  : <%=moc.getStatus()%> <br/>
                            MOC No  : <%=moc.getMocNo()%><br/>
                        </td>
                    </tr>
                </table>
                <%@ include file="include/view/I1_V.jsp" %>

                <%@ include file="include/view/I2_V.jsp" %>
                <%@ include file="include/view/I3_V.jsp" %>
                <%@ include file="include/view/I5_V.jsp" %>
            </div>
            <div class="page">
                <%@ include file="include/view/I4_V.jsp" %>
                <%@ include file="include/view/I6_V.jsp" %>
                <%@ include file="include/view/I7_V.jsp" %>
                <%@ include file="include/view/I8_V.jsp" %>
            </div>
            <div class="page">
                <%@ include file="include/view/I9_V.jsp" %>
                <%if (ts != null) { %>
                <%@ include file="include/view/T1_V.jsp" %>
                <%} else {%>
                <p class="form_table">Technical Service : No Data / Moc Not visited</p><%}%>
                <%if (hse != null) {%>
                <%@ include file="include/view/H1_V.jsp" %>
                <%} else {%>
                <p class="form_table">HSE Verification : No Data / Moc Not visited</p><%}%>
                <%if (engs != null) {%>
                <%@ include file="include/view/E1_V.jsp" %>
                <%} else {%>
                <p class="form_table">Eng Cost Estimation : No Data / Moc Not visited</p><%}%>
                <div class="page">
                    <%if (qc != null) {%>
                    <%@ include file="include/view/Q1_V.jsp" %>
                    <%} else {%>
                    <p class="form_table">QC Verification : No Data / Moc Not visited</p><%}%>
                    <%@include file="include/workflow.jsp" %>
                </div>

            </div>
    </body>
</html>
