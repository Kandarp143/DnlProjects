<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="Bean.WorkTermBean"%>
<%@page import="Bean.WorkItemBean"%>
<%@page import="Bean.WorkTaxBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.WorkBean"%>
<%@page import="Dao.WorkDao"%>
<%
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
%>
<style>
    .lab{
        font-weight: bold;
    }
</style>
<!-- HEADER -->
<table style="padding: 10px" cellspacing="5" class="dtb">
    <% if (sid == 4) {
    %>
    <tr>
        <td colspan="7">
        </td>
        <th>
            <a href="updatewo.jsp?pono=<%=request.getParameter("pono")%>&sid=<%=request.getParameter("sid")%>">
                <span style="color: #8B0000;font-weight: bold"><b>Update WO</b></span>
            </a>
            <%}%>
        </th>
    </tr>

    <tr>
        <th colspan="12"> Summary </th>
    </tr>
    <tr>
        <td class="lab">WO Number:</td>
        <td><%=wo1.getPO_NO()%></td>
        <td class="lab">WO Title:</td>
        <td ><%=wo1.getPO_TITLE()%></td>
        <td class="lab">Status:</td>
        <td><%=wo1.getSTATUS()%></td>
        <td class="lab">Created By:</td>
        <td colspan="3"><%=wo1.getUSER_NAME()%></td>
    </tr>
    <tr>
        <td class="lab" >Supplier:</td>
        <td><%=wo1.getSUP_NAME()%></td>
        <td class="lab">Site:</td>
        <td><%=wo1.getSITE_NAME()%></td>
        <td class="lab">Currency:</td>
        <td><%=wo1.getCUR()%></td>
        <td class="lab">Operating Unit:</td>
        <td><%=wo1.getOU()%></td>
        <td class="lab">Creation Date:</td>
        <td colspan="3"><%=wo1.getCR_DATE()%></td>
    </tr>
    <tr>

        <td class="lab" >Retention Amount:</td>
        <td align="right"><%=wo1.getRET_AMT()%></td>
        <td class="lab" >Deposit Amount:</td>
        <td align="right"><%=wo1.getDEP_AMT()%></td>
        <td class="lab">Type:</td>
        <td><%=wo1.getTYPE()%></td>
        <td class="lab">Valid From :</td>
        <td><%=wo1.getFR_DATE()%></td>
        <td class="lab">Valid To :</td>
        <td><%=wo1.getEXP_DATE()%></td>
    </tr>
</table>
<!-- ITEM -->   
<table id="myTable" class="dtb">
    <thead>
        <tr>
            <th colspan="8"> Item Detail </th>
            <th colspan="3"> <%if (request.getParameterMap().containsKey("export")) {%>
                <a href="uxls/<%=wo1.getPO_NO().replace("/", "-")%>_Items.xlsx">Download File</a>
                <% } else {%>
    <form method="POST" ACTION="ExportWOItem?pono=<%=request.getParameter("pono")%>&sid=<%=request.getParameter("sid")%>" onsubmit="submit.disabled = true;
            submit.value = 'Processing ..';
            return true;">
        <input type="submit" value="Export to Excel" id="submit" 
               name="submit" />
    </form>
    <%} %></th>
</tr>
<tr>
    <th>Line NO</th>
    <th>Item </th>
    <th> Description </th>
    <th>U.O.M</th>
    <th>Quantity</th>
    <th>Rate</th>
    <th>Value</th>
    <th>Plant</th>
    <th>Project</th>
    <th>Task</th>
    <th>Note</th>
</tr>
</thead>
<tbody>
    <%  int index2v = 1;
        for (WorkItemBean wi2 : wi1) {%>
    <tr>
        <td><%=wi2.getLINE_NO()%></td>
        <td><%=wi2.getITEM_ID()%></td>
        <td><%=wi2.getITEM_DESC()%></td>
        <td><%=wi2.getUOM()%></td>
        <td align="right"><%=wi2.getQTY()%></td>
        <td align="right"><%=wi2.getRATE()%></td>
        <td align="right"><%=wi2.getVAL()%></td>
        <td><%=wi2.getPLANT()%></td>
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
                        <td align="right"><%=wt2.getTAX_VAL()%></td>
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
<table style="padding: 15px">
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