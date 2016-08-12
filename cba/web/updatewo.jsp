
<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="Bean.WorkTermBean"%>
<%@page import="Bean.WorkItemBean"%>
<%@page import="Bean.WorkTaxBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.WorkBean"%>
<%@page import="Dao.WorkDao"%>
<%!Date startDate;
    Date endDate;%>
<%
    Logic.Dropdown.LoadDropdown();
    Logic.Dropdown.LoadProjectDropdown(session.getAttribute("unit").toString());
    Logic.Dropdown.LoadTaxDropdown(session.getAttribute("unit").toString());
    String pono1 = request.getParameter("pono");
    WorkBean wo1 = new WorkBean();
    ArrayList<WorkTaxBean> wt1 = new ArrayList<WorkTaxBean>();
    ArrayList<WorkItemBean> wi1 = new ArrayList<WorkItemBean>();
    WorkTermBean wp1 = new WorkTermBean();
    WorkDao wdao1 = new WorkDao();
    wo1 = wdao1.getWO(pono1);
    wi1 = wdao1.getWOItem(pono1);
    wt1 = wdao1.getWOTax(pono1);
    wp1 = wdao1.getWOTerm(pono1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLCBA</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <link href="css/jquery.loader.css" type="text/css" rel="stylesheet"/>
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery-latest.min.js" ></script>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.searchabledropdown-1.0.8.min.js"></script>
        <script src="js/Datepicker.js" type="text/javascript"></script>
        <script src="js/jquery.loader.js"></script>
        <script src="js/custom/wo.js"></script>
        <script src="js/custom/wodt.js"></script>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <div>
                    <h4 class="page_title">Update Work Order</h4>
                </div>
                <form action="UpdateWO" method="post"  onsubmit="submit.disabled = true;
                        submit.value = 'Processing ..';
                        return true;">
                    <fieldset>
                        <legend style="">Header</legend>
                        <%@include file="include/womstu.jsp" %>
                    </fieldset>
                    <fieldset>
                        <legend>Item Detail</legend>
                        <%@include file="include/wodtu.jsp" %>
                    </fieldset>
                    <fieldset>
                        <legend>Total Value and Terms</legend>
                        <%@include file="include/wofootu.jsp" %>
                    </fieldset>
                    <fieldset>
                        Comment for approver<span style="color: red">*</span> <input type="text" value="" name="ucmt" id="ucmt" size="20"/>
                        <input type="submit" value="submit" id="submit" onclick="return validate();" name="submit" onsubmit="submit.disabled = true;
                                submit.value = 'Processing ..';
                                return true;"  />
                    </fieldset>
                </form>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>