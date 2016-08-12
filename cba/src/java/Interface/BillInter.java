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
import java.util.ArrayList;

/**
 *
 * @author 02948
 */
public interface BillInter {

    public void CreateBill(WorkBean wo, ArrayList<WorkItemBean> woit, ArrayList<WorkTaxBean> wotx, WorkTermBean woterm);

    public void UpdateBill(WorkBean wo, ArrayList<WorkItemBean> woit, ArrayList<WorkTaxBean> wotx, WorkTermBean woterm);

    public void UpdateBillstatus(String ponum, String status);

    public void InsBill(WorkBean bean);

    public void InsBillTax(ArrayList<WorkTaxBean> wotx);

    public void InsBillItem(ArrayList<WorkItemBean> woit);

    public void InsBillTerm(WorkTermBean bean);

    public void UpdateWORemain(ArrayList<WorkItemBean> problem, Boolean isReject);

    public WorkBean getBill(String billno);

    public ArrayList<WorkBean> getBillbyUid(String uid, Boolean isApproved);

    public ArrayList<WorkBean> getRelatedBillbyUid(String uid);

    public ArrayList<WorkBean> getBillbyStatus(String uid, String status);

    public ArrayList<WorkBean> getBillbyStatus(String status);

    public ArrayList<WorkBean> getBillbySid(String uid, Boolean isApproved);

    public ArrayList<WorkBean> getBillbyOU(String ou, Boolean isApproved);

    public ArrayList<WorkBean> getBill(Boolean isApproved);

    public ArrayList<WorkBean> getBillbyPONO(String pono);

    public ArrayList<WorkTaxBean> getBillTax(String ponum);

    public ArrayList<WorkItemBean> getBillItem(String ponum);

    public WorkTermBean getBillTerm(String ponum);

    public ArrayList<WorkBean> getBill_usr_no(String sup);
}
