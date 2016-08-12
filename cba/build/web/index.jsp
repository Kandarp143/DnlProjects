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
        <title>Login to DNLCBA</title>
    </head>
    <body>
        <%@include file="include/headerl.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <div class="content">
                    <%IpAddress ip = new IpAddress();
                        if (ip.isProdEnv()) {%>
                    <h1 style="color: green">This is Production Environment</h1>  
                    <%} else {%>
                    <h1 style="color: red">This is Test Environment</h1>    
                    <% }
                    %>
                    <p>This system is tested and best compatible with Google Chrome.</p>
                    <p>All the colors are background color, so there is no need for background images to give the impression of full height columns.</p>
                </div>
                <div class="content">
                    <h1>User Manuals</h1>
                    <table >
                        <tr>
                            <td>--><a href="docs/DNLCBA System Flow.pdf" target="_blank">DNLCBA Overall System Flow</a></td>
                        </tr>
                        <tr>
                            <td>--><a href="docs/DNLCBA User Guide 1.pdf" target="_blank">DNLCBA Overall System User Guide</a></td>
                        </tr>
                        <tr>
                            <td>--><a href="docs/DNLCBA User Guide 2.pdf" target="_blank">DNLCBA Overall Process : Create and View Work Order</a></td>
                        </tr>
                        <tr>
                            <td>--><a href="docs/DNLCBA User Guide 3.pdf" target="_blank">DNLCBA Overall Process : Create Bill/Release WO and View Bill</a></td>
                        </tr>
                        <tr>
                            <td>--><a href="docs/DNLCBA User Guide 4.pdf" target="_blank">DNLCBA Overall Process : WO/Bill Approval </a></td>
                        </tr>
                        <tr>
                            <td>--><a href="docs/DNLCBA User Guide 5.pdf" target="_blank">DNLCBA Overall Process : WO/Bill Updetation</a></td>
                        </tr>
                        <tr>
                            <td>--><a href="docs/DNLCBA User Guide 6.pdf" target="_blank" >DNLCBA Overall Process : Create WO using Excel File Loading</a></td>
                        </tr>
                        <tr>
                            <td>--><a href="docs/DNLCBA User Guide 7.pdf" target="_blank" >DNLCBA Overall Process : Create BILL using Excel File Loading</a></td>
                        </tr>
                        <tr>
                            <td>--><a href="docs/DNLCBA User Guide 8.pdf" target="_blank" style="color: #8B0000">DNLCBA Overall Process : AP invoice creation</a></td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <mark><b>
                                        Please Ignore 'PROJECT' and 'TASK' fields in CBA system,Still it is not fully implemented  and enabled
                                    </b>
                                </mark>
                            </td>
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
                                    <td><h4><b>Username :</b></h4></td>
                                    <td><input type="text" name="login_id"/>
                                </tr>
                                <tr>
                                    <td><b>Password   :</b></td>
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
