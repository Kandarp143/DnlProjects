<%-- 
    Document   : App
    Created on : Aug 25, 2014, 10:55:26 AM
    Author     : 02948
--%>


<form action="WOApproval?pono=<%=request.getParameter("pono")%>&sid=<%=request.getParameter("sid")%>" method="post" onsubmit="submit.disabled = true;
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
            <td>
                <input type="submit" value="submit" id="submit"  name="submit" onsubmit="submit.disabled = true;
                        submit.value = 'Processing ..';
                        return true;" onclick="validate()"  />
                <script>
                    function validate() {
                        var cmt = document.getElementById("cmt").value;
                        if (cmt == "") {
                            alert("Please Fill Mandatory Fields : Comment");
                            return false;
                        }
                    }
                </script>
            </td>
        </tr>
    </table>
</form>

