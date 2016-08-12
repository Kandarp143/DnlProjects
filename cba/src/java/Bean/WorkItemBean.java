/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.logging.Logger;

/**
 *
 * @author 02948
 */
public class WorkItemBean extends PoBean {

    /**
     * @return the LOG
     */
    public static Logger getLOG() {
        return LOG;
    }

    /**
     * @param aLOG the LOG to set
     */
    public static void setLOG(Logger aLOG) {
        LOG = aLOG;
    }

    private String ITEM_ID;
    private String INV_ITEM_ID;
    private String ITEM_DESC;
    private String UOM;
    private float QTY;
    private float RATE;
    private float ITEM_VAL;
    private String PLANT;
    private String CC;
    private String CMT;
    private String PROJ;
    private String TASK;
    private String PROJ_NAME;
    private String TASK_NAME;
    private String ISCHECK;
    private float BUDGET;

    public WorkItemBean(String ITEM_ID, String INV_ITEM_ID, String ITEM_DESC, String UOM, float QTY, float RATE, float ITEM_VAL, String PLANT, String CMT, String ISCHECK) {

        this.INV_ITEM_ID = INV_ITEM_ID;
        this.ITEM_DESC = ITEM_DESC;
        this.UOM = UOM;
        this.QTY = QTY;
        this.RATE = RATE;
        this.ITEM_VAL = ITEM_VAL;
        this.PLANT = PLANT;
        this.CMT = CMT;
        this.ISCHECK = ISCHECK;

    }

    public WorkItemBean() {

    }

    /**
     * @return the ITEM_ID
     */
    public String getITEM_ID() {
        return ITEM_ID;
    }

    /**
     * @param ITEM_ID the ITEM_ID to set
     */
    public void setITEM_ID(String ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    /**
     * @return the ITEM_DESC
     */
    public String getITEM_DESC() {
        return ITEM_DESC;
    }

    /**
     * @param ITEM_DESC the ITEM_DESC to set
     */
    public void setITEM_DESC(String ITEM_DESC) {
        this.ITEM_DESC = ITEM_DESC;
    }

    /**
     * @return the UOM
     */
    public String getUOM() {
        return UOM;
    }

    /**
     * @param UOM the UOM to set
     */
    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    /**
     * @return the RATE
     */
    public float getRATE() {
        return RATE;
    }

    /**
     * @param RATE the RATE to set
     */
    public void setRATE(float RATE) {
        this.RATE = RATE;
    }

    /**
     * @return the VAL
     */
    public float getVAL() {
        return getITEM_VAL();
    }

    /**
     * @param VAL the VAL to set
     */
    public void setVAL(float VAL) {
        this.setITEM_VAL(VAL);
    }

    /**
     * @return the CMT
     */
    public String getCMT() {
        return CMT;
    }

    /**
     * @param CMT the CMT to set
     */
    public void setCMT(String CMT) {
        this.CMT = CMT;
    }

    /**
     * @return the QTY
     */
    public float getQTY() {
        return QTY;
    }

    /**
     * @param QTY the QTY to set
     */
    public void setQTY(float QTY) {
        this.QTY = QTY;
    }

    /**
     * @return the PLANT
     */
    public String getPLANT() {
        return PLANT;
    }

    /**
     * @param PLANT the PLANT to set
     */
    public void setPLANT(String PLANT) {
        this.PLANT = PLANT;
    }

    /**
     * @return the ISCHECK
     */
    public String getISCHECK() {
        return ISCHECK;
    }

    /**
     * @param ISCHECK the ISCHECK to set
     */
    public void setISCHECK(String ISCHECK) {
        this.ISCHECK = ISCHECK;
    }

    /**
     * @return the INV_ITEM_ID
     */
    public String getINV_ITEM_ID() {
        return INV_ITEM_ID;
    }

    /**
     * @param INV_ITEM_ID the INV_ITEM_ID to set
     */
    public void setINV_ITEM_ID(String INV_ITEM_ID) {
        this.INV_ITEM_ID = INV_ITEM_ID;
    }

    /**
     * @return the CC
     */
    public String getCC() {
        return CC;
    }

    /**
     * @param CC the CC to set
     */
    public void setCC(String CC) {
        this.CC = CC;
    }
    private static Logger LOG = Logger.getLogger(WorkItemBean.class.getName());

    /**
     * @return the PROJ
     */
    public String getPROJ() {
        return PROJ;
    }

    /**
     * @param PROJ the PROJ to set
     */
    public void setPROJ(String PROJ) {
        this.PROJ = PROJ;
    }

    /**
     * @return the TASK
     */
    public String getTASK() {
        return TASK;
    }

    /**
     * @param TASK the TASK to set
     */
    public void setTASK(String TASK) {
        this.TASK = TASK;
    }

    /**
     * @return the ITEM_VAL
     */
    public float getITEM_VAL() {
        return ITEM_VAL;
    }

    /**
     * @param ITEM_VAL the ITEM_VAL to set
     */
    public void setITEM_VAL(float ITEM_VAL) {
        this.ITEM_VAL = ITEM_VAL;
    }

    /**
     * @return the PROJ_NAME
     */
    public String getPROJ_NAME() {
        return PROJ_NAME;
    }

    /**
     * @param PROJ_NAME the PROJ_NAME to set
     */
    public void setPROJ_NAME(String PROJ_NAME) {
        this.PROJ_NAME = PROJ_NAME;
    }

    /**
     * @return the TASK_NAME
     */
    public String getTASK_NAME() {
        return TASK_NAME;
    }

    /**
     * @param TASK_NAME the TASK_NAME to set
     */
    public void setTASK_NAME(String TASK_NAME) {
        this.TASK_NAME = TASK_NAME;
    }

    /**
     * @return the BUDGET
     */
    public float getBUDGET() {
        return BUDGET;
    }

    /**
     * @param BUDGET the BUDGET to set
     */
    public void setBUDGET(float BUDGET) {
        this.BUDGET = BUDGET;
    }
}
