<%-- 
   Document   : dashboard
   Created on : Feb 19, 2015, 3:04:17 PM
   Author     : 02948
--%>
<%@page import="Dao.TranDao"%>
<%@page import="Bean.MitBean"%>
<%@page import="Dao.ItemDao"%>
<%@page import="Bean.ItemBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Container DNLMMT</title>
        <link href="css/nav.css" type="text/css" rel="stylesheet"/>
        <link href="css/container.css" type="text/css" rel="stylesheet"/>
        <link href="css/dtb.css" type="text/css" rel="stylesheet"/>
    
        <link href="css/datasource_gui.css" type="text/css" rel="stylesheet"/>
        <!-- JS -->
        <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="js/table_rpt.js" type="text/javascript"></script>

        <!--Datagrid call -->
        <script type="text/javascript">
            $(document).ready(function () {
                $("#dtb_gui").dataTable({
                    "sPaginationType": "full_numbers",
                    "bJQueryUI": true,
                    "aaSorting": []});

            });
        </script>
    </head>
    <body>
        <%@include file="include/header.jsp" %>
        <div id="wrapper">
            <%@include file="include/navbar.jsp" %>
            <div class="page_title">
                <h3>Item Consumption Transaction Detail</h3>  
            </div>
            <div>
                <table class="dtb" id="dtb_gui" class ="display">
                    <thead>
                        <tr>
                            <th>Item Code</th>
                            <th>Description</th>
                            <th>Consume By</th>
                            <th>Tran-Date</th>
                            <th>Location</th>
                            <th>Pre-Shipment</th>
                            <th>Receipt No</th>
                            <th>Lot-No</th>
                            <th>MIT-NO</th>
                            <th>Purpose of Consume</th>
                            <th>Customer PO</th>
                            <th>QTY</th> 
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<MitBean> tranlist = new ArrayList<MitBean>();
                            TranDao tdao = new TranDao();
                            tranlist = tdao.getMIT_TRAN();
                            for (MitBean bean : tranlist) {
                        %>
                        <tr>
                            <td><%=bean.getITEM_CODE()%></td>
                            <td><%=bean.getITEM_DESC()%></td>
                            <td><%=bean.getUSER_NAME()%></td>
                            <td><%=bean.getTRAN_DATE()%></td>
                            <td><%=bean.getLOC_ID()%></td>
                            <td><%=bean.getPRE_SHIP()%></td>
                            <td><%=bean.getRECIEPT_NO()%></td>
                            <td><%=bean.getLOT_NO()%></td>
                            <td><%=bean.getMITNO()%></td>
                            <td><%=bean.getPUR_NAME()%></td>
                            <td><%=bean.getCUS_PO()%></td>
                            <td class="cur"><%=bean.getCUN_QTY()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
