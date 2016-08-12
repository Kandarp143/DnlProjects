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
        <script type="text/javascript" src="js/Datepicker.js"></script>
        <script type="text/javascript" src="js/jquery.loader.js"></script>
        <!--<script src="js/custom/WOglobalHeader.js"></script>-->
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right" class="right">
                <div>
                    <h4 class="page_title">Create Work Order Excel Data Loader</h4>
                </div>
                <div class="hero-unit">
                    <fieldset>
                        <legend>Item Detail</legend>
                        <%@include file="include/wodt_data.jsp" %>
                    </fieldset>
                </div>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
