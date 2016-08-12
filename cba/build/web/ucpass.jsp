<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
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
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <div>
                      <h4 class="page_title">Change Password</h4>
                </div> 
                <form action="ChangePass" method="post" onsubmit="submit.disabled = true;
                        submit.value = 'Processing ..';
                        return true;">
                    <table class="dtb" style="width: 50%">
                        <tr>
                            <td style="text-align: right" ><b>Username :</b></td>
                            <td><input type="text" name="uid"/>
                        </tr>
                        <tr>
                            <td style="text-align: right"><b>Old Password   :</b></td>
                            <td><input type="password"  name="old"/>
                        </tr>
                        <tr>
                            <td style="text-align: right"><b>New Password   :</b></td>
                            <td><input type="password"  name="new"/>
                        </tr>
                        <tr>
                            <td></td>
                            <td ><input type="submit" value="Submit" id="submit" name="submit"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                            <% if (request.getParameterMap().containsKey("ans")) {
                                    if (request.getParameter("ans").equals("Y")) {%>
                            <p style="color: green">Password has been successfully changed</p>
                            <% } else {%>
                            <p style="color: red">Something went wrong ! Password isn't changed</p>
                            <% }
                                }
                            %>

    
                            </td>
                            

                        </tr>
                    </table>
                </form>

            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
