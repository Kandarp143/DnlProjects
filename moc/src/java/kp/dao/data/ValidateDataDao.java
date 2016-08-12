/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.dao.data;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import kp.beans.data.pojo.MocInitEle;
import kp.beans.data.pojo.MocInitMst;
import kp.beans.data.pojo.MocInitPip;
import kp.beans.data.pojo.MocInitProc;
import kp.beans.data.pojo.MocInitProd;
import kp.beans.data.pojo.MocInitPseq;
import kp.beans.data.pojo.MocInitReq;
import kp.beans.data.pojo.MocInitRot;
import kp.beans.data.pojo.MocInitSta;
import kp.dao.wf.StgDao;

/**
 *
 * @author 02948
 */
public class ValidateDataDao {

    private ArrayList<String> errors = new ArrayList<>();

    public ArrayList<String> ValidateMoc(MocInitMst mst, MocInitReq a, MocInitSta b, MocInitRot c,
            MocInitPip d, MocInitEle e, MocInitProd f, MocInitProc g, MocInitPseq h) {
        errors.clear();
        if ("".equals(a.getA1())) {
            errors.add("Please Select Unit");
        }
        if ("".equals(a.getA2())) {
            errors.add("Please Select Plant");
        }
        if ("".equals(a.getA4())) {
            errors.add("Moc title is required");
        }

        Logger.getLogger(ValidateDataDao.class.getName()).log(Level.SEVERE, "Error : " + errors.size());
        return errors;
    }

    public ArrayList<String> ValidateWfCmt(String cmt) {
        errors.clear();
        if ("".equals(cmt)) {
            errors.add("Please Enter Comment for Approver");
        }
        return errors;
    }

    public ArrayList<String> ValidateWfAct(HttpServletRequest request) {
        errors.clear();
        if ("".equals(request.getParameter("cmt")) || request.getParameter("cmt") == null) {
            errors.add("Please Enter Comment for Approver");
        }
        if (request.getParameterMap().containsKey("act")) {
            if ("4".equals(request.getParameter("act"))) {
                if (request.getParameter("usr") == null) {
                    errors.add("Please Select User to Demote");

                }
            }

        } else {
            errors.add("Please select at least one action");
        }
        return errors;
    }

    public ArrayList<String> ValidateWfUser(HttpServletRequest request) {
        ArrayList<String> ans = new ArrayList<>();
        StgDao sdao = new StgDao();
        //till eng cost estimation
        for (int i = 1; i <= 7; i++) {
            if (request.getParameterMap().containsKey("CB" + i)) {
            } else {
                String stg = sdao.getStgName(i);
                if (i == 1) {
                    stg = stg + " / " + sdao.getStgName(20);
                }
                if (i == 2) {
                    stg = stg + " / " + sdao.getStgName(21);
                }
                ans.add("Please select user for stage : " + stg);
            }
        }
        //cost 
        if (request.getParameterMap().containsKey("CB8")) {
        } else {

            for (int i = 9; i < 13; i++) {
                if (request.getParameterMap().containsKey("CB" + i)) {
                } else {
                    ans.add("Please select user for stage : " + sdao.getStgName(i));

                }
            }

        }

        //after eng cost estimation
        for (int i = 14;
                i < 20; i++) {
            if (request.getParameterMap().containsKey("CB" + i)) {
            } else {
                String stg = sdao.getStgName(i);
                ans.add("Please select user for stage : " + stg);
            }
        }

        return ans;
    }
}
