<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLMOC</title>
        <link href="css/global.css" type="text/css" rel="stylesheet"/>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>     
        <link href="css/datasource_gui.css" type="text/css" rel="stylesheet"/>
        <!--Datagrid call -->
        <script type="text/javascript">
            $(document).ready(function () {
                $("#dtb2").dataTable({
                    "sPaginationType": "full_numbers",
                    "iDisplayLength": 18,
                    "bJQueryUI": true,
                    "aaSorting": []});
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
                <h2>Under Construction</h2>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
