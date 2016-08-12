
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLMOC</title>
        <link href="css/global.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <div id="Left">
                <%@include file="include/navbar.jsp" %>
            </div>
            <div id="Right">
                <div>
                    <h4 id="page_header">Select User to Forward Moc : <%=request.getParameter("cid")%></h4>
                </div>
                <form action="process/processSel.jsp?cid=<%=request.getParameter("cid")%>" method="post" onsubmit="submit.disabled = true;
                        submit.value = 'Processing ..';
                        return true;">
                    <%@ include file="include/form/SEL.jsp" %>
                    <%@ include file="include/form/CMT.jsp" %>
                    <input type="submit" value="Forward MOC for Approval" id="submit" name="submit" class="btn"/>
                </form>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
