<%@page import="kp.logic.Email"%>
<%@page import="kp.dao.user.UserDao"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
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
    GeneralMethods gm = new GeneralMethods();
    ValidateDataDao validate = new ValidateDataDao();
    MocInitMst mst = new MocInitMst();
    mst.setCaseId(Integer.parseInt(request.getParameter("cid")));
    ArrayList<String> errors = validate.ValidateWfAct(request);
%>

<%    if (errors.size() > 0) {
        request.setAttribute("errors", errors);
%>
<jsp:forward page="../retryApp.jsp"></jsp:forward>
<%
    } else {
        //START WORKFLOW TRANSACTION
        TranDao tdao = new TranDao();
        WfUserDao wfusrdao = new WfUserDao();
        StgDao stgdao = new StgDao();
        MocDao mdao = new MocDao();
        ArrayList<Integer> stg_n = new ArrayList<>();
        ArrayList<MocUserSelection> usr_n = new ArrayList<>();
        //INITIATE
        int cid = Integer.parseInt(request.getParameter("cid"));
        String usr_c = session.getAttribute("usr").toString();
        //1.update current tran flag
        tdao.completeTransaction(cid, usr_c);
        //2.assign values 
        int stg_c = Integer.parseInt(request.getParameter("sid"));
        int act = Integer.parseInt(request.getParameter("act"));
        if (act == 4) {
            //if user demote moc then get next user from himself
            stg_n.add(Integer.parseInt(request.getParameter("usr").substring(request.getParameter("usr").indexOf("/") + 1)));
            MocUserSelection us = new MocUserSelection();
            us.setUserId(request.getParameter("usr").substring(0, request.getParameter("usr").indexOf("/")));
            us.setStgId(Integer.parseInt(request.getParameter("usr").substring(request.getParameter("usr").indexOf("/") + 1)));
            usr_n.add(us);
            //Email to cencern user
            Email em = new Email();
            em.Notify(cid);
        } else {
            //if user do other action then standard formula
            stg_n = stgdao.getNxtStg(stg_c, act, cid);
            for (int stg : stg_n) {
                usr_n.addAll(wfusrdao.getNxtUser(cid, stg));
            }
        }

        //3.transaction addition
        //if perellel transaction is there
        if (tdao.isTranactionPending(cid, stg_c)) {
            //user demote  or not verified moc
            if (act == 4 || act == 7) {
                ArrayList<MocUserSelection> parellelusr = wfusrdao.getParellelUser(cid, stg_c);
                for (MocUserSelection bean : parellelusr) {
                    tdao.completeTransaction(cid, bean.getUserId());
                }
                //Add transaction for each user
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
                //user reject moc  
            } else if (act == 5) {
                ArrayList<MocUserSelection> parellelusr = wfusrdao.getParellelUser(cid, stg_c);
                for (MocUserSelection bean : parellelusr) {
                    tdao.completeTransaction(cid, bean.getUserId());
                }
                mdao.updateMocStatus(cid, "Rejected");
                //Add transaction for each user
                for (MocUserSelection usr : usr_n) {
                    MocWfTran tran = new MocWfTran();
                    tran.setMocInitMst(mst);
                    tran.setStgC(stg_c);
                    tran.setStgN(usr.getStgId());
                    tran.setAct(act);
                    tran.setUserC(usr_c);
                    tran.setUserN(usr.getUserId());
                    tran.setCmt(request.getParameter("cmt"));
                    tran.setIsCompleted('R');
                    tdao.saveTransaction(tran);
                }
                //user promote moc or insert data
            } else {
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
            }
            //if parellel transaction is not there
        } else {
            //Add transaction for each user
            if (act == 5) {
                mdao.updateMocStatus(cid, "Rejected");
                //Add transaction for each user
                for (MocUserSelection usr : usr_n) {
                    MocWfTran tran = new MocWfTran();
                    tran.setMocInitMst(mst);
                    tran.setStgC(stg_c);
                    tran.setStgN(usr.getStgId());
                    tran.setAct(act);
                    tran.setUserC(usr_c);
                    tran.setUserN(usr.getUserId());
                    tran.setCmt(request.getParameter("cmt"));
                    tran.setIsCompleted('R');
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
                    //but if stg_n is approved
                    if (usr.getStgId() == 19) {
                        mdao.updateMocStatus(cid, "Approved");
                        Email em = new Email();
                        em.Notify_Approved(cid);
                    }
                    tdao.saveTransaction(tran);
                }
                //Email to cencern user
                Email em = new Email();
                em.Notify(cid);
            }
        }

        response.sendRedirect("../msg.jsp?cid=" + cid);
    }
%>