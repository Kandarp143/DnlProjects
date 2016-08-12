<%@page import="kp.logic.Email"%>
<%@page import="kp.dao.data.MocDao"%>
<%@page import="kp.logic.ValidateDataDao"%>
<%@page import="kp.beans.mst.pojo.MocUserSelection"%>
<%@page import="kp.beans.wf.pojo.MocWfTran"%>
<%@page import="kp.dao.wf.StgDao"%>
<%@page import="kp.dao.wf.TranDao"%>
<%@page import="kp.dao.wf.WfUserDao"%>
<%@page import="kp.beans.data.pojo.MocInitMst"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.beans.wf.pojo.MocWfUser"%>
<%
    ArrayList<MocWfUser> usrs = new ArrayList<>();
    MocWfUser bean = null;
    String[] cb = null;
    MocInitMst mst = new MocInitMst();
    mst.setCaseId(Integer.parseInt(request.getParameter("cid")));
    ValidateDataDao validate = new ValidateDataDao();
    ArrayList<String> errors = validate.ValidateWfCmt(request.getParameter("cmt"));
    errors.addAll(validate.ValidateWfUser(request));
    if (errors.size() > 0) {
        request.setAttribute("errors", errors);
%>
<jsp:forward page="../retryUser.jsp"></jsp:forward>
<%        
    } else {
        for (int i = 1; i <= 22; i++) {
            if (request.getParameterMap().containsKey("CB" + i)) {
                cb = request.getParameterValues("CB" + i);
                for (String b : cb) {
                    out.println("CB" + i + " :" + b + "<br/>");
                    bean = new MocWfUser();
                    bean.setMocInitMst(mst);
                    bean.setStgId(i);
                    bean.setUserId(b);
                    usrs.add(bean);
                    if (i == 1) {
                        out.println("CB20 :" + b + "<br/>");
                        bean = new MocWfUser();
                        bean.setMocInitMst(mst);
                        bean.setStgId(20);
                        bean.setUserId(b);
                        usrs.add(bean);
                    }
                    if (i == 2) {
                        out.println("CB21 :" + b + "<br/>");
                        bean = new MocWfUser();
                        bean.setMocInitMst(mst);
                        bean.setStgId(21);
                        bean.setUserId(b);
                        usrs.add(bean);
                    }
                }
            }
        }
        WfUserDao d = new WfUserDao();
        d.setWorkFlowUsers(usrs);

        //START WORKFLOW TRANSACTION
        TranDao tdao = new TranDao();
        WfUserDao wfusrdao = new WfUserDao();
        StgDao stgdao = new StgDao();
        ArrayList<Integer> stg_n = new ArrayList<>();
        ArrayList<MocUserSelection> usr_n = new ArrayList<>();
        ArrayList<MocWfTran> trans = new ArrayList<>();
        //INITIATE
        int cid = Integer.parseInt(request.getParameter("cid"));
        String usr_c = session.getAttribute("usr").toString();
        int stg_c = 1;
        int act = 0;
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
        mdao.updateMocStatus(cid, "Approval Pending");
        //Email to cencern user
        Email em = new Email();
        em.Notify(cid);
        em.Notify_tsk(cid);
        
        response.sendRedirect("../msg.jsp?cid=" + request.getParameter("cid"));
    }
%>