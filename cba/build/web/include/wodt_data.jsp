<%-- 
    Document   : F_Upload
    Created on : Aug 25, 2014, 5:41:27 AM
    Author     : Kandarp
--%>
<%@page import="Logic.GetMethod"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <table class="dtb">
            <tr>
                <th>Download XLS Tamplete for load Data</th>
                    <%
//                        Logic.GetMethod g = new GetMethod();
//                        String orgid = g.Get_Perameter(session.getAttribute("uid").toString(), "UNIT", "CBA_USER_MST");
//                        if(orgid.endsWith("NDS")){
//                            orgid = "123";
//    }
                    %>
                <td><form id="feedbackform" name="feedbackform" method="post" 
                          action="ExportExcelTemplate?org=123" 
                          onsubmit="submit.disabled = true;
                                  submit.value = 'Processing ..';
                                  return true;">
                        <input type="submit" value="Download Excel" id="submit"/>
                    </form>
                </td>
            </tr>
        </table>
        <form method="post" action="ReadExcelWO" enctype="multipart/form-data" onsubmit="submit.disabled = true;
                submit.value = 'Processing ..';
                return true;">
            <table class="dtb">

                <tr>
                    <th colspan="2">
                        Upload Excel 
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
                        <span style="color: green"> 
                            File Is altered ! Pl re-download file and upload.
                        </span>
                        <%} else if ("4".equals(status)) {
                        %>
                        <span style="color: green"> 
                            File is successfully uploaded,Click on close - to close window.
                        </span>
                        <%} else {
                            }
                        %>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
