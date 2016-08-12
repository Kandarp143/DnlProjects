<%-- 
    Document   : createMOC
    Created on : Oct 7, 2015, 5:30:44 PM
    Author     : 02948
--%>


<%@page import="kp.beans.mst.pojo.MocUnitMst"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.logic.DropDown"%>
<%@page import="kp.logic.GeneralMethods"%>
<% DropDown dd = new DropDown();
    ArrayList<MocUnitMst> units = dd.getUnitDD();
GeneralMethods gm = new GeneralMethods();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/global.css" type="text/css" rel="stylesheet"/>
        <link href="../css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="../css/container.css" type="text/css" rel="stylesheet"/>
        <link href="../css/Datepicker.css" rel="stylesheet" type="text/css" />
            <link href="../css/dtb.css" type="text/css" rel="stylesheet"/>
        <title>JSP Page</title>
        <script src="../js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="../js/Datepicker.js" type="text/javascript" ></script>
        <script>
            $(document).ready(function () {
                $('#a1').change(function (event) {
                    var unit = $("select#a1").val();
                    var act = 'plant';
                    $.get('../DynamicDD', {
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

    </head>
    <body>
        <%@include file="../include/header_R.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="../include/navbar_R.jsp" %>
            </div>
            <div id="Right">
                <div>
                    <h4 id="page_header">Create MOC</h4>
                </div>
                <div>
                    <form action="processMoc.jsp" method="post" onsubmit="submit.disabled = true;
                            submit.value = 'Processing ..';
                            return true;">
                        <%@ include file="include/errorlist.jsp" %>
                        <%@ include file="include/retry/I1_R.jsp" %>
                        <%@ include file="include/retry/I2_R.jsp" %>
                        <%@ include file="include/retry/I3_R.jsp" %>
                        <%@ include file="include/retry/I4_R.jsp" %>
                        <%@ include file="include/retry/I5_R.jsp" %>
                        <%@ include file="include/retry/I6_R.jsp" %>
                        <%@ include file="include/retry/I7_R.jsp" %>
                        <%@ include file="include/retry/I8_R.jsp" %>
                        <%@ include file="include/retry/I9_R.jsp" %>
                        <input type="submit" value="submit" id="submit" name="submit" class="btn"/>
                    </form>
                </div>

            </div>
        </div>
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
