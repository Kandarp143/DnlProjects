<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/global.css" type="text/css" rel="stylesheet"/>
          <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
        <title>JSP Page</title>
        <script>
            $(document).ready(function () {
                $("input[name='act']").click(function () {
                    $('#show-me').css('display', ($(this).val() === '4') ? 'block' : 'none');
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
                    <h4 id="page_header">Approval</h4>
                </div>
                <div>
                    <form action="process/processApp.jsp?cid=<%=request.getParameter("cid")%>&sid=<%=request.getParameter("sid")%>" method="post" onsubmit="submit.disabled = true;
                            submit.value = 'Processing ..';
                            return true;">
                        <%@ include file="include/form/APP.jsp" %>
                        <%@ include file="include/form/CMT.jsp" %>
                        <input type="submit" value="submit" id="submit" name="submit" class="btn"/>
                    </form>
                </div>

            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
