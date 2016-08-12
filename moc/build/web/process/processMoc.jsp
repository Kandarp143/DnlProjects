<%@page import="kp.logic.Email"%>
<%@page import="kp.beans.wf.pojo.MocWfTran"%>
<%@page import="kp.beans.mst.pojo.MocUserSelection"%>
<%@page import="kp.dao.wf.StgDao"%>
<%@page import="kp.dao.wf.WfUserDao"%>
<%@page import="kp.dao.wf.TranDao"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="kp.beans.data.pojo.MocInitTskDt"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.logic.ValidateDataDao"%>
<%@page import="kp.logic.GeneralMethods"%>
<%@page import="kp.dao.data.MocDao"%>
<%@page import="kp.beans.data.pojo.MocInitMst"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!-- GET ALL FORM DATA INTO BEANS -->
<jsp:useBean id="objA" class="kp.beans.data.pojo.MocInitReq" scope="request">
    <jsp:setProperty property="*" name="objA"/>
</jsp:useBean>  
<jsp:useBean id="objB" class="kp.beans.data.pojo.MocInitSta" scope="request">
    <jsp:setProperty property="*" name="objB"/>
</jsp:useBean>  
<jsp:useBean id="objC" class="kp.beans.data.pojo.MocInitRot" scope="request">
    <jsp:setProperty property="*" name="objC"/>
</jsp:useBean>  
<jsp:useBean id="objD" class="kp.beans.data.pojo.MocInitPip" scope="request">
    <jsp:setProperty property="*" name="objD"/>
</jsp:useBean>  
<jsp:useBean id="objE" class="kp.beans.data.pojo.MocInitEle" scope="request">
    <jsp:setProperty property="*" name="objE"/>
</jsp:useBean>  
<jsp:useBean id="objF" class="kp.beans.data.pojo.MocInitProd" scope="request">
    <jsp:setProperty property="*" name="objF"/>
</jsp:useBean>  
<jsp:useBean id="objG" class="kp.beans.data.pojo.MocInitProc" scope="request">
    <jsp:setProperty property="*" name="objG"/>
</jsp:useBean>  
<jsp:useBean id="objH" class="kp.beans.data.pojo.MocInitPseq" scope="request">
    <jsp:setProperty property="*" name="objH"/>
</jsp:useBean>  
<%
    GeneralMethods gm = new GeneralMethods();
    MocDao d = new MocDao();
    ValidateDataDao validate = new ValidateDataDao();
    ArrayList<String> errors = new ArrayList<>();
    MocInitMst mst = new MocInitMst();
    mst.setUserId(session.getAttribute("usr").toString());
    mst.setUnitId(session.getAttribute("usrunit").toString());
    //Task
    LinkedHashSet<MocInitTskDt> tasks = new LinkedHashSet<>();
    for (int i = 1; i <= 17; i++) {
        MocInitTskDt bean = new MocInitTskDt();
        if (request.getParameter("q" + i) == "0" || request.getParameter("r" + i) == "") {
        } else {
            bean.setDocId(i);
            bean.setResParty(request.getParameter("q" + i));
            bean.setUp("on");
            bean.setTarDate(gm.parseDate(request.getParameter("r" + i)));
            bean.setMocInitMst(mst);
            tasks.add(bean);
        }

    }
    mst.setMocInitTskDts(tasks);
    errors = validate.ValidateMoc(mst, objA, objB, objC, objD, objE, objF, objG, objH);
    if (errors.size() > 0) {
        request.setAttribute("errors", errors);
%>
<jsp:forward page="../retryMoc.jsp"></jsp:forward>
<%
    } else {
        int cid = 0;
        if (request.getParameterMap().containsKey("cid")) {
            cid = Integer.parseInt(request.getParameter("cid"));
            mst.setCaseId(cid);
            mst.setStatus("Updated");
            d.updateMoc(mst, objA, objB, objC, objD, objE, objF, objG, objH);
            //START WORKFLOW TRANSACTION
            TranDao tdao = new TranDao();
            WfUserDao wfusrdao = new WfUserDao();
            StgDao stgdao = new StgDao();
            ArrayList<Integer> stg_n = new ArrayList<>();
            ArrayList<MocUserSelection> usr_n = new ArrayList<>();
            //INITIATE
            String usr_c = session.getAttribute("usr").toString();
            //1.update current tran flag
            tdao.completeTransaction(cid, usr_c);
            int stg_c = 1;
            int act = 2;
            stg_n = stgdao.getNxtStg(stg_c, act, cid);
            for (int stg : stg_n) {
                usr_n.addAll(wfusrdao.getNxtUser(cid, stg));
            }
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
            //Change status of moc
            MocDao mdao = new MocDao();
            mdao.updateMocStatus(cid, "Updated");
            //Email to cencern user
            Email em = new Email();
            em.Notify(cid);
            response.sendRedirect("../msg.jsp?cid=" + cid + "&sid=" + stg_n.get(0));
        } else {
            cid = d.createMoc(mst, objA, objB, objC, objD, objE, objF, objG, objH);
            response.sendRedirect("../selUser.jsp?cid=" + cid);
        }
    }

%>

