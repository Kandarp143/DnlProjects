<%-- 
    Document   : index
    Created on : Feb 12, 2015, 4:36:43 PM
    Author     : 02948
--%>
<%@page import="Logic.IpAddress"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/login.css" type="text/css" rel="stylesheet"  />
        <link href="css/dtb.css" type="text/css" rel="stylesheet"  />
        <title>Login to DNLMMT</title>
    </head>
    <body>
        <%@include file="include/headerl.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <div class="content">
                    <%
                        IpAddress ip = new IpAddress();
                        if (ip.isProdEnv()) {%>
                    <h1 style="color: green">This is Production Environment</h1>
                    <%} else {%>
                    <h1 style="color: red">This is Test Environment</h1>
                    <%   }
                    %>

                    <p>This system is tested and best compatible with Google Chrome.</p>
                    <p>System which track Material Consumption  </p>
                </div>
                <div class="content">
                    <h1>User Manuals</h1>
                    <table >
                        <tr>
                            <td>-->TEST DOCS WILL BE HERE</td>
                        </tr>
                    </table>
                    <h4>Latest Update in system</h4>
                    <table >
                        <tr>
                            <td style="color: red">-->Report Tab : Customer collection Summary & Detailed Report included</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="Right">
                <div class="content">
                    <form action="Login" method="post" onsubmit="submit.disabled = true;
                            submit.value = 'Processing ..';
                            return true;">
                        <fieldset>
                            <legend>Login Into System</legend>
                            <table>
                                <tr>
                                    <td><b>Username   &nbsp;: </b></td>
                                    <td><input type="text" name="login_id"/>
                                </tr>
                                <tr>
                                    <td><b>Password  &nbsp; : </b></td>
                                    <td><input type="password"  name="pass"/>
                                </tr>
                                <tr>
                                    <td><input type="submit" value="Submit" id="submit" name="submit"/></td>
                                </tr>
                                <tr class="err">
                                    <td colspan="2"> <h4><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></h4></td>
                                </tr>
                            </table>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
