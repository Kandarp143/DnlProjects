<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
<%@page import="Bean.DDBean"%>
<%@page import="Logic.Dropdown"%>
<%
    Logic.Dropdown dd = new Dropdown();
    dd.LoadDropdown(session.getAttribute("uid").toString());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLMMT</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {
                //Select MIT event
                $('#itm').change(function (event) {
                    var itmval = $("select#itm").val();
                    $.get('MitJson', {
                        itm: itmval
                    }, function (response) {
                        var select = $('#mit');
                        select.find('option').remove();
                        $.each(response, function (index, value) {
                            $('<option>').val(value).text(value).appendTo(select);
                        });
                    });
                });
                //After MIT load data event
                $('#mit').change(function (event) {
                    var mitval = $("select#mit").val();
                    $.get('MitDtJson', {
                        mit: mitval
                    }, function (jsonResponse) {
                        $.each(jsonResponse, function (index, value) {
                            if (index === 0) {
                                $('#a').val(value);
                            }
                            if (index === 1) {
                                $('#b').val(value);
                            }
                            if (index === 2) {
                                $('#c').val(value);
                            }
                            if (index === 3) {
                                $('#d').val(value);
                            }
                            if (index === 4) {
                                $('#g').val(value);
                            }
                        });
                    });
                });
            });
        </script>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <%@include file="include/navbar.jsp" %>
            <div class="page_title">
                <h3>New Item Entry</h3>  
            </div>
            <div>
                <form method="post" action="NewFG" onsubmit="submit.disabled = true;
                        submit.value = 'Processing ..';
                        return true;">
                    <table class="dtbf">
                        <tr>
                            <th>Select Item :</th>    

                            <td>
                                <select id="itm" name="itm">
                                    <option value="">Select Raw Item </option>
                                    <% for (DDBean loc : dd.DDitm) {%>
                                    <option value="<%=loc.getItem_code()%>"><%=loc.getItem_desc()%></option>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>Select MIT No :</th>    
                            <td>
                                <select id="mit" name="mit">
                                    <option value="">Select MIT-NO </option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th> Receipt No:</th>
                            <td> <input type="text" size="20" id="a" name="a" value="" class="readon" readonly/></td>
                        </tr>
                        <tr>
                            <th>Location:</th>
                            <td><input type="text" size="20" id="b" name="b" value="" class="readon" readonly/></td>
                        </tr>
                        <tr>
                            <th> Lot No:</th>
                            <td><input type="text" size="20" id="c" name="c" value="" class="readon" readonly/></td>
                        </tr>
                        <tr>
                            <th>Pre-Shipment NO: </th>
                            <td><input type="text" size="20" id="d" name="d" value="" class="readon" readonly/></td>
                        </tr>
                        <tr>
                            <th>Select New Item-Code:</th>
                            <td>
                                <select id="e" name="e">
                                    <% for (DDBean loc : dd.DDitmmst) {%>
                                    <option value="<%=loc.getItem_code()%>"><%=loc.getItem_desc()%></option>
                                    <%}%>
                                </select>    
                            </td> </tr>

                        <tr>
                            <th> Enter QTY:</th>
                            <td><input type="text" size="20" id="f" name="f" value="0" class="cur" onfocus="makeClear(this)" onkeypress="return isNumberKey(event)"/></td>
                        </tr>
                        <tr>
                            <th>U.O.M:</th>
                            <td><input type="text" size="20" id="g" name="g" value="" class="readon" readonly/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td style="float: right"><input type="submit" value="Submit" id="submit" name="submit"/></td>
                        </tr>
                    </table>

                </form>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
    <script>
        function makeClear(id) {
            id.value = '';
        }
        function checkQTY(id) {
            var x = document.getElementById("j" + id).value;
            var y = document.getElementById("h" + id).value;
            if (x == 0) {
                document.getElementById("j" + id).value = "0";
            }
            if (parseFloat(x) > parseFloat(y)) {
                alert("Please enter QTY less than or euqal to available QTY :" + y)
                document.getElementById("j" + id).value = "0";
            }
            if (isNaN(x)) {
                document.getElementById("j" + id).value = "0";
            }
        }
        function isNumberKey(evt)
        {
            var charCode = (evt.which) ? evt.which : event.keyCode
            if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;

            return true;
        }
    </script>
</html>
