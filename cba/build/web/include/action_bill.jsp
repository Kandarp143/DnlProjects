<%-- 
    Document   : App
    Created on : Aug 25, 2014, 10:55:26 AM
    Author     : 02948
--%>
<%@page import="Dao.LoginDao"%>
<%@page import="Bean.LoginBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<script>
    $(document).ready(function () {
        $("input[name='act']").click(function () {
            $('#show-me').css('display', ($(this).val() === '1') ? 'block' : 'none');
        });
    });
</script>
<form action="BillApproval?bill=<%=request.getParameter("bill")%>&sid=<%=request.getParameter("sid")%>" method="post" onsubmit="submit.disabled = true;
        submit.value = 'Processing ..';
        return true;">
    <table width="100%" border="1" cellpadding="5" cellspacing="0" class="auto-style1">
        <tr>
            <td>
                <label for="cmt">Comment<span style="color:red;font-size:x-large">*</span></label>
            </td>
            <td>
                <% if (request.getParameter("cmt") == null) {%>
                <textarea id="cmt" name="cmt"></textarea>
                <%} else {%>
                <textarea id="cmt" name="cmt"><%=request.getParameter("cmt")%> </textarea>
                <%}%>
            </td>
        </tr>
        <tr>
            <td>
                <label for="act">Action : <span style="color:red;font-size:x-large">*</span></label>
            </td>
            <td>
                <input type="radio" name="act" id="act" value="1"> Promote
                &nbsp;&nbsp;&nbsp; 
                <input type="radio" name="act" id="act" value="2"> Demote
                &nbsp;&nbsp;&nbsp;        
                <input type="radio" name="act" id="act" value="3"> Reject
                &nbsp;&nbsp;&nbsp;        
            </td>
        </tr>
        <tr> 
            <td colspan="1">
                <%if (request.getParameter("sid").equals("18")) {%>
                <div id='show-me' style='display:none'>
                    <div style="font-size: large;color: #A70303;float: left;width: 300px" >
                        <%
                            ArrayList<LoginBean> ulist = new ArrayList<LoginBean>();
                            LoginDao ldao = new LoginDao();
                            ulist = ldao.getUserByRole("PLI");%>
                        <select id="NUID" name="NUID"  style="width: 250px" >  
                            <option value="">Select Plant In-Charge</option>
                            <%  for (LoginBean lb : ulist) {%>
                            <option value="<%=lb.getUSER_ID()%>"><%=lb.getUSER_NAME()%></option>
                            <%}%>
                        </select>
                        <span style="color:red;font-size:x-large">*</span>   </div> </div>
                <input type="submit" value="submit" id="submit" onclick="return validate();" name="submit" onsubmit="submit.disabled = true;
                        submit.value = 'Processing ..';
                        return true;"  />
                <%} else {%>
            </td>
            <td colspan="3">
                <input type="submit" value="submit" id="submit" onclick="return validate();" name="submit" onsubmit="submit.disabled = true;
                        submit.value = 'Processing ..';
                        return true;"  />
                <%}%>
                <script>
                    function validate() {
                        var cmt = document.getElementById("cmt").value;
                        if (cmt == "") {
                            alert("Please Fill Mandatory Fields : Comment ");
                            return false;
                        }
                    }
                </script>
            </td>
        </tr>
    </table>
</form>
