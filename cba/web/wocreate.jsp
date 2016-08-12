<%@page import="Bean.WorkItemBean"%>
<%@page import="java.util.ArrayList"%>
<%
    Logic.Dropdown.LoadDropdown();
    Logic.Dropdown.LoadProjectDropdown(session.getAttribute("unit").toString());
    Logic.Dropdown.LoadTaxDropdown(session.getAttribute("unit").toString());
    boolean xls = false;
    ArrayList<WorkItemBean> wi1 = null;
    if (request.getParameterMap().containsKey("xls")) {
        wi1 = Logic.Variables.xlsitmwo;
        xls = true;
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLCBA</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <link href="css/jquery.loader.css" type="text/css" rel="stylesheet"/>
        <link href="css/Datepicker.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery-latest.min.js" ></script>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery.searchabledropdown-1.0.8.min.js"></script>
        <script src="js/Datepicker.js" type="text/javascript"></script>
        <script src="js/jquery.loader.js"></script>
        <script src="js/custom/wo.js"></script>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right" class="right">
                <div>
                    <h4 class="page_title">Create Work Order</h4>
                </div>
                <div class="hero-unit">
                    <form  id="testForm" action="CreateWO" method="post" onsubmit="submit.disabled = true;
                            submit.value = 'Processing ..';
                            return true;">
                        <fieldset>
                            <legend>Header</legend>
                            <%@include file="include/womst.jsp" %>
                        </fieldset>
                        <fieldset>
                            <legend>Item Detail</legend>
                            <%if (xls) {%>
                            <%@include file="include/wodt_data2.jsp" %>
                            <%} else {
                                %>
                            <%@include file="include/wodt.jsp" %>
                            <%}
                            %>
                        </fieldset>
                        <fieldset>
                            <legend>Total Value and Terms</legend>
                            <%@include file="include/wofoot.jsp" %>
                        </fieldset>
                        <fieldset>
                            <input type="submit" value="submit" id="submit" onclick="return validate();" name="submit" onsubmit="submit.disabled = true;
                                    submit.value = 'Processing ..';
                                    return true;"  />

                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
