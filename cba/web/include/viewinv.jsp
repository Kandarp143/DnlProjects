<%@page import="Dao.BudgetDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="Bean.InvItemBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Bean.InvBean"%>
<%@page import="Dao.ProjINVDao"%>
<%
    ProjINVDao prodao = new ProjINVDao();
    InvBean invbean = new InvBean();
    ArrayList<InvItemBean> invitm = new ArrayList<InvItemBean>();
    invbean = prodao.getProjInv(wo1, wi1, wt1, wp1);
    invitm = prodao.getProjInvItem(wo1, wi1, wt1, wp1);
    Calendar c = Calendar.getInstance();   // this takes current date
    c.set(Calendar.DAY_OF_MONTH, 1);
    SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
    String formatted = format1.format(c.getTime());
    System.out.println();       // this returns java.util.Date
    BudgetDao budgetdao = new BudgetDao();

%>
<!-- HEADER -->
<table style="padding: 10px" cellspacing="5" class="dtb">
    <small style="color: red;float: right;margin-right: 30px;margin-bottom: 2px">
        Note :Please note this is just projected invoice not created in ERP, it will create after complete approved.
    </small>
    <tr>
        <th colspan="13">Projected Invoice Header Summary</th>
    </tr>
    <tr>
        <td class="lab">Invoice Number:</td>
        <td><%=invbean.getINVOICE_NUM()%></td>
        <td class="lab">Invoice Date :</td>
        <td><%=invbean.getINVOICE_DATE()%></td>
        <td class="lab">Invoice Amount :</td>
        <td ><%=invbean.getINVOICE_AMOUNT()%></td>
        <td class="lab">Currency Code:</td>
        <td><%=invbean.getINVOICE_CURRENCY_CODE()%></td>
        <td class="lab" colspan="2">GL Date:</td>
        <td colspan="3"><%=formatted%></td>
    </tr>
    <tr>
        <td class="lab" >Supplier:</td>
        <td><%=invbean.getVENDOR_NAME()%></td>
        <td class="lab">Site:</td>
        <td><%=invbean.getVENDOR_SITE_CODE()%></td>
        <td class="lab">Operating Unit:</td>
        <td><%=wo1.getOU()%></td>
        <td class="lab">Account Pay Id:</td>
        <td><%=invbean.getACCpayID()%></td>
        <td class="lab">Invoice Type:</td>
        <td><%=invbean.getINVOICE_TYPE_LOOKUP_CODE()%></td>
        <td class="lab"colspan="2">Source:</td>
        <td><%=invbean.getSOURCE()%></td>
    </tr>
</table>
<table style="padding: 10px" cellspacing="5" class="dtb">
    <tr>
        <th colspan="12">Distribution</th>
    </tr>
    <tr>
        <th>Line Number</th>
        <th>Lookup Code</th>
        <th>Org Id</th>
        <th>General Ledger</th>
        <th>Project</th>
        <th>Task</th>
        <th>Amount</th>
        <th>Budget Amount</th>
        <th>Description</th>
    </tr>

    <% float sum = 0;
        for (InvItemBean itm : invitm) {
            sum = sum + itm.getAMOUNT();
    %>
    <tr>
        <td><%=itm.getLINE_NUMBER()%></td>
        <td><%=itm.getLINE_TYPE_LOOKUP_CODE()%></td>
        <td class="cur"><%=itm.getORG_ID()%></td>
        <%if (itm.getSeg4() == 25553) {%>
        <td>if ->recoverable then service tax GL
            <br/>if-> non recoverable then item charge account
        </td>
        <%} else {%>
        <td class="cur"><%=itm.getGL_CODE()%></td>
        <%}%>
        <td><%=itm.getPROJ()%></td>
        <td><%=itm.getTASK()%></td>
        <td class="cur"><b><%=itm.getAMOUNT()%></b></td>
        <td class="cur"><%=budgetdao.getBudgetValue(itm.getPROJ(), itm.getTASK())%></td>
        <td><%=itm.getDESCRIPTION()%></td>
    </tr>
    <%}%>
    <tr>
        <td colspan="4"></td>
        <th colspan="2"><b>Total Amount<b></th>
                    <th><%=sum%></th>
                    </tr>
                    </table>