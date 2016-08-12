<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
<%@page import="Dao.BillDao"%>
<%@page import="Bean.LoginBean"%>
<%@page import="Dao.LoginDao"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="Bean.WorkTermBean"%>
<%@page import="Bean.WorkItemBean"%>
<%@page import="Bean.WorkTaxBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.WorkBean"%>
<%@page import="Dao.WorkDao"%>
<%
    Logic.Dropdown.LoadDropdown();
    Logic.Dropdown.LoadProjectDropdown(session.getAttribute("unit").toString());
    Logic.Dropdown.LoadTaxDropdown(session.getAttribute("unit").toString());
    String pono1 = request.getParameter("pono");
    WorkBean wo1 = new WorkBean();
    ArrayList<WorkTaxBean> wt1 = new ArrayList<WorkTaxBean>();
    ArrayList<WorkItemBean> wi1 = new ArrayList<WorkItemBean>();
    WorkTermBean wp1 = new WorkTermBean();
    WorkDao wdao1 = new WorkDao();
    wo1 = wdao1.getWO(pono1);
    wi1 = wdao1.getWOItem(pono1);
    wt1 = wdao1.getWOTax(pono1);
    wp1 = wdao1.getWOTerm(pono1);
    ArrayList<WorkBean> billidlist = new ArrayList<WorkBean>();
    BillDao bdao1 = new BillDao();
    billidlist = bdao1.getBill_usr_no(pono1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLCBA</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.searchabledropdown-1.0.8.min.js"></script>
        <script type="text/javascript" src="js/Datepicker.js" ></script>
        <script type="text/javascript" src="js/custom/bill.js" ></script>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <div>
                    <h4 class="page_title">Release Work Order / Generate Bill</h4>
                </div>
                <form action="CreateBill" method="post" onsubmit="submit.disabled = true;
                        submit.value = 'Processing ..';
                        return true;">
                    <fieldset>
                        <legend style="">Header</legend>
                        <%@include file="include/womstb.jsp" %>
                    </fieldset>
                    <fieldset>
                        <legend>Item Detail</legend>
                        <%@include file="include/wodtb.jsp" %>
                    </fieldset>
                    <fieldset>
                        <legend>Total Value and Terms</legend>
                        <%@include file="include/wofootb.jsp" %>
                    </fieldset>
                    <fieldset style="background: #878D90">
                        <div style="font-size: large;color: #A70303;float: left;width: 300px" >
                            <%
                                ArrayList<LoginBean> ulist = new ArrayList<LoginBean>();
                                LoginDao ldao = new LoginDao();
                                ulist = ldao.getUserByRole("CRT");%>
                            <select id="NUID" name="NUID"  style="width: 250px" >  
                                <option value="">Select User For Approval</option>
                                <%  for (LoginBean lb : ulist) {%>
                                <option value="<%=lb.getUSER_ID()%>"><%=lb.getUSER_NAME()%></option>
                                <%}%>
                            </select>
                        </div>
                        <div style="float: left">
                            <input type="submit" value="submit" id="submit" onclick="return validate();" name="submit" onsubmit="submit.disabled = true;
                                    submit.value = 'Processing ..';
                                    return true;"  />
                            <script>
                                var bills = [];
                                <%for (int i = 0; i < billidlist.size(); i++) {%>
                                bills.push("<%=billidlist.get(i).getBILL_ID().toUpperCase()%>");
                                <%}%>
                                function validate() {
                                    var bid = document.getElementById("bid").value;
                                    bid = bid.toUpperCase();
                                    bid = bid.replace(/[^a-zA-Z0-9]/g, '');
                                    var k = document.getElementById("k").value;
                                    var itemrows = document.getElementById("itemrows").value;
                                    var NUID = document.getElementById("NUID");
                                    var valNUID = NUID.options[NUID.selectedIndex].value;
                                    var bool = "true";
                                    if (bid === "" || k === "") {
                                        alert("Please Fill Mandatory Fields : BillNumber and Bill Date ");
                                        bool = "false";
                                    }
                                    if (itemrows === "") {
                                        alert("Atleast one item should be selected");
                                        bool = "false";
                                    }
                                    if (valNUID === "") {
                                        alert("Please Select Next User for Approval");
                                        bool = "false";
                                    }
                                    for (i = 0; i < bills.length; i++) {
                                        if (bid === bills[i]) {
                                            alert("This Bill No is already used in system");
                                            bool = "false";
                                        }
                                    }
                                    if (bool === "false") {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            </script>
                        </div>

                    </fieldset>
                </form>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
