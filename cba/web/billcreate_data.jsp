<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
<%@page import="Bean.LoginBean"%>
<%@page import="Dao.LoginDao"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="Bean.WorkTermBean"%>
<%@page import="Bean.WorkItemBean"%>
<%@page import="Bean.WorkTaxBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.WorkBean"%>
<%@page import="Dao.WorkDao"%>

<%
    Logic.Dropdown.LoadDropdown();
    String pono1 = request.getParameter("pono");
    WorkBean wo1 = new WorkBean();
    ArrayList<WorkTaxBean> wt1 = new ArrayList<WorkTaxBean>();
    WorkTermBean wp1 = new WorkTermBean();
    WorkDao wdao1 = new WorkDao();
    wo1 = wdao1.getWO(pono1);
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
        <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.searchabledropdown-1.0.8.min.js"></script>
        <script src="js/Datepicker.js" type="text/javascript"></script>
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <!-- CONTENTS ARE GOES HERE OF REAL PAGE ----->
                <div>
                    <h4 class="page_title">Release Work Order / Generate Bill</h4>
                </div>
                <fieldset>
                    <legend>Item Detail</legend>    
                    <%@include file="include/wodtb_data.jsp" %>
                </fieldset>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
