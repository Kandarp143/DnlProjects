<%-- 
    Document   : F_Upload
    Created on : Aug 25, 2014, 5:41:27 AM
    Author     : Kandarp
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <form method="post" action="ReadExcel?pono=<%=request.getParameter("pono")%>&sid=6" enctype="multipart/form-data" onsubmit="submit.disabled = true;
                submit.value = 'Processing ..';
                return true;">
            <table class="dtb">
                <tr>
                    <th colspan="2">
                        Add Excel File for WO :<%=request.getParameter("pono")%>
                    </th>
                </tr>
                <tr>
                    <td>
                        <input type="file" name="file" id="file" />
                    </td>
                    <td>
                        <input type="submit" id="submit" name="submit" value="Upload" /></td>
                </tr>

                <tr>
                    <td colspan="2">
                        <%!String status;%>
                        <%status = request.getParameter("status");%>
                        <% if ("1".equals(status)) {%>
                        <span style="color: red"> 
                            Invalid File. Please Upload only xls Template File
                        </span>
                        <%} else if ("2".equals(status)) {
                        %>
                        <span style="color: red"> 
                            Invalid File. Please Upload Downloaded Template only
                        </span>
                        <%} else if ("3".equals(status)) {
                        %>
                        <span style="color: red"> 
                            File Is altered ! Pl re-download file and upload.
                        </span>
                        <%} else if ("4".equals(status)) {
                        %>
                        <span style="color: green"> 
                            File is successfully uploaded,Click on close - to close window.
                        </span>
                        <%} else if ("E".equals(status)) {
                        %>
                        <span style="color: red"> 
                            There was error : <%=request.getParameter("err")%> , Please Upload file with correct data
                        </span>
                        <%} else {
                            }
                        %>
                    </td>
                </tr>

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
