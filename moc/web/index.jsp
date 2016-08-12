<%-- 
    Document   : index
    Created on : Oct 21, 2015, 4:29:56 PM
    Author     : 02948
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/global.css" type="text/css" rel="stylesheet"  />
        <link href="css/login.css" type="text/css" rel="stylesheet"  />
        <title>Login to DNLMOC</title>
    </head>
    <body>
        <%@include file="include/headerl.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <div class="content">
                    <h1 style="color: red">This is Production Environment</h1>
                    <p>This system is tested and best compatible with Google Chrome.</p>
                    <p>All the colors are background color, so there is no need for background images to give the impression of full height columns.</p>
                </div>
                <div class="content">
                    <h1 style="color: green">Welcome to the new version of MOC</h1>
                    <table style="color: green">
                        <tr>
                            <td><b>Following are new features</b></td>
                        </tr>
                        <tr>
                            <td>1.User can select workflow user to whom forward at moc creation time.</td>
                        </tr>
                        <tr>
                            <td>2.No need to maintain and use PDF, moc detail can be view online as well can print moc report</td>
                        </tr>
                        <tr>
                            <td>3.Completely new look and feel</td>
                        </tr>
                        <tr>
                            <td>4.User can do attchment at any stage</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="Right">
                <div class="content">
                    <form action="Login?act=login" method="post" onsubmit="submit.disabled = true;
                            submit.value = 'Processing ..';
                            return true;">
                        <fieldset>
                            <legend><b>Login Into System</b></legend>
                            <table>
                                <tr>
                                    <td><h4><b>Username :</b></h4></td>
                                    <td><input type="text" name="user_id"/>
                                </tr>
                                <tr>
                                    <td><b>Password   :</b></td>
                                    <td><input type="password"  name="password"/>
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
