<%@page import="kp.beans.wf.pojo.MocWfTran"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.dao.wf.TranDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLMOC</title>
        <link href="css/global.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <div>
                    <h4 id="page_header">Notification for CASE ID :<%=request.getParameter("cid")%>!</h4>
                </div>
                <div>
                    <table class="dtb" style="width: 50%">
                        <tr>
                            <th>MSG:</th>
                            <td>Your Action is completed and email has been sent to next user</td>
                        </tr>
                        <tr>
                            <th>Add Attachment</th>
                            <td>
                                <div>  
                                    <input type="button" id="aa" name="aa" value="Add Attachment" onclick="aa()"/>
                                    <span style="color: red">   <-- click here for attach file</span>
                                </div>
                                <script type="text/javascript">
                                    var popup;
                                    function aa() {
                                        popup = window.open("include/fileupload.jsp?status=N&cid=<%=request.getParameter("cid")%>", "att", "width=500,height=200");
                                        popup.focus();
                                        return false;
                                    }
                                </script>
                            </td>
                        </tr>
                        <tr>
                            <th>Moc Detail</th>
                            <td><a href="viewMoc.jsp?cid=<%=request.getParameter("cid")%>"  target="_blank">Click Here</a></td>
                        </tr>
                        <%if (request.getParameterMap().containsKey("sid")
                                    && request.getParameter("sid").equalsIgnoreCase("0")) {%>
                        <tr>
                            <td colspan="2" style="color: red">
                                You have just created moc,Please forward it for approval!
                            </td>
                        </tr>
                        <tr>
                            <th>Forward Moc For Approval</th>
                            <td><a href="selUser.jsp?cid=<%=request.getParameter("cid")%>">Select User</a></td>
                        </tr>
                        <%}%>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
