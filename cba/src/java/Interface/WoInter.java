/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Bean.WorkBean;
import Bean.WorkItemBean;
import Bean.WorkTaxBean;
import Bean.WorkTermBean;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author 02948
 */
public interface WoInter {

    public void CreateWO(WorkBean wo, ArrayList<WorkItemBean> woit, ArrayList<WorkTaxBean> wotx, WorkTermBean woterm);

    public void UpdateWO(WorkBean wo, ArrayList<WorkItemBean> woit, ArrayList<WorkTaxBean> wotx, WorkTermBean woterm);

    public void UpdateWOstatus(String ponum, String status);

    public void InsWO(WorkBean bean, Connection con);

    public void InsWOTax(ArrayList<WorkTaxBean> bean, Connection con);

    public void InsWOItem(ArrayList<WorkItemBean> bean, Connection con);

    public void InsWORemain(ArrayList<WorkItemBean> bean, Connection con);

    public void InsWOTerm(WorkTermBean bean, Connection con);

    public WorkBean getWO(String ponum);

    public ArrayList<WorkBean> getWO(Boolean isApproved);

    public ArrayList<WorkBean> getWObyUid(String uid, Boolean isApproved);

    public ArrayList<WorkBean> getWObySid(String uid, Boolean isApproved);

    public ArrayList<WorkBean> getWObyOU(String ou, Boolean isApproved);

    public ArrayList<WorkTaxBean> getWOTax(String ponum);

    public ArrayList<WorkItemBean> getWOItem(String ponum);

    public WorkTermBean getWOTerm(String ponum);

    public boolean isProjWO(String pono);

}
