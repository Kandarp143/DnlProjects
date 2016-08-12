<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLMMT</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <%@include file="include/navbar.jsp" %>
            <div class="page_title">
                <h3>Pre-built Reports</h3>  
            </div>
            <div>
                <table class="dtb">
                    <tr>
                        <th>Report Title </th>
                        <th>Export to Excel</th>
                    </tr>
                    <tr>
                        <td>Item wise & Location wise Stocked QTY summary report </td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" action="ExportRpt?rpt=1" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>Item Consumption transaction detail </td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" action="ExportRpt?rpt=2" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>Item Master Report </td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" action="ExportRpt?rpt=3" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>Outstanding report (current period) </td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" action="ExportRpt?rpt=4" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                      <tr>
                        <td>Outstanding report (all period) </td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" action="ExportRpt?rpt=4-1" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>Collection Report (Summary)</td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" action="ExportRpt?rpt=5" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>Detailed Collection Report (Invoice VS Payment)</td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" action="ExportRpt?rpt=6" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>

        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
