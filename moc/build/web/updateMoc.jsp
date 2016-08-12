<%-- 
    Document   : createMOC
    Created on : Oct 7, 2015, 5:30:44 PM
    Author     : 02948
--%>
<%@page import="kp.beans.data.pojo.MocInitPseq"%>
<%@page import="kp.logic.GeneralMethods"%>
<%@page import="kp.beans.data.pojo.MocInitProc"%>
<%@page import="kp.beans.data.pojo.MocInitProd"%>
<%@page import="kp.beans.data.pojo.MocInitEle"%>
<%@page import="kp.beans.data.pojo.MocInitEle"%>
<%@page import="kp.beans.data.pojo.MocInitPip"%>
<%@page import="kp.beans.data.pojo.MocInitRot"%>
<%@page import="kp.beans.data.pojo.MocInitSta"%>
<%@page import="kp.beans.data.pojo.MocInitReq"%>
<%@page import="kp.beans.data.pojo.MocInitMst"%>
<%@page import="kp.dao.data.MocDao"%>
<%@page import="kp.beans.mst.pojo.MocUnitMst"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.logic.DropDown"%>
<% DropDown dd = new DropDown();
    ArrayList<MocUnitMst> units = dd.getUnitDD();
    int cid = Integer.parseInt(request.getParameter("cid"));
    MocDao mocd = new MocDao();
    GeneralMethods gm = new GeneralMethods();
    MocInitMst mst = mocd.fetchMoc(cid);
    MocInitReq objA = mst.getMocInitReq();
    MocInitSta objB = mst.getMocInitSta();
    MocInitRot objC = mst.getMocInitRot();
    MocInitPip objD = mst.getMocInitPip();
    MocInitEle objE = mst.getMocInitEle();
    MocInitProd objF = mst.getMocInitProd();
    MocInitProc objG = mst.getMocInitProc();
    MocInitPseq objH = mst.getMocInitPseq();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/global.css" type="text/css" rel="stylesheet"/>
          <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/Datepicker.js" ></script>
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
        <script>
            $(document).ready(function () {
                $('#a1').change(function (event) {
                    var unit = $("select#a1").val();
                    var act = 'plant';
                    $.get('DynamicDD', {
                        unit: unit,
                        act: act
                    }, function (response) {
                        var select = $('#a2');
                        select.find('option').remove();
                        $.each(response, function (index, value) {
                            $('<option>').val(index).text(value).appendTo(select);
                        });
                    });
                });

                //Date Picker for update task
                for (var i = 1; i < 18; i++) {
                    $("#r" + i).datepicker({
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
                    <h4 id="page_header">Update MOC</h4>
                </div>
                <div>
                    <form action="process/processMoc.jsp?cid=<%=cid%>" method="post" onsubmit="submit.disabled = true;
                            submit.value = 'Processing ..';
                            return true;">
                        <%@ include file="include/update/I1_U.jsp" %>
                        <%@ include file="include/update/I2_U.jsp" %>
                        <%@ include file="include/update/I3_U.jsp" %>
                        <%@ include file="include/update/I4_U.jsp" %>
                        <%@ include file="include/update/I5_U.jsp" %>
                        <%@ include file="include/update/I6_U.jsp" %>
                        <%@ include file="include/update/I7_U.jsp" %>
                        <%@ include file="include/update/I8_U.jsp" %>
                        <%@ include file="include/update/I9_U.jsp" %>
                        <%@ include file="include/form/CMT.jsp" %>
                        <span class="err">
                            Workflow users will be same.Which you have selected at moc Create time.
                            If you want to change Workflow users. Please contact Administrator
                        </span><br/>
                        <input type="submit" value="submit and foward for Approval" id="submit" name="submit" class="btn"/>
                    </form>
                </div>

            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
