<%-- 
    Document   : F_Upload
    Created on : Aug 25, 2014, 5:41:27 AM
    Author     : Kandarp
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script>
            function refreshParent() {
                // This informs the user that the record has been added successfully
                alert('The Attachment has been done, page will reload !');
                window.opener.location.reload();
                window.close();
            }
        </script>
    </head>
    <body>
        <h2>Add Attachment for PO :<%=request.getParameter("pono")%></h2>
        <form method="post" action="../Upload" enctype="multipart/form-data" onsubmit="submit.disabled = true;
                submit.value = 'Processing ..';
                return true;">

            <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border: thin #999999">
                <tr>
                    <td>
                        <input type="file" name="file" id="file" />
                    </td>
                    <td>
                        <input type="submit" id="submit" name="submit" value="Upload" /></td>
                </tr>
                <tr> 
                    <td colspan="2">
                        <input type="button" value="Close" onclick="refreshParent()"/>  </td>
                </tr>

                <% if ("Y".equals(request.getParameter("status"))) {%>
                <tr>
                    <td colspan="2">
                        <span style="color: green"> 
                            File is successfully uploaded,Click on close - to close window.
                        </span>
                    </td>
                </tr>
                <%} else {
                    }%>

            </table>
            <input type="hidden" value="<%=request.getParameter("pono")%>" id="ponoh" name="ponoh"/>
            <% if (request.getParameter("bill") == null) {%>
            <input type="hidden" value="0" id="billh" name="billh"/>
            <%} else {%>
            <input type="hidden" value="<%=request.getParameter("bill")%>" id="billh" name="billh"/>
            <%}%> 
        </form>
    </body>
</html>
