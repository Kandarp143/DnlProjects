/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Bean.InvBean;
import Bean.InvItemBean;
import Bean.WorkBean;
import Bean.WorkItemBean;
import Bean.WorkTaxBean;
import Bean.WorkTermBean;
import java.util.ArrayList;

/**
 *
 * @author 02948
 */
public interface ProjINVInter {

    public InvBean getProjInv(WorkBean billbean, ArrayList<WorkItemBean> billitem, ArrayList<WorkTaxBean> billtax, WorkTermBean billterm);

    public int getACCpayID(String sup, String site);

    public int getGLcodeID(String seg1, String seg2, String seg3, String seg4, String seg5, String seg6);

    public ArrayList<InvItemBean> getProjInvItem(WorkBean billbean, ArrayList<WorkItemBean> billitem, ArrayList<WorkTaxBean> billtax, WorkTermBean billterm);

}
