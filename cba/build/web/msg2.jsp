<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>

<%@page import="Logic.GetMethod"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLCBA</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.searchabledropdown-1.0.8.min.js"></script>
        <script src="js/dynamicrow.js" type="text/javascript"></script>
        <script src="js/Datepicker.js" type="text/javascript"></script>
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
        <!--[if lte IE 7]>
        <style type="text/css">
           body {word-wrap:break-word;}
           #outer2 {float:left; width:59.8%; background:#c0c0c0;}
           /* for IE6 */
           * html #wrapper {display:inline-block;}
        </style>
        <![endif]-->
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <table class="dtb" style="width: 50%;">

                    <tr>
                        <th >Bill ID</th>
                        <td><%=request.getParameter("bill")%></td>
                    </tr>
                    <tr>
                        <th>Next Stage</th>
                            <%
                                Logic.GetMethod m2gg = new GetMethod();
                                String stg = m2gg.Get_Perameter("STG_NAME", "CBA_STG_MST", "STG_ID", request.getParameter("nsid"));
                            %>
                        <td><%=stg%></td>
                    </tr>
                    <tr>
                        <th>Bill Detail</th>
                        <td><a href="billdetail.jsp?bill=<%=request.getParameter("bill")%>&sid=0&pono=<%=request.getParameter("pono")%>">click here</a></td>
                    </tr>
                </table>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
