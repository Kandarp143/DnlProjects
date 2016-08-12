<%@page import="kp.logic.Email"%>
<%@page import="kp.beans.data.pojo.MocInitTskDt"%>
<%@page import="kp.beans.wf.pojo.MocWfTran"%>
<%@page import="kp.beans.mst.pojo.MocUserSelection"%>
<%@page import="kp.dao.wf.StgDao"%>
<%@page import="kp.dao.wf.WfUserDao"%>
<%@page import="kp.dao.wf.TranDao"%>
<%@page import="kp.dao.data.QcDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kp.logic.ValidateDataDao"%>
<%@page import="kp.logic.GeneralMethods"%>
<%@page import="kp.dao.data.MocDao"%>
<%@page import="kp.beans.data.pojo.MocInitMst"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
    GeneralMethods gm = new GeneralMethods();
    MocDao d = new MocDao();
    int cid = Integer.parseInt(request.getParameter("cid"));
    //Task
    ArrayList<MocInitTskDt> tsklist = new ArrayList<>();
    for (int i = 1; i <= 17; i++) {
        MocInitTskDt bean = new MocInitTskDt();
        if (request.getParameterMap().containsKey("s" + i)) {
            if (request.getParameter("s" + i) != null && request.getParameter("s" + i) != "") {
                bean.setDocId(i);
                bean.setComDate(gm.parseDate(request.getParameter("s" + i)));
                tsklist.add(bean);
            }
        }
    }
    d.updateMocInitTskDate(cid, tsklist);
    //Initiate Workflow
    MocInitMst mst = new MocInitMst();
    mst.setCaseId(cid);
    TranDao tdao = new TranDao();
    WfUserDao wfusrdao = new WfUserDao();
    StgDao stgdao = new StgDao();
    String usr_c = session.getAttribute("usr").toString();
    ArrayList<Integer> stg_n = new ArrayList<>();
    ArrayList<MocUserSelection> usr_n = new ArrayList<>();
    //1.update current tran flag
    tdao.completeTransaction(cid, usr_c);
    //2.add new transation
    int stg_c = Integer.parseInt(request.getParameter("sid"));
    int act = 6;
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
//Email to cencern user
    Email em = new Email();
    em.Notify(cid);
    response.sendRedirect("../msg.jsp?cid=" + cid);

%>

