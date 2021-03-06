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


<%
    ArrayList<MitBean> tranlist = new ArrayList<MitBean>();
    TranDao tdao = new TranDao();
    tranlist = tdao.getONHAND_TRAN(request.getParameter("itm"), request.getParameter("loc"));
    String itmdesc = "";
    if (!tranlist.isEmpty()) {
        itmdesc = tranlist.get(0).getITEM_DESC();
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
                <h3>Transaction detail</h3>  
            </div>
            <div>
                <table class="dtb" id="dtb_gui">
                    <thead>
                        <tr style="color: #A70303;font-weight: bold">
                            <td>
                                Item : 
                            </td>
                            <td colspan="3">
                                <%=itmdesc%>
                            </td>
                        </tr>
                        <tr>
                            <th>Item</th>
                            <th>Location</th>
                            <th>In-Transit Loc</th>
                            <th>Receipt No</th>
                            <th>GRN Date</th>
                            <th>Pre-Shipment No</th>
                            <th>CRO NO</th>
                            <th>QTY</th>
                            <th>UOM</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%     if (tranlist.size() < 1) {%>
                        <td colspan="12">No Records</td>
                        <%
                        } else {
                            for (MitBean bean : tranlist) {
                        %>
                        <tr>
                            <td><%=bean.getITEM_DESC()%></td>
                            <td><%=bean.getLOC_ID()%></td>
                            <td><%=bean.getIN_TRANSIT_LOC()%></td>
                            <td><%=bean.getRECIEPT_NO()%></td>
                            <td><%=bean.getGRN_DATE()%></td>
                            <td><%=bean.getPRE_SHIP()%></td>
                            <td><%=bean.getCRO_NO()%></td>
                            <td class="cur"><%=bean.getQTY()%></td>
                            <td><%=bean.getUOM()%></td>
                            <td><b><%=bean.getSTATUS()%></b></td>

                        </tr>
                        <%}
                            }%>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="include/footer.jsp" %>
    </body>
</html>
