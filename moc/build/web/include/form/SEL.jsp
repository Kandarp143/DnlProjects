<%@page import="kp.beans.user.pojo.MocUserHier"%>
<%@page import="kp.dao.user.UserDao"%>
<%@page import="kp.logic.GeneralMethods"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="java.util.HashSet"%>
<%@page import="kp.beans.mst.pojo.MocUserSelection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.dao.wf.WfUserDao"%>
<%
    WfUserDao wfuserdao = new WfUserDao();
    UserDao userDao = new UserDao();
    GeneralMethods gm = new GeneralMethods();
    String unit = session.getAttribute("usrunit").toString();
    ArrayList<MocUserSelection> users = wfuserdao.getWorkFlowSelection(unit);
    ArrayList<MocUserHier> userhier = userDao.getUserHierarchy(session.getAttribute("usr").toString());
    boolean isAllEng = false;
    for (MocUserSelection bean : users) {
        //if other cost estimation user selection not then all eng there
        if (bean.getStgId() == 8 && bean.getUserId() != "") {
            isAllEng = true;
        }
    }
%>
<table class="dtb">
    <tr>
        <td colspan="2">
            <span style="color: green">
                <span class="err">  Please Note :</span>    <br/>
                -Please select users to forward moc.<br/>
                -User can attach document after forward moc<br/>
                -At least  one user at each stage is mandatory</span>
        </td>
    </tr>
    <tr>
        <th>MOC Request / Moc Conformation</th>
        <td>
            <input type="checkbox" name="CB1" value="<%=session.getAttribute("usr").toString()%>" checked>
            <%=session.getAttribute("usrname").toString()%>
        </td>
    </tr>
    <tr>
        <th>Respective Head Approval / Moc Verification</th>
        <td>
            <ul class="listofcheckbox">
                <%int i = 0;
                    for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 2) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="checkbox" name="CB<%=bean.getStgId()%>"  value="<%=bean.getUserId()%>"
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                        i++;
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>Production Head Approval</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 3) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="checkbox" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>"
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>TS Verification</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 4) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="radio" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>TS Approval</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 5) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="checkbox" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>HSE Verification</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 6) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="radio" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>HSE Approval</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 7) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="checkbox" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>


    <%if (isAllEng) {  %>
    <tr>
        <th>All ENG Cost Estimation</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 8) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="radio" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <%} else {%>
    <tr>
        <th>Mechanical Cost Estimation</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 9) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="radio" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>Civil Cost Estimation</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 10) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="radio" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>"
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>Instrumentation Cost Estimation</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 11) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="radio" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>Electrical Cost Estimation</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 12) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="radio" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <%}%>
    <tr>
        <th>Engineering Head Approval</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 13) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="checkbox" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>QC Verification</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 14) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="radio" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>"
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>QC Approval</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 15) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="checkbox" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>Unit Head Approval</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 16) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="checkbox" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>Safety Head Approval</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 17) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="checkbox" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>VP Technology Approval</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 18) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="checkbox" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" 
                               <% for (MocUserHier userh : userhier) {
                                       if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                               && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
    <tr>
        <th>VP Operation Approval</th>
        <td>
            <ul class="listofcheckbox">
                <%for (MocUserSelection bean : users) {
                        if (bean.getStgId() == 19) {%>
                <li><label for="<%=bean.getStgId()%>">
                        <input type="checkbox" name="CB<%=bean.getStgId()%>" id="<%=bean.getStgId()%>" value="<%=bean.getUserId()%>" <% for (MocUserHier userh : userhier) {
                                if (userh.getWfUserId().equalsIgnoreCase(bean.getUserId())
                                        && userh.getStgId() == bean.getStgId()) { %>
                               checked
                               <%}
                                   }%>
                               />
                        <%=bean.getUserName()%>
                    </label>
                </li>
                <% }
                    }%>
            </ul>
        </td>
    </tr>
</table>
