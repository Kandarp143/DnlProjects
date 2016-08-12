<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLCBA</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/jquery.loader.css" type="text/css" rel="stylesheet"/>
        <script src="js/jquery.loader.js"></script>
        <script src="js/jquery-latest.min.js"
        type="text/javascript"></script>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.searchabledropdown-1.0.8.min.js"></script>
        <script src="js/Datepicker.js" type="text/javascript"></script>
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
        <script src="js/jquery.loader.js"></script>

        <script type="text/javascript" charset="utf-8">
            $(document).ready(function () {
                //Date Picker
                $("#fd").datepicker({
                    showOn: 'button',
                    buttonText: 'Show Date',
                    buttonImageOnly: true,
                    buttonImage: 'image/calendar.gif',
                    dateFormat: 'mm/dd/yy',
                    constrainInput: true
                });
                $("#td").datepicker({
                    showOn: 'button',
                    buttonText: 'Show Date',
                    buttonImageOnly: true,
                    buttonImage: 'image/calendar.gif',
                    dateFormat: 'mm/dd/yy',
                    constrainInput: true
                });
            });
        </script>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <div>
                    <h4 style="color:#A70303;font-size: 18px;margin-top: 2px;margin-left: 40%;margin-bottom: 2px;">
                        Pre-Built Reports
                    </h4>
                </div>
                <table class="dtb" style="width: 80%">
                    <tr><th>Report Name</th><th>Export to Excel</th><th>Input Parameter</th></tr>
                    <tr>
                        <td>Supplier wise expanse summary</td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" 
                                  action="ExportRpt?rpt=1&name=Supplier wise expanse summary" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                        </td>
                        <td>
                            <input type="text" id="fd" name="fd" size="15" placeholder="From Date"/>
                            <p style="color: gray;font-size: small">
                                <b><i>From Date</i></b> (MM/DD/YYYY)</p>
                        </td>
                        <td>
                            <input type="text" id="td" name="td" size="15" placeholder="To Date"/>
                            <p style="color: gray;font-size: small"><b><i>To Date</i></b> (MM/DD/YYYY)</p>
                            </p> 

                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>Item & Production Center wise expanse</td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" 
                                  action="ExportRpt?rpt=2&name=Item & Production Center wise expanse" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>Item wise expanse</td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" 
                                  action="ExportRpt?rpt=3&name=Item wise expanse" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>Bill Detail creator & department wise</td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" 
                                  action="ExportRpt?rpt=4&name=Bill Detail creator & department wise" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>CBA Bill V/S ERP Invoice Detail</td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" 
                                  action="ExportRpt?rpt=5&name=CBA Bill vs Invoice Detail" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>ERP Invoice detail with service group detail</td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" 
                                  action="ExportRpt?rpt=6&name=ERP Invoice detail with service group detail" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>CBA Pending bills detail report</td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" 
                                  action="ExportRpt?rpt=7&name=CBA Pending bills detail report" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>CBA Pending bills summary</td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" 
                                  action="ExportRpt?rpt=8&name=CBA Pending bills summary" 
                                  onsubmit="submit.disabled = true;
                                          submit.value = 'Processing ..';
                                          return true;">
                                <input type="submit" value="Export to Excel" id="submit"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>CBA All Bill List</td>
                        <td>
                            <form id="feedbackform" name="feedbackform" method="post" 
                                  action="ExportRpt?rpt=9&name=CBA All Bill List" 
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
