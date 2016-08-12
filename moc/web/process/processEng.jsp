<%@page import="kp.logic.Email"%>
<%@page import="kp.beans.wf.pojo.MocWfTran"%>
<%@page import="kp.beans.mst.pojo.MocUserSelection"%>
<%@page import="kp.dao.wf.StgDao"%>
<%@page import="kp.dao.wf.WfUserDao"%>
<%@page import="kp.dao.wf.TranDao"%>
<%@page import="kp.dao.data.EngDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.logic.ValidateDataDao"%>
<%@page import="kp.logic.GeneralMethods"%>
<%@page import="kp.dao.data.MocDao"%>
<%@page import="kp.beans.data.pojo.MocInitMst"%>
<%
    int cid = Integer.parseInt(request.getParameter("cid"));
    GeneralMethods gm = new GeneralMethods();
    ValidateDataDao validate = new ValidateDataDao();
    EngDao d = new EngDao();
    StgDao stgdao = new StgDao();
    MocInitMst mst = new MocInitMst();
    mst.setCaseId(cid);
    ArrayList<String> errors = validate.ValidateWfCmt(request.getParameter("cmt"));
%>
<jsp:useBean id="objE" class="kp.beans.data.pojo.MocEngMst" scope="request">
    <jsp:setProperty property="l2" name="objE"/>
    <%try {
            Double.parseDouble(request.getParameter("l1"));
        } catch (Exception e) {
            errors.add("Please Enter Valid Estimated Cost");
            request.setAttribute("errors", errors);
    %>
    <jsp:forward page="../retryEng.jsp"></jsp:forward>
    <%}%>
    <jsp:setProperty property="l1" name="objE"/>
</jsp:useBean>  
<%
    if (errors.size() > 0) {
        request.setAttribute("errors", errors);
%>
<jsp:forward page="../retryEng.jsp"></jsp:forward>
<%
    } else {
        objE.setMocInitMst(mst);
        objE.setUserId(session.getAttribute("usr").toString());
        objE.setDeptId(stgdao.getStgName(Integer.parseInt(request.getParameter("sid"))));
        if (d.fecthEng(cid, session.getAttribute("usr").toString()) == null) {
            cid = d.saveEng(objE);
        } else {
            d.updateEng(objE);
        }
        //Initiate Workflow
        TranDao tdao = new TranDao();
        WfUserDao wfusrdao = new WfUserDao();
        String usr_c = session.getAttribute("usr").toString();
        ArrayList<Integer> stg_n = new ArrayList<>();
        ArrayList<MocUserSelection> usr_n = new ArrayList<>();
        //1.update current tran flag
        tdao.completeTransaction(cid, usr_c);
        //2.add new transation
        int stg_c = Integer.parseInt(request.getParameter("sid"));
        int act = Integer.parseInt(request.getParameter("act"));
        stg_n = stgdao.getNxtStg(stg_c, act, cid);
        for (int stg : stg_n) {
            usr_n.addAll(wfusrdao.getNxtUser(cid, stg));
        }
        //3.if parellel then else
        if (tdao.isTranactionPending(cid, Integer.parseInt(request.getParameter("sid")))) {
            for (int sid : stg_n) {
                MocWfTran tran = new MocWfTran();
                tran.setMocInitMst(mst);
                tran.setStgC(stg_c);
                tran.setStgN(sid);
                tran.setAct(act);
                tran.setUserC(usr_c);
                tran.setUserN("-");
                tran.setCmt(request.getParameter("cmt"));
                tran.setIsCompleted('Y');
                tdao.saveTransaction(tran);
            }
        } else {
            for (MocUserSelection usr : usr_n) {
                MocWfTran tran = new MocWfTran();
                tran.setMocInitMst(mst);
                tran.setStgC(stg_c);
                tran.setStgN(usr.getStgId());
                tran.setAct(act);
                tran.setUserC(usr_c);
                tran.setUserN(usr.getUserId());
                tran.setCmt(request.getParameter("cmt"));
                tdao.saveTransaction(tran);
            }
        }
//Email to cencern user
        Email em = new Email();
        em.Notify(cid);
        response.sendRedirect("../msg.jsp?cid=" + cid);

    }
%>


