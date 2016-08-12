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
public class WFBean {

    private int TRAN_ID;
    private String PO_NUMBER;
    private String BILL_NO;
    private int STG_C;
    private int STG_N;
    private int ACT_ID;
    private String USER_ID;
    private String CMT;
    private String NXT_UID;
    private String IS_COMP;

    /**
     * @return the TRAN_ID
     */
    public int getTRAN_ID() {
        return TRAN_ID;
    }

    /**
     * @param TRAN_ID the TRAN_ID to set
     */
    public void setTRAN_ID(int TRAN_ID) {
        this.TRAN_ID = TRAN_ID;
    }

    /**
     * @return the PO_NUMBER
     */
    public String getPO_NUMBER() {
        return PO_NUMBER;
    }

    /**
     * @param PO_NUMBER the PO_NUMBER to set
     */
    public void setPO_NUMBER(String PO_NUMBER) {
        this.PO_NUMBER = PO_NUMBER;
    }

    /**
     * @return the BILL_NO
     */
    public String getBILL_NO() {
        return BILL_NO;
    }

    /**
     * @param BILL_NO the BILL_NO to set
     */
    public void setBILL_NO(String BILL_NO) {
        this.BILL_NO = BILL_NO;
    }

    /**
     * @return the STG_C
     */
    public int getSTG_C() {
        return STG_C;
    }

    /**
     * @param STG_C the STG_C to set
     */
    public void setSTG_C(int STG_C) {
        this.STG_C = STG_C;
    }

    /**
     * @return the STG_N
     */
    public int getSTG_N() {
        return STG_N;
    }

    /**
     * @param STG_N the STG_N to set
     */
    public void setSTG_N(int STG_N) {
        this.STG_N = STG_N;
    }

    /**
     * @return the ACT_ID
     */
    public int getACT_ID() {
        return ACT_ID;
    }

    /**
     * @param ACT_ID the ACT_ID to set
     */
    public void setACT_ID(int ACT_ID) {
        this.ACT_ID = ACT_ID;
    }

    /**
     * @return the USER_ID
     */
    public String getUSER_ID() {
        return USER_ID;
    }

    /**
     * @param USER_ID the USER_ID to set
     */
    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
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
     * @return the NXT_UID
     */
    public String getNXT_UID() {
        return NXT_UID;
    }

    /**
     * @param NXT_UID the NXT_UID to set
     */
    public void setNXT_UID(String NXT_UID) {
        this.NXT_UID = NXT_UID;
    }

    /**
     * @return the IS_COMP
     */
    public String getIS_COMP() {
        return IS_COMP;
    }

    /**
     * @param IS_COMP the IS_COMP to set
     */
    public void setIS_COMP(String IS_COMP) {
        this.IS_COMP = IS_COMP;
    }
    private static final Logger LOG = Logger.getLogger(WFBean.class.getName());
}
