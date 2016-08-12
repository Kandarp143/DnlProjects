<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
<%@page import="Bean.DDBean"%>
<%@page import="Bean.DDBean"%>
<%@page import="Logic.Dropdown"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.HashSet"%>
<%@page import="Bean.ItemBean"%>    
<%@page import="java.util.ArrayList"%>
<%@page import="Dao.ItemDao"%>
<%
    String uid = session.getAttribute("uid").toString();
    ItemDao d = new ItemDao();
    Logic.Dropdown dd = new Dropdown();
    dd.LoadDropdown(uid);
    ArrayList<ItemBean> itmlist = new ArrayList<>();
    int i = 0;
    boolean isallow = true;
    if (request.getParameterMap().containsKey("loc")) {
        if (request.getParameter("loc").equalsIgnoreCase("all")) {
            isallow = false;
        }
        if (request.getParameter("loc").equalsIgnoreCase("in-transit")) {
            isallow = false;
        }
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLMMT</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <!--[if lte IE 8]>
<script src="html5.js" type="text/javascript"></script>
<![endif]-->
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <%@include file="include/navbar.jsp" %>
            <div class="page_title">
                <h3>Item Consumption Entry</h3>   
            </div>
            <% if (request.getParameterMap().containsKey("loc")) {%>  
            <form method="post" action="itmcun.jsp" onsubmit="submit.disabled = true;
                    submit.value = 'Processing ..';
                    return true;">
                <div class="dtb"> <p>Select Location</p> 
                    <select id="loc" name="loc">
                        <option value="<%=request.getParameter("loc")%>"><%=request.getParameter("loc")%></option>
                        <% for (DDBean loc : dd.DDloc) {%>
                        <option value="<%=loc.getLoc_Id()%>"><%=loc.getLoc_name()%></option>
                        <%}%>
                    </select>
                    <input type="submit" value="Submit" id="submit" name="submit"/>
                </div>
            </form>
            <form method="post" action="ItmCun" onsubmit="submit.disabled = true;
                    submit.value = 'Processing ..';
                    return true;">
                <table class="dtb">
                    <tr>
                        <td colspan="13" style="color: #A70303">
                            <p style="font-weight: bold">Location : <%=request.getParameter("loc")%></p>
                        </td>
                    </tr>
                    <tr>
                        <th>Sr.NO</th>
                        <th>Item Code</th>
                        <th>Description</th>
                        <th>Container_no</th>
                        <th>Pre-Shipment No.</th>
                        <th>Lot No</th>
                        <th>Receipt No.</th>
                        <th>GRN Date</th>
                        <th>Invoice</th>
                        <th>Qty</th>
                        <th>UOM</th>
                        <th>Required Qty</th>
                        <th>Purpose of consume</th>
                        <th>Customer PO No.</th>
                    </tr>
                    <%
                        if (request.getParameter("loc").equalsIgnoreCase("all")) {
                            itmlist = d.getALLitem();
                        } else {
                            itmlist = d.getALLitem("LOC_ID", request.getParameter("loc"), uid);
                        }
                        for (ItemBean bean : itmlist) {
                            i++;%>
                    <tr>
                        <td><%=i%></td>
                        <td><input type="text" size="6" id="a<%=i%>" name="a<%=i%>" value="<%=bean.getITEM_CODE()%>" class="readon" readonly/></td>
                        <td><textarea id="b<%=i%>" name="b<%=i%>" class="readon" readonly><%=bean.getITEM_DESC()%> </textarea></td>
                        <input type="hidden" id="c<%=i%>" name="c<%=i%>" class="readon" value="<%=bean.getLOC_ID()%>" readonly style="resize: both">
                        <!--<td><input type="text" size="20" id="b<%=i%>" name="b<%=i%>" value="<%=bean.getITEM_DESC()%>" class="readon" readonly/></td>-->	
                        <!--<td><input type="text" size="20" id="c<%=i%>" name="c<%=i%>" value="<%=bean.getLOC_ID()%>" class="readon" readonly/></td>-->
                            <td><input type="text" size="14"  value="<%=bean.getCONTAINER_NO()%>" class="readon" readonly/></td>
                            <td><input type="text" size="14" id="d<%=i%>" name="d<%=i%>" value="<%=bean.getPRE_SHIP()%>" class="readon" readonly/></td>
                            <td><input type="text" size="12" id="e<%=i%>" name="e<%=i%>" value="<%=bean.getLOT_NO()%>" class="readon" readonly/></td>
                            <td><input type="text" size="6" id="f<%=i%>" name="f<%=i%>" value="<%=bean.getRECIEPT_NO()%>" class="readon" readonly/></td>
                            <td><input type="text" size="8" id="m<%=i%>" name="m<%=i%>" value="<%=bean.getGRN_DATE()%>" class="readon" readonly/></td>
                            <td><input type="text" size="9" id="g<%=i%>" name="g<%=i%>" value="<%=bean.getINV_NO()%>" class="readon" readonly/></td>
                            <td><b><input type="text" size="2" id="h<%=i%>" name="h<%=i%>" value="<%=bean.getRE_QTY()%>" class="readon cur" readonly/></b></td>
                            <td><input type="text" size="6" id="i<%=i%>" name="i<%=i%>" value="<%=bean.getUOM()%>" class="readon" readonly/></td>
                            <% if (isallow) {%>
                            <td><input type="text" size="4" id="j<%=i%>" name="j<%=i%>" value="0" onfocus="makeClear(this)" onblur="checkQTY(<%=i%>)" onkeypress="return isNumberKey(<%=i%>)"/></td>
                            <td> <select id="k<%=i%>" name="k<%=i%>">
                                    <% for (DDBean pur : dd.DDpur) {%>
                                    <option value="<%=pur.getPur_id()%>"><%=pur.getPurpose()%></option>
                                    <%}%>
                                </select></td>
                            <td><input type="text" size="5" id="l<%=i%>" name="l<%=i%>" value="-" onfocus="makeClear(this)" onblur="checkClear(this)"/></td>
                            <%} else {%>
                            <td colspan="3">Select spacific location for consume item
                            </td>
                            <%}%>
                    </tr>
                    <%}%>
                    <input type="hidden" name="totrow" id="totrow" value="<%=i%>"/>
                    <% if (isallow) {  %>
                    <tr>
                        <td colspan="12"></td>
                        <th> 
                            <%if (isallow) {%>
                            <input type="submit" value="Consume" id="submit" name="submit"/>
                            <%}%>
                        </th>
                    </tr>
                    <%}%>
                </table>  
            </form>
            <% } else {%>
            <form method="post" action="itmcun.jsp" onsubmit="submit.disabled = true;
                    submit.value = 'Processing ..';
                    return true;">
                <div class="dtb"> <p>Select Location</p> 
                    <select id="loc" name="loc">
                        <option value="">Select Location</option>
                        <% for (DDBean loc : dd.DDloc) {%>
                        <option value="<%=loc.getLoc_Id()%>"><%=loc.getLoc_name()%></option>
                        <%} %>
                    </select>
                    <input type="submit" value="Submit" id="submit" name="submit"/>
                </div>
                <% if (request.getParameterMap().containsKey("err")) {%>
                <table class="dtb">
                    <tr>
                        <td style="color: red"><b>Transaction terminated :</b>
                            Available QTY  less then Required QTY</td>
                    </tr>       
                </table>
                <%}%>

                <% if (request.getParameterMap().containsKey("err2")) {%>
                <table class="dtb">
                    <tr>
                        <td style="color: red"><b>Transaction terminated :</b>
                            No QTY entered for consume</td>
                    </tr>       
                </table>
                <%}%>
            </form>
            <%}%>
        </div>
        <%@include file="include/footer.jsp" %>
        <script>
            function makeClear(id) {
                id.value = '';

            }
            function checkClear(id) {
                if (id.value == '') {
                    id.value = '-';
                }
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
//                var charCode = (evt.which) ? evt.which : event.keyCode;
//                if (charCode > 31 && (charCode < 48 || charCode > 57))
//                    return false;
//                return true;

                var x = document.getElementById("j" + evt).value;
//                alert(x);
                var rgx = /^[0-9]*\.?[0-9]*$/;
                return evt.match(rgx);
            }
        </script>


    </body>
</html>
