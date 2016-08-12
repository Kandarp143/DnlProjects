<%@page import="Dao.BillDao"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="Bean.WorkTermBean"%>
<%@page import="Bean.WorkItemBean"%>
<%@page import="Bean.WorkTaxBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.WorkBean"%>
<%@page import="Dao.WorkDao"%>

<%
    String bill = request.getParameter("bill");
    WorkBean wo1 = new WorkBean();
    ArrayList<WorkTaxBean> wt1 = new ArrayList<WorkTaxBean>();
    ArrayList<WorkItemBean> wi1 = new ArrayList<WorkItemBean>();
    WorkTermBean wp1 = new WorkTermBean();
    BillDao wdao1 = new BillDao();
    wo1 = wdao1.getBill(bill);
    wi1 = wdao1.getBillItem(bill);
    wt1 = wdao1.getBillTax(bill);
    wp1 = wdao1.getBillTerm(bill);
%>
<style>
    .lab{
        font-weight: bold;

    }
</style>
<!-- HEADER -->
<table style="padding: 10px" cellspacing="5" class="dtb">
    <% if (sid == 10) {
    %>
    <tr>
        <td colspan="7">
        </td>
        <th>
    <div>
        <a href="updatebill.jsp?bill=<%=request.getParameter("bill")%>&sid=<%=request.getParameter("sid")%>">
            <span style="color: #8B0000;font-weight: bold"><b>Update Bill</b></span>
        </a>
        <%}%>
    </div>
</th>
</tr>
<tr>
    <td></td>
</tr>
<tr>
    <th colspan="10"> Summary </th>
</tr>
<tr>
    <td class="lab">Bill ID:</td>
    <td><%=wo1.getBILL_NO()%></td>
    <td class="lab">Bill Number:</td>
    <td><%=wo1.getBILL_ID()%></td>
    <td class="lab">WO Number:</td>
    <td><%=wo1.getPO_NO()%></td>
    <td class="lab">WO Title:</td>
    <td colspan="3" ><%=wo1.getPO_TITLE()%></td>

</tr>
<tr>
    <td class="lab" >Supplier:</td>
    <td><%=wo1.getSUP_NAME()%></td>
    <td class="lab">Site:</td>
    <td><%=wo1.getSITE_NAME()%></td>
    <td class="lab">Operating Unit:</td>
    <td><%=wo1.getOU()%></td>
    <td class="lab">Created By:</td>
    <td><%=wo1.getUSER_NAME()%></td>
    <td class="lab">Creation Date:</td>
    <td><%=wo1.getCR_DATE()%></td>

</tr>
<tr>
    <td class="lab">Currency:</td>
    <td><%=wo1.getCUR()%></td>
    <td class="lab">Bill Date:</td>
    <td><%=wo1.getBILL_DATE()%></td>
    <td class="lab">Status:</td>
    <td><%=wo1.getSTATUS()%></td>

</tr>
</table>
<!-- ITEM -->   

<table id="myTable" class="dtb">
    <thead>
        <tr>
            <th colspan="13"> Item Detail </th>
        </tr>
        <tr>
            <th>SR NO</th>
            <th>WO Line NO</th>
            <th>Item</th>
            <th>Description</th>
            <th>U.O.M</th>
            <th>Quantity</th>
            <th>Rate</th>
            <th>Value</th>
            <th>Plant</th>
            <th>Cost Center</th>
            <th>Project</th>
            <th>Task</th>
            <th>Note</th>
        </tr>
    </thead>
    <tbody>

        <%  int index2v = 1;
            for (WorkItemBean wi2 : wi1) {%>
        <tr>
            <td><%=index2v++%></td>           
            <td><%=wi2.getLINE_NO()%></td>
            <td><%=wi2.getITEM_ID()%></td>
            <td><%=wi2.getITEM_DESC()%></td>
            <td><%=wi2.getUOM()%></td>
            <td class="cur"><%=wi2.getQTY()%></td>
            <td class="cur"><%=wi2.getRATE()%></td>
            <td class="cur"><%=wi2.getVAL()%></td>
            <td><%=wi2.getPLANT()%></td>
            <td><%=wi2.getCC()%></td>
            <td><%=wi2.getPROJ()%></td>
            <td><%=wi2.getTASK()%></td>
            <td><%=wi2.getCMT()%></td>
        </tr>
        <% }%>

    </tbody>
</table>


<!-- FOOTER -->
<table style="border-right: solid #C4C4C4 thin;padding: 15px;float: left">
    <tr>
        <td>
            <table id="txtable" name="txtable" class="dtb" style="width: 60%;float: left">
                <thead>
                    <tr>
                        <th colspan="3"> Tax Detail </th>
                    </tr>
                    <tr>
                        <th>SR NO</th>
                        <th>Tax Name</th>
                        <th>Rate</th>
                    </tr>
                </thead>
                <tbody>

                    <%  int indexv = 1;
                        for (WorkTaxBean wt2 : wt1) {%>
                    <tr>
                        <td><%=indexv++%></td>
                        <td><%=wt2.getTAX_NAME()%></td>
                        <td><%=wt2.getTAX_VAL()%></td>
                    </tr>
                    <% }%>
                </tbody>
            </table>  
        </td>
    </tr>
    <tr>
        <td>
            <table class="dtb">
                <tr>
                    <th style="width: 350px">Payment Term</th>
                </tr>
                <tr>
                    <td>  <%=wp1.getTERM_DESC()%></td>

                </tr>
            </table>
        </td>
    </tr>
</table>
<table style="padding: 15px;float: left">
    <tr>
        <td>
            <p style="color: brown;font-size: 29;"><b> Value : </b></p>
        </td>
        <td align="right">
            <%=wo1.getVAL()%>
        </td>
    </tr>
    <tr>
        <td>
            <p style="color: brown;font-size: 29;"><b>  Applied Tax : </b></p>
        </td>
        <td align="right">
            <%=wo1.getTOTAL_TAX()%>
        </td>
    </tr>
    <tr>
        <td>
            <p style="color: brown;font-size: 29;"><b>  Total Value : </b></p>
        </td>
        <td align="right">
            <%=wo1.getTOTAL_VAL()%>
        </td>
    </tr>
    <tr>
        <td colspan="2" >
            <p style="color: brown;font-size: 29;"><b>  Additional Description<br/> </b></p>  
                    <%=wo1.getWO_DESC()%>
        </td>
    </tr>

</table>